package auxiliary;

import java.time.LocalDateTime;
import java.util.*;

import exceptions.NoElementFoundException;
import objects.Product;
import objects.Person;

/**
 * Класс для взаимодействия с коллекцией.
 */
public class CollectionHandler {

    private static CollectionHandler instance;
    private final LinkedHashSet<Product> collection;
    private final LocalDateTime creationDate;

    private CollectionHandler() {
        this.collection = new LinkedHashSet<Product>();
        this.creationDate = LocalDateTime.now();
    }

    public static CollectionHandler getInstance() {
        if (CollectionHandler.instance == null) {
            CollectionHandler.instance = new CollectionHandler();
        }
        return CollectionHandler.instance;
    }

    /**
     * Вернуть коллекцию.
     * @return коллекция типа LinkedHashSet<Product>
     */
    public LinkedHashSet<Product> getCollection() {
        return collection;
    }

    /**
     * Вернуть дату создания коллекции.
     * @return String в формате dd.MM.yy
     */
    public String getCreationDate() {
        return String.format(
                "%d.%d.%d",
                creationDate.getDayOfMonth(),
                creationDate.getMonthValue(),
                creationDate.getYear());
    }

    /**
     * Вернуть информацию о файле.
     * @return String
     */
    public String printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Тип коллекции: ").append(collection.getClass().getSimpleName()).append("\n");
        String elementType = collection.isEmpty() ? "Product" : collection.iterator().next().getClass().getSimpleName();
        sb.append("Тип элементов: ").append(elementType).append("\n");
        sb.append("Количество элементов: ").append(collection.size()).append("\n");
        sb.append("Дата инициализации: ").append(getCreationDate());
        return sb.toString();
    }

    /**
     * Добавить элемент в коллекцию
     * @param product элемент для вставки.
     */
    public void addElement(Product product) {
        getInstance().getCollection().add(product);
    }

    /**
     * Удалить элемент из коллекции.
     * @param product элемент для удаления.
     */
    public void removeElement(Product product) {
        getInstance().getCollection().remove(product);
    }

    /**
     *
     * @param id id, по которому происходит поиск в коллекции.
     * @return {@link Product} элемент с переданным id.
     * @throws NoElementFoundException если элемента с переданным id не существует.
     */
    public Product findById(int id) throws NoElementFoundException {
        for (Product elem : getInstance().getCollection()) {
            if (elem.getId() == id) {
                return elem;
            }
        }
        throw new NoElementFoundException("Элемента с таким id нет в коллекции.");
    }

    /**
     * Поиск минимального элемента в коллекции.
     * @return {@link Product} минимальный элемент.
     * @throws NoSuchElementException если коллекция пустая.
     */
    public Product getMin() throws NoSuchElementException {
        if (getInstance().getCollection().isEmpty()){
            throw new NoSuchElementException();
        }
        Iterator<Product> it = getInstance().getCollection().iterator();
        Product minProduct = it.next();
        while (it.hasNext()){
            Product element = it.next();
            if (element.compareTo(minProduct) < 0){
                minProduct = element;
            }
        }
        return minProduct;
    }

    /**
     * Поиск элемента с максимальным значением владельца.
     * @return {@link Person} владелец
     */
    public Person getMaxOwner() {
        Comparator<Person> comparator = Comparator.nullsFirst(
                Comparator.comparing(Person::getName).thenComparingDouble(Person::getWeight));
        ArrayList<Person> owners = new ArrayList<Person>();
        for (Product element : getInstance().getCollection()){
            owners.add(element.getOwner());
        }
        owners.sort(comparator);
        return owners.get(owners.size() - 1);
    }
}
