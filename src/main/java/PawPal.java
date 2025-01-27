import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PawPal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        String name = "PawPal";
        System.out.println("-----------------------------------------");
        System.out.println("Hello! I'm " + name + "\n" + "What can I do for you? \n");
        System.out.println("-----------------------------------------");

        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")){
                System.out.println("Bye. Hope to see you again soon! Meow");
                break;
            } else if (input.equalsIgnoreCase("list")){
                taskManager.listTasks();
            } else{
                taskManager.addTask(input);
            }
        }
    }
}
