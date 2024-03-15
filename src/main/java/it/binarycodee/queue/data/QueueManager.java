package it.binarycodee.queue.data;

import java.util.HashSet;
import java.util.Set;

public class QueueManager {
    private Set<Queues> queues = new HashSet<Queues>();

    public Set<Queues> getQueues() {
        return this.queues;
    }

    public Queues getQueue(String queue) {
        return this.queues.stream().filter(queues -> queues.getQueueServer().equals(queue)).findAny().orElse(null);
    }

    public void addQueue(String queue) {
        this.queues.add(new Queues(queue));
    }

    public void removeQueue(String queue) {
        this.queues.remove(queue);
    }
}