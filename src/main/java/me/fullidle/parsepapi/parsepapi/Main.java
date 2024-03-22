package me.fullidle.parsepapi.parsepapi;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main plugin;
    public static Papi papi = new Papi();

    @Override
    public void onEnable() {
        plugin = this;
        papi.register();
    }
}