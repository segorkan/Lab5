package commands;

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

public class Execute extends Command{

    public Execute(){
        super();
    }

    @Override
    public void execute(String argument) throws IOException, FileProblemException, WrongFormatException{
        Path filePath = Paths.get(argument);
        if (Files.exists(filePath)){
            if (Files.isReadable(filePath) && Files.isRegularFile(filePath)){
                if (!filePath.getFileName().toString().toLowerCase().endsWith(".txt")) {
                    throw new WrongFormatException();
                }
                getCommandHandler().execute(filePath);
            }
            else {
                throw new FileProblemException("Передан неверный файл: файл нельзя прочитать.");
            }
        }
        else {
            throw new IOException("Переданного файла не существует.");
        }
    }
}
