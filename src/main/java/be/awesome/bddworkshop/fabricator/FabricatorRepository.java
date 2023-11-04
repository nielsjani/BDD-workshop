package be.awesome.bddworkshop.fabricator;

import java.util.List;
import java.util.UUID;

public interface FabricatorRepository {

    void saveAll(List<OutpostComponent> outpostComponents);

    OutpostComponent findById(UUID id);

    void clearInventory();

    void remove(UUID outpostComponentId);
}
