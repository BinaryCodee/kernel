package it.binarycodee.commands.spawn;

import it.binarycodee.Kernel;
import it.binarycodee.utils.ChatUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtils.getFormattedText("player-only"));
            return true;
        }

        if (!sender.hasPermission("kernel.setspawn")) {
            sender.sendMessage(ChatUtils.getFormattedText("no-permission"));
            return true;
        }

        Player player = (Player) sender;

        Location location = player.getLocation();
        FileConfiguration config = Kernel.getFileManager().getConfig();

        File file = new File(Kernel.getInstance().getDataFolder(), "config.yml");

        config.set("spawn-location", location);
        Kernel.getFileManager().saveFile(config, file);

        sender.sendMessage(ChatUtils.getFormattedText("spawn.new-spawn-set"));
        return true;
    }
}