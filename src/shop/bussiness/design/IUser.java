package shop.bussiness.design;

import shop.bussiness.entity.Catalog;
import shop.bussiness.entity.Product;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public interface IUser<T,E> extends IShop<T,E> {
    void displayProductByCatalog(List<Catalog> list);
    void displayProductByDate(List<Product> list);
    void searchByProductByDisscount(List<Product> list);
    Product searchByProductName(String productName);
    Product searchByCatalogName(String catalogName);
    List<Product> searchProductByExportPrice(float minExprortPrice,float maxExportPrice);
    List<Product> searchProductByDiscount(float minDiscountPrice,float maxDiscountPrice);
    boolean register(Scanner scanner);
    boolean login(Scanner scanner);
    boolean changePassWord(Scanner scanner);
}
