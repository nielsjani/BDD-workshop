package be.awesome.bddworkshop;

import io.cucumber.java.ParameterType;

public class StarterParameterTypes {

    @ParameterType("(.+)")
    public static StarterMonth starterMonth(String starterMonth) {
        return StarterMonth.valueOf(starterMonth);
    }
}
