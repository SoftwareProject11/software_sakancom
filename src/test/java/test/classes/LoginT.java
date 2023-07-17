package test.classes;

import code.classes.Login;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class LoginT {
    Login obj;
    String username, password,user_choice;
    public LoginT() {
        this.obj=new Login();
        obj.getOwnerID();
        obj.getOwnerName();
    }

    @Given("that the user is not logged in")
    public void that_the_user_is_not_logged_in() {
        assertFalse(obj.isLoggedIn());
    }
    @Given("the username is {string}")
    public void the_username_is(String username) {
        this.username=username;
    }
    @Given("the password is {string}")
    public void the_password_is(String password) {
        this.password=password;
    }
    @Given("the userChoice is {string}")
    public void the_user_choice_is(String user_choice) {
        this.user_choice=user_choice;
    }
    @Then("the user login succeeds")
    public void the_user_login_succeeds() throws SQLException {
        obj.logInCheck(this.username,this.password,this.user_choice);
        assertTrue(obj.isLoggedIn());
    }
    @Then("the user will not login")
    public void the_user_will_not_login() throws SQLException {
        obj.logInCheck(this.username,this.password,this.user_choice);
        assertFalse(obj.isLoggedIn());
    }
    @Then("show the reason why he can't logged in")
    public void show_the_reason_why_he_can_t_logged_in() {
        obj.reasonFalseLogin();
        obj.login();
        obj.logout();
    }

}
