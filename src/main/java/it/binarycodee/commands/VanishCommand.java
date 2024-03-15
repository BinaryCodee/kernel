package it.binarycodee.commands;

import it.binarycodee.Kernel;
import it.binarycodee.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtils.getFormattedText("player-only"));
            return true;
        }

        if (!sender.hasPermission("kernel.vanish")) {
            sender.sendMessage(ChatUtils.getFormattedText("no-permission"));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {

            if (Kernel.vanishedStaffers.contains(player)) {
                Kernel.vanishedStaffers.remove(player);
                player.sendMessage(ChatUtils.getFormattedText("vanish.disabled"));
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.showPlayer(player);
                    player.setAllowFlight(false);
                    return true;
                }

            } else {
                Kernel.vanishedStaffers.add(player);
                player.sendMessage(ChatUtils.getFormattedText("vanish.enabled"));
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.hidePlayer(player);
                    player.setAllowFlight(true);
                    return true;
                }
            }
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayerExact(args[0]);

            if (target == null) {
                player.sendMessage(ChatUtils.getFormattedText("player-offline"));
                return true;
            }

            if (target == player) {
                if (Kernel.vanishedStaffers.contains(player)) {
                    Kernel.vanishedStaffers.remove(player);
                    player.sendMessage(ChatUtils.getFormattedText("vanish.disabled"));
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.showPlayer(player);
                        player.setAllowFlight(false);
                        return true;
                    }

                } else {
                    Kernel.vanishedStaffers.add(player);
                    player.sendMessage(ChatUtils.getFormattedText("vanish.enabled"));
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.hidePlayer(player);
                        player.setAllowFlight(true);
                        return true;
                    }
                }
            }

            if (Kernel.vanishedStaffers.contains(target)) {
                Kernel.vanishedStaffers.remove(target);
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.showPlayer(target);
                    target.setAllowFlight(false);
                    player.sendMessage(ChatUtils.getFormattedText("vanish.disabled-for-player")
                            .replaceAll("%name%", target.getName()));
                    target.sendMessage(ChatUtils.getFormattedText("vanish.disabled-by-staff")
                            .replaceAll("%name%", player.getName()));
                    return true;
                }

            } else {
                Kernel.vanishedStaffers.add(target);
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.hidePlayer(target);
                    target.setAllowFlight(true);
                    player.sendMessage(ChatUtils.getFormattedText("vanish.enabled-for-player")
                            .replaceAll("%name%", target.getName()));
                    target.sendMessage(ChatUtils.getFormattedText("vanish.enabled-by-staff")
                            .replaceAll("%name%", player.getName()));
                    return true;
                }
            }
        }
        return true;
    }
}