package ee.taltech.iti0202.json.school;

import ee.taltech.iti0202.json.student.Student;

import java.util.ArrayList;
import java.util.List;

public class School {

    private final List<Student> students = new ArrayList<>();
    private final String name;

    /**
     * Constructs a new school with given name.
     * @param name Name of the school
     */
    public School(String name) {
        this.name = name;
    }

    /**
     * Method to add student in to the school.
     * @param student Student to be added.
     */
    public void addStudent(Student student) {
        this.students.add(student);
    }

    /**
     * Method to remove a student from the school.
     * @param student Student to be removed.
     */
    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    /**
     * Method to get list of students.
     * @return List of students
     */
    public List<Student> getStudents() {
        return this.students;
    }

    /**
     * Method to get school name.
     * @return School name
     */
    public String getName() {
        return this.name;
    }
}
