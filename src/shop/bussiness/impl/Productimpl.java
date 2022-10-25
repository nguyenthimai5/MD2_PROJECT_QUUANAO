package shop.bussiness.impl;

import shop.bussiness.design.IProduct;
import shop.bussiness.entity.Catalog;
import shop.bussiness.entity.Color;
import shop.bussiness.entity.Product;
import shop.bussiness.entity.Size;
import shop.config.ShopConstant;
import shop.config.ShopMessage;
import shop.config.ShopValiDation;
import shop.data.Fileimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Productimpl implements IProduct<Product, Integer> {
    @Override
    public boolean create(Product product) {
        List<Product> productList = readFromFile();
        if (productList == null) {
            productList = new ArrayList<>();
        }
        productList.add(product);
        boolean result = writeToFile(productList);
        return result;
    }

    @Override
    public Product input(Scanner scanner) {
        List<Product> productList = readFromFile();
        if (productList == null) {
            productList = new ArrayList<>();
        }
        Product productNew = new Product();

        System.out.print("Mã sản phẩm:");
        do {
            String productIdNew = scanner.nextLine();
            if (ShopValiDation.checkEmpty(productIdNew)) {
                if (ShopValiDation.checkLenght(productIdNew, 5, 5)) {
                    if (productIdNew.charAt(0) == 'P') {
                        boolean checkValidatePrId = true;
                        for (Product pro : productList) {
                            if (productIdNew.trim().equals(pro.getProductId())) {
                                checkValidatePrId = false;
                                break;
                            }
                        }
                        if (checkValidatePrId) {
                            productNew.setProductId(productIdNew);
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
                System.err.println(ShopMessage.CHECK_EMPTY_NAME_PRODUCTID);
            }
        } while (true);

        System.out.print("Tên sản phẩm:");
        do {
            String productNameNew = scanner.nextLine();
            if (ShopValiDation.checkEmpty(productNameNew)) {
                if (ShopValiDation.checkLenght(productNameNew, 6, 30)) {
                    boolean checkValidatePrName = true;
                    for (Product pro : productList) {
                        if (productNameNew.trim().equals(pro.getProductName())) {
                            checkValidatePrName = false;
                            break;
                        }
                    }
                    if (checkValidatePrName) {
                        productNew.setProductName(productNameNew);
                        break;
                    } else {
                        System.err.println(ShopMessage.CHECK_VALIDATE_NAME_PRODUCT);
                    }
                } else {
                    System.err.println(ShopMessage.CHECK_LENGH_NAME_PRODUCT);
                }
            } else {
                System.err.println(ShopMessage.CHECK_EMPTY_NAME_PRODUCT);
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
                productNew.setProductPrice(priceNew);
                break;
            } else {
                System.err.println(ShopMessage.CHECK_PRICE_PRODUCT);
            }
        } while (true);
        System.out.print("% giảm giá của sản phẩm là:");
        float productDiscountNew = 0;
        do {
            try {
                productDiscountNew = Float.parseFloat(scanner.nextLine());

            } catch (NumberFormatException exception) {
                System.err.println(ShopMessage.CHECK_NUMBER);

            }
            if (productDiscountNew > 0 && productDiscountNew < 100) {
                productNew.setProductDiscount(productDiscountNew);
                break;
            } else {
                System.err.println(ShopMessage.CHECK_PRODUCT_DISCOUNT);
            }
        } while (true);
        System.out.println("Tiêu để sản phẩm:");
        do {
            String titleNew = scanner.nextLine();
            if (ShopValiDation.checkEmpty(titleNew)) {
                if (ShopValiDation.checkLenght(titleNew, 10, 50)) {
                    productNew.setTitle(titleNew);
                    break;
                } else {
                    System.err.println(ShopMessage.CHECK_TITLE_PRODUCT);
                }
            } else {
                System.err.println("Không được để trống!");
            }

        } while (true);
        System.out.println("Mô tả sản phẩm:");
        String descriptionProduct = scanner.nextLine();
        do {
            if (ShopValiDation.checkEmpty(descriptionProduct)) {
                productNew.setDescriptionsProduct(descriptionProduct);
                break;
            } else {
                System.err.println(ShopMessage.CHECK_DECRIPTION_PRODUCT);
            }
        } while (true);
        System.out.println("Chọn màu sắc cho sản phẩm:");
        Product product = new Product();
        Colorimpl colorimpl = new Colorimpl();
        List<Color> colorList = colorimpl.readFromFile();
        if (colorList == null) {
            colorList = new ArrayList<>();
        }
        do {
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
            List<Color> colorListNew = product.getColorList();
            if (colorListNew == null) {
                colorListNew = new ArrayList<>();
            }
            colorListNew.add(colorList.get(choice - 1));
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


        } while (true);
        System.out.println("Kích cỡ của sản phẩm:");
        Sizeimpl sizeimpl = new Sizeimpl();
        List<Size> sizeList = sizeimpl.readFromFile();
        if (sizeList == null) {
            sizeList = new ArrayList<>();
        }
        do {

            for (Size size : sizeList) {
                System.out.printf("%d.%s\n", size.getSizeId(), size.getSizeName());
            }

            System.out.print("Chọn kích cỡ: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException exception) {
                System.err.println(ShopMessage.CHECK_NUMBER);
            }
            List<Size> sizeListNew = product.getSizeList();
            if (sizeListNew == null) {
                sizeListNew = new ArrayList<>();
            }
            sizeListNew.add(sizeList.get(choice - 1));
            System.out.println("Bạn có muốn chọn thêm kích cỡ  không: ");
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
        } while (true);
        System.out.println("Danh mục sản phẩm: ");
        Catalogimpl catalogimpl = new Catalogimpl();
        List<Catalog> catalogList = catalogimpl.readFromFile();
        if (catalogList == null) {
            catalogList = new ArrayList<>();
        }
        for (Catalog cat : catalogList) {
            boolean check = false;
            for (Catalog catChild : catalogList) {
                if (catChild.getCatalog() != null && catChild.getCatalog().getCatalogId() == cat.getCatalogId()) {
                    check = true;
                    break;
                }
            }
            if (!check && cat.isCatalogStatus()) {
                catalogimpl.displayData(cat);
            }
        }
        System.out.println("Sự lựa chọn của bạn: ");
        String choiceCatalogs;
        int choiceCatalog=0;
        do {
            choiceCatalogs = scanner.nextLine();
            do {
               try {
                    choiceCatalog=Integer.parseInt(choiceCatalogs);
                   break;
               }catch (NumberFormatException ex){
                   System.err.println(ShopMessage.CHECK_NUMBER);
               }
            } while (true);
            if (choiceCatalog >= 1 && choiceCatalog <= catalogList.size()) {
                for (Catalog catExist : catalogList) {
                    if (catExist.getCatalogId() == choiceCatalog) {
                        productNew.setCatalog(catExist);
                        break;
                    }
                }
                break;
            } else {
                System.err.println("Mã không có trong dữ liệu!!!!");
            }
        } while (true);
        System.out.println("Trạng thái sản phẩm:");
        System.out.println("1.Đang bán");
        System.out.println("2.Hết hàng");
        System.out.print("Sự lựa chọn của bạn:");
        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException exception) {
            System.err.println(ShopMessage.CHECK_NUMBER);
        }

        switch (choice) {
            case 1:
                productNew.setStatusProduct(true);
                break;
            case 2:
                productNew.setStatusProduct(false);
                break;
            default:
                System.err.println(ShopMessage.CHECK_CHOICE1_2);

        }
        exportPriceProduct(productNew);
        Date date = new Date();
        productNew.setDateInputProduct(date);

        return productNew;
    }

    public static void exportPriceProduct(Product product) {
        float exportPrice = product.getProductPrice() - (100 - product.getProductDiscount() / 100);
        product.setExportPriceProduct(exportPrice);

    }

    @Override
    public void displayData(Product product) {
        String status = "Hết hàng";
        if (product.isStatusProduct()) {
            status = "Đang bán";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = formatter.format(product.getDateInputProduct());
        System.out.printf("%-30s%-32s%-30f%-30f%-30f", product.getProductId(), product.getProductName(), product.getProductPrice(), product.getProductDiscount(), product.getExportPriceProduct());
        System.out.printf("%-40s%-40s\n", status, strDate);
    }

    @Override
    public boolean update(Product product) {
        List<Product> productList = readFromFile();
        boolean returnProduct = false;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId() == product.getProductId()) {
                productList.set(i, product);
                returnProduct = true;
                break;
            }
        }
        boolean result = writeToFile(productList);
        if (returnProduct && result) {
            return true;
        }
        return false;
    }


    @Override
    public boolean delete(Integer id) {
        List<Product> productList = readFromFile();
        boolean check = false;
        for (Product product : productList) {
            if (Integer.parseInt(product.getProductId()) == id) {
                product.setStatusProduct(false);
                check = true;
                break;
            }
        }
        boolean result = writeToFile(productList);
        if (check && result) {
            System.out.println(ShopMessage.CHECK_DELETE_PRODUCT_TRUE);
            return true;
        }
        System.err.println(ShopMessage.CHECK_DELETE_PRODUCT_FASLE);
        return false;

    }

    @Override
    public List<Product> readFromFile() {
        Fileimpl fileimpl = new Fileimpl();
        return fileimpl.readFrommFile(ShopConstant.URL_PRODUCT);
    }

    @Override
    public boolean writeToFile(List<Product> list) {
        Fileimpl fileimpl = new Fileimpl();
        return fileimpl.writeToFile(list, ShopConstant.URL_PRODUCT);
    }

    public static boolean checkChildless(Catalog child, List<Catalog> catalogList) {
        boolean check = true;
        for (Catalog cat : catalogList) {
            if (cat.getCatalog() != null && child.getCatalogId() != cat.getCatalog().getCatalogId()) {
                check = false;
                break;
            }
        }
        if (check) {
            return true;
        } else {
            return false;
        }
    }
}
