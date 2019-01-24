package me.rayzr522.energypoints.api.gui.impl;


import me.rayzr522.energypoints.api.gui.Bounds;
import me.rayzr522.energypoints.api.gui.Component;
import me.rayzr522.energypoints.api.gui.Panel;
import me.rayzr522.energypoints.api.gui.RenderContext;
import me.rayzr522.energypoints.util.ItemFactory;

public abstract class AbstractComponent implements Component {
    private ItemFactory icon;
    private Bounds bounds;
    private Panel parent;

    /**
     * Creates a new abstract component with the given icon and the given bounds.
     *
     * @param icon   The icon to use for the component.
     * @param bounds The bounds of the component.
     */
    public AbstractComponent(ItemFactory icon, Bounds bounds) {
        this.icon = icon;
        this.bounds = bounds;
    }

    /**
     * @return The icon of this component.
     */
    public ItemFactory getIcon() {
        return icon;
    }

    /**
     * Updates the icon of this component.
     *
     * @param icon The new icon to set.
     */
    public void setIcon(ItemFactory icon) {
        this.icon = icon;
    }

    @Override
    public Bounds getBounds() {
        return bounds;
    }

    @Override
    public Panel getParent() {
        return parent;
    }

    @Override
    public void setParent(Panel parent) {
        this.parent = parent;
    }

    @Override
    public void render(RenderContext renderContext) {
        for (int x = bounds.getX(); x < bounds.getX() + bounds.getWidth(); x++) {
            for (int y = bounds.getY(); y < bounds.getY() + bounds.getHeight(); y++) {
                renderContext.setIcon(x, y, getIcon().build());
            }
        }
    }
}
