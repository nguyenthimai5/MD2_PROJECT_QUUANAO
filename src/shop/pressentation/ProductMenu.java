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
    private static Catalogimpl catalogimpl = new Catalogimpl();

    public static void productManager(Scanner scanner) {
        boolean checkExit = true;
        do {
            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.println("                                        QUẢN LÝ SẢN PHẨM                                  |");
            System.out.println("1.Danh sách sản phẩm được sắp xếp theo danh mục sản phẩm                                  |");
            System.out.println("2.Thêm mới sản phẩm                                                                       |");
            System.out.println("3.Cập nhật sản phẩm                                                                       |");
            System.out.println("4.Xóa sản phẩm                                                                            |");
            System.out.println("5.Thoát                                                                                   |");
            System.out.println("-------------------------------------------------------------------------------------------");
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
                    displayProduct();
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


    public static void inputData(Scanner scanner) {
        List<Product> productList = productimpl.readFromFile();
        if (productList == null) {
            productList = new ArrayList<>();
        }
        System.out.println("Bạn muốn thêm bao nhiêu sản phẩm:");
        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException exception) {
            System.err.println(ShopMessage.CHECK_NUMBER);
        }
        for (int i = 0; i < choice; i++) {
            System.out.printf("Nhập thông tin cho màu sắc thứ %d\n", i + 1);
            Product product = productimpl.input(scanner);
            productimpl.create(product);
        }

    }

    public static void deleteProduct(Scanner scanner) {
        List<Product> productList = productimpl.readFromFile();
        if (productList == null) {
            productList = new ArrayList<>();
        }
        boolean check=false;
        System.out.print("Nhập mã danh mục mà quý khách muốn xoá:");
            String string = scanner.nextLine();
          if (ShopValiDation.checkEmpty(string)){
              for (Product pro:productList ) {
                  if (pro.getProductId().equals(string)){
                      pro.setStatusProduct(!pro.isStatusProduct());
                      check=true;
                      break;
                  }
              }
          }else {
              System.out.println("Không được để trống!!");
          }
          if (!check){
              System.out.println("Không tìm thấy sản phẩm");
          }else {
              productimpl.writeToFile(productList);
          }

    }

    public static void updateProduct(Scanner scanner) {
        List<Product> productList = productimpl.readFromFile();
        if (productList == null) {
            productList = new ArrayList<>();
        }
    }

    public static void displayListCatalog(Catalog root, List<Catalog> catalogList, int count) {
        List<Product> productList = productimpl.readFromFile();
        if (productList == null) {
            productList = new ArrayList<>();
        }
        for (int i = 0; i < count; i++) {
            System.out.printf("\t");
        }
        String status = "Không hoạt động!";
        if (root.isCatalogStatus()) {
            status = "Hoạt động!";
        }
        System.out.printf("%d.%s-%s\n", root.getCatalogId(), root.getCatalogName(), status);
        List<Catalog> listChild = new ArrayList<>();
        for (Catalog catalogs : catalogList) {
            if (catalogs.getCatalog() != null && catalogs.getCatalog().getCatalogId() == root.getCatalogId()) {
                listChild.add(catalogs);
            }
        }
        if (listChild.size() != 0) {
            count++;
        } else {

            int cntProduct = count+1;
            for (Product pro : productList) {
                if (pro.getCatalog()!=null&&pro.getCatalog().getCatalogId()==root.getCatalogId()){
                    for (int i = 0; i < cntProduct; i++) {
                        System.out.printf("\t");
                    }
                    System.out.printf("%s-%s-%b\n",pro.getProductId(),pro.getProductName(),pro.isStatusProduct());
                }
            }
        }
        for (Catalog cata : listChild) {
            displayListCatalog(cata, catalogList, count);
        }
    }

    public static void displayProduct() {
        Catalogimpl catalogimpl = new Catalogimpl();
        List<Catalog> catalogList = catalogimpl.readFromFile();
        if (catalogList == null) {
            catalogList = new ArrayList<>();
        }
        for (Catalog cat : catalogList) {
            if (cat.getCatalog() == null) {
                displayListCatalog(cat, catalogList, 0);
            }
        }
    }
}