package ee.taltech.iti0202.exam.hospital;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private String name;
    private List<IllnessType> specialties;
    private List<Patient> patients;
    private int healedPatients;

    /**
     * Constructor for creating a new Doctor.
     * @param name        The name of the doctor.
     * @param specialties The list of IllnessTypes the doctor specializes in.
     */
    public Doctor(String name, List<IllnessType> specialties) {
        this.name = name;
        this.specialties = specialties;
        this.patients = new ArrayList<>();
        this.healedPatients = 0;
    }

    /**
     * Returns the name of the doctor.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of IllnessTypes the doctor specializes in.
     */
    public List<IllnessType> getSpecialties() {
        return specialties;
    }

    /**
     * Returns the list of patients under this doctor's care.
     */
    public List<Patient> getPatients() {
        return patients;
    }

    /**
     * Returns the number of patients this doctor has healed.
     */
    public int getHealedPatientsAmount() {
        return healedPatients;
    }

    /**
     * Increase healed patient number.
     */
    public void increaseHealedPatientsAmount() {
        healedPatients++;
    }

    /**
     * Adds a patient to the doctor's care if there is space (max 10 patients).
     * @param patient The patient to add.
     */
    public void addPatient(Patient patient) {
        if (patients.size() < 10) {
            patients.add(patient);
        }
    }

    /**
     * Remove patient.
     */
    public void removePatient(Patient patient) {
        patients.remove(patient);
    }

}
