import java.util.ArrayList;
public class TaskManager {
    ArrayList<Task>tasks = new ArrayList<>();
   //添加任务
    public void addTask(Task task)
    {
        tasks.add(task);
    }
    public void markCompleted(int index)
    {
        tasks.get(index).markCompleted();
    }
    public ArrayList<Task> getTasks()
    {
        return tasks;
    }
}

