package shop.pressentation;

import shop.bussiness.entity.Catalog;
import shop.bussiness.entity.Color;
import shop.bussiness.entity.Product;
import shop.bussiness.entity.Size;
import shop.bussiness.impl.Catalogimpl;
import shop.bussiness.impl.Colorimpl;
import shop.bussiness.impl.Productimpl;
import shop.bussiness.impl.Sizeimpl;
import shop.config.ShopMessage;
import shop.config.ShopValiDation;

import java.util.*;

public class ProductMenu {
    private static Productimpl productimpl = new Productimpl();
    private static List<Product> productList = productimpl.readFromFile();
    private static Colorimpl colorimpl = new Colorimpl();
    private static List<Color> colorList = colorimpl.readFromFile();
    private static Sizeimpl sizeimpl = new Sizeimpl();
    private static List<Size> sizeList = sizeimpl.readFromFile();
    private static Catalogimpl catalogimpl = new Catalogimpl();
    private static List<Catalog> catalogList = catalogimpl.readFromFile();

    public static void productManager(Scanner scanner) {
        Date date=new Date();
        Product product=new Product("Prd1","Áo len",7,3,6,"đẹp","Đẹp",colorList,sizeList,catalogList,true,date);
        boolean checkExit = true;
        do {
            System.out.println("********************************** QUẢN LÝ SẢN PHẨM *******************************");
            System.out.println("1.Danh sách sản phẩm được sắp xếp theo danh mục sản phẩm");
            System.out.println("2.Thêm mới sản phẩm");
            System.out.println("3.Cập nhật sản phẩm");
            System.out.println("4.Xóa sản phẩm");
            System.out.println("5.Thoát");
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
                    updateProduct(scanner);
                    break;
                case 4:
                    deleteProduct(scanner);
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
        System.out.printf("%-30s%-30s%-20f%-20f%-20f%-40s%-40s%-40s%-30s%-50s", "Mã sản phẩm", "Tên sản phẩm", "Giá sản phẩm", "Giảm giá", "Giá bán", "Danh sách màu", "Danh sách kích cỡ", "Danh mục", "Trạng thái", "Ngày tạo");
        for (Product pro : productList) {
            productimpl.displayData(pro);
        }

    }

    public static void inputData(Scanner scanner) {
        System.out.println("Bạn muốn thêm bao nhiêu danh mục:");
        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException exception) {
            System.err.println(ShopMessage.CHECK_NUMBER);
        }
        for (int i = 0; i < choice; i++) {
            System.out.printf("Nhập thông tin cho màu sắc thứ %d", i + 1);
            Product product = productimpl.input(scanner);
            productList.add(product);
            productimpl.create(product);
        }

    }

    public static void deleteProduct(Scanner scanner) {
        System.out.print("Nhập mã danh mục mà quý khách muốn xoá:");
        int idProduct = 0;

        do {
            String string=scanner.nextLine();
            try {
                if (ShopValiDation.checkEmpty(string)){
                    idProduct = Integer.parseInt(string);
                    break;
                }

            } catch (NumberFormatException exception) {
                System.err.println(ShopMessage.CHECK_NUMBER);
            }


        } while (true);
        boolean check = productimpl.delete(idProduct);
        if (check) {
            System.out.println("Xoá thành công");
        }
    }

