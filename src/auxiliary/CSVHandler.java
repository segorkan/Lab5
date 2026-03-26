package auxiliary;

import com.opencsv.*;
import exceptions.FileProblemException;
import exceptions.NoEnvironmentVariableException;
import exceptions.WrongFormatException;
import objects.Product;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

public class CSVHandler {

    private static CSVHandler instance;

    private CSVHandler() {
    }

    public static CSVHandler getInstance() {
        if (CSVHandler.instance == null) {
            CSVHandler.instance = new CSVHandler();
        }
        return CSVHandler.instance;
    }

    public void read(Path csvPath) throws IOException, NoEnvironmentVariableException, FileProblemException, WrongFormatException {
        if (Files.exists(csvPath)) {
            if (Files.isReadable(csvPath) && Files.isRegularFile(csvPath)) {
                if (!csvPath.getFileName().toString().toLowerCase().endsWith(".csv")) {
                    throw new WrongFormatException();
                }

                try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader
                        (Files.newInputStream(csvPath), StandardCharsets.UTF_8)).withCSVParser(
                        new CSVParserBuilder().withSeparator(';').build()).build()) {

                    String[] nextLine;
                    int count = 1;
                    while ((nextLine = reader.readNext()) != null) {
                        System.out.printf("Начинаю ввод продукта %d.%n", count);
                        parseProduct(nextLine);
                        count += 1;
                        System.out.printf("Ввод строки %d завершён.%n", count - 1);
                    }
                } catch (Exception e) {
                    throw new WrongFormatException("Проблема с данными в исходном файле. Последний продукт некорректен.");
                }
            } else {
                throw new FileProblemException("Передан неверный файл: файл нельзя прочитать.");
            }
        } else {
            throw new NoEnvironmentVariableException("Файла с именем в переменной окружения не существует.");
        }
    }

    public void parseProduct(String[] line) throws IOException {
        Path newObject = Paths.get("resources/new_object.txt");
        try (OutputStreamWriter writer = new OutputStreamWriter(new BufferedOutputStream(Files.newOutputStream(
                newObject, StandardOpenOption.TRUNCATE_EXISTING)))) {
            for (String obj : line) {
                writer.write(obj + "\n");
                writer.flush();
            }

            InputStream original = CurrentInput.getInputStream();
            CurrentInput.changeInputStream(Files.newInputStream(newObject));
            CommandHandler commandHandler = CommandHandler.getInstance();
            Scanner sc = new Scanner(CurrentInput.getInputStream());
            Product product = commandHandler.makeElementFromCSV(sc);
            CurrentInput.changeInputStream(original);

            if (sc.hasNextLine()) {
                sc.close();
                throw new IOException("Проблема с данными в исходном файле. Последний продукт некорректен.");
            }
            sc.close();
            CollectionHandler.getInstance().addElement(product);
        }
    }

    public void write() throws IOException, FileProblemException, WrongFormatException {
        Path csvPath = Paths.get(System.getenv("FILE_NAME"));
        if (Files.exists(csvPath)){
            if (Files.isWritable(csvPath) && Files.isRegularFile(csvPath)){
                if (!csvPath.getFileName().toString().toLowerCase().endsWith(".csv")) {
                    throw new WrongFormatException();
                }

                try (CSVWriter writer = (CSVWriter) new CSVWriterBuilder(new OutputStreamWriter(new BufferedOutputStream(
                        Files.newOutputStream(csvPath, StandardOpenOption.TRUNCATE_EXISTING)),
                        StandardCharsets.UTF_8)).withSeparator(';').build())  {

                    for (Product product : CollectionHandler.getInstance().getCollection()) {

                        ArrayList<String> productMembers = new ArrayList<String>();

                        productMembers.add(Integer.toString(product.getId()));
                        productMembers.add(product.getName());
                        productMembers.add(Integer.toString(product.getCoordinates().getX()));
                        productMembers.add(Float.toString(product.getCoordinates().getY()));
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        String formattedDate = dateFormat.format(product.getDate());
                        productMembers.add(formattedDate);
                        try {
                            productMembers.add(Float.toString(product.getPrice()));
                        } catch (NullPointerException e) {
                            productMembers.add("");
                        }
                        productMembers.add(product.getPartNumber());
                        productMembers.add(product.getUnitOfMeasure().toString());
                        if (product.getOwner() != null) {
                            productMembers.add("Y");
                            productMembers.add(product.getOwner().getName());
                            productMembers.add(Long.toString(product.getOwner().getWeight()));
                            try {
                                productMembers.add(product.getOwner().getEyeColor().toString());
                            } catch (NullPointerException e) {
                                productMembers.add("");
                            }
                            try {
                                productMembers.add(product.getOwner().getHairColor().toString());
                            } catch (NullPointerException e) {
                                productMembers.add("");
                            }
                            productMembers.add(product.getOwner().getNationality().toString());
                            if (product.getOwner().getLocation() != null) {
                                productMembers.add("Y");
                                productMembers.add(Integer.toString(product.getOwner().getLocation().getX()));
                                productMembers.add(Float.toString(product.getOwner().getLocation().getY()));
                                productMembers.add(product.getOwner().getLocation().getName());
                            } else {
                                productMembers.add("N");
                            }
                        } else {
                            productMembers.add("N");
                        }

                        String[] csvData = productMembers.toArray(new String[0]);
                        writer.writeNext(csvData);
                    }
                }

            } else{
                throw new FileProblemException("В файл нельзя записать данные.");
            }
        } else{
            throw new IOException("Файла с именем в переменной окружения не существует.");
        }
    }
}
