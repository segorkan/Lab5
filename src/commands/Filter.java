package commands;

import objects.UnitOfMeasure;

public class Filter extends Command{

    public Filter(){
        super();
    }

    @Override
    public void execute(String argument) throws IllegalArgumentException {
        UnitOfMeasure unitOfMeasure = UnitOfMeasure.valueOf(argument.trim());
        getCommandHandler().filter(unitOfMeasure);
    }
}
