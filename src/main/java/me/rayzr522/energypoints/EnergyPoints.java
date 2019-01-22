package me.rayzr522.energypoints;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

public class EnergyPoints extends JavaPlugin {
    private static EnergyPoints instance;

    public static EnergyPoints getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        reload();
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

        // TODO: load data handlers
    }

    public void save() {
        // TODO: save data handlers
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
}
