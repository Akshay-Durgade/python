package process_events.exceptions;

// Custom exception for crash
public class ProcessorCrashedException extends RuntimeException {
    public ProcessorCrashedException(String message) {
        super(message);
    }
}
