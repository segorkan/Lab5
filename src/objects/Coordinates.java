package objects;

import java.util.Objects;

public class Coordinates {
    private Integer x; //Значение поля должно быть больше -352, Поле не может быть null
    private float y; //Значение поля должно быть больше -765
    private static final float eps = 0.000000001f;

    public Coordinates(int x, float y) {
        this.x = x;
        this.y = y;
    }

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
        return String.format("Coordinates[x = %d, y = %.5f]", getX(), getY());
    }
}