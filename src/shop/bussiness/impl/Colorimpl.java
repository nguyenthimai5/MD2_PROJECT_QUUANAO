package shop.bussiness.impl;

import shop.bussiness.design.IColor;
import shop.bussiness.entity.Color;

import java.util.List;
import java.util.Scanner;

public class Colorimpl implements IColor<Color,Integer> {
    @Override
    public boolean create(Color color) {
        return false;
    }

    @Override
    public Color input(Scanner scanner) {
        return null;
    }

    @Override
    public void displayData(Color color) {

    }

    @Override
    public boolean update(Color color) {
        return false;
    }


    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public List<Color> readFromFile() {
        return null;
    }

    @Override
    public boolean writeToFile(List<Color> list) {
        return false;
    }
}
