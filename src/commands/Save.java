package commands;

import java.io.IOException;

public class Save extends Command{

    public Save(){
        super();
    }

    @Override
    public void execute() throws IOException{
        getCommandHandler().save();
    }
}
