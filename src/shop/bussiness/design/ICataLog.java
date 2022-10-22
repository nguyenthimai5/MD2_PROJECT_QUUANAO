package shop.bussiness.design;

import java.util.Scanner;

public interface ICataLog<T,E> extends IShop<T,E> {
   T searchByName(Scanner scanner);
}
