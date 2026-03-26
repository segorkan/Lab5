package objects;

import java.util.Objects;

public class Person{
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long weight; //Значение поля должно быть больше 0
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле может быть null
    private Country nationality; //Поле не может быть null
    private Location location; //Поле может быть null

    public Person(String name, long weight, Color eyeColor, Color hairColor, Country nationality, Location location) {
        this.name = name;
        this.weight = weight;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    public String getName() {
        return this.name;
    }

    public long getWeight() {
        return this.weight;
    }

    public Color getEyeColor() {
        return this.eyeColor;
    }

    public Color getHairColor() {
        return this.hairColor;
    }

    public Country getNationality() {
        return this.nationality;
    }

    public Location getLocation() {
        return this.location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(weight, person.weight) &&
                Objects.equals(eyeColor, person.eyeColor) &&
                Objects.equals(hairColor, person.hairColor) &&
                Objects.equals(nationality, person.nationality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, eyeColor, hairColor, nationality);
    }

    @Override
    public String toString() {
        return String.format("Person[name = %s" +
                        ", weight = %d" +
                        ", eyeColor = %s" +
                        ", hairColor = %s" +
                        ", nationality = %s" +
                        ", location = %s]",
                name, weight, eyeColor, hairColor, nationality, location);
    }
}