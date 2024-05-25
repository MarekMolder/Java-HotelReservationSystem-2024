package ee.taltech.iti0202.exam.timetable;

import java.util.List;
import java.util.Optional;

public class Timetable {
    public Timetable() {

    }

    public Optional<String> addTask(String name, int day, int duration, boolean priority) {
        return Optional.empty();
    }

    public boolean markTaskDone(String taskNumber) {
        return false;
    }

    public List<String> getTasksForDay(int day) {
        return null;
    }
}
