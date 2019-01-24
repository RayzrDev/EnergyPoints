package me.rayzr522.energypoints.api.gui.impl;

import me.rayzr522.energypoints.api.gui.Gui;
import me.rayzr522.energypoints.api.gui.RenderContext;
import org.bukkit.inventory.ItemStack;

/**
 * A basic render context that renders to a gui's inventory, with an optional offset.
 */
public class GuiRenderContext implements RenderContext {
    private Gui targetGui;

    /**
     * Creates a new render context for the given gui, with no offset.
     *
     * @param targetGui The gui to render to.
     */
    public GuiRenderContext(Gui targetGui) {
        this.targetGui = targetGui;
    }

    @Override
    public void setIcon(int x, int y, ItemStack icon) {
        if (x < 0 || y < 0 || x >= 9 || y >= targetGui.getInventory().getSize() / 9) {
            // Do not throw errors or anything, just silently fail -- this way rendering things off of the edge doesn't error.
            return;
        }

        targetGui.getInventory().setItem(x + y * 9, icon);
    }
}
