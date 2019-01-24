package me.rayzr522.energypoints.api.gui;

import me.rayzr522.energypoints.api.gui.event.ClickEvent;
import me.rayzr522.energypoints.api.gui.impl.DefaultPanel;
import me.rayzr522.energypoints.api.gui.impl.GuiRenderContext;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class Gui extends DefaultPanel implements InventoryHolder {
    private Inventory inventory;

    public Gui(int size, String title) {
        super(new Bounds(0, 0, 9, size / 9));
        inventory = Bukkit.createInventory(this, size, title);
    }

    /**
     * Opens the inventory for a player.
     *
     * @param player The player to open the inventory for.
     */
    public void open(Player player) {
        queueRender();
        player.openInventory(inventory);
    }

    /**
     * Called when the inventory is closed.
     *
     * @param player The player who closed the inventory.
     */
    public void onClose(Player player) {
        // not needed by default
    }

    /**
     * Note: used internally, no need to ever call this. Used in the gui listener to notify a gui that it has been clicked.
     *
     * @param e The inventory click event that triggered this.
     */
    public void handleClickEvent(InventoryClickEvent e) {
        onClick(new ClickEvent(this, e));
    }

    /**
     * @return The raw inventory of this gui.
     */
    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public RenderContext getRenderContext() {
        return new GuiRenderContext(this);
    }
}
