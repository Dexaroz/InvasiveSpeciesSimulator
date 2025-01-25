package test.dexterity;

import org.junit.Test;
import software.dexterity.arquitecture.model.*;

import static org.junit.Assert.assertEquals;

public class HabitatSimulatorTest {

    @Test
    public void testSimulationStep() {
        Habitat habitat = new Habitat();

        Specie jaguar = new Specie("Jaguar", "Panthera onca", "Mammal", "Tropical Forest", "Carnivore");
        Specie capibara = new Specie("Capibara", "Hydrochoerus hydrochaeris", "Mammal", "Tropical Forest", "Herbivore");

        Organism jaguarOrganism = new Organism(jaguar, 10, 0.05, 50);
        Organism capibaraOrganism = new Organism(capibara, 100, 0.1, 500);

        habitat.addOrganisms(jaguarOrganism);
        habitat.addOrganisms(capibaraOrganism);
        habitat.addInteraction(jaguarOrganism, capibaraOrganism, InteractionType.Depredation);

        HabitatSimulator simulator = new HabitatSimulator(habitat, 1);

        // Simula un paso
        simulator.simulateSteps();

        Organism updatedJaguar = habitat.getOrganisms().stream()
                .filter(o -> o.specie().commonName().equals("Jaguar"))
                .findFirst()
                .orElseThrow();

        Organism updatedCapibara = habitat.getOrganisms().stream()
                .filter(o -> o.specie().commonName().equals("Capibara"))
                .findFirst()
                .orElseThrow();

        // Verifica que las poblaciones se actualizan correctamente
        //assertEquals(11, updatedJaguar.population()); // Jaguar crece por depredación
        assertEquals(95, updatedJaguar.population()); // Capibara disminuye por depredación
    }
}
