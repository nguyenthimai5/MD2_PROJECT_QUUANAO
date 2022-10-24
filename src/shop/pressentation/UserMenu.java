package shop.pressentation;

import shop.bussiness.entity.*;
import shop.bussiness.impl.*;
import shop.config.ShopMessage;
import shop.config.ShopValiDation;

import java.util.List;
import java.util.Scanner;

public class UserMenu {
    private static Userimpl userimpl = new Userimpl();
    private static List<User> userList = userimpl.readFromFile();
    private static Catalogimpl catalogimpl = new Catalogimpl();
    private static List<Catalog> catalogList = catalogimpl.readFromFile();
    private static Sizeimpl sizeimpl = new Sizeimpl();
    private static List<Size> sizeList = sizeimpl.readFromFile();
    private static Colorimpl colorimpl = new Colorimpl();
    private static List<Color> colorList = colorimpl.readFromFile();
    private static Productimpl productimpl = new Productimpl();
    private static List<Product> productList = productimpl.readFromFile();

    public static void manager(Scanner scanner) {
        boolean checkExit = true;
        do {
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~QUẢN TRỊ CỬA HÀNG M-LEMON~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("|1.Quản trị danh mục");
            System.out.println("|2.Quản trị màu sắc sản phẩm");
            System.out.println("|3.Quản trị kích cỡ sản phẩm");
            System.out.println("|4.Quản trị các sản phẩm");
            System.out.println("|5.Quản trị tài khoản");
            System.out.println("|6.Thoát");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.print("| Sự lựa chọn của quý khách:");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException exception) {
                System.err.println(ShopMessage.CHECK_NUMBER);
            }

            System.out.println("Rất vui khi được phục vụ quý khách^-^");
            switch (choice) {
                case 1:
                    CatalogMenu.catalogManager(scanner);
                    break;
                case 2:
                    ColorMenu.colorManager(scanner);
                    break;
                case 3:
                    SizeMenu.sizeManager(scanner);
                    break;
                case 4:
                    ProductMenu.productManager(scanner);
                    break;
                case 5:
                    AccountAdministrationMenu.AccountAdministration(scanner);
                    break;
                case 6:
                    checkExit = false;
                    break;
                default:
                    System.err.println(ShopMessage.CHECK_CHOICE1_6);
            }

        } while (checkExit);


    }

    public static void customer(Scanner scanner) {
        boolean checkExit = true;
        do {
            System.out.println("--------------------------------------------------------------------------------------------------");
            System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~CỬA HÀNG M-LEMON XIN KÍNH CHÀO QUÝ KHÁCH~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("|1.Xem danh sách các sản phẩm");
            System.out.println("|2.Tìm kiếm sản phẩm");
            System.out.println("|3.Đăng kí");
            System.out.println("|4.Đăng nhập");
            System.out.println("|5.Đổi mật khẩu");
            System.out.println("|6.Thoát");
            System.out.println("--------------------------------------------------------------------------------------------------");
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
                    listOfProducts(scanner);
                    break;
                case 2:
                    searchProduct(scanner);
                    break;
                case 3:
                    userimpl.register(scanner);
                    break;
                case 4:
                    Userimpl.loginH(scanner);
                    break;
                case 5:
                    changePassword(scanner);
                    break;
                case 6:
                    checkExit = false;
                    break;
                default:
                    System.err.println(ShopMessage.CHECK_CHOICE1_6);
            }

        } while (checkExit);


    }

    public static void changePassword(Scanner scanner) {
        List<User> userList1 = userimpl.readFromFile();
        System.out.print("Tên đăng nhập:");
        String userName = scanner.nextLine();
        System.out.println("Mật khẩu:");
        String password = scanner.nextLine();
        User user = userimpl.checkLogin(userName, password);
        if (user != null) {
            System.out.print("Nhập mật khẩu mới:");
            String passwordNew = scanner.nextLine();
            user.setPassword(passwordNew);
            userimpl.writeToFile(userList1);
        } else {
            System.err.println(ShopMessage.CHECK_LOGIN);
            userimpl.login(scanner);
        }
    }

    public static void listOfProducts(Scanner scanner) {
        boolean checkExit = true;
        do {
            System.out.println("------------------------------------------------------------------------------------------------");
            System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ DANH SÁCH SẢN PHẨM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("|1.Danh sách sản phẩm theo cây thư mục");
            System.out.println("|2.Xem danh sách sản phẩm mới");
            System.out.println("|3.Xem danh sách sản phẩm giảm giá");
            System.out.println("|4.Thoát");
            System.out.println("------------------------------------------------------------------------------------------------");
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
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    checkExit = false;
                    break;
                default:
                    System.err.println(ShopMessage.CHECK_CHOICE1_4);
            }

        } while (checkExit);

    }

    public static void searchProduct(Scanner scanner) {
        boolean checkExit = true;
        do {
            System.out.println("-------------------------------------------------------------------------------------------------");
            System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ TÌM KIẾM SẢN PHẨM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("|1.Tìm kiếm theo tên sản phẩm");
            System.out.println("|2.Tìm kiếm sản phẩm theo tên danh mục sản phẩm");
            System.out.println("|3.Tìm kiếm sản phẩm theo khoảng giá bán sản phẩm");
            System.out.println("|4.Tìm kiếm sản phẩm theo khoảng giảm giá của sản phẩm");
            System.out.println("|5.Thoát");
            System.out.println("-------------------------------------------------------------------------------------------------");
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
                    searchByNameProduct(scanner);
                    break;
                case 2:
                    searchByCatalogProduct(scanner);
                    break;
                case 3:
                    searchByExPriceProduct(scanner);
                    break;
                case 4:
                    searchByDisscountProduct(scanner);
                    break;
                case 5:
                    checkExit = false;
                    break;
                default:
                    System.err.println(ShopMessage.CHECK_CHOICE1_5);
            }

        } while (checkExit);
    }

    public static void searchByCatalogProduct(Scanner scanner) {
        System.out.println("Nhập tên danh mục:");
        String searchName = scanner.nextLine();
        for (Catalog catalog : catalogList) {
            if (catalog.getCatalogName().trim().contains(searchName.trim())) {
                catalogimpl.displayData(catalog);
            }
        }
    }

    public static void searchByExPriceProduct(Scanner scanner) {
        System.out.println("Nhập giá bạn muốn tìm kiếm:");
        float exprice = Float.parseFloat(scanner.nextLine());
        for (Product pro : productList) {
            if (pro.getExportPriceProduct() == exprice) {
                productimpl.displayData(pro);
            }
        }
    }

    public static void searchByDisscountProduct(Scanner scanner) {
        System.out.println("Nhập % giảm giá:");
        float disscount = Float.parseFloat(scanner.nextLine());
        for (Product pro : productList) {
            if (pro.getProductDiscount() == disscount) {
                productimpl.displayData(pro);
            }
        }
    }

    public static void searchByNameProduct(Scanner scanner) {
        System.out.println("Nhập tên sản phẩm muốn tìm:");
        String searchName = scanner.nextLine();
        for (Product pro : productList) {
            if (pro.getProductName().trim().contains(searchName.trim())) {
                productimpl.displayData(pro);
            }
        }
    }
}