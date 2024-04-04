package view;

import model.Products;
import utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductsView {
    public static void addMenuProducts() {
        List<Products> product = FileUtils.readFile();
        if (product == null) {
            product = new ArrayList<>();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tác vụ muốn chon");
        System.out.println("1. Xem danh sách sản phẩm");
        System.out.println("2. Nhập sản phẩm mới");
        System.out.println("3. Sửa sản phẩm");
        System.out.println("4. Xóa sản phẩm");
        System.out.println("5. Lên đơn hàng");
        System.out.println("0. Thoát");
        int choose = scanner.nextInt();
        boolean check = true;
        while (check) {
            switch (choose) {
                case 1:
                    FileUtils.showListProduct(product);
                    break;
                case 2:
                    FileUtils.addProduct(product);
                    FileUtils.writeFile(product);
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 0:
                    check = false;
                    break;
            }
        }


    }
}
