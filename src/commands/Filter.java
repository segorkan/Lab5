package commands;

import objects.UnitOfMeasure;

public class Filter extends Command {

    public Filter() {
        super();
    }

    /**
     * Перенаправление реализации команды filter_greater_than_unit_of_measure.
     *
     * @param argument
     * @throws IllegalArgumentException
     */
    @Override
    public void execute(String argument) throws IllegalArgumentException {
        UnitOfMeasure unitOfMeasure = UnitOfMeasure.valueOf(argument.trim());
        getCommandHandler().filter(unitOfMeasure);
        getConsoleHandler().addToHistory("filter_greater_than_unit_of_measure");
        System.out.println("Команда выполнена успешно.");
    }
}
