package commands;

public class MaxByOwner extends Command{

    public MaxByOwner(){
        super();
    }

    @Override
    public void execute() {
        getCommandHandler().maxByOwner();
    }
}
