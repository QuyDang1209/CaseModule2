package model;

public enum ECategory {
    MALE(1,"MALE"), FELMALE(2,"FELMALE"), CHILDREN(3,"CHILDREN");
    private int id;
    private String name;

    private ECategory(int id, String name) {
        this.id = id;
        this.name = name;
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
}
