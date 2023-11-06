package be.awesome.bddworkshop.player;

import be.awesome.bddworkshop.fabricator.OutpostComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Player {
    private final UUID id;
    private PlayerResources resources;
    private final List<OutpostComponent> outpostRooms;
    private int remainingActions;

    public Player(UUID id, int remainingActions) {
        this.id = id;
        this.resources = PlayerResources.startingResources();
        this.outpostRooms = new ArrayList<>();
        this.remainingActions = remainingActions;
    }

    public void gatherResources(PlayerResources extraResources) {
        assertActionsRemaining();
        if(hasGatheredAllowedAmountOf(extraResources)) {
            this.resources = this.resources.mergeWith(extraResources);
            remainingActions--;
        } else {
            throw new IllegalArgumentException("Should gather exactly 2 resources");
        }
    }

    public void addOutpostRoom(OutpostComponent purchasedRoom) {
        assertActionsRemaining();
        this.resources = this.resources.subtract(purchasedRoom.constructionCost());
        this.outpostRooms.add(purchasedRoom);
        remainingActions--;
    }

    private void assertActionsRemaining() {
        if(this.remainingActions <= 0) {
            throw new IllegalStateException("No actions remaining");
        }
    }

    private boolean hasGatheredAllowedAmountOf(PlayerResources extraResources) {
        return extraResources.getSum() == 2;
    }

    public UUID getId() {
        return id;
    }

    public PlayerResources getResources() {
        return resources;
    }

    public List<OutpostComponent> getOutpostRooms() {
        return outpostRooms;
    }

    public int getRemainingActions() {
        return remainingActions;
    }
}
