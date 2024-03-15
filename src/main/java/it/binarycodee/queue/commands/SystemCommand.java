package it.binarycodee.queue.commands;

import it.binarycodee.Kernel;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SystemCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("kernel.queue.system")) {
            sender.sendMessage(Kernel.instance.getConfigC().Prefix + "§cNessun permesso!");
            return true;
        }
        if (args.length > 0 && args[0].equalsIgnoreCase("on")) {
            Kernel.instance.getConfigC().system = true;
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Kernel.instance.getConfigC().Prefix + "&eIl sistema di coda è &aabilitato&6!"));
            return true;
        }
        if (args.length > 0 && args[0].equalsIgnoreCase("off")) {
            Kernel.instance.getConfigC().system = false;
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Kernel.instance.getConfigC().Prefix + "&eIl sistema di coda è &cdisabilitato&6!"));
            return true;
        }
        sender.sendMessage(Kernel.instance.getConfigC().Prefix + ChatColor.RED + "/kernelsystem <on / off>");
        return false;
    }
}
