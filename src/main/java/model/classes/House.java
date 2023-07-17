package model.classes;

import code.classes.Login;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class House {
    private int id;
    private String link;
    private String location;
    private String services;
    private Double price;
    private int owner_id;
    private int noof_floors;
    private int noof_tenant;

    private static Logger logger = Logger.getLogger(Login.class.getName());

    public House() {
    }
    public House(int id, String link, String location, String services, Double price, int owner_id, int noof_floors) {
        this.id = id;
        this.link = link;
        this.location = location;
        this.services = services;
        this.price = price;
        this.owner_id = owner_id;
        this.noof_floors = noof_floors;
    }
    public House(int id, String link, String location, String services, Double price, int owner_id, int noof_floors, int noof_tenant) {
        this.id = id;
        this.link = link;
        this.location = location;
        this.services = services;
        this.price = price;
        this.owner_id = owner_id;
        this.noof_floors = noof_floors;
        this.noof_tenant = noof_tenant;
    }



//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public void setLink(String link) {
//        this.link = link;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public void setServices(String services) {
//        this.services = services;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public void setOwner_id(int owner_id) {
//        this.owner_id = owner_id;
//    }
//
//    public void setNoof_floors(int noof_floors) {
//        this.noof_floors = noof_floors;
//    }
//
//    public void setNoof_tenant(int noof_tenant) {
//        this.noof_tenant = noof_tenant;
//    }

    public int getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public String getLocation() {
        return location;
    }

    public String getServices() {
        return services;
    }

    public Double getPrice() {
        return price;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public int getNoof_floors() {
        return noof_floors;
    }

    public int getNoof_tenant() {
        return noof_tenant;
    }



    public static boolean findHouseId(int idhouse) throws SQLException {
        boolean ret=false;
//        try {
            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakancom", "root", "memesa32002@");
            Statement stmt1 = con1.createStatement();
            ResultSet result1 = stmt1.executeQuery("select idhouse from house");
            while (result1.next()) {
                if(idhouse==result1.getInt("idhouse")){
                    ret= true;
                    break;
                }
            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        return ret;
    }

    public static void addHouse(House house) throws SQLException {
//        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakancom", "root", "memesa32002@");
            Statement stmt = con.createStatement();
            String insertHouse="INSERT INTO house VALUES('"+house.getId()+"','"+house.getLink()+"','"+house.getLocation()+"','"+house.getServices()+"','"+house.getPrice()+"','"+house.getOwner_id()+"','"+house.getNoof_floors()+"',0)";
            if(!house.findHouseId(house.getId())){
                stmt.executeUpdate(insertHouse);
                logger.info("House added successfully");
            }else {
                logger.info("this id is already exist, please enter another id: ");
            }

            con.close();

//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    public static void addHouseInfo(HouseFloor housef) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakancom", "root", "memesa32002@");
            Statement stmt = con.createStatement();
            String insertHouseInfo="INSERT INTO house_floor VALUES('"+housef.getId_house()+"','"+housef.getId_floor()+"','"+housef.getId_apart()+"','"+housef.getNo_bathrooms()+"','"+housef.getNo_bedrooms()+"','"+housef.getBalcony()+"')";
            stmt.executeUpdate(insertHouseInfo);
            logger.info("House's Information added successfully");

//            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateInfo(String attribute, Object value, Integer house_id) throws SQLException {
//        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakancom", "root", "memesa32002@");
            Statement stmt = con.createStatement();

            if(House.findHouseId(house_id)){
                if(attribute.equalsIgnoreCase("services")){
                    String updateServices="UPDATE house SET services='"+value+"' WHERE idhouse='"+house_id+"'";
                    stmt.executeUpdate(updateServices);
                }
                else if(attribute.equalsIgnoreCase("price")){
                    String updatePrice="UPDATE house SET price='"+value+"' WHERE idhouse='"+house_id+"'";
                    stmt.executeUpdate(updatePrice);
                }
                else if(attribute.equalsIgnoreCase("ownerId")){
                    String updateOwnerId="UPDATE owner SET idowner='"+value+"' WHERE idowner=(select id_owner from house where idhouse='"+house_id+"')";
                    stmt.executeUpdate(updateOwnerId);
                }
          }
            con.close();


//        }catch (Exception e){
//            e.printStackTrace();
//        }

    }
    public void updateMsg() {
        logger.info(" Information Updated Successfully");
    }

    public void unupdatedMsg() {
        logger.info(" Information will not Updated, since houseId doesn't exist ");
    }


}
