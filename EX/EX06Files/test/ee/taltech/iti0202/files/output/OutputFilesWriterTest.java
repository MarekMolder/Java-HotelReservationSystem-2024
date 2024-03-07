package ee.taltech.iti0202.files.output;

import ee.taltech.iti0202.files.input.InputFilesLines;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OutputFilesWriterTest {
    InputFilesLines lines = new InputFilesLines();
    OutputFilesWriter write = new OutputFilesWriter();

    @Test
    public void testWriteLinesToFile() {
        write.writeLinesToFile(new ArrayList<>(Arrays.asList("tere sa oled, kole", "mis su nimi on?")), "test.txt");
        List<String> expected = new ArrayList<>(Arrays.asList("tere sa oled, kole", "mis su nimi on?"));
        List<String> result = lines.readTextFromFile("test.txt");
        assertEquals(expected, result);
    }

    @Test
    public void testWriteLinesToFileNoFile() {
        assertEquals(false, write.writeLinesToFile(new ArrayList<>(Arrays.asList(
                "tere sa oled, kole", "mis su nimi on?")), ""));
    }

    @Test
    public void testWriteLinesToFileDoesntExist() {
        assertEquals(false, write.writeLinesToFile(new ArrayList<>(Arrays.asList(
                "tere sa oled, kole", "mis su nimi on?")), "??.txt"));
    }
}
