package auxiliary;

import commands.Command;
import exceptions.ConditionsNotMetException;
import exceptions.FileProblemException;
import exceptions.WrongFormatException;
import interfaces.HistoryGetter;

import java.io.IOException;
import java.util.*;

/**
 * Класс, отвечающий за хранение команд и истории команд.
 */
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

    /**
     * Добавляет команду в таблицу команд.
     * @param name название команды в консоли.
     * @param command команда {@link Command}.
     */
    public void addCommand(String name, Command command) {
        getInstance().commandList.put(name, command);
    }

    /**
     * Вернуть список с командами и их названиями в консоли.
     */
    public Map<String, Command> getCommandList() {
        return getInstance().commandList;
    }

    /**
     * Вернуть историю команд.
     */
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
