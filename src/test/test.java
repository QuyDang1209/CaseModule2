package test;

import model.Products;
import utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        List<Products> products = FileUtils.readFile();
        if (products == null) {
            products = new ArrayList<>();
        }
        FileUtils.addProduct(products);
        FileUtils.writeFile(products);
        FileUtils.showListProduct(products);
    }
}
