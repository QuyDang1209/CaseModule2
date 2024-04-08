package view;

import model.ECategory;
import model.EUser;
import model.Products;
import model.User;
import utils.DateTimeUtil;
import utils.ValidateUtil;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsersView {
    private static final String filePath = "./data/users.txt";
//    private List<User> users =new ArrayList<>();

    public static void writeFile(List<User> users) {
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

    public static List<User> readFile() {
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
                u.setEmail(items[3]);
                u.setRole(EUser.findEUserbyStr(items[4].trim()));
                users.add(u);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public static void addUser(List<User> users) {

        Scanner scanner = new Scanner(System.in);
        String name = checkInputValid(ValidateUtil.FIELD_NAME, ValidateUtil.FIELD_NAME_MESSAGE, ValidateUtil.REGEX_USERNAME );
        System.out.println("Nhập password");
        String pass = scanner.nextLine();
        System.out.println("Nhập ngày tháng năm sinh");
        LocalDate dob = DateTimeUtil.parse(scanner.nextLine());

        String email = checkInputValid(ValidateUtil.FIELD_EMAIL,ValidateUtil.FIELD_EMAIL_MESSAGE,ValidateUtil.REGEX_EMAIL);
        System.out.println("Nhập tên đăng kí");
        for (EUser e : EUser.values()) {
            System.out.println(e);
        }
        EUser role = EUser.findEUserbyStr(scanner.nextLine());
        users.add(new User(name, pass, dob, email, role));
    }
    private static String checkInputValid(String fieldName, String fieldMessage, String fieldPattern){
        Scanner scanner = new Scanner(System.in);
        String input = null;
        boolean validateInput = false;
        do {
            System.out.printf("Nhập %s: \n", fieldName);
            input = scanner.nextLine();
            if (!ValidateUtil.isValid(fieldPattern, input)) {         // Nếu SAI
                System.out.println(fieldMessage);
                validateInput = true;
            } else {
                validateInput = false;
            }
        } while (validateInput);
        return input;
    }

    public static void addNewUser(List<User> users) {
        addUser(users);
        writeFile(users);
    }

    public static void logginAccount(List<User> users) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên tài khoản");
        String logginName = scanner.nextLine();
        System.out.println("Nhập tên mật khẩu");
        String logginPass = scanner.nextLine();
        System.out.println("Bạn có phải là ADMIN không (Y/N)");
        String check = scanner.nextLine();
        for (User u : users) {
            if (u.getName().equals(logginName) && u.getPassword().equals(logginPass) ) {
                if (check.equals("Y")) {
                    if (u.getRole().toString().equals("ADMIN")) {
                        ProductsView productsView = new ProductsView();
                        productsView.addMenuProducts();
                    } else {
                        System.out.println("Vui lòng đăng nhập lại, quyền truy cập của bạn không đúng");
                    }
                } else {
                    if (u.getRole().toString().equals("USER")){
                        ProductsView productsView = new ProductsView();
                        productsView.addUserProduct();
                    }
                    else {
                        System.out.println("Vui lòng đăng nhập lại, quyền truy cập của bạn không đúng");
                    }
                }
            }
        }

    }

    public static void menuUser() {
        UsersView usersView = new UsersView();
        List<User> users = usersView.readFile();
        Scanner scanner = new Scanner(System.in);
        boolean check = true;
        while (check) {
            System.out.println("---------MENU LOGGIN----------");
            System.out.println("Chọn tác vụ bạn muốn thực hiện ");
            System.out.println("1. Đăng nhập tài khoản");
            System.out.println("2. Đăng ký mới tài khoản");
            System.out.println("0. Thoát");
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1:
                    logginAccount(users);
                    break;
                case 2:
                    addNewUser(users);
                    break;
                case 0:
                    check = false;
                    break;
            }

        }
    }

}
