package me.rayzr522.energypoints.api.command;

import me.rayzr522.energypoints.api.locale.LocaleStrings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractCommand implements CommandExecutor, TabCompleter {
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
            LocaleStrings.NO_PERMISSION.send(sender, permission);
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
            LocaleStrings.ONLY_PLAYERS.send(sender);
            return;
        }

        // TODO: Handle errors
        // TODO: Handle command result -- show usage or not?
        onCommand(sender, args);
    }

    public void showUsage(CommandSender sender) {
        // TODO: Handle sub commands -- recursion?
        LocaleStrings.INVALID_USAGE.send(sender, name, usage, description);
    }

    @Override
    public final List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 1 && children.size() > 0) {
            return children.stream()
                    .filter(child -> child.getName().toLowerCase().startsWith(args[0].toLowerCase()))
                    .map(AbstractCommand::getName)
                    .collect(Collectors.toList());
        } else if (args.length == 2) {
            return findSubCommand(args[0])
                    .map(child -> child.onTabComplete(sender, command, s, Arrays.copyOfRange(args, 1, args.length)))
                    .orElse(tabComplete(sender, args));
        }

        return tabComplete(sender, args);
    }

    /**
     * Provides tab completion options for the given context.
     *
     * @param sender The sender who is tab completing.
     * @param args   The current args.
     */
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public final boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        handleCommand(commandSender, args);
        return true;
    }

    /**
     * Called when this command is run.
     *
     * @param sender The user who executed the command.
     * @param args   The args that were passed tot he command.
     */
    public abstract void onCommand(CommandSender sender, String[] args);

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
     * Adds a command to this command's list of children.
     *
     * @param childCommand The child command to add.
     */
    public void addChild(AbstractCommand childCommand) {
        children.add(childCommand);
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
