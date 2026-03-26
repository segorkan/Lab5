package commands;

import interfaces.ElementCreator;

import java.util.Scanner;

public class Remove extends Command implements ElementCreator {

    public Remove() {
        super();
    }

    @Override
    public void execute(String argument) throws NumberFormatException{
        int id = Integer.parseInt(argument.trim());
        getCommandHandler().remove(id);
    }
}
