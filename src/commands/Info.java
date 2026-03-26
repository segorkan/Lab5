package commands;

public class Info extends Command{

    public Info(){
        super();
    }

    @Override
    public void execute() {
        String info = super.getCollectionHandler().printInfo();
        System.out.println(info);
    }
}
