package ee.taltech.iti0202.files.input;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputFilesScanner implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        try {
            Scanner s = new Scanner(new File(filename));

                while (s.hasNextLine()) {
                    lines.add(s.nextLine());
                }
                s.close();
            } catch (FileReaderException | IOException e) {
            e.getCause();
            return null;
        }
        return lines;
    }
}