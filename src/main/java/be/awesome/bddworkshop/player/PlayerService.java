package be.awesome.bddworkshop.player;

import be.awesome.bddworkshop.fabricator.FabricatorRepository;
import be.awesome.bddworkshop.fabricator.OutpostComponent;

import java.util.UUID;

//Imaginary Spring annotation here
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final FabricatorRepository fabricatorRepository;

    public PlayerService(PlayerRepository playerRepository, FabricatorRepository fabricatorRepository) {
        this.playerRepository = playerRepository;
        this.fabricatorRepository = fabricatorRepository;
    }

    public void gatherResources(UUID playerId, PlayerResources extraResources) {
        Player player = playerRepository.findById(playerId);
        player.gatherResources(extraResources);
        playerRepository.save(player);
    }

    public void addOutpostRoom(UUID playerId, UUID componentId) {
        Player player = playerRepository.findById(playerId);
        OutpostComponent componentBought = fabricatorRepository.findById(componentId);

        player.addOutpostRoom(componentBought);
        playerRepository.save(player);

        fabricatorRepository.remove(componentId);
    }
}
