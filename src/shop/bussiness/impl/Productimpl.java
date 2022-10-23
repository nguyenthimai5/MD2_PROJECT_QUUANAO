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
        boolean checkValidatePrId = true;
        System.out.print("Mã sản phẩm:");
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
        System.out.println("% giảm giá của sản phẩm là:");
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
        String titleNew = scanner.nextLine();
        do {
            if (ShopValiDation.checkLenght(titleNew, 10, 50)) {
                productNew.setTitle(titleNew);
                break;
            } else {
                System.err.println(ShopMessage.CHECK_TITLE_PRODUCT);
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
                    productNew.getColorList().add(colorList.get(choice - 1));
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
                    productNew.getSizeList().add(sizeList.get(choiceSize - 1));
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
                    productNew.getCatalogList().add(catalogList.get(choiceCatalog - 1));
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
        System.out.println("Nhập ngày tạo sản phẩm:");
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
        System.out.printf("%-30s%-30s%-20f%-20f%-20f%", product.getProductId(), product.getProductName(), product.getProductPrice(), product.getProductDiscount(), product.getExportPriceProduct());
        System.out.printf("%-40s%-40s%-40s%-30b%-50s\n", product.getColorList(), product.getSizeList(), product.getCatalogList(), status, strDate);
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
//            if (product.getProductId()==id){
//                product.setStatusProduct(!product.isStatusProduct());
//                check=true;
//                break;
//            }
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
}
