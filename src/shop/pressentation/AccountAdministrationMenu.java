package shop.pressentation;

import shop.bussiness.entity.User;
import shop.bussiness.impl.Userimpl;
import shop.config.ShopMessage;
import shop.config.ShopValiDation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AccountAdministrationMenu {
    private static Userimpl userimpl=new Userimpl();
    private static List<User> userList=userimpl.readFromFile();
    public static void AccountAdministration(Scanner scanner){
        boolean checkExit=true;
        do {
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~QUẢN LÝ TÀI KHOẢN~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("|1.Danh sách tài khoản được sắp xếp theo ngày tạo tài khoản giảm dần");
            System.out.println("|2.Thêm tài khoản quản trị");
            System.out.println("|3.Cập nhật tài khoản quản trị");
            System.out.println("|4.Cập nhật trạng thái tài khoản khách hàng");
            System.out.println("|5.Tìm kiếm tài khoản khách hàng theo tên đăng nhập tài khoản hoặc tên chủ tài khoản");
            System.out.println("|6.Thoát");
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println(ShopMessage.CHOICE_NOTIFY);
            int choice=0;
            try {
                choice = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException exception) {
                System.err.println(ShopMessage.CHECK_NUMBER);
            }

            System.out.println(ShopMessage.HAPPY_SHOP);
            switch (choice){
                case 1:
                    arrangementsProduct();
                    break;
                case 2:

                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    searchByName(scanner);
                    break;
                case 6:
                    checkExit=false;
                    break;
                default:
                    System.err.println(ShopMessage.CHECK_CHOICE1_6);
            }
        }while (checkExit);

    }
    public static void arrangementsProduct(){

        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        System.out.printf("%-20s%-30s%-20s%-30s%-20s%-30s%-30s%-30s%-30s\n","Mã tài khoản","Tên Đăng Nhập","Mật Khẩu","Tên Người dùng","Quyền try cập","Ngày tạo","Trạng thái","Email","Số Điện Thoại");
        for (User user:userList) {
        userimpl.displayData(user);
        }

        System.out.println("Đã sắp xếp xong các sản phẩm theo ngày tạo!!!");
    }
    public static void searchByName(Scanner scanner){
        System.out.println("Nhập vào tên muốn tìm:");
        String stringName=scanner.nextLine();
        System.out.printf("%-20s%-30s%-20s%-30s%-20s%-30s%-30s%-30s%-30s\n","Mã tài khoản","Tên Đăng Nhập","Mật Khẩu","Tên Người dùng","Quyền try cập","Ngày tạo","Trạng thái","Email","Số Điện Thoại");
        for (User user:userList) {
            if (user.getUserName().trim().contains(stringName)||user.getFullName().trim().contains(stringName)){
                userimpl.displayData(user);
            }
        }
    }
    public static void upDateAdmin(){

    }

}

