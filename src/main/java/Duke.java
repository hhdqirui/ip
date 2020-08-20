import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    protected static int EVENT = 1, DEADLINE = 2, TODO = 3;

    // print all the content in the list
    public static void printList(ArrayList<Task> list){
        for(int i = 0; i < list.size(); i++){
            System.out.println((i + 1) + list.get(i).toString());
        }
    }

    // return the description
    public static String getDescription(String s, int type){
        if(type == TODO){
            int start = 0;
            while(!s.substring(start, start + 4).equals("todo")) start++;
            return s.substring(start + 4);
        }
        String firstWord = type == EVENT ? "event" : "deadline", secondWord = type == EVENT ? "/at" : "/by";
        int start = 0, firstWordLen = type == EVENT ? 5 : 8, len = s.length();
        while(!s.substring(start, start + firstWordLen).equals(firstWord)) start++;
        start += (firstWordLen + 1);
        int end = start + 1;
        while(end + 3 < len && !s.substring(end, end + 3).equals(secondWord)) end++;
        if(end + 3 >= len) end = end + 4;
        return s.substring(start, end - 1);
    }

    /*// return the description of Event
    public static String getDeadlineDescription(String[] command, int ptr){
        String res = "";
        Boolean start = false;
        for(int i = ptr; i < command.length; i++){
            if(command[i].equals("/by")) break;
            if(start){
                res += (" " + command[i]);
            }
            else{
                start = true;
                res += command[i];
            }
        }
        return res;
    }*/

    // return the string after the substring "/by" or "/at"
    public static String getTime(String s, int type){
        String word = type == EVENT ? "/at" : "/by";
        int i = 0, len = s.length();
        while(i + 3 < len && !s.substring(i, i + 3).equals(word)) i++;
        return i + 3 == len ? "" : s.substring(i + 4);
    }

    // return the string after the substring "/by" or "/at"
    public static String getDeadlineTime(String[] command){
        int len = command.length;
        if(len <= 0) return null;
        int i = 0;
        String res = "";
        while(i < len && !command[i].equals("/by")){
            i++;
        }
        i++;
        while(i < len){
            res += command[i++];
        }
        return res;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // print greetings of chatbot
        System.out.println("____________________________________________________________\n" +
                           "Hello! I'm Duke\n" +
                           "What can I do for you?" +
                           "\n____________________________________________________________");

        // add command entered by the user to the list
        ArrayList<Task> list = new ArrayList<>();
        String inputCommand;
        String[] command;
        int ptr;
        Scanner sc = new Scanner(System.in);
        while(true){
            inputCommand = sc.nextLine();
            command = inputCommand.split(" ");
            ptr = 0;

            // if the user input is empty, continue the loop
            if(command.length <= 0 || inputCommand.equals("")){
                /*System.out.println("____________________________________________________________");
                System.out.println("\uD83D\uDE43 OOPS!!! input cannot be empty.");
                System.out.println("____________________________________________________________");*/
                continue;
            }

            while(/*ptr < command.length && */command[ptr].equals("")) ptr++;

            if(command[ptr].equals("bye")){
                break;
            }
            else if(command[ptr].equals("list")){
                System.out.println("____________________________________________________________");
                printList(list);
                System.out.println("____________________________________________________________");
            }
            else if(command[ptr].equals("done")){
                int taskNumber = Integer.parseInt(command[ptr + 1]);
                System.out.println("____________________________________________________________");
                if(taskNumber > list.size()){
                    System.out.println("no such task: task" + taskNumber + " as you only have " + list.size() + " in total");
                }
                else{
                    Task task = list.get(taskNumber - 1);
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
                }
                System.out.println("____________________________________________________________");
            }
            else if(command[ptr].equals("todo")){
                String description = getDescription(inputCommand, TODO);
                Todo newTodo = new Todo(description);
                list.add(newTodo);
                System.out.println("____________________________________________________________\n" +
                        "Got it. I've added this task:\n" +
                        newTodo.toString() + "\n" +
                        String.format("Now you have %d tasks in the list.", list.size()) +
                        "\n____________________________________________________________");
            }
            else if(command[ptr].equals("deadline")){
                String by = getTime(inputCommand, DEADLINE), description = getDescription(inputCommand, DEADLINE);
                Deadline deadline = new Deadline(description, by);
                list.add(deadline);
                System.out.println("____________________________________________________________\n" +
                        "Got it. I've added this task:\n" +
                        deadline.toString() + "\n" +
                        String.format("Now you have %d tasks in the list.", list.size()) +
                        "\n____________________________________________________________");
            }
            else if(command[ptr].equals("event")){
                String at = getTime(inputCommand, EVENT), description = getDescription(inputCommand, EVENT);
                Event event = new Event(description, at);
                list.add(event);
                System.out.println("____________________________________________________________\n" +
                        "Got it. I've added this task:\n" +
                        event.toString() + "\n" +
                        String.format("Now you have %d tasks in the list.", list.size()) +
                        "\n____________________________________________________________");
            }
            else{
                Task task = new Task(inputCommand);
                list.add(task);
                System.out.println("____________________________________________________________\n" +
                        "Got it. I've added this task:\n" +
                        task.toString() + "\n" +
                        String.format("Now you have %d tasks in the list.", list.size()) +
                        "\n____________________________________________________________");
                /*System.out.println("\uD83D\uDE43 Sorry~ please specify whether this is a todo or a deadline or a event\n" +
                        "put the word \"todo\" or \"deadline\" or \"event\" in front of your description");*/
            }
        }

        System.out.println("____________________________________________________________\n" +
                           "Bye. Hope to see you again soon!" +
                           "\n____________________________________________________________");
    }
}
