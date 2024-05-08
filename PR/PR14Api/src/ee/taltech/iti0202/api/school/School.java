package ee.taltech.iti0202.api.school;
import ee.taltech.iti0202.api.student.Student;

import java.util.ArrayList;
import java.util.List;

public class School {

    private final List<Student> students = new ArrayList<>();
    private final String name;

    /**
     * Constructs a new school with name.
     * @param name
     */
    public School(String name) {
        this.name = name;
    }

    /**
     * Method to add student in to the school.
     * @param student
     */
    public void addStudent(Student student) {
        this.students.add(student);
    }

    /**
     * Method to remove student from school.
     * @param student
     */
    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    /**
     * Method to get students from school.
     * @return list of students
     */
    public List<Student> getStudents() {
        return this.students;
    }

    /**
     * Method to get name of the school.
     * @return name
     */
    public String getName() {
        return this.name;
    }
}
