package com.mylaptop.asts.test.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDef {

    @Given("User enters valid username")
    public void user_enters_valid_username() {

    }

    @And("User enters valid password")
    public void user_enters_valid_password() {

    }

    @When("User clicks on login button")
    public void user_clicks_on_login_button() {

    }

    @Then("User is logged in successfully")
    public void user_is_logged_in_successfully() {

    }



    @Then("User is not logged in successfully")
    public void userIsNotLoggedInSuccessfully() {
    }

    @Given("User enters invalid username {string}")
    public void userEntersInvalidUsername(String username) {
    }

    @And("User enters invalid password {string}")
    public void userEntersInvalidPassword(String password) {
    }
}
