package commands;


public class Clear extends Command {

    public Clear(){
        super();
    }

    /**
     * Перенаправление реализации команды clear.
     */
    @Override
    public void execute(){
        getCommandHandler().clear();
        getConsoleHandler().addToHistory("clear");
    }
}
