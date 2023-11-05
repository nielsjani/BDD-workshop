package be.awesome.bddworkshop.stepdefinition;

import be.awesome.bddworkshop.BDDWorkshopApplication;
import be.awesome.bddworkshop.FabricatorTestExecutionContext;
import be.awesome.bddworkshop.fabricator.OutpostComponent;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class FabricatorStepDefinitions {

    private final BDDWorkshopApplication bddWorkshopApplication;
    private final FabricatorTestExecutionContext testExecutionContext;

    public FabricatorStepDefinitions(BDDWorkshopApplication bddWorkshopApplication, FabricatorTestExecutionContext testExecutionContext) {
        this.bddWorkshopApplication = bddWorkshopApplication;
        this.testExecutionContext = testExecutionContext;
    }

    @Given("I have a component")
    @Given("another component")
    public void iHaveAComponent(@Transpose OutpostComponent outpostComponent) {
        testExecutionContext.addComponentPreppedForPrinting(outpostComponent);
    }

    @When("I fill the fabricator with these components")
    @When("I fill the fabricator with components")
    public void iFillTheFabricatorWithTheseComponents() {
        try {
            bddWorkshopApplication.getFabricatorService().printNewBatch(testExecutionContext.getComponentsPreppedForPrinting());
        } catch (IllegalStateException e) {
            this.testExecutionContext.setException(e);
        }
    }

    @Given("a fabricator without any components")
    public void aFabricatorWithoutAnyComponents() {
        bddWorkshopApplication.getFabricatorTestRepository().clearInventory();
    }

    @Then("it should have all of these components available for construction")
    public void itShouldHaveAllOfTheseComponentsAvailableForConstruction() {
        Set<OutpostComponent> actual = bddWorkshopApplication.getFabricatorTestRepository().getAll();

        assertThat(actual)
                .containsExactlyInAnyOrder(testExecutionContext.getComponentsPreppedForPrinting().toArray(new OutpostComponent[0]));
        assertThat(testExecutionContext.getException())
                .isNull();
    }

    @Then("it should throw exception {string}")
    public void itShouldThrowException(String errorMessage) {
        assertThat(testExecutionContext.getException().getMessage())
                .isNotNull()
                .isEqualTo(errorMessage);
    }
}
