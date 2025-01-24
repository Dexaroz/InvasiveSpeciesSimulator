package software.dexterity.arquitecture.io;

import software.dexterity.arquitecture.model.Specie;

import java.io.IOException;
import java.util.List;

public interface SpecieLoader {
    List<Specie> loads() throws IOException;
}
