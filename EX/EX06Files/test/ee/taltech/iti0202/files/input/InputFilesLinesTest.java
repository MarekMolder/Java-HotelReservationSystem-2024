package ee.taltech.iti0202.files.input;

import ee.taltech.iti0202.files.output.OutputFilesWriter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputFilesLinesTest {
    InputFilesLines lines = new InputFilesLines();
    OutputFilesWriter write = new OutputFilesWriter();

    @Test
    public void testWriteLinesToFile() {
        write.writeLinesToFile(new ArrayList<>(Arrays.asList("tere sa oled, kole", "mis su nimi on?")), "test.txt");
        List<String> expected = new ArrayList<>(Arrays.asList("tere sa oled, kole"
                , "mis su nimi on?"));
        List<String> result = lines.readTextFromFile("test.txt");
        assertEquals(expected, result);
    }

    @Test
    public void testReadTextFromFileButFileNull() {
        assertEquals(null, lines.readTextFromFile(null));
    }

    @Test
    public void testReadTextFromFileDoesntExist() {
        assertThrows(FileReaderException.class, () -> lines.readTextFromFile("doesnt-exist.txt"));
    }

}