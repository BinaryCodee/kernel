package it.binarycodee.system.serverselector;

import org.bukkit.plugin.messaging.*;
import org.bukkit.plugin.*;
import org.bukkit.configuration.file.*;
import org.bukkit.event.inventory.*;
import it.binarycodee.utils.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import it.binarycodee.*;
import com.google.common.io.*;
import org.bukkit.event.*;

public class ServerSelectorGUIListener implements Listener, PluginMessageListener
{
    private Plugin plugin;
    private FileConfiguration fc;

    public ServerSelectorGUIListener(final Plugin plugin) {
        this.plugin = plugin;
        this.fc = plugin.getConfig();
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        final String inventoryName = event.getInventory().getName();
        final String coloredText = ChatUtils.getColoredText("&7Selettore modalit\u00e0");
        if (inventoryName != null && coloredText != null && inventoryName.equals(coloredText)) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR && event.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
                final Player player = (Player)event.getWhoClicked();
                final String serverName = "kitop";
                player.sendMessage(ChatUtils.getColoredText("&8[&c&lKERNEL&8] &eTi stai connettendo al server: &6KitPvP"));
                final ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF(serverName);
                player.sendPluginMessage((Plugin)Kernel.getInstance(), "BungeeCord", out.toByteArray());
            }
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR && event.getCurrentItem().getType() == Material.FIREWORK) {
                final Player player = (Player)event.getWhoClicked();
                final String serverName = "lifesteal";
                player.sendMessage(ChatUtils.getColoredText("&8[&c&lKERNEL&8] &eTi stai connettendo al server: &6Lifesteal"));
                final ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF(serverName);
                player.sendPluginMessage((Plugin)Kernel.getInstance(), "BungeeCord", out.toByteArray());
            }
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR && event.getCurrentItem().getType() == Material.FEATHER) {
                final Player player = (Player)event.getWhoClicked();
                player.closeInventory();
                player.sendMessage(ChatUtils.getColoredText("&cModalit\u00e0 in sviluppo!"));
                event.setCancelled(true);
            }
        }
    }

    public void onPluginMessageReceived(final String channel, final Player player, final byte[] message) {
    }
}
