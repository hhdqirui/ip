package duke;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    enum TaskType{
        TODO,
        DEADLINE,
        EVENT
    }

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) throws FileNotFoundException {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(TaskList.readTextFile2List(storage.load()));
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        ui.showWelcomeMessage();
        String inputCommand;
        Scanner sc = new Scanner(System.in);
        int condition = 1;
        while(condition == 1){
            inputCommand = sc.nextLine();
            condition = parser.parse(inputCommand, tasks);
        }
        Checker.checkAndPrint(tasks);
        storage.writeFile(tasks);
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }
}