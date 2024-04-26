package ee.taltech.iti0202.json.student;

public class Grade {
    private int grade;
    private String assignment;
    public Grade(int grade, String assignment) {
        this.grade = grade;
        this.assignment = assignment;
    }

    public int getGrade() {
        return grade;
    }

    public String getAssignment() {
        return assignment;
    }

}