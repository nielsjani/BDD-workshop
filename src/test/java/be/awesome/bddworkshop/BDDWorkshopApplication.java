package be.awesome.bddworkshop;

import be.awesome.bddworkshop.fabricator.FabricatorService;
import be.awesome.bddworkshop.testrepository.FabricatorTestRepository;

public class BDDWorkshopApplication {

    private final FabricatorTestRepository fabricatorTestRepository;
    private final FabricatorService fabricatorService;

    public BDDWorkshopApplication(FabricatorTestRepository fabricatorTestRepository) {
        this.fabricatorTestRepository = fabricatorTestRepository;
        fabricatorService = new FabricatorService(fabricatorTestRepository);
    }

    public FabricatorTestRepository getFabricatorTestRepository() {
        return fabricatorTestRepository;
    }

    public FabricatorService getFabricatorService() {
        return fabricatorService;
    }
}
