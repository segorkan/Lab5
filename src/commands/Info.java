package commands;

public class Info extends Command{

    public Info(){
        super();
    }

    @Override
    public void execute() {
        getCommandHandler().info();
        getConsoleHandler().addToHistory("info");
        System.out.println("Команда выполнена успешно.");
    }
}
