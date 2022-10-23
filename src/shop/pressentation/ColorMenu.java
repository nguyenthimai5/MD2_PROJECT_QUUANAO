package shop.pressentation;


import shop.bussiness.entity.Catalog;
import shop.bussiness.entity.Color;
import shop.bussiness.impl.Catalogimpl;
import shop.bussiness.impl.Colorimpl;
import shop.config.ShopMessage;
import shop.config.ShopValiDation;

import java.util.*;

public class ColorMenu {
    private static Colorimpl colorimpl = new Colorimpl();
    private static List<Color> colorList = colorimpl.readFromFile();

    public static void colorManager(Scanner scanner) {
        boolean checkExit = true;
        do {
            System.out.println("********************************** QUẢN LÝ MÀU SẮC SẢN PHẨM *******************************");
            System.out.println("1.Danh sách màu sắc sắp xếp a→b theo tên màu sắc");
            System.out.println("2.Tạo mới màu sắc");
            System.out.println("3.Cập nhật thông tin màu sắc");
            System.out.println("4.Xóa màu sắc");
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
                    displayListColor();
                    break;
                case 2:
                    inputData(scanner);
                    break;
                case 3:
                    updateColor(scanner);
                    break;
                case 4:
                    deleteColor(scanner);
                    break;
                case 5:
                    checkExit = false;
                    break;
                default:
                    System.err.println(ShopMessage.CHECK_CHOICE1_5);
            }
        } while (checkExit);

    }

    public static void displayListColor() {
        Collections.sort(colorList, new Comparator<Color>() {
            @Override
            public int compare(Color o1, Color o2) {
                return o1.getColorName().compareTo(o2.getColorName());
            }
        });
        for (Color color : colorList) {
            colorimpl.displayData(color);
        }
    }

    public static void inputData(Scanner scanner) {
        System.out.println("Bạn muốn thêm bao nhiêu danh mục:");
        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException exception) {
            System.err.println(ShopMessage.CHECK_NUMBER);
        }
        for (int i = 0; i < choice; i++) {
            System.out.printf("Nhập thông tin cho màu sắc thứ %d", i + 1);
            Color color = colorimpl.input(scanner);
            colorList.add(color);
            colorimpl.create(color);

        }

    }

    public static void updateColor(Scanner scanner) {
        System.out.println("Nhập mã màu sắc bạn muốn cập nhật:");
        int colorId = 0;
        try {
            colorId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.err.println(ShopMessage.CHECK_NUMBER);
        }
        for (Color color : colorList) {
            if (color.getColorId() == colorId) {
                boolean checkValiDateName = true;
                System.out.println("Tên màu sắc mới:");
                do {
                    String colorNameNew = scanner.nextLine();
                    if (ShopValiDation.checkEmpty(colorNameNew)) {
                        if (ShopValiDation.checkLenght(colorNameNew, 4, 30)) {
                            if (colorNameNew.equals(color.getColorName())) {
                                checkValiDateName = false;
                                break;
                            }


                            if (checkValiDateName) {
                                color.setColorName(colorNameNew);
                                break;
                            } else {
                                System.err.println(ShopMessage.CHECK_VALIDATE_NAME_COLOR);
                            }
                        } else {
                            System.err.println(ShopMessage.CHECK_LENGH_NAME_COLOR);
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
                        color.setColorStatus(true);
                        break;
                    case 2:
                        color.setColorStatus(false);
                        break;
                    case 3:
                        color.isColorStatus();
                    default:
                        System.err.println(ShopMessage.CHECK_CHOICE1_3);

                }
            }
        }
    }
    public static void deleteColor(Scanner scanner){
        System.out.println("Nhập vào mã màu sắc muốn xoá:");
        boolean check=false;
        int idDelete=0;
        try {
            idDelete=Integer.parseInt(scanner.nextLine());
        }catch (NumberFormatException ex){
            System.err.println(ShopMessage.CHECK_NUMBER);;
        }
        for (Color color:colorList ) {
            if (color.getColorId()==idDelete){
                colorimpl.delete(idDelete);
                check=true;
                break;
            }
        }
        if (!check){
            System.err.printf("Mã %f không có trong dữ liệu",idDelete);
        }
    }
}

