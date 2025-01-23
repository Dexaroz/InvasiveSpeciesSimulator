package software.dexterity.arquitecture.model;

public record Specie(String name, int population, double growthRate, double carryingCapacity) {
    public Specie updatePopulation(int newPopulation){
        return new Specie(name, newPopulation, growthRate, carryingCapacity);
    }
}
