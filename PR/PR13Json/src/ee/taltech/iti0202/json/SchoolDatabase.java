package ee.taltech.iti0202.json;

import com.google.gson.Gson;
import ee.taltech.iti0202.json.school.School;
import ee.taltech.iti0202.json.student.Grade;
import ee.taltech.iti0202.json.student.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchoolDatabase {
    Gson gson = new Gson();

    /*** DO NOT CHANGE */
    private final List<School> schools = new ArrayList<>();

    /**
     * DO NOT CHANGE
     * @param school school to add
     */
    public void addSchool(School school) {
        this.schools.add(school);
    }

    /**
     * DO NOT CHANGE
     * @return schools in the db
     */
    public List<School> getSchools() {
        return this.schools;
    }
    /**
     * Get all students in all schools in the database
     * @return all students json, if it's empty, return empty json {}
     */
    public String getAllStudents() {
        //TODO
        List<Student> allStudents = new ArrayList<>();
        for (School school : schools) {
            allStudents.addAll(school.getStudents());
        }

        if (allStudents.isEmpty()) {
            return "{}";
        } else {
            return gson.toJson(allStudents);
        }
    }

    /**
     * Get all students in specific school
     * @param school school's students to get
     * @return school's students json, if it's empty, return empty json {}
     */
    public String getAllStudents(School school) {
        //TODO
        List<Student> schoolStudents = new ArrayList<>();
        schoolStudents.addAll(school.getStudents());

        if (schoolStudents.isEmpty()) {
            return "{}";
        } else {
            return gson.toJson(schoolStudents);
        }
    }

    /**
     * Get student by id, check all schools that are in the database
     * @param id student's id
     * @return student class's json, if student is not found, return empty json {}
     */
    public String getStudent(int id) {
        //TODO
        for (School school : schools) {
            for (Student student : school.getStudents()) {
                if (student.getId() == id) {
                    return gson.toJson(student);
                }
            }
        }
        return "{}";
    }

    /**
     * Get student's grades by id
     * @param id student's id
     * @return student's name with key "name", and array of grades (Grade class) with key "grades" in json,
     * if student is not found, return empty json {}
     */
    public String getStudentGrades(int id) {
        //TODO
        for (School school : schools) {
            for (Student student : school.getStudents()) {
                if (student.getId() == id) {
                    Map<String, Object> resultMap = new HashMap<>();
                    resultMap.put("name", student.getName());
                    resultMap.put("grades", student.getGrades());
                    return gson.toJson(resultMap);
                }
            }
        }
        return "{}";
    }

    /**
     * Get student's average grade by id
     * @param id student's id
     * @return student's name with key "name", and average grade with key "averageGrade" in json,
     * if student is not found, return empty json {}
     */
    public String getStudentAverageGrade(int id) {
        //TODO
        Integer sum = 0;
        Integer count = 0;
        for (School school : schools) {
            for (Student student : school.getStudents()) {
                if (student.getId() == id) {
                        for (Grade grade : student.getGrades()) {
                        sum += grade.getGrade();
                        count++;
                    }
                    Map<String, Object> resultMap = new HashMap<>();
                    resultMap.put("name", student.getName());
                    resultMap.put("averageGrade", (sum / count));
                    return gson.toJson(resultMap);
                }
            }
        }
        return "{}";
    }

    /**
     * Get average grade in each school in the database
     * @return json array of [{"school": "school's name", "averageGrade": averageGrade double}, ...],
     * if no schools are in the db, return empty json {}
     */
    public String getAverageGradeInEachSchool() {
        //TODO
        if (schools.isEmpty()) {
            return "{}";
        }

        List<Map<String, Object>> resultList = new ArrayList<>();

        for (School school : schools) {
            double sum = 0;
            int count = 0;
            for (Student student : school.getStudents()) {
                for (Grade grade : student.getGrades()) {
                    sum += grade.getGrade();
                    count++;
                }
            }
            double average = count > 0 ? sum / count : 0;
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("school", school.getName());
            resultMap.put("averageGrade", average);
            resultList.add(resultMap);
        }

        return gson.toJson(resultList);
    }

    /**
     * Get average grade for each student in each school in the database
     * @return json array of [{"school": "school's name", "grades": [{"student": "student's name","averageGrade": averageGrade double}]}, ...],
     * if no schools are in the db, return empty json {}
     */
    public String getAllStudentsInEachSchoolAndTheirAverageGrades() {
        if (schools.isEmpty()) {
            return "{}";
        }

        List<Map<String, Object>> schoolsList = new ArrayList<>();

        for (School school : schools) {
            Map<String, Object> schoolMap = new HashMap<>();
            schoolMap.put("school", school.getName());

            List<Map<String, Object>> gradesList = new ArrayList<>();

            for (Student student : school.getStudents()) {
                double sum = 0;
                int count = 0;
                for (Grade grade : student.getGrades()) {
                    sum += grade.getGrade();
                    count++;
                }
                double average = count > 0 ? sum / count : 0;

                Map<String, Object> studentMap = new HashMap<>();
                studentMap.put("student", student.getName());
                studentMap.put("averageGrade", average);
                gradesList.add(studentMap);
            }

            schoolMap.put("grades", gradesList);
            schoolsList.add(schoolMap);
        }

        return gson.toJson(schoolsList);
    }

    /**
     * Get all student's names in each school
     * @return json array of [{"school": "school's name", "students": ["student1", "student2", ...]}, ...],
     * if no schools are in the db, return empty json {}
     */
    public String getAllStudentsNamesInEachSchool() {
        //TODO
        if (schools.isEmpty()) {
            return "{}";
        }

        List<Map<String, Object>> schoolsList = new ArrayList<>();

        for (School school : schools) {
            Map<String, Object> schoolMap = new HashMap<>();
            schoolMap.put("school", school.getName());

            List<String> studentNamesList = new ArrayList<>();
            for (Student student : school.getStudents()) {
                studentNamesList.add(student.getName());
            }

            schoolMap.put("students", studentNamesList);
            schoolsList.add(schoolMap);
        }

        return gson.toJson(schoolsList);
    }


    /**
     * Get average grade and all given grades count from all schools in the database
     * @return json of {"averageGrade": averageGradeDouble, "gradesTotal": gradesTotalInt}
     */
    public String getAverageGradeAndGradesCountGlobally() {
        //TODO
        double sum = 0;
        int count = 0;

        for (School school : schools) {
            for (Student student : school.getStudents()) {
                for (Grade grade : student.getGrades()) {
                    sum += grade.getGrade();
                    count++;
                }
            }
        }

        double averageGrade = count > 0 ? sum / count : 0;
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("averageGrade", averageGrade);
        resultMap.put("gradesTotal", count);

        return gson.toJson(resultMap);
    }
}