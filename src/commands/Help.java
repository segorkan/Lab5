package commands;

public class Help extends Command {

    private static String message = "help : display help on available commands\n" +
            "info : output information about the collection to the standard output stream (type, initialization date, number of elements, etc.)\n" +
            "show : output all elements of the collection in string representation to the standard output stream\n" +
            "add {element} : add a new element to the collection\n" +
            "update id {element} : update the value of the collection element whose id matches the specified one\n" +
            "remove_by_id id : remove an element from the collection by its id\n" +
            "clear : clear the collection\n" +
            "save : save the collection to a file\n" +
            "execute_script file_name : read and execute a script from the specified file. The script contains commands in the same form as the user enters them in interactive mode\n" +
            "exit : terminate the program (without saving to file)\n" +
            "add_if_min {element} : add a new element to the collection if its value is less than the smallest element of this collection\n" +
            "remove_lower {element} : remove all elements from the collection that are less than the specified one\n" +
            "history : output the last 13 commands (without their arguments)\n" +
            "max_by_owner : output any object from the collection whose owner field value is maximum\n" +
            "count_less_than_price price : output the number of elements whose price field value is less than the specified one\n" +
            "filter_greater_than_unit_of_measure unitOfMeasure : output elements whose unitOfMeasure field value is greater than the specified one";

    public Help(){
        super();
    }

    /**
     * Реализация команды help.
     */
    @Override
    public void execute() {
        System.out.println(Help.message);
    }
}
