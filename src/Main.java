import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        tasks.add(new Task("完成数据结构作业", "数据结构"));
        tasks.add(new Task("复习六级单词", "英语"));
        tasks.add(new Task("学习 Java 集合框架", "Java"));

        tasks.get(0).markCompleted();

        System.out.println("我的任务列表：");

        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}
