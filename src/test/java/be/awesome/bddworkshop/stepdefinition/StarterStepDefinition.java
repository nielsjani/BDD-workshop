package be.awesome.bddworkshop.stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

public class StarterStepDefinition {

    private int age = 0;

    @Given("My age is 35")
    public void myAgeIs35() {
        this.age = 35;
    }

    @When("It's my birthday")
    public void itsMyBirthday() {
        this.age++;
    }

    @Then("I should have an age of 36")
    public void iShouldHaveAnAgeOf36() {
        Assertions.assertThat(this.age).isEqualTo(36);
    }
}

