package ee.taltech.iti0202.api.student;

public class Grade {
    private int grade;
    private String assignment;

    /**
     * Constructs a grade.
     * @param grade
     * @param assignment
     */
    public Grade(int grade, String assignment) {
        this.grade = grade;
        this.assignment = assignment;
    }

    /**
     * Method to get grade.
     * @return grade
     */
    public int getGrade() {
        return grade;
    }

    /**
     * Method to get assignment.
     * @return assignment
     */
    public String getAssignment() {
        return assignment;
    }
}
