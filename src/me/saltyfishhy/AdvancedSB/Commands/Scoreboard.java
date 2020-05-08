package me.saltyfishhy.AdvancedSB.Commands;

import me.saltyfishhy.AdvancedSB.Events.OnJoin;
import me.saltyfishhy.AdvancedSB.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Scoreboard implements CommandExecutor {

    public static HashMap<Player, Boolean> isRemoving = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("scoreboard")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("advancedsb.admin")) {
                    if (!(args.length >= 1)) {
                        p.sendMessage(ChatColor.BLUE + "Scoreboard > " + ChatColor.GRAY + "Proper Usage: /scoreboard <show | hide> <scoreboardnumber>");
                        return true;
                    }
                    else {
                        if (!Main.data.getConfig().getConfigurationSection("scoreboards").contains(args[1])) {
                            p.sendMessage(ChatColor.BLUE + "Scoreboard > " + ChatColor.GRAY + "No such scoreboard exists with ID " + args[1]);
                            return true;
                        }
                        else {
                            if (args[0].equalsIgnoreCase("hide") && args.length == 2) {
                                if (Main.data.getConfig().getBoolean("scoreboards." + args[1] + ".hidden")) {
                                    p.sendMessage(ChatColor.BLUE + "Scoreboard > " + ChatColor.GRAY + "Scoreboard " + args[1] + " is already hidden.");
                                    return true;
                                }
                                else {
                                    Main.data.getConfig().set("scoreboards." + args[1] + ".hidden", true);
                                    p.sendMessage(ChatColor.BLUE + "Scoreboard > " + ChatColor.GRAY + "Scoreboard " + args[1] + " has been hidden.");
                                    Main.data.saveConfig();
                                    for (Player p2 : Bukkit.getOnlinePlayers()) {
                                        if (OnJoin.currScore.containsKey(p2)) {
                                            if (OnJoin.currScore.get(p2).equals(args[1])) {
                                                p2.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                                            }
                                        }
                                    }
                                    return true;
                                }
                            }
                            else if (args[0].equalsIgnoreCase("show") && args.length == 2) {
                                if (!Main.data.getConfig().getBoolean("scoreboards." + args[1] + ".hidden")) {
                                    p.sendMessage(ChatColor.BLUE + "Scoreboard > " + ChatColor.GRAY + "Scoreboard " + args[1] + " is already shown.");
                                    return true;
                                }
                                else {
                                    Main.data.getConfig().set("scoreboards." + args[1] + ".hidden", false);
                                    p.sendMessage(ChatColor.BLUE + "Scoreboard > " + ChatColor.GRAY + "Scoreboard " + args[1] + " has been shown.");
                                    Main.data.saveConfig();
                                    return true;
                                }
                            }
                            else {
                                p.sendMessage(ChatColor.BLUE + "Scoreboard > " + ChatColor.GRAY + "Proper Usage: /scoreboard <show|hide> <scoreboardnumber>");
                                return true;
                            }
                        }
                    }
                }
                else {
                    p.sendMessage(ChatColor.RED + "You miss the following permission: \n" + ChatColor.RED + "- " + ChatColor.GRAY + "advancedsb.admin");
                    return true;
                }
            }
            else {
                sender.sendMessage(ChatColor.RED + "This command is only runnable by players");
                return true;
            }
        }
        return false;
    }

}
