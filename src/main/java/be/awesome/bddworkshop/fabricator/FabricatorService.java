package be.awesome.bddworkshop.fabricator;

import java.util.Set;

//Imaginary Spring annotation here
public class FabricatorService {

    private final FabricatorRepository repository;

    public FabricatorService(FabricatorRepository repository) {
        this.repository = repository;
    }

    public void printNewBatch(Set<OutpostComponent> components) {
        if(components.isEmpty()) {
            throw new IllegalStateException("No components passed to fabricator");
        }
        repository.clearInventory();
        repository.saveAll(components);
    }
}
