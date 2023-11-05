package be.awesome.bddworkshop.fabricator;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface FabricatorRepository {

    void saveAll(Set<OutpostComponent> outpostComponents);

    OutpostComponent findById(UUID id);

    void clearInventory();

    void remove(UUID outpostComponentId);
}
