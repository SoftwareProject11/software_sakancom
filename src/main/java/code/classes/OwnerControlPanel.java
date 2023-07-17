package code.classes;

import model.classes.House;
import model.classes.HouseFloor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OwnerControlPanel {
    private static Logger logger = Logger.getLogger(Login.class.getName());
    static House house_obj;
    static HouseFloor houseFloor_obj;

//    public OwnerControlPanel() {
////        this.house_obj = new House();
//    }

    public static List<House> findHouse(int house_idd) throws SQLException {
        List<House> houseList = new ArrayList();
//
//        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakancom", "root", "memesa32002@");
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("select * from house where idhouse='" + house_idd + "'");
            while (result.next()) {
                house_obj=new House(result.getInt("idhouse"),result.getString("images"),result.getString("location"),result.getString("services"),result.getDouble("price"),result.getInt("id_owner"),result.getInt("no_floors"),result.getInt("no_tenant"));
//
//                house_obj.setId(result.getInt("idhouse"));
//                house_obj.setLink(result.getString("images"));
//                house_obj.setLocation(result.getString("location"));
//                house_obj.setServices(result.getString("services"));
//                house_obj.setPrice(result.getDouble("price"));
//                house_obj.setOwner_id(result.getInt("id_owner"));
//                house_obj.setNoof_floors(result.getInt("no_floors"));
//                house_obj.setNoof_tenant(result.getInt("no_tenant"));
            }
            houseList.add(house_obj);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return houseList;
    }

    public static void display(House h) throws SQLException {

            logger.info("number of tenant= " + h.getNoof_tenant() + "\n" + "number of floors= " + h.getNoof_floors() + "\n");
            logger.info("floors of this house:\n");
//            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakancom", "root", "memesa32002@");
                Statement stmt = con.createStatement();
                ResultSet result = stmt.executeQuery("select DISTINCT id_floor from house_floor where id_house='" + h.getId() + "'");
                while (result.next()) {
                    logger.info("" + result.getInt("id_floor") + "\n");
                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

//        }
    }

    public static void displayNOTenantAndFloors(List<House> house) throws SQLException {
        for (House h : house) {
            display(h);
        }
    }

    public static ArrayList findFloor(int floor_idd) throws SQLException {
        ArrayList apart = new ArrayList<>();
//        int house_id = house_obj.getId();
//        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakancom", "root", "memesa32002@");
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("select id_apart from house_floor where  id_house='" + house_obj.getId()+"' and  id_floor='" + floor_idd + "' ");
            while (result.next()) {
                apart.add(result.getInt("id_apart"));
            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return apart;
    }

    public static void displayAparts(ArrayList apart) {

            logger.info("apartments of this floor: \n");
            for (int i=0;i<apart.size();i++){
               logger.info(""+apart.get(i));
            }
//        }
    }

    public static List<HouseFloor> findApart(int apartment_idd) throws SQLException {
        List<HouseFloor> apartInfoList = new ArrayList();
//        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakancom", "root", "memesa32002@");
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("select * from house_floor where id_house='" + house_obj.getId() + "' and id_apart='"+apartment_idd+"'");
            while (result.next()) {
                houseFloor_obj=new HouseFloor(result.getInt("id_house"),result.getInt("id_floor"),result.getInt("id_apart"),result.getInt("no_bathrooms"),result.getInt("no_bedrooms"),result.getString("balcony"));
            }
            apartInfoList.add(houseFloor_obj);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return apartInfoList;
    }


    private static void displayApartt(HouseFloor hf) throws SQLException {

            logger.info("number of bathrooms= " + hf.getNo_bathrooms() + "\n" + "number of bedrooms= " + hf.getNo_bedrooms() + "\n there's a balcony: "+hf.getBalcony()+"\n");
            logger.info("Tenants of this apartment:\n TenantName \t contactInfo\n");

//            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakancom", "root", "memesa32002@");
                Statement stmt = con.createStatement();
                ResultSet result = stmt.executeQuery("select name, phone, email from tenant where id_apart='"+hf.getId_apart()+"'");
                while (result.next()) {
                    logger.info( result.getString("name") + "\t 0"+ result.getString("phone")+" , "+result.getString("email")+"\n");
                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

//        }
    }
    public static void displayApartInformation(List<HouseFloor> house_floor) throws SQLException {
        for (HouseFloor hf : house_floor) {
            displayApartt(hf);
        }
    }


}
