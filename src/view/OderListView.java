package view;

import model.Oder;
import model.OderList;
import utils.DateTimeUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OderListView {
    private static final String filePath = "./data/oderList.txt";
    public static List<OderList> readFile() {
        List<OderList> oderLists = new ArrayList<>();

        try {

            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] items = line.split(",");
                OderList u = new OderList();
                u.setId(Long.parseLong(items[0]));
                u.setName((items[1]));
                u.setQuanity((Integer.parseInt(items[2])));
                u.setPrice(Integer.parseInt(items[3]));
                u.setTotal(Integer.parseInt(items[4]));


                oderLists.add(u);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return oderLists;
    }
    public static void writeFile(List<OderList> oderLists) {
        File file = new File(filePath);

        try {
            PrintWriter printWriter = new PrintWriter(file);
            for (OderList p : oderLists) {
                printWriter.write(p.toString());

            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
