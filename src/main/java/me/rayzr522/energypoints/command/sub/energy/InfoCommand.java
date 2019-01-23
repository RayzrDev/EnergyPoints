package me.rayzr522.energypoints.command.sub.energy;

import me.rayzr522.energypoints.EnergyPoints;
import me.rayzr522.energypoints.api.command.AbstractCommand;
import me.rayzr522.energypoints.type.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfoCommand extends AbstractCommand {

    public InfoCommand() {
        super("info", "EnergyPoints.command.info", "[player]", "Shows you info about yourself or another player.", true);
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player target = args.length > 0 ? Bukkit.getPlayer(args[0]) : (Player) sender;

        if (target == null) {
            // TODO: Translate
            sender.sendMessage(ChatColor.RED + "That user could not be found!");
            return;
        }

        PlayerData playerData = EnergyPoints.getInstance().getPlayerDataManager().getPlayerData(target);

        // TODO: Translate
        sender.sendMessage(ChatColor.GREEN + "Energy: " + ChatColor.YELLOW + playerData.getEnergy());
    }
}
