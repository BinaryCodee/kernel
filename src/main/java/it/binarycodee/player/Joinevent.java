package it.binarycodee.player;

import it.binarycodee.system.profilo.Profilo;
import it.binarycodee.system.serverselector.ServerSelector;
import it.binarycodee.utils.ChatUtils;
import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Joinevent implements Listener {
    private final Map<UUID, Boolean> joinMessageSent = new HashMap<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().clear();

        final ItemStack profilo = Profilo.createProfilo();
        player.getInventory().setItem(3, profilo);

        final ItemStack serverSelector = ServerSelector.createServerSelector();
        player.getInventory().setItem(5, serverSelector);


        Firework firework = player.getWorld().spawn(player.getLocation(), Firework.class);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        fireworkMeta.addEffect(FireworkEffect.builder()
                .flicker(true)
                .trail(true)
                .with(FireworkEffect.Type.STAR)
                .withColor(Color.RED, Color.GREEN, Color.BLUE)
                .withFade(Color.YELLOW)
                .build());
        fireworkMeta.setPower(1);
        firework.setFireworkMeta(fireworkMeta);

        player.setGameMode(GameMode.SURVIVAL);
        player.setSprinting(false);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));

        if (player.hasPermission("kernel.join.donatore")) {
            player.setAllowFlight(true);
            player.setFlying(true);
            Bukkit.broadcastMessage(ChatUtils.getColoredText("&b&lVIP &b" + player.getName() + " &fè entrato nella Lobby"));
        } else if (player.hasPermission("kernel.join.staff")) {
            player.setAllowFlight(true);
            player.setFlying(true);
            Bukkit.broadcastMessage(ChatUtils.getColoredText("&e&lSTAFF &e" + player.getName() + " &fè entrato nella Lobby"));
        } else if (player.hasPermission("kernel.join.owner")) {
            player.setAllowFlight(true);
            player.setFlying(true);
            Bukkit.broadcastMessage(ChatUtils.getColoredText("&4&lOWNER &4" + player.getName() + " &fè entrato nella Lobby"));
        } else {
            player.setAllowFlight(false);
            player.setFlying(false);
        }
    }
}
