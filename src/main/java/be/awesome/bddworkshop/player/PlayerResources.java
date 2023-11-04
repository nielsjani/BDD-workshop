package be.awesome.bddworkshop.player;

import be.awesome.bddworkshop.fabricator.ConstructionCost;

public record PlayerResources(int electricity, int biomass, int water, int science) {
    public static PlayerResources startingResources() {
        return new PlayerResources(2, 2, 2, 2);
    }

    public PlayerResources mergeWith(PlayerResources extraResources) {
        return new PlayerResources(
                electricity + extraResources.electricity,
                biomass + extraResources.biomass,
                water + extraResources.water,
                science + extraResources.science);
    }

    public int getSum() {
        return electricity + biomass + water + science;
    }

    public PlayerResources subtract(ConstructionCost constructionCost) {
        int wildResource = electricity;
        int remainingScience = subtract(this.science, constructionCost.scienceCost(), wildResource);
        int remainingBiomass = subtract(this.biomass, constructionCost.biomassCost(), wildResource);
        int remainingWater = subtract(this.water, constructionCost.waterCost(), wildResource);
        int remainingElectricity = subtract(this.electricity, constructionCost.electricityCost(), wildResource);

        assertCostsPaid(wildResource);

        return new PlayerResources(
                remainingElectricity,
                remainingBiomass,
                remainingWater,
                remainingScience
        );
    }

    private void assertCostsPaid(int wildResource) {
        if (wildResource < 0) {
            throw new IllegalArgumentException("Could not subtract costs using current resources;");
        }
    }

    private int subtract(int currentResources, int cost, int wildResource) {
        if (cost > currentResources) {
            wildResource = wildResource - (cost - currentResources);
            return 0;
        } else {
            return currentResources - cost;
        }
    }
}
