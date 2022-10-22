package shop.bussiness.impl;

import shop.bussiness.design.IUser;
import shop.bussiness.entity.Catalog;
import shop.bussiness.entity.Product;
import shop.bussiness.entity.User;

import java.util.List;
import java.util.Scanner;

public class Userimpl implements IUser<User,Integer> {
    @Override
    public boolean create(User user) {
        return false;
    }

    @Override
    public User input(Scanner scanner) {
        return null;
    }

    @Override
    public void displayData(User user) {

    }

    @Override
    public boolean update(User user) {
        return false;
    }


    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public List<User> readFromFile() {
        return null;
    }

    @Override
    public boolean writeToFile(List<User> list) {
        return false;
    }

    @Override
    public void displayProductByCatalog(List<Catalog> list) {

    }

    @Override
    public void displayProductByDate(List<Product> list) {

    }

    @Override
    public void searchByProductByDisscount(List<Product> list) {

    }

    @Override
    public Product searchByProductName(String productName) {
        return null;
    }

    @Override
    public Product searchByCatalogName(String catalogName) {
        return null;
    }

    @Override
    public List<Product> searchProductByExportPrice(float minExprortPrice, float maxExportPrice) {
        return null;
    }

    @Override
    public List<Product> searchProductByDiscount(float minDiscountPrice, float maxDiscountPrice) {
        return null;
    }

    @Override
    public boolean register() {
        return false;
    }

    @Override
    public boolean login() {
        return false;
    }

    @Override
    public boolean changePassWord() {
        return false;
    }
}
