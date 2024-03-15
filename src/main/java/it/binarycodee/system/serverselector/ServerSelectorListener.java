package it.binarycodee.system.serverselector;

import org.bukkit.event.player.*;
import org.bukkit.inventory.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import it.binarycodee.utils.*;
import org.bukkit.*;

public class ServerSelectorListener implements Listener
{

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player) || !event.getView().getTitle().equals(ChatUtils.getColoredText("&6Selettore"))) {
            return;
        }
        final Player player = (Player)event.getWhoClicked();
        final ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem != null && clickedItem.getType() == Material.NETHER_STAR && clickedItem.hasItemMeta()) {
            ServerSelectorGUI.openSelectorGUI(player);
        }
    }
}
