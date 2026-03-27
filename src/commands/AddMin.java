package commands;

import interfaces.ElementCreator;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AddMin extends Command implements ElementCreator{

    public AddMin(){
        super();
    }

    @Override
    public void execute() throws IOException {
        InputStreamReader reader = new InputStreamReader(getStreamInput());
        getCommandHandler().addMin(reader);
    }
}
