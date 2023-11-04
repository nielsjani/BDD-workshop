package be.awesome.bddworkshop.player;

import be.awesome.bddworkshop.fabricator.OutpostComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Player {
    private final UUID id;
    private PlayerResources resources;
    private final List<OutpostComponent> outpostRooms;
    private final int remainingActions;

    public Player(UUID id, int remainingActions) {
        this.id = id;
        this.resources = PlayerResources.startingResources();
        this.outpostRooms = new ArrayList<>();
        this.remainingActions = remainingActions;
    }

    public void gatherResources(PlayerResources extraResources) {
        if(hasGatheredAllowedAmountOf(extraResources)) {
            this.resources = this.resources.mergeWith(extraResources);
        }
    }

    public void addOutpostRoom(OutpostComponent purchasedRoom) {
        this.outpostRooms.add(purchasedRoom);
        this.resources = this.resources.subtract(purchasedRoom.constructionCost());
    }

    private boolean hasGatheredAllowedAmountOf(PlayerResources extraResources) {
        return extraResources.getSum() == 2;
    }
}
