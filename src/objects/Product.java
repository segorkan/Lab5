package objects;

import java.util.Objects;
import java.util.Date;

/**
 * Тип элементов в коллекции.
 */
public class Product implements Comparable<Product> {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float price; //Поле может быть null, Значение поля должно быть больше 0
    private String partNumber; //Длина строки должна быть не меньше 24, Поле не может быть null
    private UnitOfMeasure unitOfMeasure; //Поле не может быть null
    private Person owner; //Поле может быть null
    private static int idCount = 0;
    private static final float eps = 0.000000001f;

    public Product(String name, Coordinates coordinates, Float price, String partNumber,
                   UnitOfMeasure unitOfMeasure, Person owner, boolean isNew) {
        this.id = idCount;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date();
        this.price = price;
        this.partNumber = partNumber;
        this.unitOfMeasure = unitOfMeasure;
        this.owner = owner;
        if (isNew) {
            idCount += 1;
        }
    }

    public Product(int id, String name, Coordinates coordinates, Date creationDate, Float price, String partNumber,
                   UnitOfMeasure unitOfMeasure, Person owner){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.partNumber = partNumber;
        this.unitOfMeasure = unitOfMeasure;
        this.owner = owner;
    }

    public static void incrementGlobalId(){
        idCount += 1;
    }

    public static int getGlobalId(){
        return Product.idCount;
    }

    public static void setGlobalId(int id){
        Product.idCount = id;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(Coordinates coords) {
        this.coordinates = coords;
    }

    public Date getDate() {
        return this.creationDate;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPartNumber() {
        return this.partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return this.unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Person getOwner() {
        return this.owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public int compareTo(Product other){
        if (getPrice() == null && other.getPrice() == null){
            return Integer.compare(getId(), other.getId());
        }
        if (getPrice() == null && other.getPrice() != null){
            return -1;
        }
        if (getPrice() != null && other.getPrice() == null){
            return 1;
        }
        if (compareFloat(other.getPrice())){
            return Integer.compare(getId(), other.getId());
        }
        return Float.compare(getPrice(), other.getPrice());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Objects.equals(name, product.name)
                && Objects.equals(creationDate, product.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, creationDate);
    }

    @Override
    public String toString() {
        return String.format("id: " + id + " [\n" +
                "\t" + "name = %s" + "\n" +
                "\t" + "coordinates = %s" + "\n" +
                "\t" + "creationDate = %s" + "\n" +
                "\t" + "price = %.5f" + "\n" +
                "\t" + "partNumber = %s" + "\n" +
                "\t" + "Unit Of Measure = %s" + "\n" +
                "\t" + "Owner = %s" + "\n" + "]"
                , getName(), getCoordinates(), getDate(), getPrice(), getPartNumber(), getUnitOfMeasure(), getOwner());
    }
    private Boolean compareFloat(float other) {
        if (Math.abs(price - other) < eps) {
            return true;
        }
        return false;
    }
}