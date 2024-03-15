package it.binarycodee.queue.system;

import java.util.List;

import it.binarycodee.Kernel;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;

public class Config {
    public String Prefix;
    public String Queue_Added;
    public String Queue_Move;
    public String Queue_Done;
    public String Bypass_Perm;
    public String pluginversion;
    public List<String> servers;
    public boolean system;

    public void ReloadConfig() {
        this.ReloadConfigData();
        Kernel.instance.reloadConfig();
        Kernel.instance.getConfig().options().copyDefaults();
        Kernel.instance.saveDefaultConfig();
    }

    private void ReloadConfigData() {
        this.Prefix = ChatColor.translateAlternateColorCodes('&', Kernel.instance.c.getString("queue.prefix"));
        this.Queue_Added = ChatColor.translateAlternateColorCodes('&', Kernel.instance.c.getString("queue.messages.added"));
        this.Queue_Move = ChatColor.translateAlternateColorCodes('&', Kernel.instance.c.getString("queue.messages.move"));
        this.Queue_Done = ChatColor.translateAlternateColorCodes('&', Kernel.instance.c.getString("queue.messages.done"));
        this.Bypass_Perm = Kernel.instance.c.getString("queue.perms.bypass");
        PluginDescriptionFile pdf = Kernel.instance.getDescription();
        this.pluginversion = pdf.getVersion();
        this.servers = Kernel.instance.c.getStringList("queue.servers");
        for (String servers : Kernel.instance.c.getStringList("queue.servers")) {
            Kernel.instance.getQueueManager().addQueue(servers.toLowerCase());
            System.out.println(servers.toLowerCase());
        }
        Registerer.RegisterEvents();
        this.system = true;
    }
}