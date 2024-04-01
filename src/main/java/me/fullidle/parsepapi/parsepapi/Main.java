package me.fullidle.parsepapi.parsepapi;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();

        Papi.head = getConfig().getString("head").charAt(0);
        Papi.tail = getConfig().getString("tail").charAt(0);
        if (Papi.head == Papi.tail){
            throw new RuntimeException("head equals tail!");
        }
        System.out.println(Papi.head);
        System.out.println(Papi.tail);
        new Papi().register();
    }
}