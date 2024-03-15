package it.binarycodee.system.profilo;

import me.clip.placeholderapi.expansion.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;
import java.text.*;

public class ProfilePlaceholders extends PlaceholderExpansion
{
    public String getIdentifier() {
        return "kernel";
    }

    public String getAuthor() {
        return "YourName";
    }

    public String getVersion() {
        return "1.0";
    }

    public String onRequest(final Player player, final String identifier) {
        if (player == null) {
            return "";
        }
        final String lowerCase = identifier.toLowerCase();
        switch (lowerCase) {
            case "first_join": {
                return getFirstJoin(player);
            }
            case "last_join": {
                return getLastJoin(player);
            }
            case "date": {
                return getCurrentDate();
            }
            case "player": {
                return player.getName();
            }
            case "online": {
                return String.valueOf(Bukkit.getOnlinePlayers().size());
            }
            default: {
                return null;
            }
        }
    }

    static String getFirstJoin(final Player player) {
        final long firstJoinTimestamp = player.getFirstPlayed();
        if (firstJoinTimestamp > 0L) {
            final Date firstJoinDate = new Date(firstJoinTimestamp);
            final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return dateFormat.format(firstJoinDate);
        }
        return "N/A";
    }

    static String getLastJoin(final Player player) {
        final long lastJoinTimestamp = player.getLastPlayed();
        if (lastJoinTimestamp > 0L) {
            final Date lastJoinDate = new Date(lastJoinTimestamp);
            final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return dateFormat.format(lastJoinDate);
        }
        return "N/A";
    }

    static String getCurrentDate() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateFormat.format(new Date());
    }
}
