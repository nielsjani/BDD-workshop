package be.awesome.bddworkshop.player;

import be.awesome.bddworkshop.fabricator.ConstructionCost;

import java.util.stream.IntStream;

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
        int remainingScience = subtract(this.science, constructionCost.scienceCost());
        int remainingBiomass = subtract(this.biomass, constructionCost.biomassCost());
        int remainingWater = subtract(this.water, constructionCost.waterCost());
        int remainingElectricityBeforeWildResourceUsage = subtract(this.electricity, constructionCost.electricityCost());
        int remainingElectricity = remainingElectricityBeforeWildResourceUsage - wildResourceDebt(remainingScience, remainingBiomass, remainingWater);

        assertCostsPaid(remainingElectricity);

        return new PlayerResources(
                remainingElectricity,
                positiveOrZero(remainingBiomass),
                positiveOrZero(remainingWater),
                positiveOrZero(remainingScience)
        );
    }

    private int positiveOrZero(int resource) {
        return Math.max(resource, 0);
    }

    private int wildResourceDebt(int remainingScience, int remainingBiomass, int remainingWater) {
        return IntStream.of(remainingBiomass, remainingScience, remainingWater)
                .filter(value -> value < 0)
                .map(Math::abs)
                .sum();
    }

    private void assertCostsPaid(int wildResource) {
        if (wildResource < 0) {
            throw new IllegalArgumentException("Could not subtract costs using current resources");
        }
    }

    private int subtract(int currentResources, int cost) {
        return currentResources - cost;
    }
}
