package me.rayzr522.energypoints.api.gui;

public class Bounds {
    private int x;
    private int y;
    private int width;
    private int height;

    public Bounds(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public static Bounds point(int x, int y) {
        return new Bounds(x, y, 1, 1);
    }

    /**
     * @return The X coordinate of the bounds.
     */
    public int getX() {
        return x;
    }

    /**
     * @return The Y coordinate of the bounds.
     */
    public int getY() {
        return y;
    }

    /**
     * @return The width of the bounds.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return The height of the bounds.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Checks if a point is within the bounds.
     *
     * @param x The X component of the point.
     * @param y The Y component of the point.
     * @return Whether or not the point is within the bounds.
     */
    public boolean isWithinBounds(int x, int y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height;
    }

    @Override
    public String toString() {
        return "Bounds{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
