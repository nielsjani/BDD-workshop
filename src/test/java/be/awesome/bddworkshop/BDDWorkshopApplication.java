package be.awesome.bddworkshop;

import be.awesome.bddworkshop.fabricator.FabricatorService;
import be.awesome.bddworkshop.player.PlayerService;
import be.awesome.bddworkshop.testrepository.FabricatorTestRepository;
import be.awesome.bddworkshop.testrepository.PlayerTestRepository;

public class BDDWorkshopApplication {

    private final FabricatorTestRepository fabricatorTestRepository;
    private final FabricatorService fabricatorService;
    private final PlayerTestRepository playerTestRepository;
    private final PlayerService playerService;

    public BDDWorkshopApplication(FabricatorTestRepository fabricatorTestRepository, PlayerTestRepository playerTestRepository) {
        this.fabricatorTestRepository = fabricatorTestRepository;
        fabricatorService = new FabricatorService(fabricatorTestRepository);
        this.playerTestRepository = playerTestRepository;
        playerService = new PlayerService(playerTestRepository, fabricatorTestRepository);
    }

    public FabricatorTestRepository getFabricatorTestRepository() {
        return fabricatorTestRepository;
    }

    public FabricatorService getFabricatorService() {
        return fabricatorService;
    }

    public PlayerTestRepository getPlayerTestRepository() {
        return playerTestRepository;
    }

    public PlayerService getPlayerService() {
        return playerService;
    }
}
