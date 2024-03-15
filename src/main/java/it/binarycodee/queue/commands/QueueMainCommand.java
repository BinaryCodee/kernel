package it.binarycodee.queue.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class QueueMainCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis server is running Kernel Queue-Addon v1.0 by @BinaryCodee"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cKernel v1.0 - Queue-Addon"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8* &e/kernelqueue"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8* &e/kernelsystem <on / off>"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8* &e/queue <server>"));
            return true;
        }
        if (args[0].equalsIgnoreCase("version")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis server is running Kernel Queue-Addon v1.0 by @BinaryCodee"));
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis server is running Kernel Queue-Addon v1.0 by @BinaryCodee"));
        }
        return false;
    }
}