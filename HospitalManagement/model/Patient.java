package HospitalManagement.model;

import java.util.UUID;

public class Patient {
  private final UUID id;
  private String name;

  public Patient(UUID id, String name) {
    this.id = UUID.randomUUID();
    this.name = name;
  }

  public Patient(String name) {
    this.id = UUID.randomUUID();
    this.name = name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
