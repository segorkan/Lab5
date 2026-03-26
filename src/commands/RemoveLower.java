package commands;

import interfaces.ElementCreator;

import java.util.Scanner;

public class RemoveLower extends Command implements ElementCreator {

    public RemoveLower() {
        super();
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(getStreamInput());
        getCommandHandler().removeLower(sc);
    }
}
