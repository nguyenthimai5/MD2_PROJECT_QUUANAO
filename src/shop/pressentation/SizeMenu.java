package shop.pressentation;

import shop.bussiness.entity.Color;
import shop.bussiness.entity.Size;
import shop.bussiness.impl.Colorimpl;
import shop.bussiness.impl.Sizeimpl;
import shop.config.ShopMessage;
import shop.config.ShopValiDation;

import java.util.*;

public class SizeMenu {
    private static Sizeimpl sizeimpl = new Sizeimpl();


    public static void sizeManager(Scanner scanner) {
        boolean checkExit = true;
        do {
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("                                    QUẢN LÝ KÍCH CỠ SẢN PHẨM                                |");
            System.out.println("1.Danh sách kích cỡ sắp xếp a->b theo tên kích cỡ                                           |");
            System.out.println("2.Tạo mới kích cỡ                                                                           |");
            System.out.println("3.Cập nhật kích cỡ                                                                          |");
            System.out.println("4.Xóa kích cỡ                                                                               |");
            System.out.println("5.Thoát                                                                                     |");
            System.out.println("---------------------------------------------------------------------------------------------");
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
        List<Size> sizeList=sizeimpl.readFromFile();
        if (sizeList==null){
            sizeList=new ArrayList<>();
        }
        Collections.sort(sizeList, new Comparator<Size>() {
            @Override
            public int compare(Size o1, Size o2) {
                return o1.getSizeName().compareTo(o2.getSizeName());
            }

        });
        System.out.printf("%-20s%-20s%-20s\n","Mã kích cỡ","Tên kích cỡ","Trạng thái");
        for (Size size : sizeList) {
            sizeimpl.displayData(size);
        }
    }

    public static void inputData(Scanner scanner) {
        List<Size> sizeList=sizeimpl.readFromFile();
        if (sizeList==null){
            sizeList=new ArrayList<>();
        }
        System.out.println("Bạn muốn thêm bao nhiêu kích cỡ:");
        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException exception) {
            System.err.println(ShopMessage.CHECK_NUMBER);
        }
        for (int i = 0; i < choice; i++) {
            System.out.printf("Nhập thông tin cho kích cỡ thứ %d\n", i + 1);
            Size size = sizeimpl.input(scanner);
            sizeimpl.create(size);

        }

    }

    public static void updateSize(Scanner scanner) {
        List<Size> sizeList = sizeimpl.readFromFile();
        if (sizeList == null) {
            sizeList = new ArrayList<>();
        }
        System.out.print("Nhập id danh mục bạn muốn cập nhật:");
        int idCheck = 0;
        do {
            String string = scanner.nextLine();
            if (ShopValiDation.checkEmpty(string)) {
                try {
                    idCheck = Integer.parseInt(string);
                    break;
                } catch (NumberFormatException ex) {
                    System.err.println(ShopMessage.CHECK_NUMBER);
                }
            } else {
                System.err.println("Không được để trống mã");
            }
        } while (true);
        boolean checkSize=false;
        for (Size size : sizeList) {
            if (size.getSizeId() == idCheck) {
                checkSize = true;
                break;
            }
        }
        if (!checkSize){
                System.err.printf("Không tìm thấy id %d\n",idCheck);

        }else {
            do {
                System.out.print("Tên kích cỡ:");
                String sizeNameNew=scanner.nextLine();
                if (ShopValiDation.checkEmpty(sizeNameNew)){
                    if (ShopValiDation.checkLenght(sizeNameNew,1,10)){
                        boolean checkValiDateName = true;
                        for (Size sizes:sizeList) {
                            if (sizeNameNew.equals(sizes.getSizeName())) {
                                checkValiDateName = false;
                                break;
                            }

                        }
                        if (checkValiDateName) {
                            sizeList.get(idCheck-1).setSizeName(sizeNameNew);
                            break;
                        } else {
                            System.err.println(ShopMessage.CHECK_VALIDATE_NAME_SIZE);
                        }
                    }else {
                        System.err.println(ShopMessage.CHECK_LENGH_NAME_SIZE);
                    }
                }else {
                    break;
                }
            } while (true);
            System.out.println("Bạn có muốn cập nhật trạng thái không:");
            System.out.println("1.Có");
            System.out.println("2.Không");
            System.out.println("Lựa chọn của bạn:");
            int choice=0;
            try {
                choice=Integer.parseInt(scanner.nextLine());

            }catch (NumberFormatException ex){
                System.err.println(ShopMessage.CHECK_NUMBER);
            }
            if (choice==1){
                sizeList.get(idCheck-1).setSizeStatus(!sizeList.get(idCheck-1).isSizeStatus());
            }
            sizeimpl.writeToFile(sizeList);
        }
        }
    public static void deleteSize(Scanner scanner) {
        List<Size> sizeList=sizeimpl.readFromFile();
        if (sizeList==null){
            sizeList=new ArrayList<>();
        }
        System.out.print("Nhập mã danh mục mà quý khách muốn xoá:");
        int idSize = 0;
            String string=scanner.nextLine();
            try {
                if (ShopValiDation.checkEmpty(string)){
                    idSize = Integer.parseInt(string);

                }

            } catch (NumberFormatException exception) {
                System.err.println(ShopMessage.CHECK_NUMBER);
            }
        boolean check = sizeimpl.delete(idSize);
        if (check) {
            System.out.println("Xoá thành công");
        }
    }
}
