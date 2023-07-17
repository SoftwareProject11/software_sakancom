package model.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HouseFloor {
    private int id_house;
    private int id_floor;
    private int id_apart;
    private int no_bathrooms;
    private int no_bedrooms;
    private String balcony;

//    public HouseFloor() {
//    }

    public int getId_house() {
        return id_house;
    }

    public int getId_floor() {
        return id_floor;
    }

    public int getId_apart() {
        return id_apart;
    }

    public int getNo_bathrooms() {
        return no_bathrooms;
    }

    public int getNo_bedrooms() {
        return no_bedrooms;
    }

    public String getBalcony() {
        return balcony;
    }

    public HouseFloor(int id_house, int id_floor, int id_apart, int no_bathrooms, int no_bedrooms, String balcony) {
        this.id_house = id_house;
        this.id_floor = id_floor;
        this.id_apart = id_apart;
        this.no_bathrooms = no_bathrooms;
        this.no_bedrooms = no_bedrooms;
        this.balcony = balcony;
    }
    public static boolean findHouseFloorId(int idhouse){
        boolean ret=false;
        try {
            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakancom", "root", "memesa32002@");
            Statement stmt1 = con1.createStatement();
            ResultSet result1 = stmt1.executeQuery("select id_house from house_floor");
            while (result1.next()) {
                if(idhouse==result1.getInt("id_house")){
                    ret= true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }
}
