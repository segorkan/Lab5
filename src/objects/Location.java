package objects;

import java.util.Objects;

public class Location {
    private int x;
    private float y;
    private String name;
    private static final float eps = 0.000000001f;

    public Location(int x, float y, String name){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location product = (Location) o;
        return Objects.equals(getX(), product.getX()) && compareFloat(product.getY()) &&
                Objects.equals(getName(), product.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getName());
    }

    @Override
    public String toString() {
        return String.format("Location[x = %d, y = %.5f, name = %s]", getX(), getY(), getName());
    }
}