package be.awesome.bddworkshop.testrepository;

import be.awesome.bddworkshop.fabricator.FabricatorRepository;
import be.awesome.bddworkshop.fabricator.OutpostComponent;

import java.util.*;
import java.util.stream.Collectors;

public class FabricatorTestRepository implements FabricatorRepository {

    private final Map<UUID, OutpostComponent> outpostComponents;

    public FabricatorTestRepository() {
        this.outpostComponents = new HashMap<>();
    }

    @Override
    public void saveAll(Set<OutpostComponent> newComponents) {
        newComponents.forEach(newComponent -> outpostComponents.put(newComponent.id(), newComponent));
    }

    @Override
    public OutpostComponent findById(UUID id) {
        return outpostComponents.get(id);
    }

    @Override
    public void clearInventory() {
        outpostComponents.clear();
    }

    @Override
    public void remove(UUID outpostComponentId) {
        outpostComponents.remove(outpostComponentId);
    }

    public Set<OutpostComponent> getAll() {
        return new HashSet<>(outpostComponents.values());
    }
}
