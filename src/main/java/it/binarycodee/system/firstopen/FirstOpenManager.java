package it.binarycodee.system.firstopen;

import it.binarycodee.utils.ChatUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FirstOpenManager {

    // By @BinaryCodee

    private long firstOpenTime;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final File configFile;

    public FirstOpenManager(Plugin plugin, File configFile) {
        this.configFile = configFile;

        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            plugin.saveResource("firstOpen.yml", false);
        }

        loadConfig();
    }

    private void loadConfig() {
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        if (config.contains("firstOpenTime")) {
            String dateString = config.getString("firstOpenTime");
            try {
                Date parsedDate = dateFormat.parse(dateString);
                firstOpenTime = parsedDate.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
                firstOpenTime = 0;
            }
        } else {
            firstOpenTime = 0;
        }
    }



    private void saveConfig() {
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        config.set("firstOpenTime", firstOpenTime);
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean hasOpened() {
        return firstOpenTime > 0;
    }

    public long getFirstOpenTime() {
        return firstOpenTime;
    }

    public void setFirstOpenTime(long time) {
        firstOpenTime = time;
        saveConfig();
    }

    public String getTimeSinceFirstOpen() {
        if (!hasOpened()) {
            return ChatUtils.getColoredText("&cMai Aperto");
        }
        long currentTime = System.currentTimeMillis();
        long timeDifference = currentTime - firstOpenTime;

        long seconds = timeDifference / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        long months = days / 30; // Approssimato
        long years = months / 12;

        if (years > 0) {
            return ChatUtils.getColoredText("&e" + years + " anni");
        } else if (months > 0) {
            return ChatUtils.getColoredText("&e" + months + " mesi");
        } else if (days > 0) {
            return ChatUtils.getColoredText("&e" + days + " giorni");
        } else {
            long remainingHours = hours % 24;
            long remainingMinutes = minutes % 60;
            long remainingSeconds = seconds % 60;

            StringBuilder result = new StringBuilder();
            if (remainingHours > 0) {
                result.append(ChatUtils.getColoredText("&e" + remainingHours + " ore "));
            }
            if (remainingMinutes > 0) {
                result.append(ChatUtils.getColoredText("&e" + remainingMinutes + " minuti "));
            }
            if (remainingSeconds > 0) {
                result.append(ChatUtils.getColoredText("&e" + remainingSeconds + " secondi"));
            }

            return result.toString().trim();
        }
    }


    public void setFirstOpenDate(String dateString) throws ParseException {
        try {
            Date date = dateFormat.parse(dateString);
            setFirstOpenTime(date.getTime());
            saveConfig();
        } catch (ParseException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
