package pawpal.utils;

import java.util.List;

import pawpal.tasks.*;

/**
 * Handles printing of various messages and task-related outputs to the console.
 * Provides responses for user commands and error handling feedback.
 */
public class Printer {

    /**
     * Prints a response when a new task is added to the list.
     *
     * @param description Description of the task added.
     * @param taskCount   Current number of tasks in the list.
     */
    public void printTaskAdded(String description, int taskCount) {
        System.out.println("Purr-sonally, I’d rather nap, but your " + description + " task is on the list now!");
        System.out.println("Now you have " + taskCount + " tasks.");
    }

    /**
     * Prints a greeting message when the chatbot starts.
     *
     * @param name The name of the chatbot.
     */
    public void printGreeting(String name) {
        System.out.println("-----------------------------------------");
        System.out.println("Hello! I'm " + name + "\n" + "What can I do for you? \n");
        System.out.println("-----------------------------------------");
    }

    /**
     * Prints a farewell message when the user exits the application.
     */
    public void printBye() {
        System.out.println("Bye. Hope to see you again soon! Meow");
    }

    /**
     * Prints the current list of tasks.
     *
     * @param tasks The list of tasks to be printed.
     */
    public void printTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks in your list you silly cat!");
        } else {
            System.out.println("Here are the tasks in your list you silly cat!");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    /**
     * Prints a message when a task is marked as completed.
     *
     * @param task The task that has been marked as completed.
     */
    public void printTaskMarked(Task task) {
        System.out.println("A paw-sitive development! Wanna celebrate with some head pats?");
        System.out.println(task);
    }

    /**
     * Prints a message when a task is unmarked (set to incomplete).
     *
     * @param task The task that has been unmarked.
     */
    public void printTaskUnmarked(Task task) {
        System.out.println("We don't judge around here.");
        System.out.println(task);
    }

    /**
     * Prints a message when a task is deleted from the list.
     *
     * @param task      The task that has been deleted.
     * @param taskCount The number of tasks remaining in the list.
     */
    public void printTaskDeleted(Task task, int taskCount) {
        System.out.println("Task removed: " + task);
        System.out.println("Now you have " + taskCount + " tasks left.");
    }

    /**
     * Prints an error message when the user inputs an invalid task number.
     */
    public void printInvalidTaskNumber() {
        System.out.println("Invalid task number! Are you playing with yarn again?");
    }

    /**
     * Prints an error message when the user inputs an invalid command.
     */
    public void printInvalidCommand() {
        System.out.println("Sorry, I don't understand that command :(");
    }

    /**
     * Prints an error message when the 'todo' command is missing a description.
     */
    public void printMissingToDoDetails() {
        System.out.println("You need to provide a task description after 'todo'!");
    }

    /**
     * Prints an error message when the 'deadline' command is missing details.
     */
    public void printMissingDeadlineDetails() {
        System.out.println("Invalid deadline! Use: deadline <description> /by <deadline>");
    }

    /**
     * Prints an error message when the 'event' command is missing details.
     */
    public void printMissingEventDetails() {
        System.out.println("Invalid event! Use: event <description> /from <start> /to <end>");
    }

    /**
     * Prints an error message when there's an error loading the saved task lists.
     */
    public void printLoadingErrorMessage() {
        System.out.println("There was an error loading the file. Please try again.");
    }

    /**
     * Prints an error message when there's saving new tasks into the file.
     */
    public void printSavingErrorMessage() {
        System.out.println("There was an error saving the file. Please try again.");
    }

    /*
     * Prints the list of matching tasks
     */
    public void printMatchingTasks(List<Task> tasks) {
        System.out.println("____________________________________________________________");
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    public void printCheerMessage(String message) {
        System.out.println(message);
    }

    public String printEmptyCheerFile(){
        return "No cheers found! :(";
    }

    public String printFileNotFound() {
        return "I can't find the file you are looking for! :(";
    }
}
