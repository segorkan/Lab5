package commands;

import auxiliary.CommandHandler;
import auxiliary.ConsoleHandler;

import java.io.IOException;

/**
 * Базовый класс для всех команд.
 */
public abstract class Command {

    private final CommandHandler commandHandler;
    private final ConsoleHandler consoleHandler;

    /**
     * Конструктор команды. Выполняется всеми командами и сохраняет ссылку на {@link CommandHandler} и {@link ConsoleHandler}.
     */
    Command() {
        this.commandHandler = CommandHandler.getInstance();
        this.consoleHandler = ConsoleHandler.getInstance();
    }

    /**
     * Вернуть {@link CommandHandler}.
     */
    CommandHandler getCommandHandler(){
        return this.commandHandler;
    }

    /**
     * Вернуть {@link ConsoleHandler}.
     */
    ConsoleHandler getConsoleHandler(){
        return this.consoleHandler;
    }

    /**
     * Базовый метод для определения реализации команды.
     * @throws IOException
     */
    public void execute() throws IOException {
        System.out.println("Добавьте аргумент.");
    }

    /**
     * Базовый метод для определения реализации команды с аргументом.
     * @param argument
     * @throws IOException
     */
    public void execute(String argument) throws IOException {
        System.out.println("Стандартная реализация execute с аргументом.");
    }
}
