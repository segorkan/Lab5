package commands;

import auxiliary.CollectionHandler;

public class Show extends Command {

    public Show() {
        super();
    }

    /**
     * Перенаправление реализации команды show.
     */
    @Override
    public void execute() {
        getCommandHandler().show();
        getConsoleHandler().addToHistory("show");
    }
}
