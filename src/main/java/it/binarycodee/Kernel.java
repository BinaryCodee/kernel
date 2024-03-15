package it.binarycodee;

import it.binarycodee.commands.*;
import it.binarycodee.commands.teleport.*;
import it.binarycodee.commands.gamemodes.*;
import it.binarycodee.commands.spawn.*;
import it.binarycodee.listeners.PlayerGeneralListener;
import it.binarycodee.player.Joinevent;
import it.binarycodee.system.firstopen.FirstOpenCommand;
import it.binarycodee.system.firstopen.FirstOpenHooks;
import it.binarycodee.system.firstopen.FirstOpenManager;
import me.clip.placeholderapi.PlaceholderAPI;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.cacheddata.CachedMetaData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import it.binarycodee.player.Quitevent;
import it.binarycodee.queue.checker.QueueChecker;
import it.binarycodee.queue.data.QueueManager;
import it.binarycodee.queue.data.UserManager;
import it.binarycodee.queue.system.Config;
import it.binarycodee.system.profilo.ProfiloListener;
import it.binarycodee.system.serverselector.ServerSelectorGUIListener;
import it.binarycodee.system.serverselector.ServerSelectorListener;
import it.binarycodee.utils.FileManager;
import it.binarycodee.world.voidTP.Voidtp;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public final class Kernel extends JavaPlugin implements Listener {

    private static final Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");
    private LuckPerms luckPerms;
    public static List<Player> vanishedStaffers;
    public static Kernel plugin;
    private FirstOpenManager firstOpenManager;
    public static Kernel instance;
    public FileConfiguration c;
    private Config config;
    private UserManager userManager;
    private FirstOpenHooks firstOpenHooks;
    private QueueManager queueManager;

    @Override
    public void onEnable() {
        this.getLogger().info("---------------------------");
        this.getLogger().info("");
        this.getLogger().info("Kernel v1.0 Enabled");
        this.getLogger().info("");
        this.getLogger().info("By @BinaryCodee & SIX_veliero");
        this.getLogger().info("");
        this.getLogger().info("---------------------------");
        plugin = this;
        instance = this;
        fileManager = new FileManager(instance);
        this.c = this.getConfig();
        this.config = new Config();
        this.userManager = new UserManager();
        this.queueManager = new QueueManager();
        this.config.ReloadConfig();
        System.out.println("[KERNEL] Queue-Addon Enabled!");
        System.out.println("[KERNEL] LPC-Addon Enabled!");
        QueueChecker.QueueLoop(this.config.servers);
        this.loader();
        this.luckPerms = getServer().getServicesManager().load(LuckPerms.class);
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        File configFile = new File(getDataFolder(), "firstOpen.yml");
        firstOpenManager = new FirstOpenManager(this, configFile);

        firstOpenHooks = new FirstOpenHooks(firstOpenManager);
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            firstOpenHooks.register();
        }
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (args.length == 1 && "reload".equals(args[0])) {
            reloadConfig();

            sender.sendMessage(colorize("&6Kernel LPC-Addon ricaricato."));
            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(final CommandSender sender, final Command command, final String alias, final String[] args) {
        if (args.length == 1)
            return Collections.singletonList("reload");

        return new ArrayList<>();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(final AsyncPlayerChatEvent event) {
        final String message = event.getMessage();
        final Player player = event.getPlayer();

        final CachedMetaData metaData = this.luckPerms.getPlayerAdapter(Player.class).getMetaData(player);
        final String group = metaData.getPrimaryGroup();

        String format = getConfig().getString(getConfig().getString("group-formats." + group) != null ? "group-formats." + group : "chat-format")
                .replace("{prefix}", metaData.getPrefix() != null ? metaData.getPrefix() : "")
                .replace("{suffix}", metaData.getSuffix() != null ? metaData.getSuffix() : "")
                .replace("{prefixes}", metaData.getPrefixes().keySet().stream().map(key -> metaData.getPrefixes().get(key)).collect(Collectors.joining()))
                .replace("{suffixes}", metaData.getSuffixes().keySet().stream().map(key -> metaData.getSuffixes().get(key)).collect(Collectors.joining()))
                .replace("{world}", player.getWorld().getName())
                .replace("{name}", player.getName())
                .replace("{displayname}", player.getDisplayName())
                .replace("{username-color}", metaData.getMetaValue("username-color") != null ? metaData.getMetaValue("username-color") : "")
                .replace("{message-color}", metaData.getMetaValue("message-color") != null ? metaData.getMetaValue("message-color") : "");

        format = colorize(translateHexColorCodes(getServer().getPluginManager().isPluginEnabled("PlaceholderAPI") ? PlaceholderAPI.setPlaceholders(player, format) : format));

        event.setFormat(format.replace("{message}", player.hasPermission("lpc.colorcodes") && player.hasPermission("lpc.rgbcodes")
                ? colorize(translateHexColorCodes(message)) : player.hasPermission("lpc.colorcodes") ? colorize(message) : player.hasPermission("lpc.rgbcodes")
                ? translateHexColorCodes(message) : message).replace("%", "%%"));
    }

    private String colorize(final String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    private String translateHexColorCodes(final String message) {
        final char colorChar = ChatColor.COLOR_CHAR;

        final Matcher matcher = HEX_PATTERN.matcher(message);
        final StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);

        while (matcher.find()) {
            final String group = matcher.group(1);

            matcher.appendReplacement(buffer, colorChar + "x"
                    + colorChar + group.charAt(0) + colorChar + group.charAt(1)
                    + colorChar + group.charAt(2) + colorChar + group.charAt(3)
                    + colorChar + group.charAt(4) + colorChar + group.charAt(5));
        }

        return matcher.appendTail(buffer).toString();
    }


    public static Kernel getPlugin() {
        return plugin;
    }

    @Override
    public void onDisable() {
        this.getLogger().info("---------------------------");
        this.getLogger().info("");
        this.getLogger().info("Kernel v1.0 Disabled");
        this.getLogger().info("");
        this.getLogger().info("By @BinaryCodee & SIX_veliero");
        this.getLogger().info("");
        this.getLogger().info("---------------------------");
        System.out.println(" ");
        System.out.println("[KERNEL] Queue-Addon Disabled");
        System.out.println("[KERNEL] LPC-Addon Disabled");
    }

    public void loader() {
        this.getCommand("gmc").setExecutor((CommandExecutor)new CreativeCommand());
        this.getCommand("gmsp").setExecutor((CommandExecutor)new SpectatorCommand());
        this.getCommand("gms").setExecutor((CommandExecutor)new SurvivalCommand());
        this.getCommand("tp").setExecutor((CommandExecutor)new TeleportCommand());
        this.getCommand("tpall").setExecutor((CommandExecutor)new TpallCommand());
        this.getCommand("tphere").setExecutor((CommandExecutor)new TphereCommand());
        this.getCommand("alert").setExecutor((CommandExecutor)new AlertCommand());
        this.getCommand("setspawn").setExecutor((CommandExecutor)new SetSpawnCommand());
        this.getCommand("spawn").setExecutor((CommandExecutor)new SpawnCommand());
        this.getCommand("vanish").setExecutor((CommandExecutor)new VanishCommand());
        this.getCommand("fly").setExecutor((CommandExecutor)new FlyCommand());
        this.getCommand("invsee").setExecutor((CommandExecutor)new InvseeCommand());
        this.getCommand("kernel").setExecutor((CommandExecutor)new MainCommand());
        this.getCommand("alert").setExecutor((CommandExecutor)new AlertCommand());
        this.getServer().getPluginManager().registerEvents(new Joinevent(), this);
        this.getServer().getPluginManager().registerEvents(new ProfiloListener(), this);
        this.getServer().getPluginManager().registerEvents(new ServerSelectorListener(), this);
        this.getServer().getPluginManager().registerEvents(new Joinevent(), this);
        this.getServer().getPluginManager().registerEvents(new Quitevent(), this);
        this.getServer().getPluginManager().registerEvents(new Voidtp(), this);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getPluginManager().registerEvents(new ServerSelectorGUIListener(this), this);
        this.getServer().getMessenger().registerIncomingPluginChannel((Plugin) this, "BungeeCord", (PluginMessageListener) new ServerSelectorGUIListener(this));
        Bukkit.getPluginManager().registerEvents(new PlayerGeneralListener(), this);
        getCommand("setfirstopen").setExecutor(new FirstOpenCommand(firstOpenManager));
    }

    static {Kernel.vanishedStaffers = new ArrayList<Player>();}
    public static Kernel getInstance() {
        return instance;
    }

    private static FileManager fileManager;
    public static FileManager getFileManager() {
        return fileManager;
    }

    public Config getConfigC() {
        return this.config;
    }

    public UserManager getUserManager() {
        return this.userManager;
    }

    public QueueManager getQueueManager() {
        return this.queueManager;
    }
}
