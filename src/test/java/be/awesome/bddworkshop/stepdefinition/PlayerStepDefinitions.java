package be.awesome.bddworkshop.stepdefinition;

import be.awesome.bddworkshop.BDDWorkshopApplication;
import be.awesome.bddworkshop.PlayerTestExecutionContext;
import be.awesome.bddworkshop.fabricator.OutpostComponent;
import be.awesome.bddworkshop.player.Player;
import be.awesome.bddworkshop.player.PlayerResources;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerStepDefinitions {

    private final BDDWorkshopApplication application;
    private PlayerTestExecutionContext context;

    public PlayerStepDefinitions(BDDWorkshopApplication application, PlayerTestExecutionContext context) {
        this.application = application;
        this.context = context;
    }

    @Given("a player {uuid} exists")
    public void aPlayerExists(UUID id) {
        Player player = new Player(id, 3);

        application.getPlayerTestRepository().save(player);
    }

    @When("player {uuid} gathers the following resources")
    public void playerREDGathersTheFollowingResources(UUID id, @Transpose PlayerResources playerResources) {
        try {
            application.getPlayerService().gatherResources(id, playerResources);
        } catch (Exception e) {
            context.setException(e);
        }
    }

    @Then("player {uuid} should have the following resource totals")
    public void theyShouldHaveTheFollowingResourceTotals(UUID id, @Transpose PlayerResources expected) {
        Player actual = application.getPlayerTestRepository().findById(id);
        assertThat(actual.getResources()).isEqualTo(expected);
    }

    @Then("player {uuid} should have {int} actions remaining")
    public void playerShouldHaveActionsRemaining(UUID id, int expected) {
        Player actual = application.getPlayerTestRepository().findById(id);
        assertThat(actual.getRemainingActions()).isEqualTo(expected);
    }

    @Then("error {string} should have been thrown")
    public void errorShouldHaveBeenThrown(String errorMessage) {
        assertThat(context.getException()).isNotNull().extracting(Throwable::getMessage).isEqualTo(errorMessage);
    }

    @When("player {uuid} adds outpost {uuid} to their base")
    public void playerREDAddsOutpostBUNKSToTheirBase(UUID playerId, UUID componentId) {
        try {
            application.getPlayerService().addOutpostRoom(playerId, componentId);
        } catch (Exception e) {
            context.setException(e);
        }
    }

    @Then("player {uuid} should have {uuid} in their base")
    public void playerREDShouldHaveBUNKSInTheirBase(UUID playerId, UUID componentId) {
        Player actual = application.getPlayerTestRepository().findById(playerId);
        assertThat(actual.getOutpostRooms()).extracting(OutpostComponent::id).contains(componentId);
    }

    @Then("player {uuid} should not have any rooms in their base")
    public void playerREDShouldNotHaveAnyRoomsInTheirBase(UUID playerId) {
        Player actual = application.getPlayerTestRepository().findById(playerId);
        assertThat(actual.getOutpostRooms()).isEmpty();
    }
}
