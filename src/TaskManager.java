import jdk.jfr.StackTrace;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
public class TaskManager {
    ArrayList<Task>tasks = new ArrayList<>();
    //添加任务：把传入的任务放进任务列表
    public void addTask(Task task)
    {
        tasks.add(task);
    }
    //删除任务：根据下标删除对应任务（remove：下标 index:任务在列表中的位置）
    public void deleteTask(int index) {tasks.remove(index);}
    //修改任务
    public void updateTask(int index, String title, String course)
    {
        Task task = tasks.get(index);
        task.setTitle(title);//名称
        task.setCourse(course);//课程
    }
    //标记状态：找到指定任务，并标记为已完成
    public void markCompleted(int index)
    {
        tasks.get(index).markCompleted();
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

