package commands;

import interfaces.ElementCreator;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Update extends Command implements ElementCreator {

    public Update() {
        super();
    }

    @Override
    public void execute(String argument) throws NumberFormatException, IOException {
        int id = Integer.parseInt(argument.trim());
        InputStreamReader reader = new InputStreamReader(getStreamInput());
        getCommandHandler().update(reader, id);
    }
}
