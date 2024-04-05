package view;

import model.ECategory;
import model.EUser;
import model.Products;
import model.User;
import utils.DateTimeUtil;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsersView {
    private static final String filePath = "./data/users.txt";


    public void writeFile(List<User> users) {
        File file = new File(filePath);

        try {
            PrintWriter printWriter = new PrintWriter(file);
            for (User p : users) {
                printWriter.write(p.toString());

            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> readFile() {
        List<User> users = new ArrayList<>();
        try {

            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] items = line.split(",");
                User u = new User();
                u.setName(items[0]);
                u.setPassword(items[1]);
                u.setDob((DateTimeUtil.parse(items[2])));
                u.setRole(EUser.findEUserbyStr(items[3].trim()));
                users.add(u);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void addUser(List<User> users) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên đăng ký");
        String name = scanner.nextLine();
        System.out.println("Nhập password đăng ký");
        String password = scanner.nextLine();
        System.out.println("Nhập ngày tháng năm sinh");
        LocalDate dob = DateTimeUtil.parse(scanner.nextLine());
        System.out.println("Nhập tên đăng kí");
        for (EUser e : EUser.values()) {
            System.out.println(e);
        }
        EUser role = EUser.findEUserbyStr(scanner.nextLine());
        users.add(new User(name, password, dob, role));
    }

    public void addNewUser(List<User> users) {
        addUser(users);
        writeFile(users);
    }

    public void logginAccount(List<User> users) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên tài khoản");
        String logginName = scanner.nextLine();
        System.out.println("Nhập tên mật khẩu");
        String logginPass = scanner.nextLine();
        List<User> result = new ArrayList<>();
        for (User u : users) {
            if (u.getName().equals(logginName) && u.getPassword().equals(logginPass)) {
                result.add(u);
            }
            else {
                ProductsView productsView = new ProductsView();
                productsView.addMenuProducts();
            }
        }

    }
}
