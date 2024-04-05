package view;

import model.ECategory;
import model.Products;
import utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductsView {
    public void addMenuProducts() {
        List<Products> product = FileUtils.readFile();
        if (product == null) {
            product = new ArrayList<>();
        }

        boolean check = true;
        while (check) {
            System.out.println("Nhập tác vụ muốn chon");
            System.out.println("1. Xem danh sách sản phẩm");
            System.out.println("2. Nhập sản phẩm mới");
            System.out.println("3. Sửa sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Tìm kiếm sản phẩm");
            System.out.println("6. Lên đơn hàng");
            System.out.println("0. Thoát");
            Scanner scanner = new Scanner(System.in);
            int choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    showListProduct(product);
                    break;
                case 2:
                    addNewProducts(product);
                    break;
                case 3:
                    changeProducts(product);
                    break;
                case 4:
                    deleteProducts(product);
                    break;
                case 5:
                    findProducts(product);
                    break;
                case 0:
                    check = false;
                    break;
            }
        }


    }

    private void addNewProducts(List<Products> product) {
        addProduct(product);
        FileUtils.writeFile(product);
    }

    public void addProduct(List<Products> products) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(" Nhập id ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print(" Nhập tên sản phẩm: ");
        String name = scanner.nextLine();
        System.out.print(" Nhập size sản phẩm: ");
        String size = scanner.nextLine();
        System.out.print(" Nhập giá sản phẩm: ");
        int cost = Integer.parseInt(scanner.nextLine());
        System.out.print(" Nhập số lượng sản phẩm: ");
        int quanity = Integer.parseInt(scanner.nextLine());
        System.out.println(" Nhập danh mục: ");
        for (ECategory eCategory : ECategory.values()) {
            System.out.println(eCategory.getName() + " : " + eCategory.getId());
        }
        int  icategory = (Integer.parseInt(scanner.nextLine()));
        ECategory category = ECategory.findById((icategory));
        products.add(new Products(id, name, size, cost, quanity, category));

    }
    public void showListProduct(List<Products> products) {
        System.out.printf("%5s | %10s | %15s | %10s | %10s | %10s \n", "ID", "Name", "SIZE", "COST", "QUANITY", "CATEGORY");
        for (Products p : products) {
            System.out.printf("%5s | %10s | %15s | %10s | %10s |%10s \n", p.getId(), p.getName(), p.getSize(), p.getCost(), p.getQuanity(), p.getCategory());
        }

    }
    public void changeProducts(List<Products> products){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên sản phẩm muốn sửa: ");
        String change = scanner.nextLine();
        for(Products p : products){
            if(p.getName().equals(change)){
                System.out.println("Nhập id mới" + p.getId());
                int id = Integer.parseInt(scanner.nextLine());
                System.out.println("Nhập tên mới của sản phẩm "+p.getName());
                String name = scanner.nextLine();
                System.out.println("Nhập Size mới của sản phẩm " +p.getSize());
                String size = scanner.nextLine();
                System.out.println("Nhập giá mới của sản phẩm " + p.getCost());
                int cost = Integer.parseInt(scanner.nextLine());
                System.out.println("Nhập số lượng mới của sản phẩm " + p.getQuanity());
                int quanity = Integer.parseInt(scanner.nextLine());
                System.out.println("Nhập đối tượng mới của sản phẩm " + p.getCategory());
                ECategory category = ECategory.finByStr(scanner.nextLine());
                p.setId(id);
                p.setName(name);
                p.setSize(size);
                p.setCost(cost);
                p.setQuanity(quanity);
                p.setCategory(category);
                break;

            }
        }
    }
    public  void deleteProducts(List<Products> products){
        System.out.println("Nhập sản phẩm muốn xóa");
        Scanner scanner = new Scanner(System.in);
        String delete = scanner.nextLine();
        for(Products p: products){
            if(p.getName().equals(delete)){
                products.remove(p);
                break;
            }
        }
    }
    public  void findProducts(List<Products> products){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập sản phẩm muốn tìm");
        String find = scanner.nextLine();
        List<Products> result = new ArrayList<>();
        for (Products p: products){
            if(p.getName().equals(find)){
                result.add(p);
            }
        }
        if(result == null){
            System.out.println("Không tìm thấy sản phẩm");
        }
        else {
            showListProduct(result);
        }
    }
}
