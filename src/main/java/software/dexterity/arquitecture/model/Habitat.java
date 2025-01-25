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

    public void addOrganisms(Organism organism){
        organisms.add(organism);
    }

    public void addInteraction(Organism organism1, Organism organism2, InteractionType interactionType){
        interactions.add(new Interaction(organism1, organism2, interactionType));
    }

    public List<Organism> getOrganisms(){
        return organisms;
    }

    public List<Interaction> getInteraction(){
        return interactions;
    }

    public void updateEnvironment(Organism oldOrganism, Organism newOrganism, Interaction interaction){
        replaceOrganism(oldOrganism, newOrganism);
        replaceInteraction(oldOrganism, newOrganism, interaction);
    }

    private void replaceOrganism(Organism oldOrganism, Organism newOrganism){
        organisms.remove(oldOrganism);
        organisms.add(newOrganism);
    }

    private void replaceInteraction(Organism oldOrganism, Organism newOrganism, Interaction oldInteraction){
        interactions.remove(oldInteraction);
        interactions.add(oldInteraction.organism1() == oldOrganism ? oldInteraction.updateInteraction(0, newOrganism) : oldInteraction.updateInteraction(1, newOrganism));
    }
}
