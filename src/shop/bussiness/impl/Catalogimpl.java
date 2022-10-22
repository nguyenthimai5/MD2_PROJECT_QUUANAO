package shop.bussiness.impl;

import shop.bussiness.design.ICataLog;
import shop.bussiness.design.IShop;
import shop.bussiness.entity.Catalog;
import shop.config.ShopConstant;
import shop.config.ShopMessage;
import shop.config.ShopValiDation;
import shop.data.Fileimpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Catalogimpl implements ICataLog<Catalog, Integer> {

    @Override
    public Catalog searchByName(Scanner scanner) {
        return null;
    }

    @Override
    public boolean create(Catalog catalog) {
        List<Catalog> catalogList = readFromFile();
        if (catalogList == null) {
            catalogList = new ArrayList<>();
        }
        catalogList.add(catalog);
        boolean result = writeToFile(catalogList);
        return result;
    }

    @Override
    public Catalog input(Scanner scanner) {
        List<Catalog> catalogList = readFromFile();
        if (catalogList == null) {
            catalogList = new ArrayList<>();
        }
        Catalog catalogNew = new Catalog();
        if (catalogList.size() == 0) {
            catalogNew.setCatalogId(1);
        } else {
            int maxId = 0;
            for (Catalog cata : catalogList) {
                if (cata.getCatalogId() > maxId) {
                    maxId = cata.getCatalogId();
                }
            }
            catalogNew.setCatalogId(maxId + 1);
        }
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
                        catalogNew.setCatalogName(catalogName);
                        break;
                    } else {
                        System.err.println(ShopMessage.CHECK_VALIDATE_NAME_CATALOG);
                    }
                } else {
                    System.err.println(ShopMessage.CHECK_LENGH_NAME_CATALOG);
                }
            } else {
                System.err.println(ShopMessage.CHECK_EMPTY_NAME_CATALOG);
            }
        } while (true);
        System.out.print("Mô tả danh mục:");
        do {
            String catalogDescriptions = scanner.nextLine();
            if (ShopValiDation.checkEmpty(catalogDescriptions)) {
                catalogNew.setCatalogDescriptions(catalogDescriptions);
                break;
            } else {
                System.err.println(ShopMessage.CHECK_LENGH_DECRIPTION_CATALOG);
            }
        } while (true);
        System.out.println("Trạng thái danh mục:");
        System.out.println("1.Hoạt động");
        System.out.println("2.Không hoạt động");
        System.out.print("Sự lựa chọn của bạn:");
        int choice = 0;
        ShopValiDation.checkNumber(scanner, choice);
        switch (choice) {
            case 1:
                catalogNew.setCatalogStatus(true);
                break;
            case 2:
                catalogNew.setCatalogStatus(false);
                break;
            default:
                System.err.println(ShopMessage.CHECK_CHOICE1_2);

        }
        List<Catalog> catalogOn = null;
        for (Catalog cat : catalogList) {
            if (cat.getCatalog() == null && cat.isCatalogStatus()) {
                displayListCatalog(cat, catalogList, 0);
                catalogOn.add(cat);
            }
        }
        System.out.print("Chọn danh mục mà sản phẩm thuộc về:");
        int choiceCatalog = 0;
        ShopValiDation.checkNumber(scanner, choiceCatalog);
        catalogNew.setCatalog(catalogList.get(choiceCatalog - 1));


        return catalogNew;
    }

    @Override
    public void displayData(Catalog catalog) {
        String status = "Không hoạt động!";
        if (catalog.isCatalogStatus()) {
            status = "Hoạt động!";
        }
        System.out.printf("%-20d%-30s%-40s%-20b%-50s\n", catalog.getCatalogId(), catalog.getCatalogName(), catalog.getCatalogDescriptions(), catalog.isCatalogStatus(), catalog.getCatalog());
    }


    @Override
    public boolean update(Catalog catalog) {
        List<Catalog> catalogList = readFromFile();
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        List<Catalog> catalogList = readFromFile();
        boolean check = false;
        for (Catalog cat : catalogList) {
            if (cat.getCatalogId() == id) {
                cat.setCatalogStatus(!cat.isCatalogStatus());
                check = true;
                break;
            }
        }
        if (check) {
            System.out.println(ShopMessage.CHECK_STATUS_UPDATE_TRUE);
        } else {
            System.err.println(ShopMessage.CHECK_STATUS_UPDATE_FASLE);
        }
        return check;
    }

    @Override
    public List<Catalog> readFromFile() {
        Fileimpl fileimpl = new Fileimpl();
        return fileimpl.readFrommFile(ShopConstant.URL_Catalog);

    }

    @Override
    public boolean writeToFile(List<Catalog> list) {
        Fileimpl fileimpl = new Fileimpl();
        return fileimpl.writeToFile(list, ShopConstant.URL_Catalog);
    }

    public static void displayListCatalog(Catalog root, List<Catalog> catalogList, int count) {
        for (int i = 0; i < count; i++) {
            System.out.printf("\t");
        }
        System.out.printf("%d.%s\n", root.getCatalogId(), root.getCatalogName());
        List<Catalog> listChild = new ArrayList<>();
        for (Catalog catalogs : catalogList) {
            if (catalogs.getCatalog() != null && catalogs.getCatalog().getCatalogId() == root.getCatalogId()) {
                listChild.add(catalogs);
            }
        }
        if (listChild.size() != 0) {
            count++;
        }
        for (Catalog cata : listChild) {
            displayListCatalog(cata, catalogList, count);

        }
    }
}
