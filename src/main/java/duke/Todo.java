package duke;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Todo extends Task {

    /**
     * Gets the description of the Todo
     * @param s the todo input by user
     * @return the description of the Todo
     */
    public static String getDescription(String s) {
        int start = 0;
        while (!s.substring(start, start + 4).equals("todo")) {
            start++;
        }
        return s.substring(start + 5);
    }

    /**
     * Create a Todo object
     * @param input input by user
     * @return the Todo object
     */
    public static ArrayList<Todo> of(String input) {
        assert !input.equals("") : "input string passed into of method of Todo class is empty";
        String description = getDescription(input);
        if (description.equals("")) {
            return null;
        }
        String[] descriptions = description.split(",");
        ArrayList<Todo> res = new ArrayList<>();
        for(String des : descriptions){
            res.add(new Todo(des));
        }
        return res;
    }

    /**
     * Construct a Todo object
     * @param description the description of the Todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Construct a Todo object
     * @param description the description of the Todo
     * @param isDone whether the todo is done
     */
    public Todo(String description, boolean isDone){
        super(description, isDone);
    }


    /**
     * Overrides the toString method
     * @return the String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}