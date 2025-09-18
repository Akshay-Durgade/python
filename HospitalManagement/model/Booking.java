package HospitalManagement.model;

import java.util.*;

public class Booking {
  private final UUID id;
  private final UUID patientID;
  private final UUID doctorID;
  private final String slot;

  public Booking(UUID id, UUID patientID, UUID doctorID, String slot) {
    this.id = UUID.randomUUID();
    this.patientID = patientID;
    this.doctorID = doctorID;
    this.slot = slot;
  }

  public UUID getId() {
    return id;
  }

  public UUID getPatientID() {
    return patientID;
  }

  public UUID getDoctorID() {
    return doctorID;
  }

  public String getSlot() {
    return slot;
  }

  
}
