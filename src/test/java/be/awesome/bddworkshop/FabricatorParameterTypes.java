package be.awesome.bddworkshop;

import be.awesome.bddworkshop.fabricator.ConstructionCost;
import be.awesome.bddworkshop.fabricator.OutpostComponent;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.Transpose;

import java.util.Map;
import java.util.UUID;

public class FabricatorParameterTypes {


    @ParameterType("(.+)")
    public static UUID uuid(String name) {
        return UUID.nameUUIDFromBytes(name.getBytes());
    }

    @DataTableType
    public OutpostComponent outpostComponent(Map<String, String> verticalTable) {
        return new OutpostComponent(
                uuid(verticalTable.get("ID")),
                new ConstructionCost(
                        Integer.parseInt(verticalTable.getOrDefault("ELECTRICITY_COST", "0")),
                        Integer.parseInt(verticalTable.getOrDefault("BIOMASS_COST", "0")),
                        Integer.parseInt(verticalTable.getOrDefault("WATER_COST", "0")),
                        Integer.parseInt(verticalTable.getOrDefault("SCIENCE_COST", "0"))
                ),
                Integer.parseInt(verticalTable.get("VP"))
        );
    }
}
