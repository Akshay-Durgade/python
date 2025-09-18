package HospitalManagement.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;

import HospitalManagement.model.Booking;

public class BookingRepositiory {
  private final Map<UUID, Booking> bookingMap = new HashMap<>();
  private final Map<String, Queue<UUID>> waitList = new HashMap<>();

  public void save(Booking booking) {
    bookingMap.put(booking.getId(), booking);
  }

  public void delete(Booking booking) {
    bookingMap.remove(booking.getId());
  }

  public Booking getBookingId(UUID id) {
    return bookingMap.get(id);
  }

  public List<Booking> findByDoctor(UUID doctorID) {
    List<Booking> doctorBooking = new ArrayList<>();
    for(Booking b : bookingMap.values()) {
      doctorBooking.add(b);
    }
    return doctorBooking;
  }

  public List<Booking> findByPatient(UUID doctorID) {
    List<Booking> patientBooking = new ArrayList<>();
    for(Booking b : bookingMap.values()) {
      patientBooking.add(b);
    }
    return patientBooking;
  }
}
