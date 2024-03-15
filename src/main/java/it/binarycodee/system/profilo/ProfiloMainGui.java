package it.binarycodee.system.profilo;

import org.bukkit.entity.*;
import it.binarycodee.utils.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class ProfiloMainGui
{
    public static void openProfileGUI(final Player player) {
        final Inventory profileGUI = Bukkit.createInventory((InventoryHolder)player, 27, ChatUtils.getColoredText("&eProfilo"));
        final ProfilePlaceholders placeholders = new ProfilePlaceholders();
        final String firstJoinDate = ProfilePlaceholders.getFirstJoin(player);
        final String lastJoinDate = ProfilePlaceholders.getLastJoin(player);
        final String currentDate = ProfilePlaceholders.getCurrentDate();
        final ItemStack data = new ItemStack(Material.BOOK_AND_QUILL);
        final ItemMeta dataMeta = data.getItemMeta();
        dataMeta.setDisplayName(ChatUtils.getColoredText("&B&lAUTENTICAZIONE"));
        final List<String> dataLore = new ArrayList<String>();
        dataLore.add(ChatUtils.getColoredText("&8"));
        dataLore.add(ChatUtils.getColoredText("&7Primo Login &e" + firstJoinDate));
        dataLore.add(ChatUtils.getColoredText("&8"));
        dataLore.add(ChatUtils.getColoredText("&7Ultimo Login &e" + lastJoinDate));
        dataLore.add(ChatUtils.getColoredText("&8"));
        dataMeta.setLore((List)dataLore);
        data.setItemMeta(dataMeta);
        final ItemStack stats = new ItemStack(Material.BOOK_AND_QUILL);
        final ItemMeta statsMeta = stats.getItemMeta();
        statsMeta.setDisplayName(ChatUtils.getColoredText("&B&lSTATISTICHE"));
        final List<String> statsLore = new ArrayList<String>();
        statsLore.add(ChatUtils.getColoredText("&8"));
        statsLore.add(ChatUtils.getColoredText("&6" + player.getName()));
        statsLore.add(ChatUtils.getColoredText("&eClicca per vedere le tue Statistiche"));
        statsLore.add(ChatUtils.getColoredText("&8"));
        statsMeta.setLore((List)statsLore);
        stats.setItemMeta(statsMeta);
        final ItemStack info = new ItemStack(Material.BOOK_AND_QUILL);
        final ItemMeta infoMeta = info.getItemMeta();
        infoMeta.setDisplayName(ChatUtils.getColoredText("&B&lINFORMAZIONI"));
        final List<String> infoLore = new ArrayList<String>();
        infoLore.add(ChatUtils.getColoredText("&8"));
        infoLore.add(ChatUtils.getColoredText("&7Nome: &e" + player.getName()));
        infoLore.add(ChatUtils.getColoredText("&7Data: &e" + currentDate));
        infoLore.add(ChatUtils.getColoredText("&8"));
        infoMeta.setLore((List)infoLore);
        info.setItemMeta(infoMeta);
        profileGUI.setItem(16, info);
        profileGUI.setItem(10, data);
        profileGUI.setItem(13, stats);
        player.openInventory(profileGUI);
    }
}
