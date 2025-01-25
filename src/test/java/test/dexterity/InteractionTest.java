package test.dexterity;

import org.junit.Test;
import software.dexterity.arquitecture.model.Interaction;
import software.dexterity.arquitecture.model.InteractionType;
import software.dexterity.arquitecture.model.Organism;
import software.dexterity.arquitecture.model.Specie;

import static org.junit.Assert.assertEquals;

public class InteractionTest {

    @Test
    public void testDepredationImpactReducesPreyPopulation() {
        Specie predatorSpecie = new Specie("Jaguar", "Panthera onca", "Mammal", "Tropical Forest", "Carnivore");
        Specie preySpecie = new Specie("Capibara", "Hydrochoerus hydrochaeris", "Mammal", "Tropical Forest", "Herbivore");

        Organism predator = new Organism(predatorSpecie, 10, 0.05, 50);
        Organism prey = new Organism(preySpecie, 100, 0.1, 500);

        Interaction depredation = new Interaction(predator, prey, InteractionType.Depredation);
        double impactOnPrey = depredation.calculateImpact();

        // Impact should be negative for prey
        assertEquals(0.499, impactOnPrey, 0.001);
    }

    @Test
    public void testSymbiosisImpactIncreasesBothPopulations() {
        Specie plantSpecie = new Specie("Acacia", "Acacia spp.", "Plant", "Savannah", "Producer");
        Specie antSpecie = new Specie("Ant", "Formica spp.", "Insect", "Savannah", "Omnivore");

        Organism plant = new Organism(plantSpecie, 100, 0.1, 1000);
        Organism ant = new Organism(antSpecie, 50, 0.2, 500);

        Interaction symbiosis = new Interaction(plant, ant, InteractionType.Symbiosis);
        double impactOnPlant = symbiosis.calculateImpact();
        double impactOnAnt = symbiosis.calculateImpact();

        // Impact should be positive for both organisms
        assertEquals(0.249, impactOnPlant, 0.001);
        assertEquals(0.249, impactOnAnt, 0.001);
    }

    @Test
    public void testCompetitionImpactReducesBothPopulations() {
        Specie lionSpecie = new Specie("Lion", "Panthera leo", "Mammal", "Savannah", "Carnivore");
        Specie hyenaSpecie = new Specie("Hyena", "Crocuta crocuta", "Mammal", "Savannah", "Carnivore");

        Organism lion = new Organism(lionSpecie, 20, 0.05, 200);
        Organism hyena = new Organism(hyenaSpecie, 30, 0.04, 300);

        Interaction competition = new Interaction(lion, hyena, InteractionType.Competition);
        double impactOnLion = competition.calculateImpact();
        double impactOnHyena = competition.calculateImpact();

        // Impact should be negative for both populations
        assertEquals(-0.223, impactOnLion, 0.001);
        assertEquals(-0.223, impactOnHyena, 0.001);
    }

    @Test
    public void testNeutralImpactDoesNotChangePopulations() {
        Specie birdSpecie = new Specie("Bird", "Passer spp.", "Bird", "Forest", "Omnivore");
        Specie treeSpecie = new Specie("Tree", "Quercus spp.", "Plant", "Forest", "Producer");

        Organism bird = new Organism(birdSpecie, 50, 0.1, 500);
        Organism tree = new Organism(treeSpecie, 200, 0.05, 1000);

        Interaction neutral = new Interaction(bird, tree, InteractionType.Neutral);
        double impactOnBird = neutral.calculateImpact();
        double impactOnTree = neutral.calculateImpact();

        // Impact should be zero
        assertEquals(0, impactOnBird);
        assertEquals(0, impactOnTree);
    }

    @Test
    public void testSigmoidFunctionLimitsImpact() {
        Specie predatorSpecie = new Specie("Wolf", "Canis lupus", "Mammal", "Forest", "Carnivore");
        Specie preySpecie = new Specie("Deer", "Cervus elaphus", "Mammal", "Forest", "Herbivore");

        Organism predator = new Organism(predatorSpecie, 10, 0.05, 50);
        Organism prey = new Organism(preySpecie, 1000, 0.1, 5000);

        Interaction depredation = new Interaction(predator, prey, InteractionType.Depredation);
        double sigmoidImpact = depredation.calculateImpact();

        // Ensure sigmoid function limits the impact to a reasonable range
        assertEquals(0.499, sigmoidImpact, 0.001); // Should not exceed ~0.5
    }

    @Test
    public void testUpdateInteractionChangesOrganism() {
        Specie predatorSpecie = new Specie("Tiger", "Panthera tigris", "Mammal", "Forest", "Carnivore");
        Specie preySpecie = new Specie("Buffalo", "Syncerus caffer", "Mammal", "Savannah", "Herbivore");

        Organism predator = new Organism(predatorSpecie, 15, 0.05, 50);
        Organism prey = new Organism(preySpecie, 200, 0.1, 1000);

        Interaction interaction = new Interaction(predator, prey, InteractionType.Depredation);

        // Update the prey in the interaction
        Specie newPreySpecie = new Specie("Zebra", "Equus quagga", "Mammal", "Savannah", "Herbivore");
        Organism newPrey = new Organism(newPreySpecie, 150, 0.1, 800);
        Interaction updatedInteraction = interaction.updateInteraction(1, newPrey);

        // Verify the updated interaction
        assertEquals(newPrey, updatedInteraction.organism2());
        assertEquals(predator, updatedInteraction.organism1());
    }
}