package commands;

public class Clear extends Command {

    public Clear(){
        super();
    }

    @Override
    public void execute(){
        getCommandHandler().clear();
    }
}
