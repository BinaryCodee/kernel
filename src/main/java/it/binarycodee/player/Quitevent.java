package it.binarycodee.player;

import it.binarycodee.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Quitevent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerQuitEvent event) {
        Player player = (Player) event.getPlayer();

        Bukkit.broadcastMessage(ChatUtils.getColoredText("&8[&c-&8] &7%player% è uscito dalla Lobby&e!").replaceAll("%player%", player.getName()));
    }
}
