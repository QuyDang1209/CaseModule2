package model;

import utils.DateTimeUtil;

import java.time.LocalDate;
import java.util.List;

public class Oder {
    private long id;
    private LocalDate create;
    private String name;
    private String adress;
    private String phone;
    List<OderList> oderLists;

    public Oder() {
    }

    public Oder(long id, LocalDate create, String name, String adress, String phone, List<OderList> oderLists) {
        this.id = id;
        this.create = create;
        this.name = name;
        this.adress = adress;
        this.phone = phone;
        this.oderLists = oderLists;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getCreate() {
        return create;
    }

    public void setCreate(LocalDate create) {
        this.create = create;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<OderList> getOderLists() {
        return oderLists;
    }

    public void setOderLists(List<OderList> oderLists) {
        this.oderLists = oderLists;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s",this.id, DateTimeUtil.formatDate(this.create),this.name,this.adress,this.phone,this.oderLists);
    }

    public String toFile() {
        List<OderList> oderLists1 = this.oderLists;
        String str = "[";
        for (int i = 0; i< oderLists1.size() ; i++) {
            OderList item = oderLists1.get(i);
            str += String.format("{%s|%s|%s|%s|%s}", item.getId(),item.getName(),item.getQuanity(),item.getPrice(),item.getTotal());
            if (i != oderLists1.size() - 1) {
                str += "|";
            }
        }
        str += "]";
        return String.format("%s,%s,%s,%s,%s,%s,%s",this.id, DateTimeUtil.formatDate(this.create),this.name,this.adress,this.phone,str,"\n");
    }
}
