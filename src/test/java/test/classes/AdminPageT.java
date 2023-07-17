package test.classes;

import code.classes.AdminPage;
import code.classes.Login;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.classes.Tenant;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AdminPageT {
    Login obj;
    public AdminPageT() {
        this.obj=new Login();
    }
    AdminPage ad;
    String acceptance;
    List<Tenant> tenant;


    @Given("the admin logged in")
    public void the_admin_logged_in() {
        obj.login();
    }

    @When("he enter the id_house {int}, acceptance {string}")
    public void he_enter_the_id_house_acceptance(Integer idhouse, String aacceptance) {
        ad=new AdminPage(idhouse,aacceptance);
        this.acceptance=aacceptance;
    }

    @Then("he can accept or reject it")
    public void he_can_accept_or_reject_it() {
        assertEquals(ad.getAcceptance(),acceptance);
        AdminPage.acceptReject(ad);
    }

    @When("he select to see the reservations of houses")
    public void he_select_to_see_the_reservations_of_houses() {
       tenant=AdminPage.seeReservations();
    }
    @Then("a table of reservations data will printed")
    public void a_table_of_reservations_data_will_printed() {
        AdminPage.displayReservations(tenant);
    }

}
