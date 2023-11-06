package be.awesome.bddworkshop.testrepository;

import be.awesome.bddworkshop.player.Player;
import be.awesome.bddworkshop.player.PlayerRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerTestRepository implements PlayerRepository {

    private final Map<UUID, Player> players = new HashMap<>();

    @Override
    public void save(Player player) {
        players.put(player.getId(), player);
    }

    @Override
    public Player findById(UUID id) {
        return players.get(id);
    }
}
