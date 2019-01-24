package listeners;

import me.rayzr522.energypoints.api.gui.Gui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class GuiListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory() == null || !(e.getWhoClicked() instanceof Player) || !(e.getInventory().getHolder() instanceof Gui)) {
            return;
        }

        ((Gui) e.getInventory().getHolder()).handleClickEvent(e);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getInventory() == null || !(e.getPlayer() instanceof Player) || !(e.getInventory().getHolder() instanceof Gui)) {
            return;
        }

        ((Gui) e.getInventory().getHolder()).onClose((Player) e.getPlayer());
    }
}
