package commands;

import interfaces.ElementCreator;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Update extends Command implements ElementCreator {

    public Update() {
        super();
    }

    /**
     * Перенаправление реализации команды update.
     *
     * @param argument
     * @throws NumberFormatException
     * @throws IOException
     */
    @Override
    public void execute(String argument) throws NumberFormatException, IOException {
        int id = Integer.parseInt(argument.trim());
        InputStreamReader reader = getStreamInput();
        getCommandHandler().update(reader, id);
        getConsoleHandler().addToHistory("update");
    }
}
