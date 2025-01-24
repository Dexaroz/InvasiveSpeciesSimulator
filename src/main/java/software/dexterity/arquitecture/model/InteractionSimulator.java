package software.dexterity.arquitecture.model;

public class InteractionSimulator {
    private final Interaction interaction;

    public InteractionSimulator(Interaction interaction) {
        this.interaction = interaction;
    }

    public Organism simulate(Organism organism){
        return organism.updatePopulation(newPopulation());
    }

    private int newPopulation() {
        switch (interaction.interactionType()) {
            case InteractionType.Competition -> {return calculateCompetition();}
            case InteractionType.Symbiosis -> {return calculateSymbiosis();}
            case InteractionType.Neutral -> {return calculateNeutral();}
            case InteractionType.Depredator -> {return calculateDepredator();}
            default -> {return 1;}
        }
    }

    private int calculateCompetition() {
        return 0;
    }
}
