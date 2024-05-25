package ee.taltech.iti0202.exam.timetable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Timetable {
    private List<Task> tasks;
    private int taskCounter;

    /**
     * Constructs a new timetable.
     */
    public Timetable() {
         this.tasks = new ArrayList<>();
         this.taskCounter = 0;
    }

    /**
     * adds a new task to timetable.
     * @param name
     * @param day
     * @param duration
     * @param priority
     * @return
     */
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

    /**
     * Marks the task done
     * @param taskNumber
     * @return
     */
    public boolean markTaskDone(String taskNumber) {
        for (Task task : tasks) {
            if (task.getId().equals(taskNumber) && !task.isItDone()) {
                task.setItDone(true);
                return true;
            }
        }
        return false;
    }

    /**
     * Gets list of task for the day
     * @param day
     * @return
     */
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

    /**
     * Checks if it is okay to add a new task to a day
     * @param task
     * @return
     */
    public boolean checkCanAddTask(Task task) {
        int total = 0;
        if (task.getDuration() >= 1 && task.getDay() >= 1) {
            for (Task t : tasks) {
                if (t.getDay() == task.getDay() && t.getName().equals(task.getName())) {
                    return false;
                }
                if (t.getDay() == task.getDay() && !t.isItDone()) {
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
