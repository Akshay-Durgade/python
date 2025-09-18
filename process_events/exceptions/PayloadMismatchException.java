package process_events.exceptions;

public class PayloadMismatchException extends RuntimeException {
    public PayloadMismatchException(String message) {
        super(message);
    }
}
