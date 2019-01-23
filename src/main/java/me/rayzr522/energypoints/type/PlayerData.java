package me.rayzr522.energypoints.type;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PlayerData {
    private final UUID uuid;
    private int energy;
    private List<Booster> activeBoosters;

    public PlayerData(UUID uuid, int energy, List<Booster> activeBoosters) {
        this.uuid = uuid;
        this.energy = energy;
        this.activeBoosters = activeBoosters;
    }

    public static PlayerData load(UUID uuid, ConfigurationSection config) {
        return new PlayerData(
                uuid,
                config.getInt("energy"),
                // TODO: Load boosters.
                new ArrayList<>()
        );
    }

    public void saveTo(ConfigurationSection config) {
        config.set("energy", energy);
        // TODO: Serialize boosters
    }

    /**
     * @return The unique ID of the player to whom this player data belongs.
     */
    public UUID getUniqueId() {
        return uuid;
    }

    /**
     * Attempts to get the player associated with this player data.
     *
     * @return The player associated with this player data, if they are online.
     */
    public Optional<Player> getPlayer() {
        return Optional.ofNullable(Bukkit.getPlayer(uuid));
    }

    /**
     * @return How much energy the player has.
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * Updates the player's current energy.
     *
     * @param energy The new value to set for energy.
     * @return The updated value of energy.
     */
    public int setEnergy(int energy) {
        if (energy < 0) {
            throw new IllegalArgumentException(String.format("Attempted to set player's energy for player '%s' to less than zero (%d)", uuid.toString(), energy));
        }

        return this.energy = energy;
    }

    /**
     * @return All active boosters for this player.
     */
    public List<Booster> getActiveBoosters() {
        return activeBoosters;
    }
}
