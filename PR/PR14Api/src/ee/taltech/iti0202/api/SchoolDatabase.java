package ee.taltech.iti0202.api;

import ee.taltech.iti0202.api.school.School;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import ee.taltech.iti0202.api.student.Grade;
import ee.taltech.iti0202.api.student.Student;
import org.json.JSONArray;
import org.json.JSONObject;

public class SchoolDatabase {

    //DO NOT CHANGE
    public List<School> schools = new ArrayList<>();

    public SchoolDatabase(String jsonContent) {
        //DO NOT CHANGE
        loadDatabase(jsonContent);
    }

    private void loadDatabase(String jsonContent) {
        JSONArray schools = new JSONArray(jsonContent);
        int highestId = 0;

        for (int i = 0; i < schools.length(); i++) {
            JSONObject schoolObj = schools.getJSONObject(i);

            String schoolName = schoolObj.getString("name");
            JSONArray studentsArray = schoolObj.getJSONArray("students");

            List<Student> studentList = new ArrayList<>();

            for (int j = 0; j < studentsArray.length(); j++) {
                JSONObject studentObj = studentsArray.getJSONObject(j);
                int id = studentObj.getInt("id");
                highestId = Math.max(highestId, id);
                String studentName = studentObj.getString("name");
                JSONArray gradesArray = studentObj.getJSONArray("grades");
                List<Grade> gradeList = new ArrayList<>();

                for (int k = 0; k < gradesArray.length(); k++) {
                    JSONObject gradeObj = gradesArray.getJSONObject(k);
                    int grade = gradeObj.getInt("grade");
                    String assignment = gradeObj.getString("assignment");
                    gradeList.add(new Grade(grade, assignment));
                }
                studentList.add(new Student(studentName));
            }
            this.schools.add(new School(schoolName));
        }

        Student.nextId = highestId + 1;
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
        try {
            URI uri = new URI(path);
            String query = uri.getQuery();
            String[] queryParams = query != null ? query.split("&") : new String[0];
            Map<String, String> params = new HashMap<>();
            for (String param : queryParams) {
                String[] keyValuePair = param.split("=");
                if (keyValuePair.length == 2) {
                    params.put(keyValuePair[0], keyValuePair[1]);
                }
            }

            switch (uri.getPath()) {
                case "/student/grades":
                    return getStudentGrades(params.get("studentId"));
                case "/school/students":
                    return getSchoolStudents(params.get("schoolName"));
                case "/schools":
                    return getAllSchoolNames();
                default:
                    return "404";
            }
        } catch (URISyntaxException e) {
            return "404";
        }
    }

    private String getStudentGrades(String studentId) {
        int id = Integer.parseInt(studentId);
        for (School school : schools) {
            for (Student student : school.getStudents()) {
                if (student.getId() == id) {
                    JSONArray gradesJson = new JSONArray(student.getGrades());
                    return gradesJson.toString();
                }
            }
        }
        return "404";
    }

    private String getSchoolStudents(String schoolName) {
        JSONArray studentsJson = new JSONArray();
        for (School school : schools) {
            if (school.getName().equalsIgnoreCase(schoolName)) {
                for (Student student : school.getStudents()) {
                    JSONObject studentJson = new JSONObject();
                    studentJson.put("id", student.getId());
                    studentJson.put("name", student.getName());
                    studentsJson.put(studentJson);
                }
                return studentsJson.toString();
            }
        }
        return "404";
    }

    private String getAllSchoolNames() {
        JSONArray schoolNames = new JSONArray();
        for (School school : schools) {
            schoolNames.put(school.getName());
        }
        return schoolNames.toString();
    }

    /**
     * Endpoints
     * - /school/student?schoolName=schoolName&studentName=studentName - add new student to school
     * - /student/grade?studentId=studentId&grade=grade&gradeAssignment=assignment - add new grade to student
     * @param path - endpoint path
     * @return result, if post was successful or not, for example if school or student doesn't exist, should return false
     */
    public boolean post(String path) {
        try {
            URI uri = new URI(path);
            String query = uri.getQuery();
            Map<String, String> params = new HashMap<>();
            if (query != null) {
                for (String param : query.split("&")) {
                    String[] parts = param.split("=");
                    if (parts.length > 1) {
                        params.put(parts[0], parts[1]);
                    }
                }
            }

            switch (uri.getPath()) {
                case "/school/student":
                    return addStudentToSchool(params.get("schoolName"), params.get("studentName"));
                case "/student/grade":
                    return addGradeToStudent(params.get("studentId"), params.get("grade"), params.get("gradeAssignment"));
                default:
                    return false;
            }
        } catch (URISyntaxException e) {
            return false;
        }
    }

    private boolean addStudentToSchool(String schoolName, String studentName) {
        if (schoolName == null || studentName == null) {
            return false;
        }
        for (School school : schools) {
            if (school.getName().equalsIgnoreCase(schoolName)) {
                List<Student> students = school.getStudents();
                int newId = Student.nextId++;
                students.add(new Student(studentName));
                return true;
            }
        }
        return false;
    }

    private boolean addGradeToStudent(String studentIdStr, String gradeStr, String gradeAssignment) {
        try {
            int studentId = Integer.parseInt(studentIdStr);
            int gradeValue = Integer.parseInt(gradeStr);
            for (School school : schools) {
                for (Student student : school.getStudents()) {
                    if (student.getId() == studentId) {
                        List<Grade> grades = student.getGrades();
                        grades.add(new Grade(gradeValue, gradeAssignment));
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
        if (!path.startsWith("/student/name")) {
            return false;
        }

        try {
            URI uri = new URI(path);
            String query = uri.getQuery();
            Map<String, String> params = new HashMap<>();

            if (query != null) {
                for (String param : query.split("&")) {
                    String[] parts = param.split("=");
                    if (parts.length > 1) {
                        params.put(parts[0], parts[1]);
                    }
                }
            }

            String studentIdStr = params.get("studentId");
            String newName = params.get("name");

            if (studentIdStr == null || newName == null) {
                return false;
            }

            int studentId = Integer.parseInt(studentIdStr);
            for (School school : schools) {
                for (Student student : school.getStudents()) {
                    if (student.getId() == studentId) {
                        student.setName(newName);
                        return true;
                    }
                }
            }

            return false;
        } catch (URISyntaxException | NumberFormatException e) {
            return false;
        }
    }

    /**
     * Endpoints
     * - /student/{studentId} (for example /student/10 ) - delete student from the database
     * @param path - endpoint path
     * @return result, if delete was successful or not, for example if student doesn't exist, should return false
     */
    public boolean delete(String path) {
        if (!path.startsWith("/student/")) {
            return false;
        }

        String studentIdString = path.substring("/student/".length());
        int studentId;
        try {
            studentId = Integer.parseInt(studentIdString);
        } catch (NumberFormatException e) {
            return false;
        }

        for (School school : schools) {
            for (Iterator<Student> iterator = school.getStudents().iterator(); iterator.hasNext();) {
                Student student = iterator.next();
                if (student.getId() == studentId) {
                    iterator.remove();
                    return true;
                }
            }
        }
        return false;
    }
}
