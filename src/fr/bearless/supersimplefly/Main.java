package fr.bearless.supersimplefly;

import fr.bearless.supersimplefly.commands.flyCmd;
import fr.bearless.supersimplefly.commands.reloadCmd;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getCommand("fly").setExecutor(new flyCmd(this));
        getCommand("ssf").setExecutor(new reloadCmd(this));
    }

    @Override
    public void onDisable() {
    }
}
