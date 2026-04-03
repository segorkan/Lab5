package objects;

import java.util.Objects;

/**
 * Класс координаты продукта.
 */
public class Coordinates {
    private Integer x; //Значение поля должно быть больше -352, Поле не может быть null
    private float y; //Значение поля должно быть больше -765
    private static final float eps = 0.000000001f;

    /**
     * Конструктор координаты продукта.
     *
     * @param x
     * @param y
     */
    public Coordinates(int x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Метод для проверки равенства двух вещественных чисел.
     *
     * @param other другое значение float.
     * @return
     */
    private Boolean compareFloat(float other) {
        if (Math.abs(x - y) < eps) {
            return true;
        }
        return false;
    }

    public Integer getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates product = (Coordinates) o;
        return Objects.equals(getX(), product.getX()) && compareFloat(product.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        return String.format("Coordinates[x = %d, y = %s]", getX(), formatFloat(getY()));
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