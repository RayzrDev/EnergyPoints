package me.rayzr522.energypoints.api.locale;

import me.rayzr522.energypoints.EnergyPoints;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Locale {
    private Map<String, String> messages = new HashMap<>();

    public static Locale getInstance() {
        return EnergyPoints.getInstance().getLocale();
    }

    /**
     * Loads the locale from the given config.
     */
    public void load(ConfigurationSection config) {
        messages = config.getKeys(true).stream().collect(Collectors.toMap(
                key -> key,
                key -> config.isList(key)
                        ? String.join("\n", config.getStringList(key))
                        : config.getString(key)
        ));
    }

    private String getPrefixFor(String key) {
        String base = key.indexOf('.') < 0 ? "" : key.substring(0, key.lastIndexOf('.'));
        String prefix = messages.getOrDefault(base + ".prefix", messages.getOrDefault("prefix", ""));
        String prefixAddon = messages.getOrDefault(base + ".prefix-addon", "");
        return ChatColor.translateAlternateColorCodes('&', prefix + prefixAddon);
    }

    /**
     * Translates a message with the given key and inputs the given translation objects.
     *
     * @param key                The key to translate.
     * @param translationObjects The objects to format it with.
     * @return The translated message.
     */
    public String tr(String key, Object... translationObjects) {
        return getPrefixFor(key) + ChatColor.translateAlternateColorCodes('&', String.format(messages.getOrDefault(key, key), translationObjects));
    }

}

