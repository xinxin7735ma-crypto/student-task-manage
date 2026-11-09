import java.nio.charset.StandardCharsets;
import java.io.*;
import java.util.ArrayList;

public class TaskManager {
    ArrayList<Task>tasks = new ArrayList<>();
    //添加任务：把传入的任务放进任务列表
    public void addTask(Task task)
    {
        tasks.add(task);
        saveToFile();
    }
    //删除任务：根据下标删除对应任务（remove：下标 index:任务在列表中的位置）
    public void deleteTask(int index)
    {
        tasks.remove(index);
        saveToFile();
    }
    //修改任务
    public void updateTask(int index, String title, String course)
    {
        Task task = tasks.get(index);
        task.setTitle(title);//名称
        task.setCourse(course);//课程
        saveToFile();
    }
    //标记状态：找到指定任务，并标记为已完成
    public void markCompleted(int index)
    {
        tasks.get(index).markCompleted();
        saveToFile();
    }
    //查看任务:获取任务列表，返回当前保存的所有任务
    public ArrayList<Task> getTasks()
    {
        return tasks;
    }
    //存档
    public void saveToFile()
    {
        try {
            FileWriter writer = new FileWriter("tasks.txt", StandardCharsets.UTF_8);
            System.out.println(new java.io.File("tasks.txt").getAbsolutePath());
            for (Task task : tasks)
            {
                writer.write(task.getTitle() + ","
                        + task.getCourse() + ","
                        + task.isCompleted() + "\n");
            }
            writer.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    //读档
    public void loadFromFile()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("tasks.txt"),StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] data = line.split(",",3);
                if(data.length < 3)
                {
                continue;
                }
                Task task = new Task(data[0],data[1]);
                if("ture".equals(data[2].trim()))
                {
                    task.markCompleted();
                }
            }

            reader.close();


        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

