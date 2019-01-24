package me.rayzr522.energypoints.api.gui.event;

import me.rayzr522.energypoints.api.gui.Gui;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Represents an event where
 */
public class ClickEvent {
    private final Gui gui;
    private final InventoryClickEvent rawEvent;

    /**
     * Constructs a new click event from a gui and a raw InventoryClickEvent.
     *
     * @param gui      The gui associated with the event.
     * @param rawEvent The raw click event which caused this.
     */
    public ClickEvent(Gui gui, InventoryClickEvent rawEvent) {
        this.gui = gui;
        this.rawEvent = rawEvent;
    }

    /**
     * @return The gui that the click event originated from.
     */
    public Gui getGui() {
        return gui;
    }

    /**
     * @return The raw event which lead to this ClickEvent.
     */
    public InventoryClickEvent getRawEvent() {
        return rawEvent;
    }

    /**
     * @return The player who clicked.
     */
    public Player getPlayer() {
        return (Player) rawEvent.getWhoClicked();
    }

    /**
     * @return The type of click that it was.
     */
    public ClickType getClickType() {
        return rawEvent.getClick();
    }

    /**
     * @return The slot that was clicked.
     */
    public int getSlot() {
        return rawEvent.getSlot();
    }

    /**
     * @return The X position of the slot in the inventory.
     */
    public int getX() {
        return getSlot() % 9;
    }

    /**
     * @return The Y position of the slot in the inventory.
     */
    public int getY() {
        return getSlot() / 9;
    }

    /**
     * @return The item in the player's cursor.
     */
    public ItemStack getCursor() {
        return rawEvent.getCursor();
    }

    /**
     * @return Whether or not the event was cancelled.
     */
    public boolean isCancelled() {
        return rawEvent.isCancelled();
    }

    /**
     * Changes whether or not the event is cancelled.
     *
     * @param cancelled The new state to set for the event.
     */
    public void setCancelled(boolean cancelled) {
        rawEvent.setCancelled(cancelled);
    }
}
