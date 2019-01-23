package me.rayzr522.energypoints.api.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public abstract class AbstractCommand implements CommandExecutor {
    private final String name;
    private final String permission;
    private final String usage;
    private final String description;
    private final boolean playersOnly;

    private final Set<AbstractCommand> children = new HashSet<>();

    public AbstractCommand(String name, String permission, String usage, String description, boolean playersOnly) {
        this.name = name;
        this.permission = permission;
        this.usage = usage;
        this.description = description;
        this.playersOnly = playersOnly;
    }

    private void handleCommand(CommandSender sender, String[] args) {
        if (!sender.hasPermission(permission)) {
            // TODO: Translate
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return;
        }

        if (args.length > 0) {
            String subLabel = args[0];
            AbstractCommand subCommand = findSubCommand(subLabel).orElse(null);
            if (subCommand != null) {
                subCommand.handleCommand(sender, Arrays.copyOfRange(args, 1, args.length));
                return;
            }
        }

        if (playersOnly && !(sender instanceof Player)) {
            // TODO: Translate
            sender.sendMessage(ChatColor.RED + "Only players can use this!");
            return;
        }

        // TODO: Handle errors
        // TODO: Handle command result -- show usage or not?
        onCommand(sender, args);
    }

    public void showUsage(CommandSender sender) {
        // TODO: Handle sub commands -- recursion?
        sender.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GRAY + "/" + name + " " + ChatColor.YELLOW + usage + ChatColor.DARK_GRAY + " - " + ChatColor.YELLOW + description);
    }

    @Override
    public final boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        handleCommand(commandSender, args);
        return true;
    }

    public abstract void onCommand(CommandSender commandSender, String[] args);

    /**
     * @return The name of this command.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The permission for this command.
     */
    public String getPermission() {
        return permission;
    }

    /**
     * @return The usage text of this command.
     */
    public String getUsage() {
        return usage;
    }

    /**
     * @return The description of this command.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return Whether or not this command is only usable by players or not.
     */
    public boolean isPlayersOnly() {
        return playersOnly;
    }

    /**
     * @return The set of child commands.
     */
    public Set<AbstractCommand> getChildren() {
        return children;
    }

    /**
     * Matches the given input to a sub command. The input does not have to be the complete sub command name, nor does it have to be case specific, just the start of the sub command name.
     *
     * @param input The input to match to a sub command.
     * @return The matching sub command, if one was found.
     */
    public Optional<AbstractCommand> findSubCommand(String input) {
        return children.stream()
                .filter(child -> child.getName().toLowerCase().startsWith(input.toLowerCase()))
                .findFirst();
    }
}
