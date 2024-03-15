package it.binarycodee.queue.system;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import it.binarycodee.Kernel;
import org.bukkit.entity.Player;

public class BungeeCord {
    public static void sendPlayerToServer(Player player, String server) {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            out.writeUTF("Connect");
            out.writeUTF(server);
            player.sendPluginMessage(Kernel.instance, "BungeeCord", b.toByteArray());
            b.close();
            out.close();
        }
        catch (Exception e) {
            player.sendMessage("§8[§6§lKERNEL§8] §cImpossibile connettersi al server: " + server + "§e!");
        }
    }
}
