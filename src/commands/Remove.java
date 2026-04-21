package commands;

import interfaces.ElementCreator;

import java.util.Scanner;

public class Remove extends Command implements ElementCreator {

    public Remove() {
        super();
    }

    /**
     * Перенаправление реализации команды remove_by_id.
     * @param argument
     * @throws NumberFormatException
     */
    @Override
    public void execute(String argument) throws NumberFormatException{
        int id = Integer.parseInt(argument.trim());
        getCommandHandler().remove(id);
        getConsoleHandler().addToHistory("remove_by_id");
        System.out.println("Команда выполнена.");
    }
}
