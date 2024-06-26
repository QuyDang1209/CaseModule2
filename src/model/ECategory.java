package model;

public enum ECategory {
    MALE(1,"MALE"), FELMALE(2,"FELMALE"), CHILDREN(3,"CHILDREN");
    private  int id;
    private String name;

    private ECategory(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public static ECategory finByStr(String str){
        for (ECategory i : values()){
            if(i.getName().equals(str)){
                return i;
            }
        }
        return null;
    }
    public static ECategory findById(int id) {
        for (ECategory e : values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
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
