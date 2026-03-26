package commands;

import interfaces.ElementCreator;

import java.util.Scanner;

public class AddMin extends Command implements ElementCreator{

    public AddMin(){
        super();
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(getStreamInput());
        getCommandHandler().addMin(sc);
    }
}
