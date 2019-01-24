package me.rayzr522.energypoints.gui;

import me.rayzr522.energypoints.EnergyPoints;
import me.rayzr522.energypoints.api.gui.Bounds;
import me.rayzr522.energypoints.api.gui.Gui;
import me.rayzr522.energypoints.api.gui.util.Components;
import me.rayzr522.energypoints.api.locale.LocaleStrings;
import me.rayzr522.energypoints.type.PlayerData;
import me.rayzr522.energypoints.util.ItemFactory;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class InfoGui extends Gui {
    private final Player player;

    public InfoGui(Player player) {
        super(36, LocaleStrings.INFOGUI_NAME.tr(player.getName()));

        this.player = player;

        // Bottom border
        addChild(Components.filler(new Bounds(0, 3, 9, 1)));
        addChild(Components.simpleButton(
                ItemFactory.of(Material.STAINED_GLASS_PANE)
                        .setDurability((short) 5)
                        .setName(LocaleStrings.INFOGUI_ENERGYSTATUS_NAME.tr(getPlayerData().getEnergy()))
                        .setLore(LocaleStrings.INFOGUI_ENERGYSTATUS_LORE.tr().split("\n")),
                new Bounds(4, 3, 1, 1),
                // TODO: Open another gui?
                e -> System.out.println(e.getPlayer().getName() + " wants to buy more energy!")
        ));
    }

    private PlayerData getPlayerData() {
        return EnergyPoints.getInstance().getPlayerDataManager().getPlayerData(player);
    }
}
