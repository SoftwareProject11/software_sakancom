package test.classes;

import code.classes.AddAdvertisement;
import code.classes.Login;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import model.classes.House;
import model.classes.HouseFloor;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class AddHouseT {
    Login obj;
    House house;
    HouseFloor housef;
    int id_house,id_owner ,no_floors,id_floor,id_apart,no_bathrooms,no_bedrooms;
    String images, location,  services,balcony;
    Double price;


    public AddHouseT() {
        this.obj=new Login();
    }
    @Given("that the admin is logged in")
    public void that_the_admin_is_logged_in() {
        obj.login();
    }

    @Given("admin enter the id_house {int}, photos {string}, location {string}, services {string} price {double}, id_owner {int}, no_floors {int}")
    public void admin_enter_the_id_house_photos_location_services_price_id_owner_no_floors(Integer id_house, String images, String location, String services, Double price, Integer id_owner, Integer no_floors) {
        house=new House(id_house,images,location,services,price,id_owner,no_floors);
        this.id_house=id_house;
        this.images=images;
        this.location=location;
        this.services=services;
        this.price=price;
        this.id_owner=id_owner;
        this.no_floors=no_floors;
    }

    @Then("the new house will be add to the database in system")
    public void the_new_house_will_be_add_to_the_database_in_system() throws SQLException {
        assertNotNull(house);

        assertEquals(house.getId(),id_house);
        assertEquals(house.getLink(),images);
        assertEquals(house.getLocation(),location);
        assertEquals(house.getServices(),services);
        assertEquals(house.getPrice(),price);
        assertEquals(house.getOwner_id(),id_owner);
        assertEquals(house.getNoof_floors(),no_floors);

        House.addHouse(house);
    }

    @Given("admin enter the id_house {int}, id_floor {int}, id_apart {int}, no_bathrooms {int}, no_bedrooms {int}, balcony {string}")
    public void admin_enter_the_id_house_id_floor_id_apart_no_bathrooms_no_bedrooms_balcony(Integer id_house, Integer id_floor, Integer id_apart, Integer no_bathrooms, Integer no_bedrooms, String balcony) {
        housef=new HouseFloor(id_house,id_floor,id_apart,no_bathrooms,no_bedrooms,balcony);
        this.id_house=id_house;
        this.id_floor=id_floor;
        this.id_apart=id_apart;
        this.no_bathrooms=no_bathrooms;
        this.no_bedrooms=no_bedrooms;
        this.balcony=balcony;
    }

    @Then("the extra information about the house will be saved in the database")
    public void the_extra_information_about_the_house_will_be_saved_in_the_database() {
        assertNotNull(housef);

        assertEquals(housef.getId_house(),id_house);
        assertEquals(housef.getId_floor(),id_floor);
        assertEquals(housef.getId_apart(),id_apart);
        assertEquals(housef.getNo_bathrooms(),no_bathrooms);
        assertEquals(housef.getNo_bedrooms(),no_bedrooms);
        assertEquals(housef.getBalcony(),balcony);

        House.addHouseInfo(housef);
    }

    @Then("the house will not added to system")
    public void the_house_will_not_added_to_system() throws SQLException {
//        House.addHouse(house);
        assertFalse(!House.findHouseId(id_house));


    }
}
