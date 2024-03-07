package ee.taltech.iti0202.files.input;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FileReaderExceptionTest {
    @Test
    public void exampleTest() {
        InputFilesBufferReader bufferReader = new InputFilesBufferReader();

        assertThrows(FileReaderException.class, () -> bufferReader.readTextFromFile("doesnt-exist.txt"));
    }
}