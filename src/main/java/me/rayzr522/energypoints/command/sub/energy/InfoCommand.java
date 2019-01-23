package me.rayzr522.energypoints.command.sub.energy;

import me.rayzr522.energypoints.EnergyPoints;
import me.rayzr522.energypoints.api.command.AbstractCommand;
import me.rayzr522.energypoints.api.locale.LocaleStrings;
import me.rayzr522.energypoints.type.PlayerData;
import org.bukkit.Bukkit;
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
            // TODO: Exception instead?
            LocaleStrings.UNKNOWN_PLAYER.send(sender, args[0]);
            return;
        }

        PlayerData playerData = EnergyPoints.getInstance().getPlayerDataManager().getPlayerData(target);

        LocaleStrings.COMMAND_ENERGY_INFO.send(sender, playerData.getEnergy());
    }
}
