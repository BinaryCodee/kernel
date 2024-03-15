package it.binarycodee.queue.checker;

import it.binarycodee.Kernel;
import it.binarycodee.queue.data.Queues;
import it.binarycodee.queue.data.User;
import it.binarycodee.queue.system.BungeeCord;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class QueueChecker {
    public static void QueueLoop(final List<String> servers) {
        new BukkitRunnable(){

            public void run() {
                if (Kernel.instance.getConfigC().system) {
                    for (String s : servers) {
                        Queues queues = Kernel.instance.getQueueManager().getQueue(s.toLowerCase());
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            User user;
                            if (!queues.getTotalQueue().contains(player) || (user = Kernel.instance.getUserManager().getUser(player)).getPosition() != 1) continue;
                            user.setQueue("");
                            user.setPosition(0);
                            player.sendMessage(Kernel.instance.getConfigC().Prefix + Kernel.instance.getConfigC().Queue_Done);
                            BungeeCord.sendPlayerToServer(player, queues.getQueueServer());
                            queues.RemoveQueue(player);
                            for (Player player1 : Bukkit.getOnlinePlayers()) {
                                User user1 = Kernel.instance.getUserManager().getUser(player1);
                                if (!user1.getQueue().equalsIgnoreCase(queues.getQueueServer())) continue;
                                int Position = user1.getPosition() - 1;
                                user1.setPosition(Position);
                                player1.sendMessage(Kernel.instance.getConfigC().Prefix + Kernel.instance.getConfigC().Queue_Move.replace("%pos%", String.valueOf(Position)).replace("%maxpos%", String.valueOf(queues.getTotalQueue().size())));
                            }
                        }
                    }
                }
            }
        }.runTaskTimerAsynchronously(Kernel.instance, 0L, 120L);
    }
}
