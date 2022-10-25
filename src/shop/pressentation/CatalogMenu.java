package shop.pressentation;

import shop.bussiness.entity.Catalog;
import shop.bussiness.entity.Product;
import shop.bussiness.entity.User;
import shop.bussiness.impl.Catalogimpl;
import shop.bussiness.impl.Productimpl;
import shop.bussiness.impl.Userimpl;
import shop.config.ShopMessage;
import shop.config.ShopValiDation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CatalogMenu {
    private static Catalogimpl catalogimpl = new Catalogimpl();


    public static void catalogManager(Scanner scanner) {
        boolean checkExit = true;
        do {
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println("|                                     QUẢN LÝ DANH MỤC                                   |");
            System.out.println("|1.Danh sách danh mục theo cây danh mục                                                  |");
            System.out.println("|2.Tạo mới danh mục                                                                      |");
            System.out.println("|3.Cập nhật danh mục                                                                     |");
            System.out.println("|4.Xóa danh mục                                                                          |");
            System.out.println("|5.Tìm kiếm danh mục theo tên                                                            |");
            System.out.println("|6.Thoát                                                                                 |");
            System.out.println("------------------------------------------------------------------------------------------");
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
                    displayListCatalog();
                    break;
                case 2:
                    inputData(scanner);
                    break;
                case 3:
                    updateCatalog(scanner);
                    break;
                case 4:
                    deleteCatalog(scanner);
                    break;
                case 5:
                    searchByName(scanner);
                    break;
                case 6:
                    checkExit = false;
                    break;
                default:
                    System.err.println(ShopMessage.CHECK_CHOICE1_6);
            }
        } while (checkExit);

    }

    public static void displayListCatalog() {
        List<Catalog> catalogList1 = catalogimpl.readFromFile();
        for (Catalog cat : catalogList1) {
            if (cat.getCatalog() == null) {

                catalogimpl.displayListCatalog(cat, catalogList1, 0);
            }
        }
    }

    public static void inputData(Scanner scanner) {
        List<Catalog> catalogList1 = catalogimpl.readFromFile();
        System.out.println("Bạn muốn thêm bao nhiêu danh mục:");
        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException exception) {
            System.err.println(ShopMessage.CHECK_NUMBER);
        }
        for (int i = 0; i < choice; i++) {
            System.out.printf("Nhập thông tin cho danh mục thứ %d\n", i + 1);
            Catalog catalog = catalogimpl.input(scanner);
            catalogimpl.create(catalog);
        }

    }

    public static void updateCatalog(Scanner scanner) {
        List<Catalog> catalogList = catalogimpl.readFromFile();
        if (catalogList == null) {
            catalogList = new ArrayList<>();
        }
        System.out.print("Nhập id danh mục bạn muốn cập nhật:");
        int idCheck = 0;
        do {
            try {
                idCheck = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException ex) {
                System.err.println(ShopMessage.CHECK_NUMBER);
            }
        } while (true);
        boolean checks = false;
        for (Catalog cat : catalogList) {
            if (cat.getCatalogId() == idCheck) {
                checks = true;
                break;
            }
        }
            if (!checks) {
                System.err.printf("Không tìm thấy id %d\n", idCheck);
            } else {
                System.out.print("Tên danh mục muốn cập nhật:");
                boolean checkValiDateName = true;
                do {
                    String catalogName = scanner.nextLine();
                    if (ShopValiDation.checkEmpty(catalogName)) {
                        if (ShopValiDation.checkLenght(catalogName, 6, 30)) {
                            for (Catalog cata : catalogList) {
                                if (catalogName.equals(cata.getCatalogName())) {
                                    checkValiDateName = false;
                                    break;
                                }

                            }
                            if (checkValiDateName) {
                                catalogList.get(idCheck - 1).setCatalogName(catalogName);
                                break;
                            } else {
                                System.err.println(ShopMessage.CHECK_VALIDATE_NAME_CATALOG);
                            }
                        } else {
                            System.err.println(ShopMessage.CHECK_LENGH_NAME_CATALOG);
                        }
                    } else {
                        break;
                    }
                } while (true);
                System.out.print("Mô tả danh mục:");
                do {
                    String catalogDescriptions = scanner.nextLine();
                    if (ShopValiDation.checkEmpty(catalogDescriptions)) {
                        catalogList.get(idCheck - 1).setCatalogDescriptions(catalogDescriptions);
                        break;
                    } else {
                        break;
                    }
                } while (true);
                System.out.println("Trạng thái danh mục:");
                System.out.println("1.Hoạt động");
                System.out.println("2.Không hoạt động");
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
                        catalogList.get(idCheck - 1).setCatalogStatus(true);
                        break;
                    case 2:
                        catalogList.get(idCheck - 1).setCatalogStatus(false);
                        break;
                    case 3:
                        catalogList.get(idCheck - 1).isCatalogStatus();
                    default:
                        System.err.println(ShopMessage.CHECK_CHOICE1_3);
                }
                List<Catalog> listchild = new ArrayList<>();
                Productimpl productimpl = new Productimpl();
                List<Product> productList = productimpl.readFromFile();
                if (productList == null) {
                    productList = new ArrayList<>();
                }
                System.out.println("0.Danh mục gốc");
                for (Catalog cat : catalogList) {
                    if (cat.isCatalogStatus()) {
                        boolean check = true;
                        for (Product pro : productList) {
                            if (pro.getCatalog().getCatalogId() == cat.getCatalogId()) {
                                check = false;
                            }
                        }
                        if (check) {
                            listchild.add(cat);
                            System.out.printf("%d. %s\n", cat.getCatalogId(), cat.getCatalogName());
                        }
                    }

                }


                System.out.print("Chọn danh mục mà danh mục thuộc về:");
                int choiceCatalog = 0;
                try {
                    choiceCatalog = Integer.parseInt(scanner.nextLine());

                } catch (NumberFormatException exception) {
                    System.err.println(ShopMessage.CHECK_NUMBER);
                }
                if (choiceCatalog == 0) {
                    catalogList.get(idCheck - 1).setCatalog(null);
                } else {
                    catalogList.get(idCheck - 1).setCatalog(catalogList.get(choiceCatalog - 1));
                }
                catalogimpl.writeToFile(catalogList);
            }
            }
    public static void deleteCatalog(Scanner scanner) {
        List<Catalog> catalogList = catalogimpl.readFromFile();
        System.out.print("Nhập mã danh mục mà quý khách muốn xoá:");
        int idCatalog = 0;
        do {
            String string = scanner.nextLine();
            try {
                if (ShopValiDation.checkEmpty(string)) {
                    idCatalog = Integer.parseInt(string);
                    break;
                }

            } catch (NumberFormatException exception) {
                System.err.println(ShopMessage.CHECK_NUMBER);
            }


        } while (true);
        boolean check = catalogimpl.delete(idCatalog);
        if (check) {
            System.out.println("Xoá thành công");
        }
    }

    public static void searchByName(Scanner scanner) {
        List<Catalog> catalogList = catalogimpl.readFromFile();
        System.out.println("Nhập tên danh mục muốn tìm:");
        boolean check = false;
        do {
            String searchName = scanner.nextLine();
            if (ShopValiDation.checkEmpty(searchName)) {
                for (Catalog cat : catalogList) {
                    if (cat.getCatalogName().equals(searchName)) {
                        catalogimpl.displayData(cat);
                        check = true;
                        break;
                    }
                }
                if (check) {
                    break;
                }
            } else {
                System.err.println("Không được để trống!!");
            }
        } while (true);

    }
}
