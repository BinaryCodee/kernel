package it.binarycodee.queue.data;

import org.bukkit.entity.Player;
import java.util.ArrayList;

public class Queues {
    private String queueServer;
    private ArrayList<Player> totalQueue;

    public Queues(String queue) {
        this.queueServer = queue;
        this.totalQueue = new ArrayList();
    }

    public String getQueueServer() {
        return this.queueServer;
    }

    public ArrayList<Player> getTotalQueue() {
        return this.totalQueue;
    }

    public void RemoveQueue(Player player) {
        this.totalQueue.remove(player);
    }

    public void AddQueue(Player player) {
        this.totalQueue.add(player);
    }
}