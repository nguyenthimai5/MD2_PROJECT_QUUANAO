package shop.bussiness.impl;

import shop.bussiness.design.ISize;
import shop.bussiness.entity.Size;

import java.util.List;
import java.util.Scanner;

public class Sizeimpl implements ISize<Size,Integer> {
    @Override
    public boolean create(Size size) {
        return false;
    }

    @Override
    public Size input(Scanner scanner) {
        return null;
    }

    @Override
    public void displayData(Size size) {

    }

    @Override
    public boolean update(Size size) {
        return false;
    }


    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public List<Size> readFromFile() {
        return null;
    }

    @Override
    public boolean writeToFile(List<Size> list) {
        return false;
    }
}
