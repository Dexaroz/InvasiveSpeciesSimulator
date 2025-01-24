package software.dexterity.arquitecture.model;

public record Organism(Specie specie, int population, double growthRate, double carryingCapacity) {
    public Organism updatePopulation(int newPopulation){
        return new Organism(specie, newPopulation, growthRate, carryingCapacity);
    }
}
