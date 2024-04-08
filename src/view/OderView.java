package view;

import model.*;
import utils.DateTimeUtil;
import utils.FileUtils;

import java.awt.font.FontRenderContext;
import java.io.*;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class OderView {
    private static final String filePath = "./data/oder.txt";
    public static view.OderView OderView;

    public static void writeFile(List<Oder> oders) {
        File file = new File(filePath);

        try {
            PrintWriter printWriter = new PrintWriter(file);
            for (Oder p : oders) {
                printWriter.write(p.toFile());

            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void writeFile(Oder oder) {
        File file = new File(filePath);

        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.write(oder.toFile());

            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Oder> readFile() {
        List<Oder> oders = new ArrayList<>();

        try {

            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] items = line.split(",");
                Oder u = new Oder();
                u.setId(Long.parseLong(items[0]));
                u.setCreate(DateTimeUtil.parse(items[1]));
                u.setName(((items[2])));
                u.setAdress(items[3]);
                u.setPhone((items[4]));

                // items[5]: [{85535,váy,1,350000,350000},{95752,áo polo,1,250000,250000},{107650,quần jean,1,300000,300000}]
                List<OderList> oderLists = parseStrToOderList(items[5]);
                u.setOderLists(oderLists);
                oders.add(u);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return oders;
    }
    public static List<OderList> parseStrToOderList(String str){
        str = str.replaceAll("[\\[\\]\\{\\}]", "");

        String[] productStrings = str.split("\\|");
        // Tạo danh sách để lưu các sản phẩm
        List<OderList> orderList = new ArrayList<>();

        // Duyệt qua từng chuỗi sản phẩm
        for (int i = 0; i < productStrings.length; i += 5) {
            // Lấy thông tin của từng sản phẩm
            int id = Integer.parseInt(productStrings[i]);
            String name = productStrings[i + 1];
            int quantity = Integer.parseInt(productStrings[i + 2]);
            int price = Integer.parseInt(productStrings[i + 3]);
            int total = Integer.parseInt(productStrings[i + 4]);

            // Tạo đối tượng sản phẩm và thêm vào danh sách
            OderList oderList = new OderList(id, name, quantity, price, total);
            orderList.add(oderList);
        }
        return orderList;
    }


    public static void createOder() {
        Scanner scanner = new Scanner(System.in);
        Oder order = new Oder();
        Long id = System.currentTimeMillis() % 10000000;
        LocalDate create = LocalDate.now();
        System.out.println("Nhập tên người mua");
        String name = scanner.nextLine();
        System.out.println("Nhập địa chỉ người mua");
        String address = scanner.nextLine();
        System.out.println("Nhập số điện thoại người mua");
        String phone = (scanner.nextLine());
        order.setId(id);
        order.setCreate(create);
        order.setName(name);
        order.setAdress(address);
        order.setPhone(phone);
        boolean checkContinueOrderItem = false;
        do {
            List<Products> products = FileUtils.readFile();
            System.out.println("Nhập sản phẩm muốn mua");
            String find = scanner.nextLine();
            List<Products> result = new ArrayList<>();
            for (Products p : products) {
                if (p.getName().equals(find)) {
                    result.add(p);
                }
            }
            if (result == null) {
                System.out.println("Không tìm thấy sản phẩm");
            } else {
                ProductsView.showListProduct(result);
            }

            System.out.println("Nhập số lượng: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            System.out.println("Nhập giá: ");
            int price = Integer.parseInt(scanner.nextLine());
            if (order.getOderLists() == null) {
                List<OderList> orderItems = new ArrayList<>();
                OderList orderItem = new OderList(System.currentTimeMillis() % 1000000, find, quantity, price, price * quantity);
                orderItems.add(orderItem);

                order.setOderLists(orderItems);
            } else {
                if (checkProductExistsInOrder(find, order)) {
                    for (OderList ot : order.getOderLists()) {
                        if (ot.getName() == find) {
                            ot.setQuanity(quantity);
                        }
                    }

                } else {
                    OderList orderItem = new OderList(System.currentTimeMillis() % 1000000, find, quantity, price, price * quantity);
                    order.getOderLists().add(orderItem);
                }
            }

            System.out.println("Bạn có muốn thêm tiếp sản phẩm không: Y/N");
            String actionContinueOrderItem = scanner.nextLine();
            switch (actionContinueOrderItem) {
                case "Y" -> {
                    checkContinueOrderItem = true;
                }
                case "N" -> {
                    checkContinueOrderItem = false;
                }
            }
        } while (checkContinueOrderItem);
        List<Oder> oderLists = readFile();
        oderLists.add(order);
        writeFile(oderLists);
    }

    public static boolean checkProductExistsInOrder(String product, Oder oder) {
        if (oder.getOderLists() == null) {
            return false;
        } else {
            for (OderList ot : oder.getOderLists()) {
                if (ot.getName() == product) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void showOrders() {
        //5014352,08-04-2024,quy,hue,02343546,[27784,váy,1,350000,350000
        List<Oder> orders = OderView.readFile();

        for (Oder order : orders) {
            //-- Hien thi thong tin hoa don
            System.out.printf("%10s | %15s | %15s | %15s | %15s\n", "ID", "CREATEAT", "NAME CUSTOMER", "ADDRESS", "PHONE");
            int total = 0;
            System.out.printf("%10s | %15s | %15s | %15s | %15s\n",order.getId(), order.getCreate(), order.getName(), order.getAdress(), order.getPhone());

            //-- Hien thi thong tin chi tiet hoa don
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.printf("%10s | %15s | %15s | %15s | %15s\n", "ID", "NAME ", "QUANITY", "PRICE", "TOTAL");
            for (OderList o : order.getOderLists()) {
                System.out.printf("%10s | %15s | %15s | %15s | %15s\n", o.getId(),o.getName(),o.getQuanity(),o.getPrice(),o.getTotal());
                total += o.getTotal();

            }
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.printf("%10s | %15s | %15s | %15s | %15s\n", "TOTALPRIE","","","",total);
            System.out.printf("\n");
            System.out.printf("\n");


        }
    }
    public static void showOrdersbyname() {
        //5014352,08-04-2024,quy,hue,02343546,[27784,váy,1,350000,350000
        List<Oder> orders = readFile();
        System.out.println("Tên khách hàng");
        Scanner scanner = new Scanner(System.in);
        String orderName = scanner.nextLine();

        System.out.printf("%10s | %15s | %15s | %15s | %15s\n", "ID", "CREATEAT", "NAME CUSTOMER", "ADDRESS", "PHONE");
        for (Oder order : orders) {
            //-- Hien thi thong tin hoa don
            if(orderName.equals(order.getName())) {
                int total = 0;
                System.out.printf("%10s | %15s | %15s | %15s | %15s\n", order.getId(), order.getCreate(), order.getName(), order.getAdress(), order.getPhone());

                //-- Hien thi thong tin chi tiet hoa don
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.printf("%10s | %15s | %15s | %15s | %15s\n", "ID", "NAME ", "QUANITY", "PRICE", "TOTAL");
                for (OderList o : order.getOderLists()) {
                    System.out.printf("%10s | %15s | %15s | %15s | %15s\n", o.getId(), o.getName(), o.getQuanity(), o.getPrice(), o.getTotal());
                    total += o.getTotal();

                }
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.printf("%10s | %15s | %15s | %15s | %15s\n", "TOTALPRIE", "", "", "", total);
            }}
    }


}
