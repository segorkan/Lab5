package objects;

import java.util.Objects;

/**
 * Класс локации человека.
 */
public class Location {
    private int x;
    private float y;
    private String name; //Длина строки не должна быть больше 563, Поле не может быть null
    private static final float eps = 0.000000001f;

    /**
     * Конструктор локации.
     *
     * @param x
     * @param y
     * @param name
     */
    public Location(int x, float y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    private Boolean compareFloat(float other) {
        if (Math.abs(x - y) < eps) {
            return true;
        }
        return false;
    }

    public int getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Переопределение метода для проверки равенства двух локаций.
     *
     * @param o объект с которым сравниваем.
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location product = (Location) o;
        return Objects.equals(getName(), product.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return String.format("Location[x = %d, y = %s, name = %s]", getX(), formatFloat(getY()), getName());
    }

    /**
     * Метод для форматирования значения float.
     *
     * @param number значение для вывода.
     * @return
     */
    private String formatFloat(float number){
        String tempString = Float.toString(number);
        String[] parts = tempString.split("\\.");
        while (parts[1].length() < 5){
            parts[1] += "0";
        }
        return parts[0] + "." + parts[1].substring(0, 4);
    }
}