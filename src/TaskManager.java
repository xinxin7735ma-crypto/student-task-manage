import jdk.jfr.StackTrace;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
public class TaskManager {
    ArrayList<Task>tasks = new ArrayList<>();
    //添加任务
    public void addTask(Task task)
    {
        tasks.add(task);
    }
    //删除任务
    public void deleteTask(int index) {tasks.remove(index);}
    //标记状态
    public void markCompleted(int index)
    {
        tasks.get(index).markCompleted();
    }
    //查看任务
    public ArrayList<Task> getTasks()
    {
        return tasks;
    }
    //存档
    public void saveToFile()
    {
        try {
            FileWriter writer = new FileWriter("tasks.txt");
            for (Task task : tasks)
            {
                writer.write(task.getTitle() + ","
                        + task.getCourse() + ","
                        + task.isCompleted() + "\n");
            }
            writer.close();
            System.out.println("保存成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    //读档
    public void loadFromFile()
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"));

            String line;

            while ((line = reader.readLine()) != null)
            {
                String[] data = line.split(",");
                Task task = new Task(data[0], data[1]);
                if (data[2].equals("true"))
                {
                    task.markCompleted();
                }
                tasks.add(task);
            }

            reader.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

