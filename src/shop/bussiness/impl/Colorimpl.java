package shop.bussiness.impl;

import shop.bussiness.design.IColor;
import shop.bussiness.entity.Color;
import shop.config.ShopConstant;
import shop.config.ShopMessage;
import shop.config.ShopValiDation;
import shop.data.Fileimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Colorimpl implements IColor<Color,Integer> {
    @Override
    public boolean create(Color color) {
        List<Color> colorList = readFromFile();
        if (colorList == null) {
            colorList = new ArrayList<>();
        }
        colorList.add(color);
        boolean result=writeToFile(colorList);
        return result;
    }

    @Override
    public Color input(Scanner scanner) {
        List<Color> colorList = readFromFile();
        if (colorList == null) {
            colorList = new ArrayList<>();
        }
        Color colorNew=new Color();
        if (colorList.size() == 0) {
            colorNew.setColorId(1);
        } else {
            int maxId = 0;
            for (Color color:colorList) {
                if (color.getColorId()>maxId) {
                    maxId = color.getColorId();
                }
            }
            colorNew.setColorId(maxId+1);
        }
        boolean checkValiDateName = true;
        do {
            String colorNameNew=scanner.nextLine();
            if (ShopValiDation.checkEmpty(colorNameNew)){
                if (ShopValiDation.checkLenght(colorNameNew,4,30)){
                    for (Color color:colorList) {
                        if (colorNameNew.equals(color.getColorName())) {
                            checkValiDateName = false;
                            break;
                        }

                    }
                    if (checkValiDateName) {
                        colorNew.setColorName(colorNameNew);
                        break;
                    } else {
                        System.err.println(ShopMessage.CHECK_VALIDATE_NAME_COLOR);
                    }
                }else {
                    System.err.println(ShopMessage.CHECK_LENGH_NAME_COLOR);
                }
            }else {
                System.err.println(ShopMessage.CHECK_EMPTY_NAME_COLOR);
            }
        } while (true);
        System.out.println("Trạng thái màu sắc:");
        System.out.println("1.Hoạt động");
        System.out.println("2.Không hoạt động");
        System.out.print("Sự lựa chọn của bạn:");
        int choice = 0;
        ShopValiDation.checkNumberInt(scanner, choice);
        switch (choice) {
            case 1:
                colorNew.setColorStatus(true);
                break;
            case 2:
                colorNew.setColorStatus(false);
                break;
            default:
                System.err.println(ShopMessage.CHECK_CHOICE1_2);

        }
        return colorNew;
    }

    @Override
    public void displayData(Color color) {
        String colorStatus="Không hoạt động";
        if (color.isColorStatus()){
            colorStatus="Hoạt động";
        }
        System.out.printf("%-20d%-20s%-20b",color.getColorId(),color.getColorName(),color.isColorStatus());
    }

    @Override
    public boolean update(Color color) {
        List<Color> colorList=readFromFile();
        return false;
    }


    @Override
    public boolean delete(Integer id) {
        List<Color> colorList=readFromFile();
        boolean check=false;
        for (Color color:colorList) {
            if (color.getColorId()==id){
                color.setColorStatus(!color.isColorStatus());
                check=true;
                break;
            }
        }
        if (check){
            System.out.println(ShopMessage.CHECK_DELETE_COLOR_TRUE);
        }else {
            System.err.println(ShopMessage.CHECK_DELETE_COLOR_FASLE);
        }
        return check;

    }

    @Override
    public List<Color> readFromFile() {
        Fileimpl fileimpl=new Fileimpl();
        return fileimpl.readFrommFile(ShopConstant.URL_COLOR);
    }

    @Override
    public boolean writeToFile(List<Color> list) {
        Fileimpl fileimpl=new Fileimpl();
        return fileimpl.writeToFile(list,ShopConstant.URL_COLOR);
    }
}
