package shop.pressentation;

import shop.bussiness.entity.User;
import shop.bussiness.impl.*;
import shop.config.ShopMessage;
import shop.config.ShopValiDation;

import java.util.Scanner;

public class ShopRun {
    private static Catalogimpl catalogimpl = new Catalogimpl();
    private static Productimpl productimpl = new Productimpl();
    private static Sizeimpl sizeimpl = new Sizeimpl();
    private static Colorimpl colorimpl = new Colorimpl();
    private static Userimpl userimpl = new Userimpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        User user=new User(1,"Nguyen Mai12","mnai12","mnai12","Nguyễn Thị Ngọc Mai",true,12/23/2022,true,"mainguyen1412@gmail.com","(84)-(09165334)");
        do {
            System.out.println("**********************CỬA HÀNG M-LEMON **************************");
            System.out.println("1.Đăng nhập ");
            System.out.println("2.Đăng ký");
            System.out.println("3.Thoát");
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
                    checkLogin(scanner);
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

    public static void checkLogin(Scanner scanner) {
        User user = new User();
        do {
            if (userimpl.login(scanner)) {
                if (user.isPermission()) {
                    UserMenu.manager(scanner);
                    break;
                } else {
                    UserMenu.customer(scanner);
                    break;
                }
            }

        } while (true);
    }
}
