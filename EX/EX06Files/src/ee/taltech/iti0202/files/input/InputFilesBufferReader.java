package ee.taltech.iti0202.files.input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputFilesBufferReader implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) {
        if (filename == null) {
            return null;
        }
        BufferedReader reader;
        List<String> lines = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

            reader.close();
        } catch (FileReaderException | IOException e) {
            e.getCause();
            throw new FileReaderException("No such file", e);
        }
        return lines;
    }
}
