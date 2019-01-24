package me.rayzr522.energypoints.api.gui.util;

import me.rayzr522.energypoints.api.gui.Bounds;
import me.rayzr522.energypoints.api.gui.Component;
import me.rayzr522.energypoints.api.gui.event.ClickEvent;
import me.rayzr522.energypoints.api.gui.impl.AbstractComponent;
import me.rayzr522.energypoints.util.ItemFactory;
import org.bukkit.Material;

import java.util.function.Consumer;

public class Components {

    /**
     * Creates a simple button component with a basic listener.
     *
     * @param icon     The icon to use.
     * @param bounds   The bounds of the button.
     * @param listener The click listener for the button.
     * @return The simple button component.
     */
    public static Component simpleButton(ItemFactory icon, Bounds bounds, Consumer<ClickEvent> listener) {
        return new AbstractComponent(icon, bounds) {
            @Override
            public void onClick(ClickEvent e) {
                listener.accept(e);
            }
        };
    }

    /**
     * Creates a label component that does nothing on click.
     *
     * @param icon   The icon to use for the label.
     * @param bounds The bounds of the button.
     * @return The label component.
     */
    public static Component label(ItemFactory icon, Bounds bounds) {
        return simpleButton(icon, bounds, e -> { /* do nothing */ });
    }

    /**
     * Creates a blank black stained glass pane filler component.
     *
     * @param bounds The bounds of the filler.
     * @return The filler component.
     */
    public static Component filler(Bounds bounds) {
        return label(ItemFactory.of(Material.STAINED_GLASS_PANE).setDurability((short) 15).setName(" "), bounds);
    }
}
