package me.rayzr522.energypoints;

import me.rayzr522.energypoints.listeners.GuiListener;
import me.rayzr522.energypoints.api.locale.Locale;
import me.rayzr522.energypoints.command.EnergyCommand;
import me.rayzr522.energypoints.data.PlayerDataManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class EnergyPoints extends JavaPlugin {
    private static EnergyPoints instance;

    private final Locale locale = new Locale();
    private final PlayerDataManager playerDataManager = new PlayerDataManager(this);

    public static EnergyPoints getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        reload();

        // TODO: Dynamically register
        EnergyCommand energyCommand = new EnergyCommand();

        getCommand("energy").setExecutor(energyCommand);
        getCommand("energy").setTabCompleter(energyCommand);

        getServer().getPluginManager().registerEvents(new GuiListener(), this);
    }

    @Override
    public void onDisable() {
        try {
            save();
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Failed to save data!", e);
        }

        instance = null;
    }

    public void reload() {
        saveDefaultConfig();
        reloadConfig();

        locale.load(getConfig("messages.yml"));
        playerDataManager.load(getConfig("players.yml"));
    }

    public void save() {
        saveConfig("players.yml", playerDataManager.save());
    }

    public void saveConfig(String path, YamlConfiguration config) {
        try {
            config.save(getFile(path));
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, String.format("Failed to save config '%s'", path), e);
        }
    }

    public YamlConfiguration getConfig(String path) {
        File file = getFile(path);
        if (!file.exists() && getResource(path) != null) {
            saveResource(path, false);
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public File getFile(String path) {
        return new File(getDataFolder(), path.replace('/', File.separatorChar));
    }

    /**
     * @return The player data manager instance for this plugin.
     */
    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }

    public Locale getLocale() {
        return locale;
    }
}
