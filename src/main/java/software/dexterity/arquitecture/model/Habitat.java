package software.dexterity.arquitecture.model;

import java.util.ArrayList;
import java.util.List;

public class Habitat {
    private final List<Organism> organisms;
    private final List<Interaction> interactions;

    public Habitat() {
        this.organisms = new ArrayList<>();
        this.interactions = new ArrayList<>();
    }

    public void addSOrganisms(Organism organism){
        organisms.add(organism);
    }

    public void addInteraction(Organism organism1, Organism organism2, InteractionType interactionType){
        interactions.add(new Interaction(organism1, organism2, interactionType));
    }

    public List<Organism> getSOrganisms(){
        return organisms;
    }

    public List<Interaction> getInteraction(){
        return interactions;
    }
}
