package it.binarycodee.commands;

import it.binarycodee.utils.ChatUtils;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;

public class InvseeCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtils.getFormattedText("player-only"));
            return true;
        }
        if (!sender.hasPermission("kernel.invsee")) {
            sender.sendMessage(ChatUtils.getFormattedText("no-permission"));
            return true;
        }
        final Player player = (Player)sender;
        if (args.length == 0) {
            player.sendMessage(ChatUtils.getFormattedText("invsee.usage"));
            return true;
        }
        if (args.length == 1) {
            final Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(ChatUtils.getFormattedText("player-offline"));
                return true;
            }
            player.openInventory((Inventory)target.getInventory());
            player.sendMessage(ChatUtils.getFormattedText("invsee.success").replaceAll("%name%", target.getName()));
        }
        return true;
    }
}
