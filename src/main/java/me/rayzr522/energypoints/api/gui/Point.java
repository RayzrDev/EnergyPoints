package me.rayzr522.energypoints.api.gui;

public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return The X coordinate of this point.
     */
    public int getX() {
        return x;
    }

    /**
     * @return The Y coordinate of this point.
     */
    public int getY() {
        return y;
    }

    /**
     * Adds the given X and Y values to the current X and Y and returns a new point with the result.
     * <br>
     * <b>Note:</b> this does not mutate the current point.
     *
     * @param x The X value to add.
     * @param y The Y value to add.
     * @return The modified point.
     */
    public Point add(int x, int y) {
        return new Point(this.x + x, this.y + y);
    }

    /**
     * Adds the given point's X and Y values to the current X and Y and returns a new point.
     * <br>
     * <b>Note:</b> this does not mutate the current point.
     *
     * @param point The point to add.
     * @return The combined point.
     */
    public Point add(Point point) {
        return new Point(this.x + point.getX(), this.y + point.getY());
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
