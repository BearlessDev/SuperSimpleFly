package fr.bearless.supersimplefly;

import fr.bearless.supersimplefly.commands.FlyCommand;
import fr.bearless.supersimplefly.commands.ReloadCommand;
import fr.bearless.supersimplefly.configs.BaseConfig;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import pl.mikigal.config.Config;
import pl.mikigal.config.ConfigAPI;
import pl.mikigal.config.style.CommentStyle;
import pl.mikigal.config.style.NameStyle;

import java.util.ArrayList;
import java.util.UUID;

public class SuperSimpleFly extends JavaPlugin {
    @Getter private static BaseConfig baseConfig;

    @Getter private static ArrayList<UUID> flyList = new ArrayList<>();

    @Override
    public void onEnable() {
        // Init Configs
        baseConfig = initConfig(BaseConfig.class, true); // config.yml

        // Register Commands
        getCommand("ssf-reload").setExecutor(new ReloadCommand());
        getCommand("ssf-fly").setExecutor(new FlyCommand());
    }

    private <T extends Config> T initConfig(Class<T> clazz, boolean translateColorCode) {
        return ConfigAPI.init(
                clazz,
                NameStyle.UNDERSCORE,
                CommentStyle.INLINE,
                translateColorCode,
                this
        );
    }
}