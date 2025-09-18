package process_events;

import process_events.exceptions.PayloadMismatchException;
import process_events.exceptions.ProcessorCrashedException;
import process_events.repository.EventRepository;
import process_events.service.EventProcessor;

public class ProcessEvents {
  public static void main(String args[]) throws InterruptedException {
    EventRepository repo = new EventRepository();
        EventProcessor system = new EventProcessor(repo, 3); // max 3 events/sec

        system.ingest_event(2, "payload_2");
        system.ingest_event(1, "payload_1");
        system.ingest_event(2, "payload_2"); // duplicate → ignored
        system.ingest_event(4, "payload_4");

        // Unlock pipeline
        system.ingest_event(3, "payload_3");

        System.out.println("Processed: " + system.get_processed_events());

        try {
            // Different payload for same ID → should fail
            system.ingest_event(2, "corrupted_payload");
        } catch (PayloadMismatchException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            system.simulate_failure();
        } catch (ProcessorCrashedException e) {
            System.out.println("During crash: " + e.getMessage());
        }

        try {
            system.ingest_event(5, "payload_5"); // should fail (still crashed)
        } catch (ProcessorCrashedException e) {
            System.out.println("Error: " + e.getMessage());
        }

        system.recover();
        system.ingest_event(5, "payload_5");

        System.out.println("Final processed: " + system.get_processed_events());
  }
}
