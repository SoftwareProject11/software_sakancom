package test.classes;

import code.classes.Login;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.classes.House;

import java.sql.SQLException;

import static org.junit.Assert.assertFalse;

public class UpdateHouseT {
    Login obj;
    House house;
    Object value;
    int house_id;

    public UpdateHouseT() {
        this.obj=new Login();
    }
    @Given("that the admin is logged in and want to update house info")
    public void that_the_admin_is_logged_in_and_want_to_update_house_info() {
       obj.login();
       house=new House();
       value=new Object();
    }

    @When("he select to update services and he enter the new services = {string} for the entered houseId {int}")
    public void he_select_to_update_services_and_he_enter_the_new_services_for_the_entered_house_id(String services, Integer house_id) throws SQLException {
        value=services;
        this.house_id=house_id;
        house.updateInfo("services",value,house_id);
    }

    @Then("the services will updated successfully")
    public void the_services_will_updated_successfully() {
        house.updateMsg();
    }

    @When("he select to update price and he enter the new price = {double} for the entered houseId {int}")
    public void he_select_to_update_price_and_he_enter_the_new_price_for_the_entered_house_id(Double price, Integer house_id) throws SQLException {
        value=price;
        this.house_id=house_id;
        house.updateInfo("price",value,house_id);
    }

    @Then("the price will updated successfully")
    public void the_price_will_updated_successfully() {
        house.updateMsg();
    }

    @When("he select to update ownerId and he enter the new id = {int} for the entered houseId {int}")
    public void he_select_to_update_owner_id_and_he_enter_the_new_id_for_the_entered_house_id(Integer owner_id, Integer house_id) throws SQLException {
        value=owner_id;
        this.house_id=house_id;
        house.updateInfo("ownerId",value,house_id);
    }

    @Then("the ownerId will updated successfully")
    public void the_owner_id_will_updated_successfully() {
        house.updateMsg();
    }

    @Given("he enter non existing houseId")
    public void he_enter_non_existing_house_id() throws SQLException {
        assertFalse(House.findHouseId(house_id));
    }

    @Then("the message that the house is not exist will be print")
    public void the_message_that_the_house_is_not_exist_will_be_print() {
        house.unupdatedMsg();
    }

}
