package shop.config;

import java.util.Scanner;

public class ShopValiDation {
    public static boolean checkNumber(Scanner scanner,int choice) {
        try {
            choice = Integer.parseInt(scanner.nextLine());
        return true;
        } catch (NumberFormatException exception) {
            System.err.println(ShopMessage.CHECK_NUMBER);
        return false;
        }
    }
    public static boolean checkLenght(String strings,int min,int max){
    if (strings.trim().length()>=min&&strings.trim().length()<=max){
        return true;
    }
    return false;
    }
    public static boolean checkEmpty(String strings){
        if (strings.trim().length()!=0&&strings.trim()!=""){
            return true;
        }
        return false;
    }

}
