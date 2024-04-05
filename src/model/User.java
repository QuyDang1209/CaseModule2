package model;

import utils.DateTimeUtil;

import java.time.LocalDate;

public class User {
    private String name;
    private String password;
    private LocalDate dob;
    private EUser role;
    public User() {
    }

    public User(String name, String password, LocalDate dob, EUser role) {
        this.name = name;
        this.password = password;
        this.dob = dob;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public EUser getRole() {
        return role;
    }

    public void setRole(EUser role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s",this.name,this.password, DateTimeUtil.formatDate(this.dob),(this.role) +"\n");
    }
}
