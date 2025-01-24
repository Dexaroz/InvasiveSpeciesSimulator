package software.dexterity.arquitecture.io;

import software.dexterity.arquitecture.model.Specie;

public class CsvSpecieDeserializer implements SpecieDeserializer {
    @Override
    public Specie deserialize(String line) {
        return deserializer(line.split(","));
    }

    private Specie deserializer(String[] fields) {
        return new Specie(fields[1], fields[2], fields[3], fields[4], fields[5]);
    }
}
