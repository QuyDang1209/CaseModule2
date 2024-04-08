package model;

public class OderList {
    private long id;
    private String name;
    private int quanity;
    private  int price;
    private int total = price*quanity;

    public OderList() {
    }

    public OderList(long id, String name, int quanity, int price,int total) {
        this.id = id;
        this.name = name;
        this.quanity = quanity;
        this.price = price;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s",this.id,this.name,this.quanity,this.price,this.total+"\n");
    }
}
