package test;

import model.Oder;
import view.OderView;
import view.UsersView;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        OderView o = new OderView();

        o.createOder();
        o.showOrders();
    }
}
