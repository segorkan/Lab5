package commands;

import interfaces.HistoryGetter;

public class History extends Command{

    private static final int historyPrintSize = 13;
    private final HistoryGetter historyGetter;

    public History(HistoryGetter historyGetter){
        super();
        this.historyGetter = historyGetter;
    }

    /**
     * Перенаправление реализации команды history.
     */
    @Override
    public void execute() {
        getCommandHandler().history(historyGetter.getHistory(), historyPrintSize);
        getConsoleHandler().addToHistory("history");
        System.out.println("Команда выполнена.");
    }
}
