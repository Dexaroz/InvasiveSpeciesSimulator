package software.dexterity.arquitecture.model;

public record Interaction(Organism organism1, Organism organism2, InteractionType interactionType) {
    public double calculateImpact(){
        return sigmoideFunction();
    }

    public Interaction updateInteraction(int index, Organism organism) {
        return index == 0 ? new Interaction(organism, organism2, interactionType) : new Interaction(organism1, organism, interactionType);
    }

    private double sigmoideFunction(){
        return (1./ Math.pow(Math.E, getCoefficient()));
    }

    private double getCoefficient(){
        return (interactionType.getRelationCoefficient() * organism1.growthRate() * Math.abs(this.organism1.population() - this.organism2.population()));
    }
}
