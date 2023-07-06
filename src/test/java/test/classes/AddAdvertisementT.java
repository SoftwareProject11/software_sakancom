package test.classes;

import code.classes.AddAdvertisement;
import code.classes.Login;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.logging.Logger;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class AddAdvertisementT {
    Login obj;
    AddAdvertisement adv;
    Integer id_house;
    String photos,  ownerName,  ownerContactInfo,  location,  services, rentNote;
    Double rent,price;

    private  Logger logger = Logger.getLogger(Login.class.getName());

    public AddAdvertisementT() {
        this.obj=new Login();
    }

    @Given("that the owner or admin is logged in")
    public void that_the_owner_or_admin_is_logged_in() {
        obj.login();
    }

    @Given("there is an id_house with id {int}, photos {string}, owner_name {string}, contactInfo {string}, location {string}, services {string}, monthly_rent {double}, noteRent {string} and price {double}")
    public void there_is_an_id_house_with_id_photos_owner_name_contact_info_location_services_monthly_rent_note_rent_and_price(Integer id_house, String photos, String ownerName, String ownerContactInfo,  String location, String services, Double rent, String rentNote, Double price) {
        adv=new AddAdvertisement(id_house,photos,ownerName,ownerContactInfo,location,services,rent,rentNote,price);
        this.id_house=id_house;
        this.photos=photos;
        this.ownerName=ownerName;
        this.ownerContactInfo=ownerContactInfo;
        this.location=location;
        this.services=services;
        this.rent=rent;
        this.rentNote=rentNote;
        this.price=price;
    }

    @Then("the advertisement will be saved in the database")
    public void the_advertisement_will_be_saved_in_the_database() {
        assertNotNull(adv);

        assertEquals(adv.getId_house(),id_house);
        assertEquals(adv.getPhotos(),photos);
        assertEquals(adv.getOwnerName(),ownerName);
        assertEquals(adv.getOwnerContactInfo(),ownerContactInfo);
        assertEquals(adv.getLocation(),location);
        assertEquals(adv.getServices(),services);
        assertEquals(adv.getRent(),rent);
        assertEquals(adv.getRentNote(),rentNote);
        assertEquals(adv.getPrice(),price);

        AddAdvertisement.addAdv(adv);
//        AddAdvertisement.validHouse();
//        assertTrue(AddAdvertisement.isValidHouse());

        logger.info("The advertisement is added, but waiting Administrator to accept it ");
    }

    @Then("the advertisement will not be saved in the database")
    public void the_advertisement_will_not_be_saved_in_the_database() {
        AddAdvertisement.addAdv(adv);
        assertFalse(AddAdvertisement.isValidHouse());
    }

    @Then("show the reason why it can't save")
    public void show_the_reason_why_it_can_t_save() {
        adv.displayReasonSameHouse();
    }

    @Then("show the reason why it can't save2")
    public void show_the_reason_why_it_can_t_save2() {
        adv.displayReasonHouseNotExist();
    }


}
