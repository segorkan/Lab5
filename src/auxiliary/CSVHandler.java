package auxiliary;

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

    /**
     * Метод считывает {@link Product} из csv-файла.
     * @param csvPath путь до csv-файла.
     * @throws NoEnvironmentVariableException когда не существует файла с именем, указанном в переменной окружения.
     * @throws FileProblemException           при невозможности чтения файла.
     * @throws WrongFormatException           при неверном формате файла или данных внутри файла.
     */
    public void read(Path csvPath) throws NoEnvironmentVariableException, FileProblemException, WrongFormatException {
        if (Files.exists(csvPath)) {
            if (Files.isReadable(csvPath) && Files.isRegularFile(csvPath)) {
                if (!csvPath.getFileName().toString().toLowerCase().endsWith(".csv")) {
                    throw new WrongFormatException();
                }
                try (InputStreamReader reader = new InputStreamReader(Files.newInputStream(csvPath), StandardCharsets.UTF_8)) {
                    StringBuilder newLine = new StringBuilder();
                    int ch;
                    int count = 1;
                    boolean firstline = true;
                    while ((ch = reader.read()) != -1) {
                        if (ch == '\n') {
                            String lineStr = newLine.toString();
                            if (firstline){
                                firstline = false;
                                newLine.setLength(0);
                                continue;
                            }
                            if (!lineStr.isEmpty()) {
                                System.out.printf("Начинаю ввод продукта %d.%n", count);
                                String[] fields = lineStr.split(";");
                                parseProduct(fields);
                                System.out.printf("Ввод строки %d завершён.%n", count);
                                count++;
                            }
                            newLine.setLength(0);
                        } else {
                            newLine.append((char) ch);
                        }
                    }
                    if (!newLine.isEmpty()) {
                        String lineStr = newLine.toString();
                        System.out.printf("Начинаю ввод продукта %d.%n", count);
                        String[] fields = lineStr.split(";");
                        parseProduct(fields);
                        System.out.printf("Ввод строки %d завершён.%n", count);
                    }
                    Product.incrementGlobalId();
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

    /**
     * Метод парсит 1 продукт из csv-файла.
     * @param line список полей одного продукта.
     * @throws IOException при проблемах с исходными данными.
     */
    public void parseProduct(String[] line) throws IOException {
        Path newObject = Paths.get("resources/new_object.txt");
        try (BufferedOutputStream writer = new BufferedOutputStream(Files.newOutputStream(
                newObject, StandardOpenOption.TRUNCATE_EXISTING))) {
            for (String obj : line) {
                writer.write((obj + "\n").getBytes(StandardCharsets.UTF_8));
                writer.flush();
            }
            InputStream original = CurrentInput.getInputStream();
            CurrentInput.changeInputStream(Files.newInputStream(newObject));
            CommandHandler commandHandler = CommandHandler.getInstance();
            InputStreamReader reader = new InputStreamReader(CurrentInput.getInputStream());
            Product product = commandHandler.makeElementFromCSV(reader);
            Product.setGlobalId(Math.max(Product.getGlobalId(), product.getId()));
            CurrentInput.changeInputStream(original);
            if (reader.read() != -1) {
                reader.close();
                throw new IOException("Проблема с данными в исходном файле. Последний продукт некорректен.");
            }
            reader.close();
            CollectionHandler.getInstance().addElement(product);
        }
    }

    /**
     * Метод записывает элементы коллекции в csv-файл.
     * @throws FileProblemException           при проблемах с записью в файл.
     * @throws WrongFormatException           при неправильном формате файла.
     * @throws NoEnvironmentVariableException при изменении переменной окружения во время выполнения.
     */
    public void write() throws NoEnvironmentVariableException, FileProblemException, WrongFormatException, IOException {
        Path csvPath = Paths.get(System.getenv("FILE_NAME"));
        if (Files.exists(csvPath)) {
            if (Files.isWritable(csvPath) && Files.isRegularFile(csvPath)) {
                if (!csvPath.getFileName().toString().toLowerCase().endsWith(".csv")) {
                    throw new WrongFormatException();
                }

                try (BufferedOutputStream writer = new BufferedOutputStream(
                        Files.newOutputStream(csvPath, StandardOpenOption.TRUNCATE_EXISTING))) {

                    writer.write(("ID;Имя продукта;Координата X;Координата Y;Дата создания;" +
                            "Цена;Номер продукта;Единица измерения;Владелец(Y/N);" +
                            "Имя владельца;Вес владельца;Цвет глаз;Цвет волос;Национальность;" +
                            "Местоположение(Y/N);Координата X;Координата Y;Название локации" + "\n").getBytes(StandardCharsets.UTF_8));
                    for (Product product : CollectionHandler.getInstance().getCollection()) {

                        StringBuilder productMembers = new StringBuilder();
                        productMembers.append(Integer.toString(product.getId())).append(";");
                        productMembers.append(product.getName()).append(";");
                        productMembers.append(Integer.toString(product.getCoordinates().getX())).append(";");
                        productMembers.append(Float.toString(product.getCoordinates().getY())).append(";");
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        String formattedDate = dateFormat.format(product.getDate());
                        productMembers.append(formattedDate).append(";");
                        try {
                            productMembers.append(Float.toString(product.getPrice())).append(";");
                        } catch (NullPointerException e) {
                            productMembers.append(";");
                        }
                        productMembers.append(product.getPartNumber()).append(";");
                        productMembers.append(product.getUnitOfMeasure().toString()).append(";");
                        if (product.getOwner() != null) {
                            productMembers.append("Y").append(";");
                            productMembers.append(product.getOwner().getName()).append(";");
                            productMembers.append(Long.toString(product.getOwner().getWeight())).append(";");
                            try {
                                productMembers.append(product.getOwner().getEyeColor().toString()).append(";");
                            } catch (NullPointerException e) {
                                productMembers.append(";");
                            }
                            try {
                                productMembers.append(product.getOwner().getHairColor().toString()).append(";");
                            } catch (NullPointerException e) {
                                productMembers.append(";");
                            }
                            productMembers.append(product.getOwner().getNationality().toString()).append(";");
                            if (product.getOwner().getLocation() != null) {
                                productMembers.append("Y").append(";");
                                productMembers.append(Integer.toString(product.getOwner().getLocation().getX())).append(";");
                                productMembers.append(Float.toString(product.getOwner().getLocation().getY())).append(";");
                                productMembers.append(product.getOwner().getLocation().getName());
                            } else {
                                productMembers.append("N");
                            }
                        } else {
                            productMembers.append("N");
                        }
                        productMembers.append("\n");
                        writer.write(productMembers.toString().getBytes(StandardCharsets.UTF_8));
                    }
                }

            } else {
                throw new FileProblemException("В файл нельзя записать данные.");
            }
        } else {
            throw new NoEnvironmentVariableException("Файла с именем в переменной окружения не существует.");
        }
    }
}
