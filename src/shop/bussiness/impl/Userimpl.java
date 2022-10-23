package shop.bussiness.impl;

import shop.bussiness.design.IUser;
import shop.bussiness.entity.Catalog;
import shop.bussiness.entity.Product;
import shop.bussiness.entity.Size;
import shop.bussiness.entity.User;
import shop.config.ShopConstant;
import shop.config.ShopMessage;
import shop.config.ShopValiDation;
import shop.data.Fileimpl;
import shop.pressentation.UserMenu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Userimpl implements IUser<User, Integer> {
    @Override
    public boolean create(User user) {
        List<User> userList = readFromFile();
        if (userList == null) {
            userList = new ArrayList<>();
        }
        userList.add(user);
        boolean result = writeToFile(userList);
        return result;
    }

    @Override
    public User input(Scanner scanner) {
        List<User> userList = readFromFile();
        if (userList == null) {
            userList = new ArrayList<>();
        }

        User userNew = new User();
        if (userList.size() == 0) {
            userNew.setUserId(1);
        } else {
            int maxId = 0;
            for (User user : userList) {
                if (user.getUserId() > maxId) {
                    maxId = user.getUserId();
                }
            }
            userNew.setUserId(maxId + 1);
        }

        System.out.println("Tên đăng nhập:");
        do {
            String nameLogin = scanner.nextLine();
            if (ShopValiDation.checkNameLogin(nameLogin)) {
                userNew.setUserName(nameLogin);
                break;
            } else {
                System.err.println(ShopMessage.CHECK_NAME_LOGIN);
            }
        } while (true);
        System.out.println("Mật khẩu:");
        do {
            String passWord = scanner.nextLine();
            if (ShopValiDation.checkLenght(passWord, 6, 10)) {
                userNew.setPassword(passWord);
                break;
            } else {
                System.err.println(ShopMessage.CHECK_LENGH_PASSWORD);
            }
        } while (true);
        System.out.println("Xác nhận lại mật khẩu:");
        do {
            String passWordConfirm = scanner.nextLine();
            if (passWordConfirm.trim().equals(userNew.getPassword())) {
                break;
            } else {
                System.err.println(ShopMessage.CHECK_CONFIRM_PASSWORD);
            }
        } while (true);
        System.out.println("Tên chủ tài khoản:");
        do {
            String userName = scanner.nextLine();
            if (ShopValiDation.checkEmpty(userName)) {
                userNew.setFullName(userName);
                break;
            } else {
                System.err.println(ShopMessage.CHECK_NAME_USER);
            }
        } while (true);
        System.out.println("Loại tài khoản:");
        System.out.println("1.Quản trị");
        System.out.println("2.Khách hàng");
        System.out.print("Sự lựa chọn của bạn:");
        int choice=0;
        try {
            choice = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException exception) {
            System.err.println(ShopMessage.CHECK_NUMBER);
        }

        switch (choice) {
            case 1:
                userNew.setPermission(true);
                break;
            case 2:
                userNew.setPermission(false);
                break;
            default:
                System.err.println(ShopMessage.CHECK_CHOICE1_2);

        }
        System.out.println("Ngày tạo tài khoản:");
        Date date = new Date();
        userNew.setDate(date);
        System.out.println("Trạng thái kích cỡ:");
        System.out.println("1.Hoạt động");
        System.out.println("2.Khoá");
        System.out.print("Sự lựa chọn của bạn:");
        int choiceStatus = 0;
        try {
            choiceStatus = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException exception) {
            System.err.println(ShopMessage.CHECK_NUMBER);
        }

        switch (choiceStatus) {
            case 1:
                userNew.setUserStatus(true);
                break;
            case 2:
                userNew.setUserStatus(false);
                break;
            default:
                System.err.println(ShopMessage.CHECK_CHOICE1_2);

        }
        System.out.println("Email:");
        do {
            String email = scanner.nextLine();
            if (ShopValiDation.checkEmail(email)) {
                userNew.setEmailUser(email);
                break;
            } else {
                System.err.println(ShopMessage.CHECK_EMAIL);
            }
        } while (true);
        System.out.print("Số điện thoại :");
        do {
            String phone = scanner.nextLine();
            if (ShopValiDation.checkPhone( phone)) {
                userNew.setPhoneUser(phone);
                break;
            } else {
                System.err.println(ShopMessage.CHECK_PHONE);
            }
        } while (true);
        return userNew;
    }

    @Override
    public void displayData(User user) {
        String status = "Khoá";
        if (user.isUserStatus()) {
            status = "Hoạt Động";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = formatter.format(user.getDate());
        System.out.printf("%-20s%-30s%-20s%-30s%-20s", user.getUserId(), user.getUserName(), user.getPassword(), user.getFullName(), user.isPermission());
        System.out.printf("%-30s%-30b%-30s%-30s", strDate, status, user.getEmailUser(), user.getPhoneUser());


    }

    @Override
    public boolean update(User user) {
        List<User> userList = readFromFile();
        boolean returnUser = false;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserId() == user.getUserId()) {
                userList.set(i, user);
                returnUser = true;
                break;
            }
        }
        boolean result = writeToFile(userList);
        if (returnUser && result) {
            return true;
        }
        return false;
    }


    @Override
    public boolean delete(Integer id) {
        List<User> userList = readFromFile();
        boolean check = false;
        for (User user : userList) {
            if (user.getUserId() == id) {
                user.setUserStatus(!user.isUserStatus());
                check = true;
                break;
            }
        }
        boolean result = writeToFile(userList);
        if (check && result) {
            System.out.println(ShopMessage.CHECK_DELETE_USER_TRUE);
            return true;
        }
        System.err.println(ShopMessage.CHECK_DELETE_USER_FASLE);

        return false;

    }

    @Override
    public List<User> readFromFile() {
        Fileimpl fileimpl = new Fileimpl();
        return fileimpl.readFrommFile(ShopConstant.URL_USER);
    }

    @Override
    public boolean writeToFile(List<User> list) {
        Fileimpl fileimpl = new Fileimpl();
        return fileimpl.writeToFile(list, ShopConstant.URL_USER);
    }

    @Override
    public void displayProductByCatalog(List<Catalog> list) {

    }

    @Override
    public void displayProductByDate(List<Product> list) {

    }

    @Override
    public void searchByProductByDisscount(List<Product> list) {

    }

    @Override
    public Product searchByProductName(String productName) {
        return null;
    }

    @Override
    public Product searchByCatalogName(String catalogName) {
        Productimpl productimpl = new Productimpl();
        List<Product> productList = productimpl.readFromFile();
        Catalogimpl catalogimpl = new Catalogimpl();
        List<Catalog> catalogList = catalogimpl.readFromFile();


        return null;
    }

    @Override
    public List<Product> searchProductByExportPrice(float minExprortPrice, float maxExportPrice) {
        Productimpl productimpl = new Productimpl();
        List<Product> productList = productimpl.readFromFile();
        List<Product> products = new ArrayList<>();
        for (Product pro : productList) {
            if (pro.getExportPriceProduct() >= minExprortPrice && pro.getExportPriceProduct() <= maxExportPrice) {
                products.add(pro);
            }
        }

        return products;
    }

    @Override
    public List<Product> searchProductByDiscount(float minDiscountPrice, float maxDiscountPrice) {
        Productimpl productimpl = new Productimpl();
        List<Product> productList = productimpl.readFromFile();
        List<Product> products = new ArrayList<>();
        for (Product pro : productList) {
            if (pro.getProductDiscount() >= minDiscountPrice && pro.getProductDiscount() <= maxDiscountPrice) {
                products.add(pro);
            }
        }

        return products;
    }

    @Override
    public boolean register(Scanner scanner) {
        Userimpl userimpl = new Userimpl();
        User user = userimpl.input(scanner);
        boolean check = userimpl.create(user);
        if (check) {
            System.err.println(ShopMessage.CHECK_REGESTER_TRUE);
            return true;
        }
        System.err.println(ShopMessage.CHECK_REGESTER_FASLE);
        return false;
    }

    @Override
    public boolean login(Scanner scanner) {
        List<User> userList = readFromFile();
        System.out.print("Tên đăng nhập:");
        String userName = scanner.nextLine();
        System.out.println("Mật khẩu:");
        String password = scanner.nextLine();
        User user = checkLogin(userName, password);
        if (user != null) {
           return true;
        } else {
            System.err.println(ShopMessage.CHECK_LOGIN);
            login(scanner);
        }
        return false;
    }

    @Override
    public boolean changePassWord(Scanner scanner) {
        return false;
    }

    public User checkLogin(String userName, String password) {
        List<User> userList = readFromFile();
        if (userList==null){
            userList=new ArrayList<>();
        }
        for (User user : userList) {
            if (user.getUserName().equalsIgnoreCase(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
