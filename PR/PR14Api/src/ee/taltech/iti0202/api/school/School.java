package ee.taltech.iti0202.api.school;
import ee.taltech.iti0202.api.student.Student;

import java.util.ArrayList;
import java.util.List;

public class School {

    private final List<Student> students = new ArrayList<>();
    private final String name;
    public School(String name) {
        this.name = name;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public String getName() {
        return this.name;
    }
}
