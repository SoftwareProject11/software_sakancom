package model.classes;

import java.util.Date;

public class Tenant {
    private int idtenant;
//    private String username;
//    private String password;
    private String name;
    private int phone;
    private String email;
    private int id_apart;
//    private Date time_to_pay;
    private int id_house;

//    public Tenant() {
//    }

    public Tenant(int id_house, int id_apart,int idtenant, String name, int phone, String email ) {
        this.idtenant = idtenant;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.id_apart = id_apart;
        this.id_house = id_house;
    }

    public int getIdtenant() {
        return idtenant;
    }

//    public String getUsername() {
//        return username;
//    }
//
//    public String getPassword() {
//        return password;
//    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getId_apart() {
        return id_apart;
    }

//    public Date getTime_to_pay() {
//        return time_to_pay;
//    }

    public int getId_house() {
        return id_house;
    }
}
