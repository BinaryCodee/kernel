package it.binarycodee.commands;

import it.binarycodee.utils.ChatUtils;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class FlyCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtils.getFormattedText("player-only"));
            return true;
        }
        if (!sender.hasPermission("kernel.fly")) {
            sender.sendMessage(ChatUtils.getFormattedText("no-permission"));
            return true;
        }
        final Player player = (Player)sender;
        if (args.length == 0) {
            if (player.getAllowFlight()) {
                player.setAllowFlight(false);
                player.sendMessage(ChatUtils.getFormattedText("fly.disabled"));
                return true;
            }
            player.setAllowFlight(true);
            player.sendMessage(ChatUtils.getFormattedText("fly.enabled"));
            return true;
        }
        else {
            final Player target = Bukkit.getPlayerExact(args[0]);
            if (target == player) {
                if (player.getAllowFlight()) {
                    player.setAllowFlight(false);
                    player.sendMessage(ChatUtils.getFormattedText("fly.disabled"));
                    return true;
                }
                player.setAllowFlight(true);
                player.sendMessage(ChatUtils.getFormattedText("fly.enabled"));
                return true;
            }
            else {
                if (!target.isOnline()) {
                    player.sendMessage(ChatUtils.getFormattedText("player-offline"));
                    return true;
                }
                if (target.getAllowFlight()) {
                    target.setAllowFlight(false);
                    player.sendMessage(ChatUtils.getFormattedText("fly.disabled-for-player").replaceAll("%name%", target.getName()));
                    target.sendMessage(ChatUtils.getFormattedText("fly.disabled-by-staff").replaceAll("%name%", player.getName()));
                    return true;
                }
                target.setAllowFlight(true);
                player.sendMessage(ChatUtils.getFormattedText("fly.enabled-for-player").replaceAll("%name%", target.getName()));
                target.sendMessage(ChatUtils.getFormattedText("fly.disabled-for-player").replaceAll("%name%", player.getName()));
                return true;
            }
        }
    }
}