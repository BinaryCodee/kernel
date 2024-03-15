package it.binarycodee.system.serverselector;

import org.bukkit.entity.*;
import it.binarycodee.utils.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class ServerSelectorGUI
{
    public static void openSelectorGUI(final Player player) {
        final Inventory selectorGUI = Bukkit.createInventory((InventoryHolder)player, 45, ChatUtils.getColoredText("&7Selettore modalit\u00e0"));

        //kitpvp
        final ItemStack kitpvp = new ItemStack(Material.DIAMOND_SWORD);
        final ItemMeta kitpvpMeta = kitpvp.getItemMeta();
        kitpvpMeta.setDisplayName(ChatUtils.getColoredText("&c&lKITPVP [1.8.9-1.20.1] "));
        final List<String> kitpvpLore = new ArrayList<String>();
        kitpvpLore.add(ChatUtils.getColoredText("&7Affronta una nuova avventura nel"));
        kitpvpLore.add(ChatUtils.getColoredText("&7nostro fantastico &eKITPVP&6!"));
        kitpvpLore.add(ChatUtils.getColoredText("&7"));
        kitpvpLore.add(ChatUtils.getColoredText("&8» &cNo lag &2\u2714"));
        kitpvpLore.add(ChatUtils.getColoredText("&8» &cCustom boost &2\u2714"));
        kitpvpLore.add(ChatUtils.getColoredText("&8» &cCustom kits &2\u2714"));
        kitpvpLore.add(ChatUtils.getColoredText("&8"));
        kitpvpLore.add(ChatUtils.getColoredText("&eEntra ora!"));
        kitpvpLore.add(ChatUtils.getColoredText(""));
        kitpvpMeta.setLore((List)kitpvpLore);
        kitpvp.setItemMeta(kitpvpMeta);

        //lifesteal
        final ItemStack lifesteal = new ItemStack(Material.FIREWORK);
        final ItemMeta lifestealMeta = lifesteal.getItemMeta();
        lifestealMeta.setDisplayName(ChatUtils.getColoredText("&eLifesteal [1.19.4-1.20.2] "));
        final List<String> lifestealLore = new ArrayList<String>();
        lifestealLore.add(ChatUtils.getColoredText("&7Affronta una nuova avventura nella"));
        lifestealLore.add(ChatUtils.getColoredText("&7nostra fantastica &cLifesteal&6"));
        lifestealLore.add(ChatUtils.getColoredText("&7"));
        lifestealLore.add(ChatUtils.getColoredText("&8» &cNo lag &2\u2714"));
        lifestealLore.add(ChatUtils.getColoredText("&8» &cCustom shop &2\u2714"));
        lifestealLore.add(ChatUtils.getColoredText("&8» &cCustom kits &2\u2714"));
        lifestealLore.add(ChatUtils.getColoredText("&7"));
        lifestealLore.add(ChatUtils.getColoredText("&eEntra ora!"));
        lifestealLore.add(ChatUtils.getColoredText("&8"));
        lifestealMeta.setLore((List)lifestealLore);
        lifesteal.setItemMeta(lifestealMeta);


        //soon
        final ItemStack Soon = new ItemStack(Material.FEATHER);
        final ItemMeta SoonMeta = Soon.getItemMeta();
        SoonMeta.setDisplayName(ChatUtils.getColoredText("&4&lSOON"));
        final List<String> SoonLore = new ArrayList<String>();
        SoonLore.add(ChatUtils.getColoredText("&c???"));
        SoonLore.add(ChatUtils.getColoredText(""));
        SoonLore.add(ChatUtils.getColoredText("&8» &4&l???"));
        SoonLore.add(ChatUtils.getColoredText(""));
        SoonMeta.setLore((List)SoonLore);
        Soon.setItemMeta(SoonMeta);

        //info
        final ItemStack Informazioni = new ItemStack(Material.EXP_BOTTLE);
        final ItemMeta InformazioniMeta = Informazioni.getItemMeta();
        InformazioniMeta.setDisplayName(ChatUtils.getColoredText("&6&lINFORMAZIONI"));
        final List<String> InformazioniLore = new ArrayList<String>();
        InformazioniLore.add(ChatUtils.getColoredText("&eDiscord: &7discord.storyrpg.it"));
        InformazioniLore.add(ChatUtils.getColoredText("&eTelegram: &7@StoryRPGIT"));
        InformazioniMeta.setLore((List)InformazioniLore);
        Informazioni.setItemMeta(InformazioniMeta);

        //eventi
        final ItemStack eventi = new ItemStack(Material.TNT);
        final ItemMeta EventiMeta = eventi.getItemMeta();
        EventiMeta.setDisplayName(ChatUtils.getColoredText("&e&lEVENTI SPEICALI"));
        final List<String> eventiLore = new ArrayList<String>();
        eventiLore.add(ChatUtils.getColoredText("&e"));
        eventiLore.add(ChatUtils.getColoredText("&7Questo è il server dedicato per "));
        eventiLore.add(ChatUtils.getColoredText("&7gli &aEventi &7del server"));
        eventiLore.add(ChatUtils.getColoredText("&7resta aggiornato su &aDiscord"));
        eventiLore.add(ChatUtils.getColoredText("&e"));
        eventiLore.add(ChatUtils.getColoredText("&ediscord.storyrpg.it"));
        EventiMeta.setLore((List)eventiLore);
        eventi.setItemMeta(EventiMeta);

        //supporto
        final ItemStack supporto = new ItemStack(Material.BOOK_AND_QUILL);
        final ItemMeta SupportoMeta = supporto.getItemMeta();
        SupportoMeta.setDisplayName(ChatUtils.getColoredText("&c&lSUPPORTO"));
        final List<String> supportoLore = new ArrayList<String>();
        supportoLore.add(ChatUtils.getColoredText("&7Supporto??? &bApri &7un &c&lTICKET"));
        supportoLore.add(ChatUtils.getColoredText("&7nel nostro discord oppure contatta"));
        supportoLore.add(ChatUtils.getColoredText("&7un membro delle &cstaff"));
        supportoLore.add(ChatUtils.getColoredText("&e"));
        supportoLore.add(ChatUtils.getColoredText("&ediscord.storyrpg.it"));
        SupportoMeta.setLore((List)supportoLore);
        supporto.setItemMeta(SupportoMeta);


        selectorGUI.setItem(30, lifesteal);
        selectorGUI.setItem(14, supporto);
        selectorGUI.setItem(13, eventi);
        selectorGUI.setItem(12, Informazioni);
        selectorGUI.setItem(31, Soon);
        selectorGUI.setItem(29, Soon);
        selectorGUI.setItem(33, Soon);
        selectorGUI.setItem(32, kitpvp);
        player.openInventory(selectorGUI);
    }
}
