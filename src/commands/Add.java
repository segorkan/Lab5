package commands;

import interfaces.ElementCreator;
import objects.*;

import java.util.Scanner;
import static auxiliary.InputFunctions.*;

import objects.Product;

public class Add extends Command implements ElementCreator {

    public Add() {
        super();
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(getStreamInput());
        getCommandHandler().add(sc);
    }

}
