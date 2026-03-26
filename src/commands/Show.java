package commands;

import auxiliary.CollectionHandler;

public class Show extends Command {

    public Show() {
        super();
    }

    @Override
    public void execute() {
        getCommandHandler().show();
    }
}
