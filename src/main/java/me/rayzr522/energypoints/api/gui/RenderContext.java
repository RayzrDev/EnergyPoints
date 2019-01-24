package me.rayzr522.energypoints.api.gui;

import org.bukkit.inventory.ItemStack;

public interface RenderContext {
    /**
     * Renders the given icon to the supplied X and Y coordinate.
     *
     * @param x    The X coordinate to render at.
     * @param y    The Y coordinate to render at.
     * @param icon The icon to render.
     */
    void setIcon(int x, int y, ItemStack icon);
}
