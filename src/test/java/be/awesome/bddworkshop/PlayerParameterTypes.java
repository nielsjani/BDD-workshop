package be.awesome.bddworkshop;

import be.awesome.bddworkshop.player.PlayerResources;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class PlayerParameterTypes {

    @DataTableType
    public PlayerResources playerResources(Map<String, String> verticalTable) {
        return new PlayerResources(
                Integer.parseInt(verticalTable.getOrDefault("ELECTRICITY", "0")),
                Integer.parseInt(verticalTable.getOrDefault("BIOMASS", "0")),
                Integer.parseInt(verticalTable.getOrDefault("WATER", "0")),
                Integer.parseInt(verticalTable.getOrDefault("SCIENCE", "0"))
        );
    }
}
