package it.binarycodee.queue.system;


import it.binarycodee.Kernel;
import it.binarycodee.queue.commands.*;
import it.binarycodee.queue.events.*;

public class Registerer {
    public static void RegisterEvents() {
        Kernel.instance.getServer().getPluginManager().registerEvents(new leaveQueue(), Kernel.instance);
        Kernel.instance.getServer().getPluginManager().registerEvents(new joinQueue(), Kernel.instance);
        Kernel.instance.getCommand("kernelqueue").setExecutor(new QueueMainCommand());
        Kernel.instance.getCommand("queue").setExecutor(new QueueCommand());
        Kernel.instance.getCommand("kernelsystem").setExecutor(new SystemCommand());
        Kernel.instance.getServer().getMessenger().registerOutgoingPluginChannel(Kernel.instance, "BungeeCord");
    }
}

