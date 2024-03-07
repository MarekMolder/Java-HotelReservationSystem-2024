package ee.taltech.iti0202.files.input;

import java.util.List;

public interface InputFilesReader {

    /**
     * Interface defines a method to read text from a file and
     * return it as a list of strings.
     * @param filename
     * @return
     */
    List<String> readTextFromFile(String filename);
}
