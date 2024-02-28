package fr.bearless.supersimplefly.commands;

import fr.bearless.supersimplefly.Main;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class flyCmd implements CommandExecutor {

    private Main main2;

    public flyCmd(Main main) {
        this.main2 = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission(main2.getConfig().getString("permission.fly"))){
                if(player.getGameMode() != GameMode.CREATIVE){
                    if(player.isFlying() == false){
                        player.setAllowFlight(true);
                        player.setFlying(true);

                        if(main2.getConfig().getBoolean("prefix.enable_prefix")){
                            player.sendMessage(main2.getConfig().getString("prefix.prefix").replace("&", "§") + main2.getConfig().getString("messages.fly_enabled").replace("&", "§"));
                        }else{
                            player.sendMessage(main2.getConfig().getString("messages.fly_enabled").replace("&", "§"));
                        }
                    }else{
                        player.setAllowFlight(false);
                        player.setFlying(false);

                        if(main2.getConfig().getBoolean("prefix.enable_prefix")){
                            player.sendMessage(main2.getConfig().getString("prefix.prefix").replace("&", "§") + main2.getConfig().getString("messages.fly_disabled").replace("&", "§"));
                        }else{
                            player.sendMessage(main2.getConfig().getString("messages.fly_disabled").replace("&", "§"));
                        }
                    }
                }else{
                    if(main2.getConfig().getBoolean("prefix.enable_prefix")){
                        player.sendMessage((main2.getConfig().getString("prefix.prefix").replace("&", "§")) + main2.getConfig().getString("messages.gamemode_creative_error").replace("&", "§"));
                    }else{
                        player.sendMessage(main2.getConfig().getString("messages.gamemode_creative_error").replace("&", "§"));
                    }
                }
            }else{
                if(main2.getConfig().getBoolean("prefix.enable_prefix")){
                    player.sendMessage(main2.getConfig().getString("prefix.prefix").replace("&", "§") + main2.getConfig().getString("messages.no_permission").replace("&", "§"));
                }else{
                    player.sendMessage(main2.getConfig().getString("messages.no_permission").replace("&", "§"));
                }
            }
        }

        return false;
    }
}
