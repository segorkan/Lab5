package commands;

import java.io.IOException;

public class Save extends Command {

    public Save() {
        super();
    }

    /**
     * Перенаправление реализации команды save.
     *
     * @throws IOException
     */
    @Override
    public void execute() throws IOException {
        getCommandHandler().save();
        getConsoleHandler().addToHistory("save");
    }
}
