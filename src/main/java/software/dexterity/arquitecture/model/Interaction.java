package software.dexterity.arquitecture.model;

public record Interaction(Organism organism1, Organism organism2, InteractionType interactionType) {
    public double calculateImpact(){
        return sigmoideFunction();
    }

    private double sigmoideFunction(){
        return (1./ Math.pow(Math.E, getCoefficient()));
    }

    private double getCoefficient(){
        return (interactionType.getRelationCoefficient() * Math.abs(this.organism1.population() - this.organism2.population()));
    }
}
