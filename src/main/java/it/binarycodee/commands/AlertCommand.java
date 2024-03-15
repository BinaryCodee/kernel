package it.binarycodee.commands;

import it.binarycodee.utils.ChatUtils;
import org.bukkit.command.*;
import org.bukkit.*;

public class AlertCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!sender.hasPermission("kernel.alert")) {
            sender.sendMessage(ChatUtils.getFormattedText("no-permission"));
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatUtils.getFormattedText("alert.usage"));
            return true;
        }
        final String message = String.join(" ", (CharSequence[])args);
        Bukkit.broadcastMessage(ChatUtils.getColoredText("&8[&4&lALERT&8] &c" + message));
        return true;
    }
}

