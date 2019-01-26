package me.rayzr522.energypoints.api.gui;

public class Bounds {
    private int width;
    private int height;

    public Bounds(int width, int height) {
        this.width = width;
        this.height = height;
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
     * @param self  The offset of this bounds object.
     * @param other The location of the point to check.
     * @return Whether or not the point is within the bounds.
     */
    public boolean isWithinBounds(Point self, Point other) {
        return other.getX() >= self.getX() && other.getX() <= self.getX() + this.width && other.getY() >= self.getY() && other.getY() <= self.getY() + this.height;
    }

    @Override
    public String toString() {
        return "Bounds{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
