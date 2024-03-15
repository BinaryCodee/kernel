package it.binarycodee.system.profilo;

import it.binarycodee.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Profilo {

    public static ItemStack createProfilo() {
        ItemStack item = new ItemStack(Material.EMERALD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatUtils.getColoredText("&eProfilo"));
        item.setItemMeta(meta);
        return item;
    }
}
