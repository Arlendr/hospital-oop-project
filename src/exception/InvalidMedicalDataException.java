package exception;

public class InvalidMedicalDataException extends Exception {
    public InvalidMedicalDataException(String message) {
        super(message);
    }

    public InvalidMedicalDataException(String message, Throwable cause) {
        super(message, cause);
    }
}