package auxiliary;

import commands.Command;
import exceptions.ConditionsNotMetException;
import exceptions.FileProblemException;
import exceptions.WrongFormatException;
import interfaces.HistoryGetter;

import java.io.IOException;
import java.util.*;

/**
 * Класс, отвечающий за хранение команд, истории команд и запущенных скриптов.
 */
public class ConsoleHandler implements HistoryGetter {

    private static ConsoleHandler instance;
    private Map<String, Command> commandList;
    private Deque<String> history;
    private Deque<String> scriptList;

    private ConsoleHandler() {
        this.commandList = new HashMap<String, Command>();
        this.history = new ArrayDeque<String>();
        this.scriptList = new ArrayDeque<String>();
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

    public void addScript(String name){
        getInstance().scriptList.add(name);
    }

    public Deque<String> getScriptList() {
        return getInstance().scriptList;
    }

    public void removeLastScript(){
        getInstance().scriptList.pop();
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

    /**
     * Добавить команду в историю команд.
     * @param command
     */
    public void addToHistory(String command){
        getHistory().add(command);
    }

    public void executeCommand(String command) throws NumberFormatException, ConditionsNotMetException, IllegalArgumentException, IOException {
        getInstance().getCommandList().get(command).execute();
    }

    public void executeCommand(String command, String argument) throws NumberFormatException, ConditionsNotMetException, IllegalArgumentException, IOException, FileProblemException, WrongFormatException {
        getInstance().getCommandList().get(command).execute(argument);
    }
}
