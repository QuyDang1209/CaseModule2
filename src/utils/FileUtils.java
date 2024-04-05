package utils;

import model.ECategory;
import model.Products;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils implements Serializable {
    private static String filePath = "./data/product.txt";

    public static void writeFile(List<Products> products) {
        File file = new File(filePath);

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

            FileReader fileReader = new FileReader(filePath);
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
                p.setCategory(ECategory.finByStr((items[5])));
                products.add(p);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return products;

    }





}
