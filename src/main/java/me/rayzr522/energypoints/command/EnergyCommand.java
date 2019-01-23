package me.rayzr522.energypoints.command;

import me.rayzr522.energypoints.api.command.AbstractCommand;
import me.rayzr522.energypoints.command.sub.energy.InfoCommand;
import org.bukkit.command.CommandSender;

public class EnergyCommand extends AbstractCommand {
    public EnergyCommand() {
        super("energy", "EnergyPoints.command", "<sub command>", "The main command for Energy Points", false);

        addChild(new InfoCommand());
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        // TODO: Return type instead?
        showUsage(sender);
    }
}
