package ee.taltech.iti0202.schools;

import ee.taltech.iti0202.location.Location;
import ee.taltech.iti0202.student.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class School implements Comparable<School> {

    private final Location location;
    private final String name;
    private final ArrayList<Student> students;
    private static final ArrayList<School> schools = new ArrayList<>();


    /**
     * Construct a new school with a name and Location.
     * @param name name of school
     * @param location Location of school
     */
    protected School(String name, Location location) {
        this.name = name;
        this.location = location;
        this.students = new ArrayList<>();
        schools.add(this);
    }


    /**
     * Adds student to list of students
     * @param student Student
     */
    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
        }
    }



    /**
      * Returns name of school
      * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
      * Returns location of school
      * @return Location
     */
    public Location getLocation() {
        return this.location;
    }

    /**
      * Returns List of students in school
      * @return List of students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Comparing order:
     *  1. By class
     *  2. By amount of student
     *  3. By country name
     *  4. By city name
     *  5. By school name
     * @param o the object to be compared.
     * @return int -1, 0, or 1
     */
    @Override
    public int compareTo(School o) {
        int insertedOven = getPriority(o.getClass());
        int thisOven = getPriority(this.getClass());

        if (insertedOven != thisOven) {
            return Integer.compare(thisOven, insertedOven);
        } else if (this.students.size() != o.students.size()) {
            return Integer.compare(o.students.size(), this.students.size());
        } else if (this.location.country().equals(o.location.country())) {
            return this.location.country().compareTo(o.location.country());
        } else if (this.location.equals(o.location)) {
            return this.location.city().compareTo(o.location.city());
        } else if (this.name.equals(o.name)) {
            return this.name.compareTo(o.name);
        } else {
            return 0;
        }
    }

    private int getPriority(Class<? extends School> clazz) {
        if (clazz.equals(University.class)) {
            return 3;
        } else if (clazz.equals(SecondarySchool.class)) {
            return 2;
        } else {
            return 1;
        }
    }
    
    /**
     * Adds given school to a list containing all Schools.
     * Does not add it to list if it's already added.
     * @param school School
     */
    public static void addSchool(School school) {
        if (!schools.contains(school)) {
            schools.add(school);
        }
    }

    /**
     * Clears list containing all schools
     */
    public static void clearSchools() {
        schools.clear();
    }

    /**
     * Returns sorted List of all schools.
     * @return sorted list of schools
     */
    public static List<School> getSchools() {
        Collections.sort(schools, (o1, o2) -> o1.compareTo(o2));
        return schools;
    }
}
