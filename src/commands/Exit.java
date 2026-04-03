package commands;

public class Exit extends Command {

    public Exit() {
        super();
    }

    /**
     * Перенаправление реализации команды exit.
     */
    @Override
    public void execute() {
        getCommandHandler().exit();
        getConsoleHandler().addToHistory("exit");
        System.out.println("Команда выполнена успешно.");
    }
}
