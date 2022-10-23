package shop.bussiness.impl;

import shop.bussiness.design.ISize;
import shop.bussiness.entity.Size;
import shop.config.ShopConstant;
import shop.config.ShopMessage;
import shop.config.ShopValiDation;
import shop.data.Fileimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sizeimpl implements ISize<Size,Integer> {
    @Override
    public boolean create(Size size) {
        List<Size> sizeList=readFromFile();
        if (sizeList == null) {
            sizeList = new ArrayList<>();
        }
        sizeList.add(size);
        boolean result=writeToFile(sizeList);
        return result;
    }

    @Override
    public Size input(Scanner scanner) {
            List<Size> sizeList=readFromFile();

        if (sizeList == null) {
            sizeList = new ArrayList<>();
        }
        Size sizeNew=new Size();
        if (sizeList.size() == 0) {
            sizeNew.setSizeId(1);
        } else {
            int maxId = 0;
            for (Size size:sizeList) {
                if (size.getSizeId()>maxId){
                    maxId=size.getSizeId();
                }
            }
            sizeNew.setSizeId(maxId+1);
        }
        boolean checkValiDateName = true;
        do {
            String sizeNameNew=scanner.nextLine();
            if (ShopValiDation.checkEmpty(sizeNameNew)){
                if (ShopValiDation.checkLenght(sizeNameNew,1,10)){
                    for (Size size:sizeList) {
                        if (sizeNameNew.equals(size.getSizeName())) {
                            checkValiDateName = false;
                            break;
                        }

                    }
                    if (checkValiDateName) {
                       sizeNew.setSizeName(sizeNameNew);
                        break;
                    } else {
                        System.err.println(ShopMessage.CHECK_VALIDATE_NAME_SIZE);
                    }
                }else {
                    System.err.println(ShopMessage.CHECK_LENGH_NAME_SIZE);
                }
            }else {
                System.err.println(ShopMessage.CHECK_EMPTY_NAME_SIZE);
            }
        } while (true);
        System.out.println("Trạng thái kích cỡ:");
        System.out.println("1.Hoạt động");
        System.out.println("2.Không hoạt động");
        System.out.print("Sự lựa chọn của bạn:");
        int choice=0;
        try {
            choice = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException exception) {
            System.err.println(ShopMessage.CHECK_NUMBER);
        }

        switch (choice) {
            case 1:
                sizeNew.setSizeStatus(true);
                break;
            case 2:
                sizeNew.setSizeStatus(false);
                break;
            default:
                System.err.println(ShopMessage.CHECK_CHOICE1_2);

        }
        return sizeNew;
    }

    @Override
    public void displayData(Size size) {
        String sizeStatus="Không hoạt động";
        if (size.isSizeStatus()){
            sizeStatus="Hoạt động";
        }
        System.out.printf("%-20d%-20s%-20b",size.getSizeId(),size.getSizeName(),size.isSizeStatus());
    }


    @Override
    public boolean update(Size size) {
        List<Size> sizeList=readFromFile();
        boolean returnSize=false;
        for (int i = 0; i < sizeList.size(); i++) {
            if (sizeList.get(i).getSizeId()==size.getSizeId()){
                sizeList.set(i,size);
                returnSize=true;
                break;
            }
        }boolean result=writeToFile(sizeList);
        if (returnSize&&result){
            return true;
        }
        return false;
    }


    @Override
    public boolean delete(Integer id) {
        List<Size> sizeList=readFromFile();

        boolean check=false;
        for (Size size:sizeList) {
            if (size.getSizeId()==id){
                size.setSizeStatus(!size.isSizeStatus());
                check=true;
                break;
            }
        }
        boolean result=writeToFile(sizeList);
        if (check&&result){
            System.out.println(ShopMessage.CHECK_DELETE_SIZE_TRUE);
            return true;
        }
            System.err.println(ShopMessage.CHECK_DELETE_SIZE_FASLE);
            return false;

    }

    @Override
    public List<Size> readFromFile() {
        Fileimpl fileimpl=new Fileimpl();
        return fileimpl.readFrommFile(ShopConstant.URL_SIZE);
    }

    @Override
    public boolean writeToFile(List<Size> list) {
        Fileimpl fileimpl=new Fileimpl();
        return fileimpl.writeToFile(list,ShopConstant.URL_SIZE);
    }
}
