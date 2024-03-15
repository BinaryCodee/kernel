package it.binarycodee.system.firstopen;

import it.binarycodee.utils.ChatUtils;
import javafx.print.PageLayout;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FirstOpenCommand implements CommandExecutor {

    private final FirstOpenManager manager;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public FirstOpenCommand(FirstOpenManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                try {
                    manager.setFirstOpenDate(args[0]);
                    player.sendMessage(ChatUtils.getColoredText("&cData di apertura impostata su: &n" + args[0]));
                    return true;
                } catch (ParseException e) {
                    player.sendMessage(ChatUtils.getColoredText("&cFormato data non valido! Usa: dd/MM/yyyy"));
                    return false;
                }
            }

            player.sendMessage(ChatUtils.getColoredText("&cUsa: dd/MM/yyyy"));
        } else {
            sender.sendMessage(ChatUtils.getColoredText("&cSolo i giocatori possono usare il comando!"));
        }

        return true;
    }
}
