package fr.bearless.supersimplefly.commands;

import fr.bearless.supersimplefly.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class reloadCmd implements CommandExecutor {

    private Main plugin;

    public reloadCmd(Main main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission(plugin.getConfig().getString("permission.reload"))) {

                plugin.reloadConfig();

                if(plugin.getConfig().getBoolean("prefix.enable_prefix")) {
                    player.sendMessage(plugin.getConfig().getString("prefix.prefix").replace("&", "§") + plugin.getConfig().getString("messages.reload_message").replace("&", "§"));
                }else {
                    player.sendMessage(plugin.getConfig().getString("messages.reload_message").replace("&", "§"));
                }
            }else {
                if(plugin.getConfig().getBoolean("prefix.enable_prefix")) {
                    player.sendMessage(plugin.getConfig().getString("prefix.prefix").replace("&", "§") + plugin.getConfig().getString("messages.no_permission").replace("&", "§"));
                }else {
                    player.sendMessage(plugin.getConfig().getString("messages.no_permission").replace("&", "§"));
                }
            }
        }
        return false;
    }
}