package me.saltyfishhy.AdvancedSB.Events;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import me.saltyfishhy.AdvancedSB.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

import java.util.HashMap;


public class PAPIOnJoin implements Listener {

    public static HashMap<Player, String> currScore = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        int refresh = Main.getInstance().getConfig().getInt("refreshRate");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            public void run() {
                ConfigurationSection sect = Main.data.getConfig().getConfigurationSection("scoreboards");
                for (String key : sect.getKeys(false)) {
                    String worldName = Main.data.getConfig().getString("scoreboards." + key + ".worlds").replace("[", "");
                    if (Main.getInstance().getConfig().getBoolean("requirePermission")) {
                        if (p.hasPermission("advancedsb.show." + key)) {
                            worldName = worldName.replace("]", "");
                            if ((worldName.equals(p.getWorld().getName()) || worldName.equalsIgnoreCase("all")) && !Main.data.getConfig().getBoolean("scoreboards." + key + ".hidden")) {
                                ScoreboardManager sbmng = Bukkit.getScoreboardManager();
                                Scoreboard score = sbmng.getNewScoreboard();
                                String title = Main.data.getConfig().getString("scoreboards." + key + ".title");
                                Objective obj = score.registerNewObjective("test", "dummy", ChatColor.translateAlternateColorCodes('&', title));
                                obj.setDisplaySlot(DisplaySlot.SIDEBAR);
                                int count = 1;
                                ConfigurationSection sect2 = Main.data.getConfig().getConfigurationSection("scoreboards." + key + ".lines");
                                for (String key2 : sect2.getKeys(false)) {
                                    if (!(Main.data.getConfig().getString("scoreboards." + key + ".lines." + key2) == null)) {
                                        String scoreString = Main.data.getConfig().getString("scoreboards." + key + ".lines." + key2);
                                        Score score1 = obj.getScore(ChatColor.translateAlternateColorCodes('&', scoreString));
                                        score1.setScore(count);
                                    }
                                    count++;
                                }
                                p.setScoreboard(score);
                                currScore.put(p, key);
                            }
                        }
                    }
                    else {
                        worldName = worldName.replace("]", "");
                        if ((worldName.equals(p.getWorld().getName()) || worldName.equalsIgnoreCase("all")) && !Main.data.getConfig().getBoolean("scoreboards." + key + ".hidden")) {
                            ScoreboardManager sbmng = Bukkit.getScoreboardManager();
                            Scoreboard score = sbmng.getNewScoreboard();
                            String title = Main.data.getConfig().getString("scoreboards." + key + ".title");
                            Objective obj = score.registerNewObjective("test", "dummy", ChatColor.translateAlternateColorCodes('&', title));
                            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
                            int count = 1;
                            ConfigurationSection sect2 = Main.data.getConfig().getConfigurationSection("scoreboards." + key + ".lines");
                            for (String key2 : sect2.getKeys(false)) {
                                if (!(Main.data.getConfig().getString("scoreboards." + key + ".lines." + key2) == null)) {
                                    String scoreString = Main.data.getConfig().getString("scoreboards." + key + ".lines." + key2);
                                    scoreString = PlaceholderAPI.setPlaceholders(p, scoreString);
                                    Score score1 = obj.getScore(ChatColor.translateAlternateColorCodes('&', scoreString));
                                    score1.setScore(count);
                                }
                                count++;
                            }
                            p.setScoreboard(score);
                            currScore.put(p, key);
                        }
                    }
                }
            }
        }, 0, refresh * 20);
    }
}
