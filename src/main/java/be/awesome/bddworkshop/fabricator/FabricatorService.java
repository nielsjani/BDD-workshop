package be.awesome.bddworkshop.fabricator;

import java.util.List;

//Imaginary Spring annotation here
public class FabricatorService {

    private final FabricatorRepository repository;

    public FabricatorService(FabricatorRepository repository) {
        this.repository = repository;
    }

    void printNewBatch(List<OutpostComponent> components) {
        repository.clearInventory();
        repository.saveAll(components);
    }
}
