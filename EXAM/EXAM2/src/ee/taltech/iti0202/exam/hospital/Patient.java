package ee.taltech.iti0202.exam.hospital;

public class Patient {
    private String name;
    private int age;
    private IllnessType diseaseType;

    /**
     * Constructor for creating a new Patient.
     * @param name        The name of the patient.
     * @param age         The age of the patient.
     * @param diseaseType The type of illness the patient has.
     */
    public Patient(String name, int age, IllnessType diseaseType) {
        this.name = name;
        if (age < 0) {
            throw new IllegalArgumentException();
        } else {
            this.age = age;
        }
        this.diseaseType = diseaseType;
    }

    /**
     * Returns the name of the patient.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the age of the patient.
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the type of illness the patient has.
     */
    public IllnessType getDiseaseType() {
        return diseaseType;
    }

    /**
     * Set disease type.
     */
    public void setDiseaseType() {
        diseaseType = null;
    }
}
