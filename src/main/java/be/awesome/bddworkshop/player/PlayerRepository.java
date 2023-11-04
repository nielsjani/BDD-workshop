package be.awesome.bddworkshop.player;

import java.util.UUID;

public interface PlayerRepository {
    void save(Player player);

    Player findById(UUID id);
}
