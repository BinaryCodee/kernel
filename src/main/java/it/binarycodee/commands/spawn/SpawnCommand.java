package it.binarycodee.commands.spawn;

import it.binarycodee.Kernel;
import it.binarycodee.utils.ChatUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        Location spawn = (Location) Kernel.getFileManager().getConfig().get("spawn-location");

        if (spawn == null) {
            player.sendMessage(ChatUtils.getFormattedText("spawn.no-spawn-found"));
            return true;
        }

        player.teleport(spawn);
        return true;
    }
}