package ee.taltech.iti0202.exam.timetable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Timetable {
    private List<Task> tasks;
    private int taskCounter;

    public Timetable() {
         this.tasks = new ArrayList<>();
         this.taskCounter = 0;
    }

    public Optional<String> addTask(String name, int day, int duration, boolean priority) {
        Task task = new Task(name, day, duration, priority);
        if (checkCanAddTask(task)) {
            tasks.add(task);
            taskCounter++;
            task.setId("T" + taskCounter);
            return Optional.of(task.getId());
        }
        return Optional.empty();
    }

    public boolean markTaskDone(String taskNumber) {
        for (Task task : tasks) {
            if (task.getId().equals(taskNumber) && !task.isItDone()) {
                task.setItDone(true);
                return true;
            }
        }
        return false;
    }

    public List<String> getTasksForDay(int day) {
        if (day < 1) {
            return new ArrayList<>();
        }

        List<Task> result = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDay() == day && !task.isItDone()) {
                result.add(task);
            }
        }

        return result.stream()
                .sorted(Comparator.comparing(Task::getPriority).reversed())
                .map(task -> task.getId() + " " + task.getName())
                .toList();
    }

    public boolean checkCanAddTask(Task task) {
        int total = 0;
        if (task.getDuration() >= 1 && task.getDay() >= 1) {
            for (Task t : tasks) {
                if (t.getDay() == task.getDay() && !t.isItDone()) {
                    if (t.getName().equals(task.getName())) {
                        return false;
                    }
                    total += t.getDuration();
                }
            }
            if (total + task.getDuration() <= 5) {
                return true;
            }
        }
        return false;
    }
}
