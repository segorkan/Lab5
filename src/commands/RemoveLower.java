package commands;

import interfaces.ElementCreator;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RemoveLower extends Command implements ElementCreator {

    public RemoveLower() {
        super();
    }

    /**
     * Перенаправление реализации команды remove_lower.
     *
     * @throws IOException
     */
    @Override
    public void execute() throws IOException {
        InputStreamReader reader = getStreamInput();
        getCommandHandler().removeLower(reader);
        getConsoleHandler().addToHistory("remove_lower");
        System.out.println("Команда выполнена успешно.");
    }
}
