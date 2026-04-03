package commands;

import interfaces.ElementCreator;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class AddMin extends Command implements ElementCreator{

    public AddMin(){
        super();
    }

    /**
     * Перенаправление реализации команды add_if_min.
     * @throws IOException
     */
    @Override
    public void execute() throws IOException {
        InputStreamReader reader = getStreamInput();
        getCommandHandler().addMin(reader);
        getConsoleHandler().addToHistory("add_if_min");
        System.out.println("Команда выполнена успешно.");
    }
}
