package software.dexterity.arquitecture.model;

public record Interaction(Specie specie1, Specie specie2, InteractionType interactionType) {
    public double calculateImpact(){
        return interactionType.getRelationCoefficient() * this.specie1.population() * this.specie2.population();
    }
}
