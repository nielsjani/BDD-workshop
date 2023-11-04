package be.awesome.bddworkshop.fabricator;

import java.util.UUID;

public record OutpostComponent(UUID id, ConstructionCost constructionCost, int victoryPoints) {
}
