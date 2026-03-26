package objects;

import java.util.Objects;
import java.util.Date;

public class Product implements Comparable<Product> {
    private int id;
    private String name;
    private Coordinates coordinates;
    private Date creationDate;
    private Float price;
    private String partNumber;
    private UnitOfMeasure unitOfMeasure;
    private Person owner;
    private static int idCount = 1;
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

    public static void incrementId(){
        idCount += 1;
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
        return String.format("id: " + id + "[\n" +
                "\t" + "name = %s" +
                "\t" + "coordinates = %s" +
                "\t" + "creationDate = %s" +
                "\t" + "price = %.5f" +
                "\t" + "partNumber = %s" +
                "\t" + "%s" +
                "\t" + "%s" +
                "\n]");
    }
    private Boolean compareFloat(float other) {
        if (Math.abs(price - other) < eps) {
            return true;
        }
        return false;
    }
}