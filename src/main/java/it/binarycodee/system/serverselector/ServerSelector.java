package it.binarycodee.system.serverselector;

import it.binarycodee.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ServerSelector {

    public static ItemStack createServerSelector() {
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatUtils.getColoredText("&eSelettore Modalità"));
        item.setItemMeta(meta);
        return item;
    }
}