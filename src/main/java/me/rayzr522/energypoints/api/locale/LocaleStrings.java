package me.rayzr522.energypoints.api.locale;


import org.bukkit.command.CommandSender;

public enum LocaleStrings {
    NO_PERMISSION("error.no-permission"),
    ONLY_PLAYERS("error.command.only-players"),
    UNKNOWN_PLAYER("error.command.unknown-player"),
    INVALID_USAGE("error.command.invalid-usage"),
    COMMAND_ENERGY_INFO("command.energy.info");

    private final String key;

    LocaleStrings(String key) {
        this.key = key;
    }

    /**
     * Translates this locale string with the given translation objects.
     *
     * @param translationObjects The objects to translate it with.
     * @return The translated message.
     */
    public String tr(Object... translationObjects) {
        return Locale.getInstance().tr(key, translationObjects);
    }

    /**
     * Translates this locale string and then sends it to a command sender.
     *
     * @param sender             The command sender to send the message to.
     * @param translationObjects The objects to translate it with.
     */
    public void send(CommandSender sender, Object... translationObjects) {
        sender.sendMessage(tr(translationObjects));
    }
}
