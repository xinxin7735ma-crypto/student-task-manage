import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        tasks.add(new Task("完成数据结构作业",  "数据结构"));
        tasks.add(new Task("复习六级单词", "英语"));
        tasks.add(new Task("学习 Java 集合框架", "Java"));

        tasks.get(0).markCompleted();
        
        System.out.println("======任务管理系统======");
        System.out.println("1. 查看任务");
        System.out.println("2. 添加任务");
        System.out.println("3. 标记完成");
        System.out.println("0. 退出");
        System.out.println("请输入选择：");
        while (true) {
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("我的任务列表：");

                for (Task task : tasks)
                {
                    System.out.println(task);
                }
                break;

            case 2:
                System.out.println("请输入任务名称：");
                String name = scanner.next();
                System.out.println("请输入任务分类");
                String course =scanner.next();
                tasks.add(new Task(name, course));
                break;

            case 3:
                System.out.println("请输入要完成的任务编号");
                int index = scanner.nextInt();
                tasks.get(index - 1).markCompleted();
                System.out.println("任务已完成！");1
                break;

            case 4:
                System.out.println("程序退出");
                break;

            default:
                System.out.println("输入错误");
                       }
        }

    }
}
