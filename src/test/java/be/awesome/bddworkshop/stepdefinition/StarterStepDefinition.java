package be.awesome.bddworkshop.stepdefinition;

import be.awesome.bddworkshop.StarterMonth;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

import java.util.Set;
import java.util.stream.Collectors;

public class StarterStepDefinition {

    private int age = 0;

    @Given("My age is 35")
    public void myAgeIs35() {
        this.age = 35;
    }

    @Given("I am {int} years old")
    public void iAmYearsOld(int years) {
        this.age = years;
    }

    @Then("I should be {int}")
    public void iShouldBe(int expected) {
        Assertions.assertThat(this.age).isEqualTo(expected);
    }

    @When("It's my birthday")
    public void itsMyBirthday() {
        this.age++;
    }

    @Then("I should have an age of 36")
    public void iShouldHaveAnAgeOf36() {
        Assertions.assertThat(this.age).isEqualTo(36);
    }

    @Given("my name is {string}")
    public void myNameIs(String name) {

    }

    @Given("I was born in {starterMonth}")
    public void iWasBornInFEBRUARY(StarterMonth starterMonth) {
        System.out.println(starterMonth);
    }

    @Given("other people sharing my birthday are")
    public void otherPeopleSharingMyBirthdayAre(DataTable dataTable) {
        record BirthdaySharer(String name, String occupation, boolean isNiels) {
        }


        Set<BirthdaySharer> sharers = dataTable.entries().stream()
                .map(row -> new BirthdaySharer(
                        row.get("full name"),
                        row.get("occupation"),
                        Boolean.parseBoolean(row.getOrDefault("isNiels", "false"))
                ))
                .collect(Collectors.toSet());

        System.out.println(sharers);
    }
}

