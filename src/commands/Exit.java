package commands;

public class Exit extends Command{

    public Exit(){
        super();
    }

    @Override
    public void execute() {
        getCommandHandler().exit();
    }
}
