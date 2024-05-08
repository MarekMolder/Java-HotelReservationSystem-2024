package ee.taltech.iti0202.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ee.taltech.iti0202.api.school.School;

import java.util.*;

import ee.taltech.iti0202.api.student.Grade;
import ee.taltech.iti0202.api.student.Student;


public class SchoolDatabase {

    //DO NOT CHANGE
    public List<School> schools = new ArrayList<>();

    public SchoolDatabase(String jsonContent) {
        //DO NOT CHANGE
        loadDatabase(jsonContent);
    }

    private void loadDatabase(String jsonContent) {
        Gson gson = new Gson();
        schools = gson.fromJson(jsonContent, new TypeToken<List<School>>() {}.getType());

        int maxId = schools.stream()
                .flatMap(school -> school.getStudents().stream())
                .mapToInt(Student::getId)
                .max().orElse(0);
        Student.nextId = maxId + 1;
    }

    /**
     * Endpoints (note, all results should be in json except the 404)
     * - /student/grades?studentId=studentId - get student's grades by id (return json array of grade classes)
     * - /school/students?schoolName=schoolName - get all students in school by schoolNam (return all fields except grades in Student class)
     *  - /schools - return all school names (return json array of just school's names)
     *  If school name or student's id doesn't exist, return string 404
     * @param path - endpoint path
     * @return - result, if there is no result, return 404 in string
     */
    public String get(String path) {
        if (path.startsWith("/student/grades")) {
            return getStudentGrades(path);
        } else if (path.startsWith("/school/students")) {
            return getSchoolStudents(path);
        } else if (path.equals("/schools")) {
            return getAllSchools();
        }
        return "404";
    }

    private String getStudentGrades(String path) {
        int studentId = Integer.parseInt(path.split("studentId=")[1]);
        for (School school : schools) {
            for (Student student : school.getStudents()) {
                if (student.getId() == studentId) {
                    return new Gson().toJson(student.getGrades());
                }
            }
        }
        return "404";
    }

    private String getSchoolStudents(String path) {
        String schoolName = path.split("schoolName=")[1];
        Optional<School> school = schools.stream()
                .filter(s -> s.getName().equals(schoolName))
                .findFirst();
        if (school.isPresent()) {
            List<Student> students = school.get().getStudents();
            return new Gson().toJson(students);
        }
        return "404";
    }

    private String getAllSchools() {
        List<String> schoolNames = new ArrayList<>();
        schools.forEach(school -> schoolNames.add(school.getName()));
        return new Gson().toJson(schoolNames);
    }

    /**
     * Endpoints
     * - /school/student?schoolName=schoolName&studentName=studentName - add new student to school
     * - /student/grade?studentId=studentId&grade=grade&gradeAssignment=assignment - add new grade to student
     * @param path - endpoint path
     * @return result, if post was successful or not, for example if school or student doesn't exist, should return false
     */
    public boolean post(String path) {
        if (path.startsWith("/school/student")) {
            return addStudentToSchool(path);
        } else if (path.startsWith("/student/grade")) {
            return addGradeToStudent(path);
        }
        return false;
    }

    private Map<String, String> parseQueryString(String path) {
        Map<String, String> queryParams = new HashMap<>();
        String[] parts = path.split("\\?");
        if (parts.length > 1) {
            String queryPart = parts[1];
            String[] params = queryPart.split("&");
            for (String param : params) {
                String[] keyValuePair = param.split("=");
                if (keyValuePair.length > 1) {
                    queryParams.put(keyValuePair[0], keyValuePair[1]);
                }
            }
        }
        return queryParams;
    }

    private boolean addStudentToSchool(String path) {
        Map<String, String> params = parseQueryString(path);
        String schoolName = params.get("schoolName");
        String studentName = params.get("studentName");

        if (schoolName == null || studentName == null) return false;

        for (School school : schools) {
            if (school.getName().equals(schoolName)) {
                Student newStudent = new Student(studentName);
                school.addStudent(newStudent);
                return true;
            }
        }
        return false;
    }

    private boolean addGradeToStudent(String path) {
        Map<String, String> params = parseQueryString(path);
        try {
            int studentId = Integer.parseInt(params.get("studentId"));
            int gradeValue = Integer.parseInt(params.get("grade"));
            String assignment = params.get("gradeAssignment");

            if (assignment == null) return false;

            for (School school : schools) {
                for (Student student : school.getStudents()) {
                    if (student.getId() == studentId) {
                        Grade newGrade = new Grade(gradeValue, assignment);
                        student.addGrade(newGrade);
                        return true;
                    }
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    /**
     * Endpoints
     * - /student/name?studentId=studentId&name=newName - change student's name
     * @param path - endpoint path
     * @return result, if put was successful or not, for example if student doesn't exist, should return false
     */
    public boolean put(String path) {
        if (path.startsWith("/student/name")) {
            return changeStudentName(path);
        }
        return false;
    }

    private boolean changeStudentName(String path) {
        Map<String, String> params = parseQueryString(path);
        try {
            int studentId = Integer.parseInt(params.get("studentId"));
            String newName = params.get("name");

            if (newName == null || newName.trim().isEmpty()) return false;

            for (School school : schools) {
                for (Student student : school.getStudents()) {
                    if (student.getId() == studentId) {
                        student.setName(newName);
                        return true;
                    }
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    /**
     * Endpoints
     * - /student/{studentId} (for example /student/10 ) - delete student from the database
     * @param path - endpoint path
     * @return result, if delete was successful or not, for example if student doesn't exist, should return false
     */
    public boolean delete(String path) {
        if (path.startsWith("/student/")) {
            return deleteStudent(path);
        }
        return false;
    }

    private boolean deleteStudent(String path) {
        int studentId = Integer.parseInt(path.substring(path.lastIndexOf('/') + 1));
        for (School school : schools) {
            Optional<Student> studentOptional = school.getStudents().stream()
                    .filter(student -> student.getId() == studentId)
                    .findFirst();
            if (studentOptional.isPresent()) {
                school.removeStudent(studentOptional.get());
                return true;
            }
        }
        return false;
    }
}
