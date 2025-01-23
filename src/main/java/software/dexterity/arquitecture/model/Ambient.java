package software.dexterity.arquitecture.model;

import java.util.ArrayList;
import java.util.List;

public class Ambient {
    private final List<Specie> species;
    private final List<Interaction> interactions;

    public Ambient() {
        this.species = new ArrayList<>();
        this.interactions = new ArrayList<>();
    }
}
