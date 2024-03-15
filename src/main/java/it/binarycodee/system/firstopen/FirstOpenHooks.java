package it.binarycodee.system.firstopen;

import me.clip.placeholderapi.PlaceholderHook;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FirstOpenHooks extends PlaceholderExpansion {

    private final FirstOpenManager manager;

    public FirstOpenHooks(FirstOpenManager manager) {
        this.manager = manager;
    }


    @Override
    public String getIdentifier() {
        return "kernel";
    }

    @Override
    public String getAuthor() {
        return "BinaryCodee";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier.equals("firstopen")) {
            return manager.getTimeSinceFirstOpen();
        }
        return null;
    }
}
