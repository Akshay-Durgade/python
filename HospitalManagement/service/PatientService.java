package HospitalManagement.service;

import java.util.UUID;

import HospitalManagement.exception.PatientNotFoundException;
import HospitalManagement.model.Patient;
import HospitalManagement.repository.PatientRepository;

public class PatientService {
  private final PatientRepository repo;

  public PatientService(PatientRepository repo) {
    this.repo = repo;
  }

  public Patient register(String name) {
    Patient p = new Patient(name);
    repo.save(p);
    return p;
  }

  public Patient findById(UUID id) {
    Patient patient = repo.findById(id);
    if (patient == null) {
      throw new PatientNotFoundException("Patient Not Found");
    }
    return patient;
  }
}
