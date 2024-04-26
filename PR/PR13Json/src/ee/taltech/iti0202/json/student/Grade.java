package ee.taltech.iti0202.json.student;

public class Grade {
    private int grade;
    private String assignment;

    /**
     * Constructs a grade.
     * @param grade Integer value of the grade.
     * @param assignment grade for?
     */
    public Grade(int grade, String assignment) {
        this.grade = grade;
        this.assignment = assignment;
    }

    /**
     * Method to get grade value.
     * @return grade.
     */
    public int getGrade() {
        return grade;
    }

    /**
     * Method to get assignment.
     * @return assignment.
     */
    public String getAssignment() {
        return assignment;
    }

}