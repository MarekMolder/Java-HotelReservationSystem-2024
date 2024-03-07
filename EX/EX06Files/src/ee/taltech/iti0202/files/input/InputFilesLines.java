package ee.taltech.iti0202.files.input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InputFilesLines implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) {
        List<String> text = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));

            text.addAll(lines);

        } catch (FileReaderException | IOException e) {
            e.getCause();
            return null;
        }
        return text;
    }
}