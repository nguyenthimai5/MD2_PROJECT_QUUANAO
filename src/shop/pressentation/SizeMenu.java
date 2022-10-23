package shop.pressentation;

import shop.bussiness.entity.Color;
import shop.bussiness.entity.Size;
import shop.bussiness.impl.Colorimpl;
import shop.bussiness.impl.Sizeimpl;
import shop.config.ShopMessage;
import shop.config.ShopValiDation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SizeMenu {
    private static Sizeimpl sizeimpl = new Sizeimpl();
    private static List<Size> sizeList = sizeimpl.readFromFile();

    public static void sizeManager(Scanner scanner) {
        boolean checkExit = true;
        do {
            System.out.println("********************************** QUẢN LÝ KÍCH CỠ SẢN PHẨM *******************************");
            System.out.println("1.Danh sách kích cỡ sắp xếp a->b theo tên kích cỡ");
            System.out.println("2.Tạo mới kích cỡ");
            System.out.println("3.Cập nhật kích cỡ");
            System.out.println("4.Xóa kích cỡ");
            System.out.println("5.Thoát");
            System.out.println(ShopMessage.CHOICE_NOTIFY);
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException exception) {
                System.err.println(ShopMessage.CHECK_NUMBER);
            }

            System.out.println(ShopMessage.HAPPY_SHOP);
            switch (choice) {
                case 1:
                    displayListSize();
                    break;
                case 2:
                    inputData(scanner);
                    break;
                case 3:
                    updateSize(scanner);
                    break;
                case 4:
                    deleteSize(scanner);
                    break;
                case 5:
                    checkExit = false;
                    break;
                default:
                    System.err.println(ShopMessage.CHECK_CHOICE1_5);
            }
        } while (checkExit);

    }

    public static void displayListSize() {
        Collections.sort(sizeList, new Comparator<Size>() {
            @Override
            public int compare(Size o1, Size o2) {
                return o1.getSizeName().compareTo(o2.getSizeName());
            }

        });
        for (Size size : sizeList) {
            sizeimpl.displayData(size);
        }
    }

    public static void inputData(Scanner scanner) {
        System.out.println("Bạn muốn thêm bao nhiêu kích cỡ:");
        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException exception) {
            System.err.println(ShopMessage.CHECK_NUMBER);
        }
        for (int i = 0; i < choice; i++) {
            System.out.printf("Nhập thông tin cho màu sắc thứ %d", i + 1);
            Size size = sizeimpl.input(scanner);
            sizeList.add(size);
            sizeimpl.create(size);

        }

    }

    public static void updateSize(Scanner scanner) {
        System.out.println("Nhập mã màu sắc bạn muốn cập nhật:");
        int sizeId = 0;
        try {
            sizeId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.err.println(ShopMessage.CHECK_NUMBER);
        }
        for (Size size : sizeList) {
            if (size.getSizeId() == sizeId) {
                boolean checkValiDateName = true;
                System.out.println("Tên màu sắc mới:");
                do {
                    String sizeNameNew = scanner.nextLine();
                    if (ShopValiDation.checkEmpty(sizeNameNew)) {
                        if (ShopValiDation.checkLenght(sizeNameNew, 1, 10)) {
                            if (sizeNameNew.equals(size.getSizeName())) {
                                checkValiDateName = false;
                                break;
                            }


                            if (checkValiDateName) {

                                size.setSizeName(sizeNameNew);
                                break;
                            } else {
                                System.err.println(ShopMessage.CHECK_VALIDATE_NAME_SIZE);
                            }
                        } else {
                            System.err.println(ShopMessage.CHECK_LENGH_NAME_SIZE);
                        }
                    } else {
                        break;
                    }
                } while (true);
                System.out.println("Trạng thái màu sắc:");
                System.out.println("1.Hoạt động");
                System.out.println("2.Không hoạt động");
                System.out.println("3.Không cập nhật");
                System.out.print("Sự lựa chọn của bạn:");
                int choice = 0;
                try {
                    choice = Integer.parseInt(scanner.nextLine());

                } catch (NumberFormatException exception) {
                    System.err.println(ShopMessage.CHECK_NUMBER);
                }

                switch (choice) {
                    case 1:
                        size.setSizeStatus(true);
                        break;
                    case 2:
                        size.setSizeStatus(true);
                        break;
                    case 3:
                        size.isSizeStatus();
                    default:
                        System.err.println(ShopMessage.CHECK_CHOICE1_3);

                }
            }
        }
    }

    public static void deleteSize(Scanner scanner) {
        System.out.println("Nhập vào mã màu sắc muốn xoá:");
        boolean check = false;
        int idDelete = 0;
        try {
            idDelete = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.err.println(ShopMessage.CHECK_NUMBER);
            ;
        }
        for (Size size : sizeList) {
            if (size.getSizeId() == idDelete) {
                sizeimpl.delete(idDelete);
                check = true;
            }
        }

        if (!check) {
            System.err.printf("Mã %f không có trong dữ liệu", idDelete);
        }
    }
}
