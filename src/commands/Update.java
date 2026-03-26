package commands;

import interfaces.ElementCreator;

import java.util.Scanner;

public class Update extends Command implements ElementCreator {

    public Update() {
        super();
    }

    @Override
    public void execute(String argument) throws NumberFormatException {
        int id = Integer.parseInt(argument.trim());
        Scanner sc = new Scanner(getStreamInput());
        getCommandHandler().update(sc, id);
    }
}
