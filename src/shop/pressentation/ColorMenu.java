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

    public static void colorManager(Scanner scanner) {
        boolean checkExit = true;
        do {
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("|                                  QUẢN LÝ MÀU SẮC SẢN PHẨM                                |");
            System.out.println("|1.Danh sách màu sắc sắp xếp a→b theo tên màu sắc                                          |");
            System.out.println("|2.Tạo mới màu sắc                                                                         |");
            System.out.println("|3.Cập nhật thông tin màu sắc                                                              |");
            System.out.println("|4.Xóa màu sắc                                                                             |");
            System.out.println("|5.Thoát                                                                                   |");
            System.out.println("--------------------------------------------------------------------------------------------");
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
        List<Color> colors=colorimpl.readFromFile();
        if (colors==null){
            colors=new ArrayList<>();
        }
        Collections.sort(colors, new Comparator<Color>() {
            @Override
            public int compare(Color o1, Color o2) {
                return o1.getColorName().compareTo(o2.getColorName());
            }
        });
        System.out.printf("%-20s%-20s%-20s\n","Mã màu","Tên màu","Trạng thái");
        for (Color color : colors) {
            colorimpl.displayData(color);
        }
    }

    public static void inputData(Scanner scanner) {
        List<Color> colors=colorimpl.readFromFile();
        if (colors==null){
            colors=new ArrayList<>();
        }
        System.out.println("Bạn muốn thêm bao nhiêu màu:");
        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException exception) {
            System.err.println(ShopMessage.CHECK_NUMBER);
        }
        for (int i = 0; i < choice; i++) {
            System.out.printf("Nhập thông tin cho màu sắc thứ %d\n", i + 1);
            Color color = colorimpl.input(scanner);
            colorimpl.create(color);
        }

    }

    public static void updateColor(Scanner scanner) {
        List<Color> colorList = colorimpl.readFromFile();
        if (colorList == null) {
            colorList = new ArrayList<>();
        }
        System.out.print("Nhập id danh mục bạn muốn cập nhật:");
        int idCheck = 0;
        do {
            try {
                idCheck = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException ex) {
                System.err.println(ShopMessage.CHECK_NUMBER);
            }
        } while (true);
        boolean checkExitColor = false;
        for (Color color : colorList) {
            if (color.getColorId() == idCheck) {
                checkExitColor = true;
                break;
            }
        }
        if (!checkExitColor) {
            System.err.printf("Không tìm thấy id %d\n", idCheck);
        } else {
            do {
                System.out.println("Tên màu sắc:");
                String colorNameNew = scanner.nextLine();
                if (ShopValiDation.checkEmpty(colorNameNew)) {
                    if (ShopValiDation.checkLenght(colorNameNew, 4, 30)) {
                        boolean checkValiDateName = true;
                        for (Color colors : colorList) {
                            if (colorNameNew.equals(colors.getColorName())) {
                                checkValiDateName = false;
                                break;
                            }
                        }
                        if (checkValiDateName) {
                            colorList.get(idCheck - 1).setColorName(colorNameNew);
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
            System.out.println("Bạn có muốn cập nhật trạng thái không:");
            System.out.println("1.Có");
            System.out.println("2.Không");
            System.out.println("Lựa chọn của bạn:");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException ex) {
                System.err.println(ShopMessage.CHECK_NUMBER);
            }
            if (choice == 1) {
                colorList.get(idCheck - 1).setColorStatus(!colorList.get(idCheck - 1).isColorStatus());
            }
            colorimpl.writeToFile(colorList);
        }


    }
    public static void deleteColor(Scanner scanner) {
        List<Color> colors=colorimpl.readFromFile();
        if (colors==null){
            colors=new ArrayList<>();
        }
        System.out.print("Nhập mã màu sắc mà quý khách muốn xoá:");
        int idColor = 0;
        do {
            try {
                    idColor = Integer.parseInt(scanner.nextLine());
                    break;

            } catch (NumberFormatException exception) {
                System.err.println(ShopMessage.CHECK_NUMBER);
            }
        } while (true);
        boolean check = colorimpl.delete(idColor);
        if (check) {
            System.out.println("Xoá thành công");
        }
    }
}

