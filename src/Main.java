import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();
        manager.loadFromFile();

        
        System.out.println("======任务管理系统======");
        System.out.println("1. 查看任务");
        System.out.println("2. 添加任务");
        System.out.println("3. 标记完成");
        System.out.println("4. 删除任务");
        System.out.println("5. 退出");
        System.out.println("请输入选择：");
        while (true) {
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("我的任务列表：");
                for (int i = 0;i < manager.getTasks().size();i++)
                {
                    System.out.println((i+1)+"."+ manager.getTasks().get(i));
                }
                break;

            case 2:
                System.out.println("请输入任务名称：");
                String name = scanner.next();
                System.out.println("请输入任务分类");
                String course =scanner.next();
                manager.addTask(new Task(name, course));
                break;

            case 3:
                System.out.println("请输入要完成的任务编号");
                int index = scanner.nextInt();
                manager.markCompleted(index - 1);
                System.out.println("任务已完成！");
                break;

            case 4:
                System.out.println("请输入要删除的任务编号");
                int deleteIndex = scanner.nextInt();
                manager.deleteTask(deleteIndex - 1);
                System.out.println("删除成功！");
                break;

            case 5:
                manager.saveToFile();
                System.out.println("退出");
                return;

            default:
                System.out.println("输入错误");
                       }
        }

    }
}
