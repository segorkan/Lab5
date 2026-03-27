package auxiliary;

import exceptions.*;
import objects.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static auxiliary.InputFunctions.*;

/**
 * Класс, отвечающий за реализацию консольных команд.
 */
public class CommandHandler {

    private static CommandHandler instance;

    private CommandHandler() {
    }

    /**
     * Создаёт и возвращает сущность типа CommandHandler.
     * @return Сущность типа CommandHandler
     */
    public static CommandHandler getInstance() {
        if (CommandHandler.instance == null) {
            CommandHandler.instance = new CommandHandler();
        }
        return CommandHandler.instance;
    }

    /**
     * Реализация команды add.
     * @param reader поток для чтения
     */
    public void add(InputStreamReader reader) {
        CollectionHandler.getInstance().addElement(makeElement(reader, true));
    }

    /**
     * Реализация команды add_if_min.
     * @param sc сканер
     */
    public void addMin(Scanner sc) {
        Product newProduct = makeElement(sc, false);
        try {
            Product minProduct = CollectionHandler.getInstance().getMin();
            if (newProduct.compareTo(minProduct) < 0) {
                CollectionHandler.getInstance().addElement(newProduct);
                Product.incrementGlobalId();
            }
        } catch (NoSuchElementException e) {
            CollectionHandler.getInstance().addElement(newProduct);
            Product.incrementGlobalId();
        }
    }

    /**
     * Реализация команды update.
     * @param sc сканер
     * @param id id изменяемого элемента
     */
    public void update(Scanner sc, int id) {
        try {
            Product oldProduct = CollectionHandler.getInstance().findById(id);
            Product changedProduct = makeElement(sc, false);
            oldProduct.setCoordinates(changedProduct.getCoordinates());
            oldProduct.setName(changedProduct.getName());
            oldProduct.setOwner(changedProduct.getOwner());
            oldProduct.setPrice(changedProduct.getPrice());
            oldProduct.setPartNumber(changedProduct.getPartNumber());
            oldProduct.setUnitOfMeasure(changedProduct.getUnitOfMeasure());
        } catch (NoElementFoundException e) {
            System.out.println("Элемент с данным id не найден.");
        }
    }

    /**
     * Реализация команды remove.
     * @param id id удаляемого элемента
     */
    public void remove(int id) {
        try {
            Product oldProduct = CollectionHandler.getInstance().findById(id);
            CollectionHandler.getInstance().removeElement(oldProduct);
        } catch (NoElementFoundException e) {
            System.out.println("Элемент с данным id не найден.");
        }
    }

    /**
     * Реализация команды remove_lower.
     * @param sc сканер
     */
    public void removeLower(Scanner sc) {
        Product compareProduct = makeElement(sc, false);
        Iterator<Product> it = CollectionHandler.getInstance().getCollection().iterator();
        while (it.hasNext()) {
            if (it.next().compareTo(compareProduct) < 0) {
                it.remove();
            }
        }
    }

    /**
     * Реализация команды max_by_owner.
     */
    public void maxByOwner() {
        try {
            if (CollectionHandler.getInstance().getCollection().isEmpty()) {
                throw new NoElementFoundException();
            }
            Person maxOwner = CollectionHandler.getInstance().getMaxOwner();
            for (Product element : CollectionHandler.getInstance().getCollection()) {
                if (element.getOwner().equals(maxOwner)) {
                    System.out.println(element);
                    break;
                }
            }
        } catch (NoElementFoundException e) {
            System.out.println("В коллекции нет элементов.");
        }
    }

    /**
     * Реализация команды count_less_than_price.
     * @param price цена, с которой сравниваются элементы.
     */
    public void countLessThanPrice(float price) {
        int count = 0;
        for (Product element : CollectionHandler.getInstance().getCollection()) {
            if (element.getPrice() < price) {
                ++count;
            }
        }
        System.out.println(count);
    }

    /**
     * Реализация команды filter.
     * @param unitOfMeasure сравниваемая единица измерения.
     */
    public void filter(UnitOfMeasure unitOfMeasure){
        for (Product element : CollectionHandler.getInstance().getCollection()){
            if (element.getUnitOfMeasure().ordinal() > unitOfMeasure.ordinal()){
                System.out.println(element);
            }
        }
    }

