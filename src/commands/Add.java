package commands;

import interfaces.ElementCreator;
import objects.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import static auxiliary.InputFunctions.*;

import objects.Product;

public class Add extends Command implements ElementCreator {

    public Add() {
        super();
    }

    @Override
    public void execute() throws IOException {
        InputStreamReader reader = new InputStreamReader(getStreamInput());
        getCommandHandler().add(reader);
    }

}
