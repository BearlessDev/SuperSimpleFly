package fr.bearless.supersimplefly.commands;

import fr.bearless.supersimplefly.SuperSimpleFly;
import fr.bearless.supersimplefly.configs.BaseConfig;
import fr.bearless.supersimplefly.utils.PrefixMessage;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class FlyCommand implements TabExecutor {
    private final BaseConfig baseConfig;
    private final ArrayList<UUID> flyList;

    public FlyCommand() {
        baseConfig = SuperSimpleFly.getBaseConfig();
        flyList = SuperSimpleFly.getFlyList();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(baseConfig.getOnlyPlayer());
            return true;
        }

        Player player = (Player) sender;

        switch(args.length) {
            case 0:
                handleFly(player);
                break;
            case 1:
                handleFlyOther(player, args[0]);
                break;
            default:
                player.sendMessage(new PrefixMessage(baseConfig.getFlyUsage()).build());
                break;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1) {
            List<String> players = new ArrayList<>();
            for(Player player : Bukkit.getOnlinePlayers()) {
                players.add(player.getName());
            }
            return players;
        }
        return Collections.emptyList();
    }

    private void handleFly(Player player) {
        if(!player.hasPermission(baseConfig.getFlyPermission())) {
            player.sendMessage(new PrefixMessage(baseConfig.getNoPermission()).build());
            return;
        }
        if(player.getGameMode() == GameMode.CREATIVE) {
            player.sendMessage(new PrefixMessage(baseConfig.getGamemodeCreativeError()).build());
            return;
        }
        if(flyList.contains(player.getUniqueId()) && player.getAllowFlight()) {
            flyList.remove(player.getUniqueId());
            player.setAllowFlight(false);
            player.sendMessage(new PrefixMessage(baseConfig.getFlyDisabled()).build());
            return;
        }

        flyList.add(player.getUniqueId());
        player.setAllowFlight(true);
        player.sendMessage(new PrefixMessage(baseConfig.getFlyEnabled()).build());
    }

    private void handleFlyOther(Player player, String targetName) {
        Player target = Bukkit.getPlayer(targetName);

        if(!player.hasPermission(baseConfig.getFlyOtherPermission())) {
            player.sendMessage(new PrefixMessage(baseConfig.getNoPermission()).build());
            return;
        }
        if(target.getUniqueId() == player.getUniqueId()) {
            player.sendMessage(new PrefixMessage(baseConfig.getTargetYourself()).build());
            return;
        }
        if(target.getGameMode() == GameMode.CREATIVE) {
            player.sendMessage(new PrefixMessage(baseConfig.getGamemodeCreativeErrorOther().replaceAll("%player%", target.getName())).build());
            return;
        }
        if(flyList.contains(target.getUniqueId()) && target.getAllowFlight()) {
            flyList.remove(target.getUniqueId());
            target.setAllowFlight(false);
            player.sendMessage(new PrefixMessage(baseConfig.getFlyDisabledOther().replaceAll("%player%", target.getName())).build());
            target.sendMessage(new PrefixMessage(baseConfig.getFlyDisabled()).build());
            return;
        }

        flyList.add(target.getUniqueId());
        target.setAllowFlight(true);
        player.sendMessage(new PrefixMessage(baseConfig.getFlyEnabledOther().replaceAll("%player%", target.getName())).build());
        target.sendMessage(new PrefixMessage(baseConfig.getFlyEnabled()).build());
    }
}