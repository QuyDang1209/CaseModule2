package utils;

import model.ECategory;
import model.Products;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils implements Serializable {
    private static Scanner scanner = new Scanner(System.in);

    public static void writeFile(List<Products> products) {
        File file = new File("product.txt");

        try {
            PrintWriter printWriter = new PrintWriter(file);
            for (Products p : products) {
                printWriter.write(p.toString());

            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Products> readFile() {
        List<Products> products = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("product.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                String[] items = line.split(",");
                Products p = new Products();
                p.setId(Integer.parseInt(items[0]));
                p.setName(items[1]);
                p.setSize((items[2]));
                p.setCost(Integer.parseInt(items[3]));
                p.setQuanity(Integer.parseInt(items[4]));
                p.setCategory(ECategory.findById(Integer.parseInt((items[5]))));
                products.add(p);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return products;

    }

    public static void addProduct(List<Products> products) {
        System.out.print(" Nhập id");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print(" Nhập tên sản phẩm");
        String name = scanner.nextLine();
        System.out.print(" Nhập size sản phẩm");
        String size = scanner.nextLine();
        System.out.print(" Nhập giá sản phẩm");
        int cost = Integer.parseInt(scanner.nextLine());
        System.out.print(" Nhập số lượng sản phẩm");
        int quanity = Integer.parseInt(scanner.nextLine());
        System.out.println(" Nhập danh mục");
        for (ECategory eCategory : ECategory.values()) {
            System.out.println(eCategory.getName() + " : " + eCategory.getId());
        }
        String  icategory = ((scanner.nextLine()));
        ECategory category = ECategory.findById(Integer.parseInt(icategory));
        products.add(new Products(id, name, size, cost, quanity, category));

    }

    public static void showListProduct(List<Products> products) {
        System.out.printf("%5s | %10s | %15s | %10s | %10s | %10s \n", "ID", "Name", "SIZE", "COST", "QUANITY", "CATEGORY");
        for (Products p : products) {
            System.out.printf("%5s | %10s | %15s | %10s | %10s |%10s \n", p.getId(), p.getName(), p.getSize(), p.getCost(), p.getQuanity(), p.getCategory());
        }
    }

}
