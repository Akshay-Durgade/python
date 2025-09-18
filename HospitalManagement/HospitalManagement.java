package HospitalManagement;

import HospitalManagement.model.Patient;
import HospitalManagement.repository.PatientRepository;
import HospitalManagement.service.PatientService;

public class HospitalManagement {
  public static void main(String args[]) {
    PatientRepository patientRepository = new PatientRepository();

    PatientService patientService = new PatientService(patientRepository);


    Patient p1 = patientService.register("Patient1");
    System.out.print(p1.getId());
  }
}
