package it.binarycodee.world.scoreboard;

import it.binarycodee.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

public class Scoreboard extends JavaPlugin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        org.bukkit.scoreboard.Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective obj = board.registerNewObjective("lobby", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatUtils.getColoredText("&e&lSTORY&6&LRPG"));

        Score space = obj.getScore(" ");
        space.setScore(1);

        Score name = obj.getScore(ChatUtils.getColoredText("¦&6 " + player.getName()));
        name.setScore(2);

        Score rank = obj.getScore(ChatUtils.getColoredText("&7Rank: %vault_prefix"));

        player.setScoreboard(board);
    }
}
