package commands;

import auxiliary.CollectionHandler;
import auxiliary.CommandHandler;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Базовый класс для всех команд.
 */
public abstract class Command {

    private final CommandHandler commandHandler;

    /**
     * Конструктор команды. Выполняется всеми командами и сохраняет ссылку на {@link CommandHandler}.
     */
    Command() {
        this.commandHandler = CommandHandler.getInstance();
    }

    /**
     * Вернуть {@link CommandHandler}
     * @return
     */
    CommandHandler getCommandHandler(){
        return this.commandHandler;
    }

    /**
     * Базовый метод для определения реализации команды.
     * @throws IOException
     */
    public void execute() throws IOException {
        System.out.println("Стандартная реализация execute");
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
