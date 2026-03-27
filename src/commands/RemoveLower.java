package commands;

import interfaces.ElementCreator;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RemoveLower extends Command implements ElementCreator {

    public RemoveLower() {
        super();
    }

    @Override
    public void execute() throws IOException {
        InputStreamReader reader = new InputStreamReader(getStreamInput());
        getCommandHandler().removeLower(reader);
    }
}
