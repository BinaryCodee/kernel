package it.binarycodee.listeners;

import it.binarycodee.Kernel;
import it.binarycodee.system.serverselector.ServerSelectorGUI;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.block.*;
import org.bukkit.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.*;
import java.util.*;
import it.binarycodee.utils.*;

public class PlayerGeneralListener implements Listener {
    public PlayerGeneralListener() {
        this.playersInPvP = new ArrayList<Player>();
    }
    List<Player> playersInPvP;
    private final Kernel plugin = Kernel.getInstance();

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        Location spawn = (Location) Kernel.getFileManager().getConfig().get("spawn-location");

        if (spawn == null) {
            return;
        } else {
            player.teleport(spawn);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = (Player) event.getPlayer();
        event.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item != null && item.getType() == Material.NETHER_STAR) {
            ServerSelectorGUI.openSelectorGUI(player);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();

            if (!player.hasPermission("kernel.inventory")) {
                event.setCancelled(false);
            } else {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        if (Kernel.vanishedStaffers.contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        event.setFoodLevel(20);
        event.setCancelled(true);
    }

    @EventHandler
    public void onAchievement(PlayerAchievementAwardedEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = (Player) event.getPlayer();

        if (player.hasPermission("kernel.break")) {
            event.setCancelled(false);
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void WeatherChangeEvent(WeatherChangeEvent event) {
        if (!event.toWeatherState()) {
            return;
        }

        event.setCancelled(true);
        event.getWorld().setWeatherDuration(0);
        event.getWorld().setThundering(false);
    }

    @EventHandler
    public void onPlayerPickup(PlayerPickupItemEvent event) {
        Player player = (Player) event.getPlayer();

        if (player.hasPermission("kernel.pickup")) {
            event.setCancelled(false);
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            event.setCancelled(true);
        }
    }
}