    /**
     * Реализация команды clear.
     */
    public void clear() {
        CollectionHandler.getInstance().getCollection().clear();
    }

    /**
     * Реализация команды history.
     * @param history дек с историей команд.
     * @param historyPrintSize количество выводимых команд. (по условию 13)
     */
    public void history(Deque<String> history, int historyPrintSize) {
        int edge = Math.min(history.size(), historyPrintSize);
        System.out.printf("Начинаю вывод последних %d команд.%n", historyPrintSize);
        Iterator<String> it = history.descendingIterator();
        int count = 0;
        ArrayList<String> printHistory = new ArrayList<String>();
        while (count < edge) {
            printHistory.add(it.next());
            count++;
        }
        for (int i = edge - 1; i >= 0; --i) {
            System.out.println(printHistory.get(i));
        }
    }

    /**
     * Реализация команды execute.
     * @param filePath путь до исполняемого файла.
     * @throws IOException
     */
    public void execute(Path filePath) throws IOException {
        InputStream original = CurrentInput.getInputStream();
        try (InputStream reader = Files.newInputStream(filePath)){
            CurrentInput.changeInputStream(reader);
            InputStreamReader charReader = new InputStreamReader(reader);
            Scanner sc = new Scanner(charReader);
            ConsoleHandler console = ConsoleHandler.getInstance();
            try {
                while (sc.hasNext()) {
                    ArrayList<String> parts = new ArrayList<>(Arrays.asList(sc.nextLine().trim().split(" ")));
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
                        }
                    } else {
                        throw new IOException("Неверное количество аргументов.");
                    }
                }
            } catch (IOException | CommandNotFoundException | FileProblemException | WrongFormatException e) {
                System.out.println("(In-Script) " + e.getClass().getSimpleName() + ": " + e.getMessage());
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            } catch (NumberFormatException e){
                System.out.println("(In-Script) " + "Неверный формат аргумента. " + e.getMessage());
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            } catch (ConditionsNotMetException e){
                System.out.println("(In-Script) " + "Цена обязана быть больше 0.");
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            } catch (IllegalArgumentException e){
                System.out.println("(In-Script) " + "Значение обязано быть из списка: (KILOGRAMS, CENTIMETRES, SQUARE_METERS)");
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            }
        }
        CurrentInput.changeInputStream(original);
    }

    /**
     * Вызов реализации из обработчика CSV.
     * @throws IOException
     */
    public void save() throws IOException {
        CSVHandler.getInstance().write();
    }

    /**
     * Реализация команды exit.
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * Создание нового элемента коллекции.
     * @param reader поток для чтения данных.
     * @param isNew создание нового элемента или временного для консольных команд.
     * @return Возвращает созданный {@link Product}.
     */
    public Product makeElement(InputStreamReader reader, boolean isNew) throws IOException{
        try {
            String name = validateName(reader);
            Coordinates coords = validateCoordinates(reader);
            Float price = validatePrice(reader);
            String partNumber = validatePartNumber(reader);
            UnitOfMeasure unitofMeasure = validateUnitOfMeasure(reader);
            Person owner = validatePerson(reader);
            return new Product(name, coords, price, partNumber, unitofMeasure, owner, isNew);
        } catch (IOException | NumberFormatException | CommandNotFoundException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
            System.exit(1);
            return null;
        }
    }

    /**
     * Создание элемента из исходного файла.
     * @param sc сканер.
     * @return Возвращает созданный {@link Product}.
     */
    public Product makeElementFromCSV(InputStreamReader reader) throws IOException{
        try {
            int id = validateId(reader);
            String name = validateName(reader);
            Coordinates coords = validateCoordinates(reader);
            Date date = validateDate(sc);
            Float price = validatePrice(sc);
            String partNumber = validatePartNumber(sc);
            UnitOfMeasure unitofMeasure = validateUnitOfMeasure(sc);
            Person owner = validatePerson(sc);
            return new Product(id, name, coords, date, price, partNumber, unitofMeasure, owner);
        } catch (IOException | NumberFormatException | CommandNotFoundException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
            System.exit(1);
            return null;
        } catch (Exception e){
            System.out.println("Исключение");
            System.exit(1);
            return null;
        }
    }

    /**
     * Реализация команды show.
     */
    public void show() {
        for (Product elem : CollectionHandler.getInstance().getCollection()) {
            System.out.println(elem.toString());
        }
    }
}
