package it.binarycodee.utils;

import it.binarycodee.Kernel;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class ChatUtils {
    public static String getColoredText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String getFormattedText(String path) {
        return getColoredText(Kernel.getFileManager().getMessages().getString(path));
    }

    public static String translate(String o) {
        if (o == null) {
            return "";
        }
        return ChatColor.translateAlternateColorCodes('&', o);
    }

    public static List<String> getColoredTextList(String... texts) {
        List<String> coloredList = new ArrayList<>();
        for (String text : texts) {
            coloredList.add(getColoredText(text));
        }
        return coloredList;
    }

    public static List<String> translate(List<String> list) {
        List<String> translatedList = new ArrayList<>();
        for (String element : list) {
            if (element == null) {
                translatedList.add("");
            } else {
                String translatedElement = ChatColor.translateAlternateColorCodes('&', element);
                translatedList.add(translatedElement);
            }
        }
        return translatedList;
    }
}