package process_events.model;

// Create a Entity of Events
public class Event {
  private final int eventId;
  private final String payLoad;

  public Event(int eventId, String payLoad) {
    this.eventId = eventId;
    this.payLoad = payLoad;
  }

  public int getEventId() {
    return eventId;
  }

  public String getPayLoad() {
    return payLoad;
  }

  @Override
  public String toString() {
      return eventId + ":" + payLoad; // cleaner output
  }
}
