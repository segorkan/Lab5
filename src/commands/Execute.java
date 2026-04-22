package commands;

import auxiliary.ConsoleHandler;
import auxiliary.CurrentInput;
import exceptions.FileProblemException;
import exceptions.WrongFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.Stack;

public class Execute extends Command {

    public Execute() {
        super();
    }

    /**
     * Перенаправление реализации команды execute.
     *
     * @param argument
     * @throws IOException
     * @throws FileProblemException
     * @throws WrongFormatException
     */
    @Override
    public void execute(String argument) throws IOException, FileProblemException, WrongFormatException, StackOverflowError {
        Path filePath = Paths.get(argument);
        if (Files.exists(filePath)) {
            if (Files.isReadable(filePath) && Files.isRegularFile(filePath)) {
                if (!filePath.getFileName().toString().toLowerCase().endsWith(".txt")) {
                    throw new WrongFormatException("Неверный формат файла.");
                }
                if (ConsoleHandler.getInstance().getScriptList().contains(filePath.toString())){
                    throw new StackOverflowError("Запрещен рекурсивный вызов.");
                }
                ConsoleHandler.getInstance().addScript(filePath.toString());
                System.out.println("Начинаю выполнение " + filePath);
                getCommandHandler().execute(filePath);
                getConsoleHandler().addToHistory("execute");
                System.out.println("Выполнение скрипта закончено.");
                ConsoleHandler.getInstance().removeLastScript();
            } else {
                throw new FileProblemException("Файл нельзя прочитать.");
            }
        } else {
            throw new IOException("Переданного файла не существует.");
        }
        System.out.println("Команда выполнена.");
    }
}
