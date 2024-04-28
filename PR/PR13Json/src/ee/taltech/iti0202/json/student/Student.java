package ee.taltech.iti0202.json.student;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private static int nextId;
    private static int getAndIncrementNextId() {
        return ++nextId;
    }

    private final int id;
    private final String name;
    private final List<Grade> grades = new ArrayList<>();

    /**
     * Constructs a new student with id and name.
     * @param name Name of the student
     */
    public Student(String name) {
        this.id = getAndIncrementNextId();
        this.name = name;
    }

    /**
     * Method to add grade to a student.
     * @param grade Grade to be added to new student.
     */
    public void addGrade(Grade grade) {
        this.grades.add(grade);
    }

    /**
     * Method to get name of the student.
     * @return Name of the student.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method to get id of the student.
     * @return Id of the student.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Method to get grades of the student.
     * @return grades of the student.
     */
    public List<Grade> getGrades() {
        return this.grades;
    }
}
