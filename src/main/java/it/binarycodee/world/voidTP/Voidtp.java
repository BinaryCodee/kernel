package it.binarycodee.world.voidTP;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Voidtp implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location to = event.getTo();

        if (to.getY() <= -5) {
            Location teleportLocation = new Location(to.getWorld(), 9591, 45, 10264, 0, 0);
            player.teleport(teleportLocation);
        }
    }
}


