package HospitalManagement.model;

import java.util.*;
import HospitalManagement.enums.Specialization;

public class Doctor {
  private final UUID id;
  private String name;
  private Specialization specialization;
  private final Map<String,Boolean> availability = new HashMap<>();
  private double rating;

  public Doctor(UUID id, String name, Specialization specialization, double rating) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.specialization = specialization;
    this.rating = rating;
  }

  public UUID getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public Specialization getSpecialization() {
    return specialization;
  }
  public Map<String, Boolean> getAvailability() {
    return availability;
  }
  public double getRating() {
    return rating;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setSpecialization(Specialization specialization) {
    this.specialization = specialization;
  }
  public void setRating(double rating) {
    this.rating = rating;
  }

}
