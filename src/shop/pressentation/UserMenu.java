package shop.pressentation;

import shop.bussiness.entity.*;
import shop.bussiness.impl.*;
import shop.config.ShopMessage;
import shop.config.ShopValiDation;

import java.util.*;

public class UserMenu {
    private static Userimpl userimpl = new Userimpl();
    private static Catalogimpl catalogimpl = new Catalogimpl();
    private static Sizeimpl sizeimpl = new Sizeimpl();
    private static Colorimpl colorimpl = new Colorimpl();
    private static Productimpl productimpl = new Productimpl();

    public static void manager(Scanner scanner) {
        boolean checkExit = true;
        do {
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("|                                QUẢN TRỊ CỬA HÀNG M-LEMON                           |");
            System.out.println("|1.Quản trị danh mục                                                                 |");
            System.out.println("|2.Quản trị màu sắc sản phẩm                                                         |");
            System.out.println("|3.Quản trị kích cỡ sản phẩm                                                         |");
            System.out.println("|4.Quản trị các sản phẩm                                                             |");
            System.out.println("|5.Quản trị tài khoản                                                                |");
            System.out.println("|6.Thoát                                                                             |");
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
//                    check(scanner);
                    break;
                case 6:
                    checkExit = false;
                    break;
                default:
                    System.err.println(ShopMessage.CHECK_CHOICE1_6);
            }

        } while (checkExit);


    }
//    public static void check(Scanner scanner){
//        List<User> userList=userimpl.readFromFile();
//        if (userList==null){
//            userList=new ArrayList<>();
//        }
//        for (User user:userList) {
//            if (user.getUserId()==1){
//                AccountAdministrationMenu.AccountAdministration(scanner);
//                break;
//            }else {
//                AccountAdministrationMenu.AccountAdministrationFake(scanner);
//                break;
//            }
//        }
//    }

    public static void customer(Scanner scanner) {
        boolean checkExit = true;
        do {
            System.out.println("--------------------------------------------------------------------------------------------------");
            System.out.println("|                         CỬA HÀNG M-LEMON XIN KÍNH CHÀO QUÝ KHÁCH                               |");
            System.out.println("|1.Xem danh sách các sản phẩm                                                                    |");
            System.out.println("|2.Tìm kiếm sản phẩm                                                                             |");
            System.out.println("|3.Đăng kí                                                                                       |");
            System.out.println("|4.Đăng nhập                                                                                     |");
            System.out.println("|5.Đổi mật khẩu                                                                                  |");
            System.out.println("|6.Thoát                                                                                         |");
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
        List<User> userList = userimpl.readFromFile();
        if (userList == null) {
            userList = new ArrayList<>();
        }
        System.out.print("Tên đăng nhập cảu tài khoản muốn đổi:");
        String userName = scanner.nextLine();
        System.out.println("Mật khẩu cũ:");
        String password = scanner.nextLine();
        User user = userimpl.checkLogin(userName, password);
        if (user != null) {
            System.out.print("Nhập mật khẩu mới:");
            String passwordNew = scanner.nextLine();
            user.setPassword(passwordNew);
            userimpl.writeToFile(userList);
        } else {
            System.err.println(ShopMessage.CHECK_LOGIN);
            userimpl.login(scanner);
        }
    }

    public static void listOfProducts(Scanner scanner) {
        boolean checkExit = true;
        do {
            System.out.println("------------------------------------------------------------------------------------------------");
            System.out.println("|                                     DANH SÁCH SẢN PHẨM                                       |");
            System.out.println("|1.Danh sách sản phẩm theo cây thư mục                                                         |");
            System.out.println("|2.Xem danh sách sản phẩm mới                                                                  |");
            System.out.println("|3.Xem danh sách sản phẩm giảm giá                                                             |");
            System.out.println("|4.Thoát                                                                                       |");
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
                    ProductMenu.displayProduct();
                    break;
                case 2:
                    dateNew();
                    break;
                case 3:
                    selPro();
                    break;
                case 4:
                    checkExit = false;
                    break;
                default:
                    System.err.println(ShopMessage.CHECK_CHOICE1_4);
            }

        } while (checkExit);

    }
    public static void selPro(){
        List<Product> productList=productimpl.readFromFile();
        if (productList==null){
            productList=new ArrayList<>();
        }
        System.out.printf("%-30s%-32s%-30s%-30s%-30s%-40s%-40s\n", "Mã sản phẩm", "Tên sản phẩm", " giá sản phẩm", "Giá giảm"," giá bán sản phẩm","Trạng thái","Ngày tạo");
        for (Product product : productList) {
            if (product.getProductDiscount()>0&&product.getProductDiscount()<100){
                productimpl.displayData(product);
            }
        }
    }
