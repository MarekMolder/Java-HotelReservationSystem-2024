package ee.taltech.iti0202.exam.hospital;

import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private List<Doctor> doctors;
    private List<Patient> patients;

    /**
     * Constructor for creating a new Hospital.
     * @param doctors  The list of doctors in the hospital.
     */
    public Hospital(List<Doctor> doctors) {
        this.doctors = doctors;
        this.patients = new ArrayList<>();
    }

    /**
     * Returns the list of patients in the hospital.
     */
    public List<Patient> getPatients() {
        return patients;
    }

    /**
     * Returns the list of doctors in the hospital.
     */
    public List<Doctor> getDoctors() {
        return doctors;
    }

    /**
     * Adds a patient to a hospital.
     * @param patient The patient to add.
     */
    public void addPatient(Patient patient) {
        if (!patients.contains(patient)) {
            patients.add(patient);
        }
    }

    /**
     * Adds a patient to a doctor's care if the doctor specializes in the patient's illness type.
     * If patient is not in hospital patients list, then patient should be added to the hospital.
     * @param patient The patient to add.
     * @param doctor  The doctor to add the patient to.
     */
    public void addPatientToDoctor(Patient patient, Doctor doctor) {
        if (doctors.contains(doctor) && doctor.getSpecialties().contains(patient.getDiseaseType())) {
            addPatient(patient);
            if (doctor.getPatients().size() < 10) {
                doctor.addPatient(patient);
            }
        }
    }

    /**
     * Heals a patient by removing them from the doctor's care and increasing the doctor's healed patients count.
     * @param patient The patient to heal.
     * @return True if the patient was successfully healed, false otherwise.
     */
    public boolean healPatient(Patient patient) {
        for (Doctor doctor: getDoctors()) {
            if (doctor.getPatients().contains(patient)) {
                patient.setDiseaseType();
                doctor.removePatient(patient);
                doctor.increaseHealedPatientsAmount();
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the name of the best doctor based on the number of healed patients. If number of healed patients is equal
     * when choose doctor with more specializations.
     * @return The name of the best doctor.
     */
    public String getBestDoctor() {
        Doctor doctor = null;
        for (Doctor doctor1: doctors) {
            if (doctor == null) {
                doctor = doctor1;
            }else if (doctor1.getHealedPatientsAmount() < doctor.getHealedPatientsAmount()) {
                doctor = doctor1;
            } else if (doctor1.getHealedPatientsAmount() == doctor.getHealedPatientsAmount()) {
                if (doctor1.getSpecialties().size() > doctor.getSpecialties().size()) {
                    doctor = doctor1;
                }
            }
        }
        assert doctor != null;
        return doctor.getName();
    }
}

