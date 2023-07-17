package test.classes;

import model.classes.House;
import code.classes.Login;
import code.classes.OwnerControlPanel;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import model.classes.HouseFloor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OwnerControlPanelT {

    Login obj;
    List<House> house;
    ArrayList apart=new ArrayList<>();
    List<HouseFloor> house_floor;

    public OwnerControlPanelT() {
        this.obj=new Login();
    }

    @Given("that the owner logged in and select to see his housings")
    public void that_the_owner_logged_in_and_select_to_see_his_housings() {
        obj.login();
    }

    @Given("the owner enter the house ID ={int}")
    public void the_owner_enter_the_house_id(Integer house_idd) throws SQLException {
        house= OwnerControlPanel.findHouse(house_idd);
        HouseFloor.findHouseFloorId(house_idd);
    }

    @Then("the number of tenants and floors and the id of floors are printed")
    public void the_number_of_tenants_and_floors_and_the_id_of_floors_are_printed() throws SQLException {
        OwnerControlPanel.displayNOTenantAndFloors(house);
    }

    @Given("that the owner logged in")
    public void that_the_owner_logged_in() {
        obj.isLoggedin=true;
    }

    @Given("the owner enter the floor ID ={int}")
    public void the_owner_enter_the_floor_id(Integer floor_idd) throws SQLException {
        apart=OwnerControlPanel.findFloor(floor_idd);
    }

    @Then("the apartments of this floor printed")
    public void the_apartments_of_this_floor_printed() {
        OwnerControlPanel.displayAparts(apart);
    }

    @Given("the owner enter the apartments ID ={int}")
    public void the_owner_enter_the_apartments_id(Integer apartment_idd) throws SQLException {
        house_floor=OwnerControlPanel.findApart(apartment_idd);
    }

    @Then("the names of tenants and their communication info, and number of bathrooms, bedrooms, and if it has a balcony are printed")
    public void the_names_of_tenants_and_their_communication_info_and_number_of_bathrooms_bedrooms_and_if_it_has_a_balcony_are_printed() throws SQLException {
        OwnerControlPanel.displayApartInformation(house_floor);
    }

}
