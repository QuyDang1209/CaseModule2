package model;

public class Products {
    private int id;
    private String name;
    private String size;
    private int cost;
    private int quanity;
    private ECategory category;

    public Products() {
    }

    public Products(int id, String name, String size, int cost, int quanity, ECategory category) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.cost = cost;
        this.quanity = quanity;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public ECategory getCategory() {
        return category;
    }

    public void setCategory(ECategory category) {
        this.category = category;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }
}
