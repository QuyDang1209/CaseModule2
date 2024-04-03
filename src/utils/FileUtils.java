package utils;

import model.Products;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class FileUtils implements Serializable {
    public static void writeFile(List<Products> products){
        File file = new File("product.txt");
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.write(products.add(new Products(id,name,size,cost,quanity,)));
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static File readFile(){

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("product.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNextLine()){
            System.out.println(scanner.nextLine());
        }
        scanner.close();
        return null;
    }

    public static void main(String[] args) {
//        writeFile();
        readFile();
    }

}
