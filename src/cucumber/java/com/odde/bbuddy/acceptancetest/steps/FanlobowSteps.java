package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class FanlobowSteps {

    @Autowired
    UiDriver uiDriver;

    @Given("^there is a word \"([^\"]*)\"$")
    public void there_is_a_word(String arg1) throws Throwable {

    }

    @When("^this game started$")
    public void this_game_started() throws Throwable {
        uiDriver.navigateTo("/yyy");
    }

    @Then("^I can see \"([^\"]*)\"$")
    public void i_can_see(String arg1) throws Throwable {
        String guess = uiDriver.findElementById("guess").getText();
        assertEquals(arg1, guess);

    }

    @Then("^used \"([^\"]*)\"$")
    public void used(String arg1) throws Throwable {
        String used = uiDriver.findElementById("used").getText();
        assertEquals(arg1, used);
    }

    @Then("^length (\\d+)$")
    public void length(int arg1) throws Throwable {
        String length = uiDriver.findElementById("length").getText();
        assertEquals(arg1, Integer.parseInt(length));
    }

    @Then("^tries (\\d+)$")
    public void tries(int arg1) throws Throwable {
        String tries = uiDriver.findElementById("tries").getText();
        assertEquals(arg1, Integer.parseInt(tries));
    }
}
