package commands;

import auxiliary.CollectionHandler;
import auxiliary.CommandHandler;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Command {

    private final CollectionHandler collectionHandler;
    private final CommandHandler commandHandler;

    Command() {
        this.collectionHandler = CollectionHandler.getInstance();
        this.commandHandler = CommandHandler.getInstance();
    }

    CollectionHandler getCollectionHandler() {
        return this.collectionHandler;
    }

    CommandHandler getCommandHandler(){
        return this.commandHandler;
    }

    public void execute() throws IOException {
        System.out.println("Стандартная реализация execute");
    }

    public void execute(String argument) throws IOException {
        System.out.println("Стандартная реализация execute с аргументом.");
    }
}
