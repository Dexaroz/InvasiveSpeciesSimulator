package software.dexterity.arquitecture.model;

public enum InteractionType {
    Depredator(-1),
    Symbiosis(2),
    Neutral(1),
    Competition(-0.5);

    private final double relationCoefficient;

    InteractionType(double relationCoefficient) {
        this.relationCoefficient = relationCoefficient;
    }

    public double getRelationCoefficient() {
        return relationCoefficient;
    }
}
