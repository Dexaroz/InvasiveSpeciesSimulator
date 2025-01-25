package software.dexterity.arquitecture.model;

public enum InteractionType {
    Depredation(-0.7),
    Symbiosis(1.2),
    Neutral(1),
    Competition(-0.3);

    private final double relationCoefficient;

    InteractionType(double relationCoefficient) {
        this.relationCoefficient = relationCoefficient;
    }

    public double getRelationCoefficient() {
        return relationCoefficient;
    }
}
