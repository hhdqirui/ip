package duke;

public class Todo extends Task {

    public static String getDescription(String s){
        int start = 0;
        while(!s.substring(start, start + 4).equals("todo")) start++;
        return s.substring(start + 5);
    }

    public static Todo of(String input){
        String description = getDescription(input);
        if(description.equals("")){
            return null;
        }
        return new Todo(description);
    }

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone){
        super(description, isDone);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}