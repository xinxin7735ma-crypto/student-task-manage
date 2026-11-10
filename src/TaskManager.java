import java.sql.*;
import java.util.ArrayList;

public class TaskManager {
    ArrayList<Task>tasks = new ArrayList<>();
    //添加任务：先写数据库，成功后才加进内存列表
    public void addTask(String title, String course)
    {
        String sql = "INSERT INTO dbo.tasks (title, course, completed) VALUES (?, ?, 0)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, title);
            ps.setString(2, course);
            ps.executeUpdate();
            tasks.clear();
            loadFromDb();      // 重新从库里读，拿到数据库生成的 id
        } catch (SQLException e)
        {
            System.out.println("添加失败：" + e.getMessage());
        }
    }

    //删除任务：根据下标删除对应任务（remove：下标 index:任务在列表中的位置）
    public void deleteTask(int index)
    {
        String sql ="DELETE FROM dbo.tasks WHERE id =?";
        try(Connection conn =DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1,tasks.get(index).getId());
            ps.executeUpdate();
            tasks.remove(index);
        } catch (SQLException e)
        {
            System.out.println("删除失败："+e.getMessage());
        }

    }
    //修改任务：先更新数据库，成功后才更新内存列表
    public void updateTask(int index, String title, String course)
    {
        String sql = "UPDATE dbo.tasks SET title = ?, course = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, title);
            ps.setString(2, course);
            ps.setInt(3, tasks.get(index).getId());
            ps.executeUpdate();
            Task task = tasks.get(index);
            task.setTitle(title);
            task.setCourse(course);
        } catch (SQLException e)
        {
            System.out.println("修改失败：" + e.getMessage());
        }
    }
    //切换完成状态：先写数据库，成功后才更新内存列表
    public void toggleCompleted(int index)
    {
        String sql = "UPDATE dbo.tasks SET completed = ? WHERE id = ?";
        boolean target = !tasks.get(index).isCompleted();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setBoolean(1,target);
            ps.setInt(2, tasks.get(index).getId());
            ps.executeUpdate();

            tasks.get(index).setCompleted(target);
        } catch (SQLException e)
        {
            System.out.println("切换失败：" + e.getMessage());
        }
    }
    //查看任务:获取任务列表，返回当前保存的所有任务
    public ArrayList<Task> getTasks()
    {
        return tasks;
    }
    //从数据库加载全部任务
    public void loadFromDb()
        {
            tasks.clear();
            String sql = "SELECT id,title,course,completed FROM dbo.tasks ORDER BY id";
            try(Connection conn = DBUtil.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql))
            {
                while(rs.next())
                {
                    int id = rs.getInt("id");
                    Task task = new Task(id,rs.getString("title"),rs.getString("course"));
                    if(rs.getBoolean("completed"))
                    {
                        task.markCompleted();
                    }
                    tasks.add(task);

                }

            }
            catch(SQLException e)
            {
                System.out.println("数据库读取失败："+e.getMessage());
            }
        }

}
