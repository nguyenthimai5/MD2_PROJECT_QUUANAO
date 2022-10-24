package shop.pressentation;

import shop.bussiness.entity.*;
import shop.bussiness.impl.*;
import shop.config.ShopMessage;
import shop.config.ShopValiDation;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ShopRun {
    private static Userimpl userimpl = new Userimpl();
    private static List<User> userList=userimpl.readFromFile();
//    Date date=new Date();
//    User user1 = new User(1,"123","123","123","jjjjjjjjjj",true,date,true,"H@gmail.com","84-0343800644");
//    boolean user = userimpl.create(user1);
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("|------------------------------------------------------------------------------");
            System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~CỬA HÀNG M-LEMON ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("|1.Đăng nhập ");
            System.out.println("|2.Đăng ký");
            System.out.println("|3.Thoát");
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

    }

