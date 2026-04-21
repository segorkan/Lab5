import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.nio.file.Paths;
import java.nio.file.Path;

import auxiliary.*;
import commands.*;
import exceptions.*;

import static auxiliary.CurrentInput.*;

public class Main {
    public static void main(String[] args) {

        ConsoleHandler console = ConsoleHandler.getInstance();

        console.addCommand("help", new Help());
        console.addCommand("info", new Info());
        console.addCommand("show", new Show());
        console.addCommand("add", new Add());
        console.addCommand("update", new Update());
        console.addCommand("remove_by_id", new Remove());
        console.addCommand("clear", new Clear());
        console.addCommand("save", new Save());
        console.addCommand("execute_script", new Execute());
        console.addCommand("exit", new Exit());
        console.addCommand("add_if_min", new AddMin());
        console.addCommand("remove_lower", new RemoveLower());
        console.addCommand("history", new History(console));
        console.addCommand("max_by_owner", new MaxByOwner());
        console.addCommand("count_less_than_price", new Count());
        console.addCommand("filter_greater_than_unit_of_measure", new Filter());

        CurrentInput.changeInputStream(System.in);
        InputStreamReader reader = CurrentInput.getInputStreamReader();

        try {
            String csvPath = System.getenv("FILE_NAME");
            if (csvPath == null) {
                throw new NoEnvironmentVariableException();
            }
            Path path = Paths.get(csvPath);
            CSVHandler handler = CSVHandler.getInstance();
            handler.read(path);
        } catch (InvalidPathException e) {
            System.out.println("В переменной окружения FILE_NAME находится неверный путь.");
            System.exit(1);
        } catch (NoEnvironmentVariableException e) {
            System.out.println("Не существует переданной переменной окружения.");
            System.exit(1);
        } catch (WrongFormatException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
            System.exit(1);
        }

        System.out.println("Все продукты перенесены. Можно вводить команды.");
        while (true) {
            try {
                StringBuilder sb = new StringBuilder();
                String command = null;
                int ch;
                while ((ch = reader.read()) != -1) {
                    if (ch == '\n') {
                        command = sb.toString();
                        break;
                    }
                    sb.append((char) ch);
                }
                if (ch == -1) {
                    command = sb.toString();
                }
                if (command.isBlank()){
                    continue;
                }
                ArrayList<String> parts = new ArrayList<>(Arrays.asList(command.trim().split(" ")));
                Iterator<String> it = parts.iterator();
                while (it.hasNext()) {
                    String cur = it.next();
                    if (cur.isEmpty()) {
                        it.remove();
                    }
                }
                if (parts.size() == 1) {
                    if (console.getCommandList().containsKey(parts.get(0))) {
                        console.executeCommand(parts.get(0));
                    } else {
                        throw new CommandNotFoundException("Команда не была найдена");
                    }
                } else if (parts.size() == 2) {
                    if (console.getCommandList().containsKey(parts.get(0))) {
                        console.executeCommand(parts.get(0), parts.get(1));
                    } else{
                        throw new CommandNotFoundException("Команда не была найдена");
                    }
                } else {
                    throw new IOException("В команде не может быть больше 2 аргументов.");
                }
            } catch (IOException | CommandNotFoundException | FileProblemException | WrongFormatException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат аргумента. " + e.getMessage());
            } catch (ConditionsNotMetException e) {
                System.out.println("Цена обязана быть больше 0.");
            } catch (IllegalArgumentException e) {
                System.out.println("Значение обязано быть из списка: (KILOGRAMS, CENTIMETRES, SQUARE_METERS)");
            }
        }
    }
}