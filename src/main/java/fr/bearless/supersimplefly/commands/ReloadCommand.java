package fr.bearless.supersimplefly.commands;

import fr.bearless.supersimplefly.SuperSimpleFly;
import fr.bearless.supersimplefly.configs.BaseConfig;
import fr.bearless.supersimplefly.utils.PrefixMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {
    private final BaseConfig baseConfig;

    public ReloadCommand() {
        baseConfig = SuperSimpleFly.getBaseConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission(baseConfig.getReloadPermission())) {
            sender.sendMessage(new PrefixMessage(baseConfig.getNoPermission()).build());
            return true;
        }
        if(args.length != 0) {
            sender.sendMessage(new PrefixMessage(baseConfig.getReloadUsage()).build());
            return true;
        }

        baseConfig.getBukkitConfiguration().load();
        sender.sendMessage(new PrefixMessage(baseConfig.getReloadMessage()).build());
        return true;
    }
}