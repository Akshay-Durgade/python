package process_events.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import process_events.exceptions.PayloadMismatchException;
import process_events.model.Event;

public class EventRepository {
    private final Map<Integer, String> processedEvents = new LinkedHashMap<>();
    private final Map<Integer, Event> buffer = new HashMap<>();

    public boolean isProcessed(int eventId, String payload) {
        if (processedEvents.containsKey(eventId)) {
            String existingPayload = processedEvents.get(eventId);
            if (!existingPayload.equals(payload)) {
                throw new PayloadMismatchException(
                        "Conflict: Event " + eventId +
                        " was already processed with payload '" + existingPayload +
                        "', new payload = '" + payload + "'"
                );
            }
            return true;
        }
        return false;
    }

    public void markProcessed(Event e) {
        processedEvents.put(e.getEventId(), e.getPayLoad());
    }

    public List<Event> getProcessedEvents() {
        List<Event> result = new ArrayList<>();
        for (Map.Entry<Integer, String> entry : processedEvents.entrySet()) {
            result.add(new Event(entry.getKey(), entry.getValue()));
        }
        return result;
    }

    public void bufferEvent(Event e) {
        int id = e.getEventId();
        if (!buffer.containsKey(id) && !processedEvents.containsKey(id)) {
            buffer.put(id, e);
        }
    }

    public Event pollBuffered(int eventId) {
        return buffer.remove(eventId);
    }
}