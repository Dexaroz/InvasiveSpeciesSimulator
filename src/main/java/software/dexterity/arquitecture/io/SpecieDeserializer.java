package software.dexterity.arquitecture.io;

import software.dexterity.arquitecture.model.Specie;

public interface SpecieDeserializer {
    Specie deserialize(String line);
}
