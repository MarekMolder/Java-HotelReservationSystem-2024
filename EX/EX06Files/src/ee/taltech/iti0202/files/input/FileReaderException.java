package ee.taltech.iti0202.files.input;

public class FileReaderException extends RuntimeException {

    /**
     * Exception thrown to indicate that a specific
     *
     * @param message
     * @param reason
     */
    public FileReaderException(String message, Throwable reason) {
        super(message, reason);
    }

}