    public static void updateProduct(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm muốn cập nhật:");
        String productId = scanner.nextLine();
        for (Product product : productList) {
            if (productId.trim().equals(product.getProductName().trim())) {
                boolean checkValidatePrId = true;
                System.out.print("Mã sản phẩm mới:");
                do {
                    String productIdNew = scanner.nextLine();
                    if (ShopValiDation.checkEmpty(productIdNew)) {
                        if (ShopValiDation.checkLenght(productIdNew, 5, 5)) {
                            if (productIdNew.charAt(0) == 'P') {
                                for (Product pro : productList) {
                                    if (productIdNew.trim().equals(pro.getProductId())) {
                                        checkValidatePrId = false;
                                        break;
                                    }
                                }
                                if (checkValidatePrId) {
                                    product.setProductId(productIdNew);
                                    break;
                                } else {
                                    System.err.println(ShopMessage.CHECK_VALIDATE_PRODUCTID);
                                }
                            } else {
                                System.err.println(ShopMessage.CHECK_NAME_PRODUCTID);
                            }
                        } else {
                            System.err.println(ShopMessage.CHECK_LENGH_NAME_PRODUCTID);
                        }
                    } else {
                        break;
                    }
                } while (true);
                boolean checkValidatePrName = true;
                System.out.print("Tên sản phẩm:");
                do {
                    String productNameNew = scanner.nextLine();
                    if (ShopValiDation.checkEmpty(productNameNew)) {
                        if (ShopValiDation.checkLenght(productNameNew, 6, 30)) {
                            for (Product pro : productList) {
                                if (productNameNew.trim().equals(pro.getProductName())) {
                                    checkValidatePrId = false;
                                    break;
                                }
                            }
                            if (checkValidatePrName) {
                                product.setProductName(productNameNew);
                                break;
                            } else {
                                System.err.println(ShopMessage.CHECK_VALIDATE_NAME_PRODUCT);
                            }
                        } else {
                            System.err.println(ShopMessage.CHECK_LENGH_NAME_PRODUCT);
                        }
                    } else {
                        break;
                    }
                } while (true);
                System.out.print("Giá của sản phẩm:");
                float priceNew = 0;
                do {

                    try {
                        priceNew = Float.parseFloat(scanner.nextLine());

                    } catch (NumberFormatException exception) {
                        System.err.println(ShopMessage.CHECK_NUMBER);

                    }
                    if (priceNew > 0) {
                        product.setProductPrice(priceNew);
                        break;
                    } else {
                        System.err.println(ShopMessage.CHECK_PRICE_PRODUCT);
                    }
                } while (true);
                System.out.println("% giảm giá của sản phẩm là:");
                float productDiscountNew = 0;
                do {
                    try {
                        productDiscountNew = Float.parseFloat(scanner.nextLine());

                    } catch (NumberFormatException exception) {
                        System.err.println(ShopMessage.CHECK_NUMBER);

                    }
                    if (productDiscountNew > 0 && productDiscountNew < 100) {
                        product.setProductDiscount(productDiscountNew);
                        break;
                    } else {
                        System.err.println(ShopMessage.CHECK_PRODUCT_DISCOUNT);
                    }
                } while (true);
                System.out.println("Tiêu để sản phẩm:");
                String titleNew = scanner.nextLine();
                do {
                    if (ShopValiDation.checkEmpty(titleNew)){
                        if (ShopValiDation.checkLenght(titleNew, 10, 50)) {
                            product.setTitle(titleNew);
                            break;
                        } else {
                            System.err.println(ShopMessage.CHECK_TITLE_PRODUCT);
                        }
                    }else {
                        break;
                    }

                } while (true);
                System.out.println("Mô tả sản phẩm:");
                String descriptionProduct = scanner.nextLine();
                do {
                    if (ShopValiDation.checkEmpty(descriptionProduct)) {
                        product.setDescriptionsProduct(descriptionProduct);
                        break;
                    } else {
                        break;
                    }
                } while (true);
                System.out.println("Chọn màu sắc cho sản phẩm:");

                do {
                    Colorimpl colorimpl = new Colorimpl();
                    List<Color> colorList = colorimpl.readFromFile();
                    if (colorList == null) {
                        colorList = new ArrayList<>();
                    }
                    for (Color color : colorList) {
                        System.out.printf("%d.%s\n", color.getColorId(), color.getColorName());
                    }
                    System.out.print("Chọn màu sắc: ");
                    int choice = 0;
                    try {
                        choice = Integer.parseInt(scanner.nextLine());

                    } catch (NumberFormatException exception) {
                        System.err.println(ShopMessage.CHECK_NUMBER);
                    }

                    if (choice > 0 && choice < colorList.size()) {
                        boolean checkColorExist = false;
                        for (Color colorExist : product.getColorList()) {
                            if (colorExist.getColorId() == product.getColorList().get(choice - 1).getColorId()) {
                                checkColorExist = true;
                            }
                        }
                        if (!checkColorExist) {
                            product.getColorList().add(colorList.get(choice - 1));
                        } else {
                            System.err.println("Màu sắc đã tồn tại trong sản phẩm");
                        }
                        System.out.println("Bạn có muốn chọn thêm màu sắc không: ");
                        System.out.println("1. Có");
                        System.out.println("2. Không");
                        System.out.print("Lựa chọn của bạn : ");
                        int choice2 = 0;
                        try {
                            choice2 = Integer.parseInt(scanner.nextLine());

                        } catch (NumberFormatException exception) {
                            System.err.println(ShopMessage.CHECK_NUMBER);
                        }

                        if (choice2 != 1) {
                            break;
                        }
                    } else {
                        System.err.println(ShopMessage.CHECK_COLOR_PRODUCT);
                        ;
                    }
                } while (true);
                System.out.println("Kích cỡ của sản phẩm:");
                do {
                    Sizeimpl sizeimpl = new Sizeimpl();
                    List<Size> sizeList = sizeimpl.readFromFile();
                    if (sizeList == null) {
                        sizeList = new ArrayList<>();
                    }
                    for (Size size : sizeList) {
                        System.out.printf("%d.%s\n", size.getSizeId(), size.getSizeName());
                    }
                    System.out.print("Chọn kích cỡ: ");
                    int choiceSize = 0;
                    try {
                        choiceSize = Integer.parseInt(scanner.nextLine());

                    } catch (NumberFormatException exception) {
                        System.err.println(ShopMessage.CHECK_NUMBER);
                    }

                    if (choiceSize > 0 && choiceSize < sizeList.size()) {
                        boolean checkSizeExit = false;
                        for (Size sizeExit : product.getSizeList()) {
                            if (sizeExit.getSizeId() == product.getSizeList().get(choiceSize - 1).getSizeId()) {
                                checkSizeExit = true;
                            }
                        }

                        if (!checkSizeExit) {
                            product.getSizeList().add(sizeList.get(choiceSize - 1));
                        } else {
                            System.err.println("Size đã tồn tại trong sản phẩm");
                        }
                        System.out.println("Bạn có muốn chọn thêm kích cỡ không: ");
                        System.out.println("1. Có");
                        System.out.println("2. Không");
                        System.out.print("Lựa chọn của bạn : ");
                        int choice2 = 0;
                        try {
                            choice2 = Integer.parseInt(scanner.nextLine());

                        } catch (NumberFormatException exception) {
                            System.err.println(ShopMessage.CHECK_NUMBER);
                        }
                        ;
                        if (choice2 != 1) {
                            break;
                        }
                    } else {
                        System.err.println(ShopMessage.CHECK_SIZE_PRODUCT);
                        ;
                    }
                } while (true);
                Catalogimpl catalogimpl = new Catalogimpl();
                List<Catalog> catalogList = catalogimpl.readFromFile();
                List<Catalog> listCatalogChild = new ArrayList<>();
                if (catalogList == null) {
                    catalogList = new ArrayList<>();
                }
                do {
                    int cnt = 1;
                    for (Catalog cat : catalogList) {
                        if (ShopValiDation.checkProductChild(cat, catalogList)) {
                            listCatalogChild.add(cat);
                        }
                    }
                    for (Catalog cat : listCatalogChild) {
                        System.out.printf("%d. %s", cnt, cat.getCatalogName());
                        cnt++;
                    }
                    System.out.print("Chọn danh mục sản phẩm thuộc về: ");
                    int choiceCatalog = 0;

                    try {
                        choiceCatalog = Integer.parseInt(scanner.nextLine());

                    } catch (NumberFormatException exception) {
                        System.err.println(ShopMessage.CHECK_NUMBER);
                    }

                    if (choiceCatalog > 0 && choiceCatalog < catalogList.size()) {
                        boolean checkCatalog = false;
                        for (Catalog catalogExit : product.getCatalogList()) {
                            if (catalogExit.getCatalogId() == product.getCatalogList().get(choiceCatalog - 1).getCatalogId()) {
                                checkCatalog = true;
                            }
                        }

                        if (!checkCatalog) {
                            product.getCatalogList().add(catalogList.get(choiceCatalog - 1));
                        } else {
                            System.err.println("Danh mục đã tồn tại trong sản phẩm");
                        }
                        System.out.println("Bạn có muốn chọn thêm danh mục không: ");
                        System.out.println("1. Có");
                        System.out.println("2. Không");
                        System.out.print("Lựa chọn của bạn : ");
                        int choice2 = 0;
                        try {
                            choice2 = Integer.parseInt(scanner.nextLine());

                        } catch (NumberFormatException exception) {
                            System.err.println(ShopMessage.CHECK_NUMBER);
                        }

                        if (choice2 != 1) {
                            break;
                        }
                    } else {
                        System.err.println(ShopMessage.CHECK_CATALOG_PRODUCT);
                        ;
                    }
                } while (true);
                System.out.println("Trạng thái sản phẩm:");
                System.out.println("1.Đang bán");
                System.out.println("2.Hết hàng");
                System.out.println("3.Không cập nhật");
                System.out.print("Sự lựa chọn của bạn:");
                int choice = 0;
                try {
                    choice = Integer.parseInt(scanner.nextLine());

                } catch (NumberFormatException exception) {
                    System.err.println(ShopMessage.CHECK_NUMBER);
                }

                switch (choice) {
                    case 1:
                        product.setStatusProduct(true);
                        break;
                    case 2:
                        product.setStatusProduct(false);
                        break;
                    case 3:
                        product.isStatusProduct();
                        break;
                    default:
                        System.err.println(ShopMessage.CHECK_CHOICE1_3);

                }
                productimpl.exportPriceProduct(product);
                System.out.println("Nhập ngày tạo sản phẩm:");
                Date date = new Date();
                product.setDateInputProduct(date);
            }
            productimpl.create(product);
        }
    }
}
