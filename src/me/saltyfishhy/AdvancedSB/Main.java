package me.saltyfishhy.AdvancedSB;

import me.saltyfishhy.AdvancedSB.Commands.Scoreboard;
import me.saltyfishhy.AdvancedSB.Events.ChangedWorld;
import me.saltyfishhy.AdvancedSB.Events.OnJoin;
import me.saltyfishhy.AdvancedSB.Events.PAPIOnJoin;
import me.saltyfishhy.AdvancedSB.FileManager.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;
    public static FileManager data;

    public void onEnable() {
        this.data = new FileManager(this);
        instance = this;
        loadConfig();
        getCommand("scoreboard").setExecutor(new Scoreboard());
        getLogger().info(ChatColor.GREEN + "Advanced Scoreboards -> Plugin Enabled");
        PluginManager pm = Bukkit.getServer().getPluginManager();
        if (pm.getPlugin("PlaceholderAPI") != null) {
            pm.registerEvents(new PAPIOnJoin(), this);
        } else {
            pm.registerEvents(new OnJoin(), this);
        }
        pm.registerEvents(new ChangedWorld(), this);
    }

    public void onDisable() {
        getLogger().info(ChatColor.GREEN + "Advanced Scoreboards -> Plugin Disabled");
    }

    public static Main getInstance() {
        return instance;
    }

    public void loadConfig(){
        //tells the config file to copy the defaults that were created with the file initially
        getConfig().options().copyDefaults(true);
        if (getConfig().getDouble("refreshRate") <= 1) {
            getLogger().warning("A refresh rate equal to or less than 1 will break the scoreboard. Please use a number higher than 1.");
            Bukkit.shutdown();
        }
        //saves the config
        saveConfig();
    }


}
