package software.dexterity.arquitecture.io;

import software.dexterity.arquitecture.model.Specie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoaderSpecie implements SpecieLoader {
    private final SpecieDeserializer deserializer;
    private final File file;

    public FileLoaderSpecie(SpecieDeserializer deserializer, File file) {
        this.deserializer = deserializer;
        this.file = file;
    }

    @Override
    public List<Specie> loads() throws IOException {
        List<Specie> species = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String line = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null){
                species.add(deserializer.deserialize(line));
            }
        }

        return species;
    }
}
