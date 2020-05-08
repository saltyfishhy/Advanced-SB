package me.saltyfishhy.AdvancedSB.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class ChangedWorld implements Listener {

    @EventHandler
    public void changedWorld(PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }
 }
