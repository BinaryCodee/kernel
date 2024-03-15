package it.binarycodee.queue.events;

import it.binarycodee.Kernel;
import it.binarycodee.queue.data.Queues;
import it.binarycodee.queue.data.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class leaveQueue implements Listener {

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        User user = Kernel.instance.getUserManager().getUser(player);
        if (user.getPosition() != 0) {
            int postione = user.getPosition();
            Queues queues = Kernel.instance.getQueueManager().getQueue(user.getQueue());
            queues.RemoveQueue(player);
            for (Player player1 : Bukkit.getOnlinePlayers()) {
                User user1 = Kernel.instance.getUserManager().getUser(player1);
                if (user1.getPosition() == 1 || user1.getPosition() < postione || !user1.getQueue().equalsIgnoreCase(queues.getQueueServer()))
                    continue;
                int Position = user1.getPosition() - 1;
                user1.setPosition(Position);
                player1.sendMessage(Kernel.instance.getConfigC().Prefix + Kernel.instance.getConfigC().Queue_Move.replace("%pos%", String.valueOf(Position)).replace("%maxpos%", String.valueOf(queues.getTotalQueue().size())));
            }
        }
        Kernel.instance.getUserManager().removeUser(player);
    }

}