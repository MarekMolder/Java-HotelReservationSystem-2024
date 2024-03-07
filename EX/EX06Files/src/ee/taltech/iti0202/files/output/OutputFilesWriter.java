package ee.taltech.iti0202.files.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OutputFilesWriter {

    /**
     * Method to write lines into file.
     * @param lines
     * @param filename
     * @return
     */
    public boolean writeLinesToFile(List<String> lines, String filename) {
        if (filename.isEmpty()) {
            return false;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String text : lines) {
                writer.write(text);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
