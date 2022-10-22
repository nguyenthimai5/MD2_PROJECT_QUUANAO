package shop.bussiness.impl;

import shop.bussiness.design.IProduct;
import shop.bussiness.entity.Product;

import java.util.List;
import java.util.Scanner;

public class Productimpl implements IProduct<Product,Integer> {
    @Override
    public boolean create(Product product) {
        return false;
    }

    @Override
    public Product input(Scanner scanner) {
        return null;
    }

    @Override
    public void displayData(Product product) {

    }

    @Override
    public boolean update(Product product) {
        return false;
    }


    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public List<Product> readFromFile() {
        return null;
    }

    @Override
    public boolean writeToFile(List<Product> list) {
        return false;
    }
}
