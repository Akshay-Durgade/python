package process_events.service;

import process_events.model.Event;
import process_events.repository.EventRepository;
import process_events.exceptions.ProcessorCrashedException;

import java.util.List;

public class EventProcessor {
    private final EventRepository repo;
    private int nextExpectedId = 1;
    private boolean crashed = false;

    private final int maxEventsPerSecond;
    private long lastWindowStart = System.currentTimeMillis();
    private int eventsInWindow = 0;

    public EventProcessor(EventRepository repo, int maxEventsPerSecond) {
        this.repo = repo;
        this.maxEventsPerSecond = maxEventsPerSecond;
    }

    public void ingest_event(int eventId, String payload) throws InterruptedException {
        throttle();

        if (crashed) {
            throw new ProcessorCrashedException("Processor is crashed.");
        }

        if (repo.isProcessed(eventId, payload)) {
            System.out.println("Duplicate event ignored: " + eventId);
            return;
        }

        Event e = new Event(eventId, payload);
        repo.bufferEvent(e);

        processBufferedEvents();
    }

    private void processBufferedEvents() {
        while (true) {
            Event next = repo.pollBuffered(nextExpectedId);
            if (next == null) break;

            repo.markProcessed(next);
            nextExpectedId++;
        }
    }

    public void simulate_failure() {
        crashed = true;
        throw new ProcessorCrashedException("Processor crashed");
    }

    public void recover() {
        crashed = false;
        System.out.println("recover(): processor recovered.");
        processBufferedEvents();
    }

    // Return processed events with both ID and payload
    public List<Event> get_processed_events() {
        return repo.getProcessedEvents();
    }

    private void throttle() throws InterruptedException {
        if (maxEventsPerSecond <= 0) return;

        long now = System.currentTimeMillis();
        if (now - lastWindowStart >= 1000) {
            lastWindowStart = now;
            eventsInWindow = 0;
        }

        if (eventsInWindow >= maxEventsPerSecond) {
            long sleepTime = 1000 - (now - lastWindowStart);
            if (sleepTime > 0) {
                Thread.sleep(sleepTime);
            }
            lastWindowStart = System.currentTimeMillis();
            eventsInWindow = 0;
        }
        eventsInWindow++;
    }
}
