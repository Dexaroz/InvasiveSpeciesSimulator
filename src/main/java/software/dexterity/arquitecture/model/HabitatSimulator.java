package software.dexterity.arquitecture.model;

import java.util.ArrayList;
import java.util.List;

public class HabitatSimulator {
    private final Habitat habitat;
    private final int timeStep;

    public HabitatSimulator(Habitat habitat, int timeStep) {
        this.habitat = habitat;
        this.timeStep = timeStep;
    }

    public void simulateSteps(){
        List<Interaction> interactionsCopy = new ArrayList<>(habitat.getInteraction());

        for (Interaction interaction : interactionsCopy){
            Organism organism1 = interaction.organism1();
            Organism organism2 = interaction.organism2();

            double impactOnOrganism1 = calculateImpact(interaction, organism1, organism2);
            double impactOnOrganism2 = calculateImpact(interaction, organism2, organism1);

            Organism updatedOrganism1 = organism1.updateOrganism(
                    (int) Math.max(organism1.population() + impactOnOrganism1 * timeStep, 0)
            );
            Organism updatedOrganism2 = organism2.updateOrganism(
                    (int) Math.max(organism2.population() + impactOnOrganism2 * timeStep, 0)
            );

            habitat.updateEnvironment(organism1, updatedOrganism1, interaction);
            habitat.updateEnvironment(organism2, updatedOrganism2, interaction);
        }
    }

    public double calculateImpact(Interaction interaction, Organism target, Organism source){
        switch (interaction.interactionType()){
            case InteractionType.Competition -> {return calculateCompetitionImpact(interaction, source);}
            case InteractionType.Neutral -> {return calculateNeutralImpact();}
            case InteractionType.Symbiosis -> {return calculateSymbiosisImpact(interaction, source);}
            case InteractionType.Depredation -> {return calculateDepredationImpact(interaction, target, source);}
            default -> {return 1.0;}
        }
    }

    private int calculateDepredationImpact(Interaction interaction, Organism target, Organism source) {
        return target == interaction.organism1() ? (int) interaction.calculateImpact() * source.population() : (int) -interaction.calculateImpact() * source.population();
    }

    private int calculateSymbiosisImpact(Interaction interaction, Organism source) {
        return (int) interaction.calculateImpact() * source.population();
    }

    private int calculateNeutralImpact() {
        return 0;
    }

    private int calculateCompetitionImpact(Interaction interaction, Organism source) {
        return (int) (interaction.calculateImpact() * source.population());
    }
}
