package be.awesome.bddworkshop;

import be.awesome.bddworkshop.fabricator.OutpostComponent;

import java.util.HashSet;
import java.util.Set;

public class FabricatorTestExecutionContext {
    private final Set<OutpostComponent> componentsPreppedForPrinting = new HashSet<>();
    private IllegalStateException exception;

    public void addComponentPreppedForPrinting(OutpostComponent outpostComponent) {
        componentsPreppedForPrinting.add(outpostComponent);
    }

    public Set<OutpostComponent> getComponentsPreppedForPrinting() {
        return componentsPreppedForPrinting;
    }

    public void setException(IllegalStateException e) {
        this.exception = e;
    }

    public IllegalStateException getException() {
        return exception;
    }
}
