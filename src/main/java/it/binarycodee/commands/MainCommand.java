package it.binarycodee.commands;

import it.binarycodee.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

         if (player.hasPermission("kernel.admin")) {
            player.sendMessage(ChatUtils.getColoredText("&6&lKERNEL &8- &c&lADMIN"));
            player.sendMessage(ChatUtils.getColoredText(""));
            player.sendMessage(ChatUtils.getColoredText("&cComandi admin in sviluppo!"));
            player.sendMessage(ChatUtils.getColoredText(""));
        } else {
             player.sendMessage(ChatUtils.getColoredText("&cThis server is running Kernel v 1.1 by @SIX_veliero &@Binarycodee for storyrpg"));
         }
        return true;
    }
}
