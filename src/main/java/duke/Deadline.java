package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Gets the description of the deadline
     * @param s user input
     * @return the description of the deadline
     */
    public static String getDescription(String s) {
        String firstWord = "deadline", secondWord = "/by";
        int start = 0, len = s.length();
        while (!s.substring(start, start + 8).equals(firstWord)) {
            start++;
        }
        start += 9;
        if (start >= len) {
            return s.substring(len);
        }
        int end = start + 1;
        while (end + 3 < len && !s.substring(end, end + 3).equals(secondWord)) {
            end++;
        }
        if (end + 3 >= len) {
            end = len + 1;
        }
        return s.substring(start, end - 1);
    }

    /**
     * Gets the time of the deadline
     * @param s user input
     * @return the time of the deadline
     */
    public static String getTime(String s) {
        String word = "/by";
        int i = 0, len = s.length();
        while (i + 3 < len && !s.substring(i, i + 3).equals(word)) {
            i++;
        }
        return i + 3 == len ? "" : s.substring(i + 4);
    }

    /**
     * Changes the date format in the user input
     * @param command user input
     * @return the formatted date
     */
    public static String changeDateFormat(String[] command) {
        String word = "/by";
        for (int i = 0; i < command.length; i++) {
            if (command[i].equals(word)) {
                if (i + 1 < command.length) {
                    command[i + 1] = command[i + 1].replace('/', '-');
                    return command[i + 1];
                }
            }
        }
        return null;
    }

    /**
     * Gets the time in user input
     * @param command user input
     * @return the time in user input
     */
    public static String getLocalTime(String[] command) {
        String word = "/by";
        for (int i = 0; i < command.length; i++) {
            if (command[i].equals(word)) {
                if (i + 2 < command.length) {
                    return command[i + 2];
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * Create a Deadline object
     * @param input user input
     * @return a Deadline object
     */
    public static Deadline of(String input) {
        String by = getTime(input), description = getDescription(input);
        String[] command = input.split(" ");
        int ptr = 0;
        while (command[ptr].equals("")) {
            ptr++;
        }
        if (description.equals("") || by.equals("") || command[command.length - 1].equals("/by") || ptr == command.length - 1) {
            return null;
        }
        Deadline deadline = new Deadline(description, by);
        try {
            LocalDate date = LocalDate.parse(changeDateFormat(command));
            deadline.setDate(date);
        } catch (Exception e) {

        }
        try {
            LocalTime time = LocalTime.parse(getLocalTime(command));
            deadline.setTime(time);
        } catch (Exception e) {

        }
        return deadline;
    }

    /**
     * Create a Deadline object
     * @param description description of the deadline
     * @param by by of the deadline
     * @param isDone whether the deadline is done
     * @return a Deadline object
     */
    public static Deadline of(String description, String by, boolean isDone) {
        assert !description.equals("") : "description of deadline is empty";
        Deadline ddl = new Deadline(description, by, isDone);
        String[] dateAndTime = by.replace('/', '-').split(" ");
        try {
            LocalDate d = LocalDate.parse(dateAndTime[0]);
            ddl.setDate(d);
        } catch (Exception e) {

        }
        try {
            LocalTime t = LocalTime.parse(dateAndTime[1]);
            ddl.setTime(t);
        } catch (Exception e) {

        }
        return ddl;
    }

    /**
     * Construct a Deadline object
     * @param description description of the deadline
     * @param by by of the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Construct a Deadline object
     * @param description description of the deadline
     * @param by by of the deadline
     * @param isDone whether the deadline is done
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns by
     * @return by
     */
    public String getBy() {
        return by;
    }

    /**
     * Sets the date of the deadline
     * @param date date to be set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets the time of the deadline
     * @param time time to be set
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Returns the date
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the time
     * @return the time
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Returns whether is done
     * @return whether is done
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Overrides the toString method
     * @return the String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + (date == null ? by : (date.toString() + " (" + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")"))
                + (time != null ? " " + time.toString() : "") + ")";
    }
}