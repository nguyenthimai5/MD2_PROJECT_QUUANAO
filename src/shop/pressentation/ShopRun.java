package shop.pressentation;

import shop.bussiness.entity.*;
import shop.bussiness.impl.*;
import shop.config.ShopMessage;
import shop.config.ShopValiDation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ShopRun {
    private static Userimpl userimpl = new Userimpl();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("|                               CỬA HÀNG M-LEMON                             |");
            System.out.println("|1.Đăng nhập                                                                 | ");
            System.out.println("|2.Đăng ký                                                                   |");
            System.out.println("|3.Thoát                                                                     |");
            System.out.println("-------------------------------------------------------------------------------");
            System.out.print(ShopMessage.CHOICE_NOTIFY);

            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException exception) {
                System.err.println(ShopMessage.CHECK_NUMBER);
            }
            System.out.println(ShopMessage.HAPPY_SHOP);
            switch (choice) {
                case 1:
                    Userimpl.loginH(scanner);
                    break;
                case 2:
                    userimpl.register(scanner);
                    break;
                case 3:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.err.println(ShopMessage.CHECK_CHOICE1_2);
            }
        } while (true);

    }

//    public static void accountIsLocked(Scanner scanner) {
//        boolean check = true;
//        do {
//            System.out.println("------------------------------------------------------------------------------");
//            System.out.println("|                               CỬA HÀNG M-LEMON                             |");
//            System.out.println("|1.Tài khoản bạn đang bị khoá.Mở khoá ngay!                                  | ");
//            System.out.println("|2.Lý do bị khoá                                                             |");
//            System.out.println("|3.Thoát                                                                     |");
//            System.out.println("-------------------------------------------------------------------------------");
//            System.out.print(ShopMessage.CHOICE_NOTIFY);
//
//            int choice = 0;
//            try {
//                choice = Integer.parseInt(scanner.nextLine());
//
//            } catch (NumberFormatException exception) {
//                System.err.println(ShopMessage.CHECK_NUMBER);
//            }
//            System.out.println(ShopMessage.HAPPY_SHOP);
//            switch (choice) {
//                case 1:
//                    loginHC(scanner);
//                    break;
//                case 2:
//                    System.out.println("Có một tài khoản đã báo cáo bạn!!!");
//                    break;
//                case 3:
//                    check = false;
//                    break;
//                default:
//                    System.err.println(ShopMessage.CHECK_CHOICE1_2);
//            }
//        } while (check);
//
//
//    }

//    public static void loginHC(Scanner scanner) {
//        List<User>userList=new ArrayList<>();
//        do {
//            System.out.print("Tên đăng nhập:");
//            String userName = scanner.nextLine();
//            System.out.println("Mật khẩu:");
//            String password = scanner.nextLine();
//            User user = userimpl.checkLogin(userName, password);
//            if (user != null) {
//             do {
//                 if (user.isUserStatus()) {
//                     if (user.isPermission()) {
//                         if (user.getUserId()==1){
//                             UserMenu.manager(scanner);
//                             break;
//                         }else {
//                             UserMenu.manager(scanner);
//                             break;
//                         }
//                     } else {
//                         UserMenu.customer(scanner);
//                         break;
//                     }
//                 } else {
//                     user.setUserStatus(true);
//                 }
//             }while (true);
//            } else {
//                System.err.println(ShopMessage.CHECK_LOGIN);
//                break;
//            }
//        }
//        while (true);
//        userimpl.writeToFile()
//    }

}

