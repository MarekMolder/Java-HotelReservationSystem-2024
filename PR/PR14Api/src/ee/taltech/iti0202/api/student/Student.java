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

    public Student(String name) {
        this.id = getAndIncrementNextId();
        this.name = name;
    }

    public void addGrade(Grade grade) {
        this.grades.add(grade);
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public List<Grade> getGrades() {
        return this.grades;
    }

    public void setName(String name) {
        this.name = name;
    }
}
