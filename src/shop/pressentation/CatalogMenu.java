package shop.pressentation;

import shop.bussiness.entity.Catalog;
import shop.bussiness.entity.User;
import shop.bussiness.impl.Catalogimpl;
import shop.bussiness.impl.Userimpl;
import shop.config.ShopMessage;
import shop.config.ShopValiDation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CatalogMenu {
    private static Catalogimpl catalogimpl = new Catalogimpl();
    private static List<Catalog> catalogList = catalogimpl.readFromFile();

    public static void catalogManager(Scanner scanner) {
        boolean checkExit = true;
        do {
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~QUẢN LÝ DANH MỤC~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("|1.Danh sách danh mục theo cây danh mục");
            System.out.println("|2.Tạo mới danh mục");
            System.out.println("|3.Cập nhật danh mục");
            System.out.println("|4.Xóa danh mục");
            System.out.println("|5.Tìm kiếm danh mục theo tên");
            System.out.println("|6.Thoát");
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
        System.out.printf("%-20s%-30s%-40s%-20s%-50s\n", "Mã danh mục", "Tên danh mục", "Mô tả danh mục", "Trạng thái danh mục", "Danh mục ");
        for (Catalog cata : catalogList) {
            catalogimpl.displayData(cata);
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
            System.out.printf("Nhập thông tin cho danh mục thứ %d\n", i + 1);
            Catalog catalog = catalogimpl.input(scanner);
            catalogimpl.create(catalog);
        }

    }

    public static void updateCatalog(Scanner scanner) {
        System.out.println("Nhập tên danh mục muốn cập nhật: ");
        scanner.nextLine();
        String updateCatalog = scanner.nextLine();
        boolean exitCatalog = false;
        for (Catalog catalog : catalogList) {
            if (catalog.getCatalogName().equals(updateCatalog)) {
                catalog.getCatalogId();
                System.out.print("Tên danh mục:");
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
                                catalog.setCatalogName(catalogName);
                                break;
                            } else {
                                System.err.println(ShopMessage.CHECK_VALIDATE_NAME_CATALOG);
                            }
                        } else {
                            System.err.println(ShopMessage.CHECK_LENGH_NAME_CATALOG);
                        }
                    }
                    break;
                } while (true);
                System.out.print("Mô tả danh mục:");
                do {
                    String catalogDescriptions = scanner.nextLine();
                    if (ShopValiDation.checkEmpty(catalogDescriptions)) {
                        catalog.setCatalogDescriptions(catalogDescriptions);
                        break;
                    }
                    break;
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
                        catalog.setCatalogStatus(true);
                        break;
                    case 2:
                        catalog.setCatalogStatus(false);
                        break;
                    case 3:
                        catalog.isCatalogStatus();
                        break;
                    default:
                        System.err.println(ShopMessage.CHECK_CHOICE1_3);

                }
                List<Catalog> catalogOn = new ArrayList<>();
                for (Catalog cat : catalogList) {
                    if (cat.getCatalog() == null && cat.isCatalogStatus()) {
                        catalogimpl.displayListCatalog(cat, catalogList, 0);
                        catalogOn.add(cat);
                    }
                }
                System.out.print("Chọn danh mục mà sản phẩm thuộc về:");
                int choiceCatalog = 0;

                try {
                    choiceCatalog = Integer.parseInt(scanner.nextLine());

                } catch (NumberFormatException exception) {
                    System.err.println(ShopMessage.CHECK_NUMBER);
                }

                catalog.setCatalog(catalogList.get(choiceCatalog - 1));
                catalogimpl.create(catalog);
                if (!exitCatalog) {
                    System.out.printf(" Danh mục %s khong co trong du lieu\n", updateCatalog);
                }
            }
            System.out.println(ShopMessage.CHECK_UPDATE);
        }
    }

    public static void deleteCatalog(Scanner scanner) {
        System.out.print("Nhập mã danh mục mà quý khách muốn xoá:");
        int idCatalog = 0;
        do {
            String string=scanner.nextLine();
            try {
                if (ShopValiDation.checkEmpty(string)){
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
        System.out.println("Nhập tên danh mục muốn tìm:");
        String searchName = scanner.nextLine();
        for (Catalog cat : catalogList) {
            if (cat.getCatalogName().trim().contains(searchName)) {
                catalogimpl.displayData(cat);
            }
        }
    }
}