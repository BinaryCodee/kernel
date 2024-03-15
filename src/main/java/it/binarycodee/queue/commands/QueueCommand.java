package it.binarycodee.queue.commands;

import it.binarycodee.Kernel;
import it.binarycodee.queue.data.Queues;
import it.binarycodee.queue.data.User;
import it.binarycodee.queue.system.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QueueCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!Kernel.instance.getConfigC().system) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Kernel.instance.getConfigC().Prefix + "&cLa coda è al momento in pausa, riprova più tardi..."));
                return true;
            }
            if (args.length < 1) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Kernel.instance.getConfigC().Prefix + "&cÈ necessario inserire un server, Usa: /queue <server>&e!"));
                return true;
            }

            String serverName = args[0].toLowerCase();

            if (args.length > 1) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Kernel.instance.getConfigC().Prefix + "&cUsa: /queue <server>&e!"));
                return true;
            }

            User user = Kernel.instance.getUserManager().getUser(player);
            if (user.getPosition() != 0) {
                player.sendMessage(Kernel.instance.getConfigC().Prefix + ChatColor.translateAlternateColorCodes('&', "&cSei già in queue&e!"));
            } else {
                if (Kernel.instance.getQueueManager().getQueue(serverName) != null) {
                    if (!player.hasPermission(Kernel.instance.getConfigC().Bypass_Perm)) {
                        Queues queues = Kernel.instance.getQueueManager().getQueue(serverName);
                        queues.AddQueue(player);
                        user.setQueue(serverName);
                        int queuesize = queues.getTotalQueue().size();
                        user.setPosition(queuesize);
                        player.sendMessage(Kernel.instance.getConfigC().Prefix + Kernel.instance.getConfigC().Queue_Added.replace("%pos%", String.valueOf(queuesize)).replace("%maxpos%", String.valueOf(queuesize)).replace("%server%", serverName));
                    } else {
                        player.sendMessage(Kernel.instance.getConfigC().Prefix + Kernel.instance.getConfigC().Queue_Done);
                        BungeeCord.sendPlayerToServer(player, serverName);
                    }
                } else {
                    player.sendMessage(Kernel.instance.getConfigC().Prefix + ChatColor.translateAlternateColorCodes('&', "&cQuesto server non è nella lista delle code&e!"));
                }
            }
        } else {
            sender.sendMessage("Non sei un giocatore!");
        }
        return false;
    }
}
