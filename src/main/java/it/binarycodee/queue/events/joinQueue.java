package it.binarycodee.queue.events;

import it.binarycodee.Kernel;
import it.binarycodee.queue.data.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class joinQueue implements Listener {

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Kernel.instance.getUserManager().addUser(player);
        User user = Kernel.instance.getUserManager().getUser(player);
    }

}