package shop.config;

import shop.bussiness.entity.Catalog;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopValiDation {




    public static boolean checkLenght(String strings, int min, int max) {
        if (strings.trim().length() >= min && strings.trim().length() <= max) {
            return true;
        }
        return false;
    }

    public static boolean checkEmpty(String strings) {
        if (strings.trim().length() != 0 && strings.trim() != "") {
            return true;
        }
        return false;
    }

    public static boolean checkProductChild(Catalog child, List<Catalog> list) {
        for (Catalog cat : list) {
            if (child.getCatalogId() == cat.getCatalog().getCatalogId()) {
                return false;
            }
            break;
        }
        return true;
    }

    public static boolean checkEmail( String emailString) {
        String email = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
        Pattern pattern = Pattern.compile(email);
        Matcher matcher = pattern.matcher(emailString);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static boolean checkPhone( String numberPhoneCheck) {
        String validatePhone = "^\\d{2}[-][0]\\d{8,}$";
        ;
        Pattern pattern = Pattern.compile(validatePhone);
        Matcher matcher = pattern.matcher(numberPhoneCheck);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static boolean checkNameLogin( String numberPhoneCheck) {
        String validateNameLogin = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$";
        Pattern pattern = Pattern.compile(validateNameLogin);
        Matcher matcher = pattern.matcher(numberPhoneCheck);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
