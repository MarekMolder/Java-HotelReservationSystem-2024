package ee.taltech.iti0202.exam.timetable;

public class Task {
    private String name;
    private String id;
    private int duration;
    private boolean priority;
    private int day;
    private boolean isItDone;

    public Task(String name, int day, int duration, boolean priority) {
        this.id = null;
        this.name = name;
        this.day = day;
        this.duration = duration;
        this.priority = priority;
        this.isItDone = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Boolean getPriority() {
        return priority;
    }

    public void setPriority(Boolean priority) {
        this.priority = priority;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isItDone() {
        return isItDone;
    }

    public void setItDone(boolean isItDone) {
        this.isItDone = isItDone;
    }
}
