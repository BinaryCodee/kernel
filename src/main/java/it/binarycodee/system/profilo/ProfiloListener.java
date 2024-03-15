package it.binarycodee.system.profilo;

import org.bukkit.inventory.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.*;
import it.binarycodee.utils.*;
import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;

public class ProfiloListener implements Listener
{

    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        final ItemStack item = event.getItem();
        if (item != null && item.getType() == Material.EMERALD && ChatUtils.getColoredText("&eProfilo").equals(item.getItemMeta().getDisplayName())) {
            ProfiloMainGui.openProfileGUI(event.getPlayer());
        }
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        final Player player = (Player)event.getWhoClicked();
        final ItemStack clickedItem = event.getCurrentItem();
        if (event.getCurrentItem() == null) {
            return;
        }
        if (event.getClickedInventory().getTitle().equals(ChatUtils.getColoredText("&eProfilo"))) {
            event.setCancelled(true);
        }
        if (event.getView().getTitle().equals(ChatUtils.getColoredText("&eProfilo"))) {
            if (clickedItem != null && clickedItem.getType() == Material.EMERALD) {
                final ProfiloMainGui profiloMainGui = new ProfiloMainGui();
                ProfiloMainGui.openProfileGUI(player);
            }
        }
        else if (event.getView().getTitle().equals(ChatUtils.getColoredText("&eProfilo"))) {
            event.setCancelled(true);
        }
    }
}
