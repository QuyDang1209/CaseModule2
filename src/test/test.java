package test;

import model.Products;
import model.User;
import utils.FileUtils;
import view.ProductsView;
import view.UsersView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        UsersView usersView = new UsersView();
        List<User> users = usersView.readFile();
        if(users == null){
            users = new ArrayList<>();
        }
//        usersView.addNewUser(users);
        usersView.findUsers(users);
    }
}
