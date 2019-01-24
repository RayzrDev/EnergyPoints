package me.rayzr522.energypoints.api.gui.impl;

import me.rayzr522.energypoints.api.gui.RenderContext;
import org.bukkit.inventory.ItemStack;

public class OffsetRenderContext implements RenderContext {
    private final RenderContext parentContext;
    private final int offsetX;
    private final int offsetY;

    public OffsetRenderContext(RenderContext parentContext, int offsetX, int offsetY) {
        this.parentContext = parentContext;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    @Override
    public void setIcon(int x, int y, ItemStack icon) {
        parentContext.setIcon(x + offsetX, y + offsetY, icon);
    }
}