public static void dateNew(){
        List<Product> productList=productimpl.readFromFile();
        if (productList==null){
            productList=new ArrayList<>();
        }

    Collections.sort(productList, new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return o1.getDateInputProduct().compareTo(o2.getDateInputProduct());
        }
    });
    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-40s%-40s\n", "Mã sản phẩm", "Tên sản phẩm", " giá sản phẩm", "Giá giảm"," giá bán sản phẩm","Trạng thái","Ngày tạo");    for (Product product : productList) {
        productimpl.displayData(product);
    }
    System.out.println("Đã sắp xếp xong các sản phẩm theo ngày tạo!!!");
}
    public static void searchProduct(Scanner scanner) {
        boolean checkExit = true;
        do {
            System.out.println("-------------------------------------------------------------------------------------------------");
            System.out.println("|                                       TÌM KIẾM SẢN PHẨM                                       |");
            System.out.println("|1.Tìm kiếm theo tên sản phẩm                                                                   |");
            System.out.println("|2.Tìm kiếm sản phẩm theo tên danh mục sản phẩm                                                 |");
            System.out.println("|3.Tìm kiếm sản phẩm theo khoảng giá bán sản phẩm                                               |");
            System.out.println("|4.Tìm kiếm sản phẩm theo khoảng giảm giá của sản phẩm                                          |");
            System.out.println("|5.Thoát                                                                                        |");
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
        List<Product> listProduct = productimpl.readFromFile();
        if (listProduct==null){
            listProduct = new ArrayList<>();
        }
        System.out.println("Nhập tên danh mục:");
        String searchName = scanner.nextLine();
        for (Product pro : listProduct) {
            if (pro.getCatalog().getCatalogName().contains(searchName)) {
                productimpl.displayData(pro);
            }
        }
    }

    public static void searchByExPriceProduct(Scanner scanner) {
        List<Product> productList = productimpl.readFromFile();
        if (productList == null) {
            productList = new ArrayList<>();
        }
        System.out.print("Nhập  giá trong khoảng từ :");
        float expriceStart = Float.parseFloat(scanner.nextLine());
        System.out.print(" đến: ");
        float expriceEnd = Float.parseFloat(scanner.nextLine());
        System.out.printf("%-30s%-32s%-30s%-30s%-30s%-40s%-40s\n", "Mã sản phẩm", "Tên sản phẩm", " giá sản phẩm","Giá giảm"," giá bán sản phẩm","Trạng thái","Ngày tạo");
        for (Product pro : productList) {
            if (pro.getExportPriceProduct()<=expriceEnd && pro.getExportPriceProduct()>=expriceStart) {
                productimpl.displayData(pro);
            }
        }
    }

    public static void searchByDisscountProduct(Scanner scanner) {
        List<Product> productList = productimpl.readFromFile();
        if (productList == null) {
            productList = new ArrayList<>();
        }
        System.out.print("Nhập % giảm giá trong khoảng từ :");
        float disscountStart = Float.parseFloat(scanner.nextLine());
        System.out.print(" đến: ");
        float disscountEnd = Float.parseFloat(scanner.nextLine());
        System.out.printf("%-30s%-32s%-30s%-30s%-30s%-40s%-40s\n", "Mã sản phẩm", "Tên sản phẩm", " giá sản phẩm","Giá giảm"," giá bán sản phẩm","Trạng thái","Ngày tạo");
        for (Product pro : productList) {
            if (pro.getProductDiscount() <disscountEnd&&pro.getProductDiscount()>disscountStart) {
                productimpl.displayData(pro);
            }
        }
    }

    public static void searchByNameProduct(Scanner scanner) {
        List<Product> productList = productimpl.readFromFile();
        if (productList == null) {
            productList = new ArrayList<>();
        }
        System.out.println("Nhập tên sản phẩm muốn tìm:");
        String searchName = scanner.nextLine();
        System.out.printf("%-30s%-32s%-30s%-30s%-30s%-40s%-40s\n", "Mã sản phẩm", "Tên sản phẩm", " giá sản phẩm","Giá giảm"," giá bán sản phẩm","Trạng thái","Ngày tạo");
        if (ShopValiDation.checkEmpty(searchName)){
            for (Product pro : productList) {
                if (pro.getProductName().equals(searchName)) {
                    productimpl.displayData(pro);
                }
            }
        }

    }
}