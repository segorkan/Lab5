package auxiliary;

import commands.Command;
import exceptions.ConditionsNotMetException;
import exceptions.FileProblemException;
import exceptions.WrongFormatException;
import interfaces.HistoryGetter;

import java.io.IOException;
import java.util.*;

public class ConsoleHandler implements HistoryGetter {

    private static ConsoleHandler instance;
    private Map<String, Command> commandList;
    private Deque<String> history;

    private ConsoleHandler() {
        this.commandList = new HashMap<String, Command>();
        this.history = new ArrayDeque<String>();
    }

    public static ConsoleHandler getInstance() {
        if (ConsoleHandler.instance == null) {
            ConsoleHandler.instance = new ConsoleHandler();
        }
        return ConsoleHandler.instance;
    }

    public void addCommand(String name, Command command) {
        getInstance().commandList.put(name, command);
    }

    public Map<String, Command> getCommandList() {
        return getInstance().commandList;
    }

    @Override
    public Deque<String> getHistory() {
        return getInstance().history;
    }

    public void executeCommand(String command) throws NumberFormatException, ConditionsNotMetException, IllegalArgumentException, IOException {
        getInstance().getCommandList().get(command).execute();
        getInstance().getHistory().add(command);
    }

    public void executeCommand(String command, String argument) throws NumberFormatException, ConditionsNotMetException, IllegalArgumentException, IOException, FileProblemException, WrongFormatException {
        getInstance().getCommandList().get(command).execute(argument);
        getInstance().getHistory().add(command);
    }
}
