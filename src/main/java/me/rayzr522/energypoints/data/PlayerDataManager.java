package me.rayzr522.energypoints.data;

import me.rayzr522.energypoints.EnergyPoints;
import me.rayzr522.energypoints.type.PlayerData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class PlayerDataManager {
    private final EnergyPoints plugin;
    private Map<UUID, PlayerData> playerDataMap = new HashMap<>();

    public PlayerDataManager(EnergyPoints plugin) {
        this.plugin = plugin;
    }

    public void load(ConfigurationSection config) {
        playerDataMap = config.getKeys(false).stream()
                .filter(config::isConfigurationSection)
                .map(key -> {
                    try {
                        return PlayerData.load(UUID.fromString(key), config.getConfigurationSection(key));
                    } catch (Exception e) {
                        plugin.getLogger().log(Level.WARNING, String.format("Failed to load player data for key '%s':", key), e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(PlayerData::getUniqueId, playerData -> playerData));
    }

    public YamlConfiguration save() {
        YamlConfiguration config = new YamlConfiguration();

        playerDataMap.forEach((id, playerData) -> playerData.saveTo(config.createSection(id.toString())));

        return config;
    }
}
