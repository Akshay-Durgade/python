package HospitalManagement.repository;

import java.util.HashMap;
import java.util.UUID;

import HospitalManagement.model.Patient;

public class PatientRepository {
  private final HashMap<UUID, Patient> patientMap = new HashMap<>();

  public void save(Patient patient) {
    patientMap.put(patient.getId(), patient);
  }

  public Patient findById(UUID id) {
    return patientMap.get(id);
  }
}
