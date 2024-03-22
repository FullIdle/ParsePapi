package me.fullidle.parsepapi.parsepapi;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.regex.Pattern;

import static me.fullidle.parsepapi.parsepapi.Main.plugin;

public class Papi extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return plugin.getDescription().getName().toLowerCase();
    }

    @Override
    public String getAuthor() {
        return Arrays.toString(plugin.getDescription().getAuthors().toArray(new String[0]));
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        long l = System.nanoTime();

        Stack<Integer> indices = new Stack<>();
        ArrayList<String> sub = new ArrayList<>();
        String result = params;
        int upLevel = 0;

        for (int i = 0; i < params.length(); i++) {
            char c = params.charAt(i);
            if (c == '{') {
                indices.push(i);
            } else if (c == '}') {
                if (indices.isEmpty()) {
                    continue;
                }
                int startIndex = indices.pop();
                String odata = params.substring(startIndex, i + 1);
                int level = indices.size();
                if (level < upLevel||level == 0) {
                    String orData = odata;
                    for (String s : sub) {
                        odata = odata.replaceFirst(Pattern.quote(s), PlaceholderAPI.setPlaceholders(player, replacePapiSymbol(s)));
                    }
                    result = result.replaceFirst(Pattern.quote(orData), PlaceholderAPI.setPlaceholders(player, replacePapiSymbol(odata)));
                    sub.clear();
                    continue;
                }
                upLevel = level;
                sub.add(odata);
            }
        }

        result = PlaceholderAPI.setPlaceholders(player, replacePapiSymbol(result));

        if (!result.equals(params)) return result;
        return super.onRequest(player, params);
    }

    public static String replacePapiSymbol(String str) {
        return str.replace("{", "%").replace("}", "%");
    }
}
