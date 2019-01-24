package me.rayzr522.energypoints.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ItemFactory {
    private final ItemStack item;

    private ItemFactory(ItemStack item) {
        this.item = item.clone();
    }

    /**
     * Creates an item factory out of a raw item.
     *
     * @param item The item to create a factory out of.
     * @return The item factory of that item.
     */
    public static ItemFactory of(ItemStack item) {
        return new ItemFactory(item);
    }

    /**
     * Creates an item factory of a specific material.
     *
     * @param type The type of the item to create a factory for.
     * @return The item factory of that item.
     */
    public static ItemFactory of(Material type) {
        return of(new ItemStack(type));
    }

    /**
     * Sets the item's type.
     *
     * @param type The type to set for the item.
     * @return The current item factory instance.
     */
    public ItemFactory setType(Material type) {
        item.setType(type);
        return this;
    }

    /**
     * Set's the item's amount.
     *
     * @param amount The new amount to set for the item.
     * @return The current item factory instance.
     */
    public ItemFactory setAmount(int amount) {
        if (amount < 1) {
            return setType(Material.AIR);
        }

        item.setAmount(amount);
        return this;
    }

    /**
     * Sets the item's durability.
     *
     * @param durability The new durability to set for the item.
     * @return The current item factory instance.
     */
    public ItemFactory setDurability(short durability) {
        item.setDurability(durability);
        return this;
    }

    /**
     * @return The item's ItemMeta.
     */
    public ItemMeta getItemMeta() {
        return item.getItemMeta();
    }

    /**
     * Sets the item's item meta.
     *
     * @param meta The meta to set.
     * @return The current item factory instance.
     */
    public ItemFactory setItemMeta(ItemMeta meta) {
        item.setItemMeta(meta);
        return this;
    }

    /**
     * Sets the item's custom name.
     *
     * @param name The custom name to set.
     * @return The current item factory instance.
     */
    public ItemFactory setName(String name) {
        ItemMeta meta = getItemMeta();
        meta.setDisplayName(name);
        return setItemMeta(meta);
    }

    /**
     * Sets the item's custom lore.
     *
     * @param lore The lore to set.
     * @return The current item factory instance.
     */
    public ItemFactory setLore(List<String> lore) {
        ItemMeta meta = getItemMeta();
        meta.setLore(lore);
        return setItemMeta(meta);
    }

    /**
     * Sets the item's custom lore.
     *
     * @param lore The lore to set.
     * @return The current item factory instance.
     */
    public ItemFactory setLore(String... lore) {
        return setLore(Arrays.asList(lore));
    }

    /**
     * @return The built item stack with all modifications.
     */
    public ItemStack build() {
        return item.clone();
    }
}

