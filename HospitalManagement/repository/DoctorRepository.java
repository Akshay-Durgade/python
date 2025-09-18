package HospitalManagement.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import HospitalManagement.enums.Specialization;
import HospitalManagement.model.Doctor;

public class DoctorRepository {
  private final Map<UUID, Doctor> doctorMap = new HashMap<>();

  public void save(Doctor doctor) {
    doctorMap.put(doctor.getId(), doctor);
  }

  public Doctor findById(UUID id) {
    return doctorMap.get(id);
  }

  public List<Doctor> findBySpecialization(Specialization specialization) {
    List<Doctor> result = new ArrayList<>();

    for(Doctor doc : doctorMap.values()) {
      if(doc.getSpecialization() == specialization) {
        result.add(doc);
      }
    }

    return result;
  }
}
