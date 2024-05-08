package ee.taltech.iti0202.api.student;

import java.util.ArrayList;
import java.util.List;

public class Student {

    public static int nextId;
    private static int getAndIncrementNextId() {
        return ++nextId;
    }

    private final int id;
    private String name;
    private final List<Grade> grades = new ArrayList<>();

    /**
     * Constructs a new student.
     * @param name
     */
    public Student(String name) {
        this.id = getAndIncrementNextId();
        this.name = name;
    }

    /**
     * Method to add grade
     * @param grade
     */
    public void addGrade(Grade grade) {
        this.grades.add(grade);
    }

    /**
     * Method to get name
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method get id
     * @return
     */
    public int getId() {
        return this.id;
    }

    /**
     * Method to get grades
     * @return
     */
    public List<Grade> getGrades() {
        return this.grades;
    }

    /**
     * Method to set name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
