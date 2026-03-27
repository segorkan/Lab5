package commands;

public class MaxByOwner extends Command{

    public MaxByOwner(){
        super();
    }

    /**
     * Перенаправление реализации команды max_by_owner.
     */
    @Override
    public void execute() {
        getCommandHandler().maxByOwner();
    }
}
