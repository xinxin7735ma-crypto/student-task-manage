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
        System.out.println("5. 修改任务");
        System.out.println("6. 退出");
        System.out.println("请输入选择：");
        while (true) {
            int choice;

            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("请输入数字！");
                scanner.nextLine();
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.println("我的任务列表：");
                    for (int i = 0; i < manager.getTasks().size(); i++) {
                        System.out.println((i + 1) + "." + manager.getTasks().get(i));
                    }
                    break;


                case 2:
                    System.out.println("请输入任务名称：");
                    scanner.nextLine(); // 读取上一次输入留下的回车
                    String name = scanner.nextLine(); // 读取完整任务名称
                    System.out.println("请输入任务分类：");
                    String course = scanner.nextLine(); // 读取完整任务分类
                    manager.addTask(new Task(name, course));
                    System.out.println("添加成功");
                    break;


                case 3:
                    System.out.println("请输入要完成的任务编号");
                    int index = scanner.nextInt();

                    if (index > 0 && index <= manager.getTasks().size()) {

                        manager.markCompleted(index - 1);
                        System.out.println("任务已完成！");

                    } else {
                        System.out.println("没有这个任务编号！");
                    }

                    break;

                case 4:
                    System.out.println("请输入要删除的任务编号");
                    int deleteIndex = scanner.nextInt();

                    if (deleteIndex > 0 && deleteIndex <= manager.getTasks().size()) {
                        manager.deleteTask(deleteIndex - 1);
                        System.out.println("删除成功！");
                    } else {
                        System.out.println("没有这个任务编号！");
                    }

                    break;

                case 5:
                    System.out.println("请输入要修改的任务编号");

                    int updateIndex = scanner.nextInt();

                    if (updateIndex > 0 && updateIndex <= manager.getTasks().size()) {
                        System.out.println("请输入新的任务名称");
                        String newName = scanner.next();

                        System.out.println("请输入新的任务分类");
                        String newCourse = scanner.next();

                        manager.updateTask(updateIndex - 1, newName, newCourse);

                        System.out.println("修改成功！");

                    } else {
                        System.out.println("没有这个任务编号！");
                    }

                    break;

                case 6:
                    manager.saveToFile();
                    System.out.println("退出");
                    return;
            }

        }
    }
}
