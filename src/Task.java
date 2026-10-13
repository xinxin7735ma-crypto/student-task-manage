public class Task {
    private String title;
    private String course;
    private boolean completed;

    public Task(String title, String course) {
        this.title = title;
        this.course = course;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getCourse() {
        return course;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        completed = true;
    }
    //修改
    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setCourse(String course)
    {
        this.course = course;
    }

    @Override
    public String toString() {
        String status = completed ? "已完成" : "未完成";
        return "[" + status + "] " + course + " - " + title;
    }
}
