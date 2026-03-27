package commands;

import exceptions.ConditionsNotMetException;

public class Count extends Command {

    public Count() {
        super();
    }

    /**
     * Перенаправление реализации команды count_less_than_price.
     *
     * @param argument
     * @throws NumberFormatException
     * @throws ConditionsNotMetException
     */
    @Override
    public void execute(String argument) throws NumberFormatException, ConditionsNotMetException {
        float price = Float.parseFloat(argument.trim());
        if (price <= 0) {
            throw new ConditionsNotMetException();
        }
        getCommandHandler().countLessThanPrice(price);
    }
}
