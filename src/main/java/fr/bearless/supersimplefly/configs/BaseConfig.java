package fr.bearless.supersimplefly.configs;

import pl.mikigal.config.Config;
import pl.mikigal.config.annotation.Comment;
import pl.mikigal.config.annotation.ConfigName;
import pl.mikigal.config.annotation.ConfigPath;

@ConfigName("config.yml")
public interface BaseConfig extends Config {

    // Prefix
    @ConfigPath("prefix.enable_prefix")
    @Comment("# Set to true to have the prefix before the message and false to disable it.")
    default boolean getEnablePrefix() { return true; }

    @ConfigPath("prefix.prefix")
    @Comment("The prefix who appear before the message.")
    default String getPrefix() { return "&7[&aSuperSimpleFly&7]"; }

    // Message
    @ConfigPath("messages.fly_enabled")
    @Comment("Message who appear when you activate the /fly")
    default String getFlyEnabled() { return "&eFly Mode: &aEnabled"; }

    @ConfigPath("messages.fly_disabled")
    @Comment("Message who appear when you deactivate the /fly")
    default String getFlyDisabled() { return "&eFly Mode: &cDisabled"; }

    @ConfigPath("messages.fly_enabled_other")
    @Comment("Message who appear when you activate the /fly for other Player")
    default String getFlyEnabledOther() { return "&eFly Mode: &aEnabled &efor %player%"; }

    @ConfigPath("messages.fly_disabled_other")
    @Comment("Message who appear when you deactivate the /fly for other Player")
    default String getFlyDisabledOther() { return "&eFly Mode: &cDisabled &efor %player%"; }

    @ConfigPath("messages.target_yourself")
    @Comment("Message who appear when you try to target yourself in a command.")
    default String getTargetYourself() { return "&cYou can't target Yourself"; }

    @ConfigPath("messages.gamemode_creative_error")
    @Comment("Message who appear when you are in creative mode and you do /fly")
    default String getGamemodeCreativeError() { return "&cYou are in creative mode you can already fly."; }

    @ConfigPath("messages.gamemode_creative_error_other")
    @Comment("Message who appear when the targeted player is in creative mode and you do /fly")
    default String getGamemodeCreativeErrorOther() { return "&c&l%player% &cis in creative mode he can already fly."; }

    @ConfigPath("messages.no_permission")
    @Comment("Message who appear when you don't have the permission")
    default String getNoPermission() { return "&cYou do not have the permission for this command."; }

    @ConfigPath("messages.only_player")
    @Comment("Message who appear when the config execute an Only Player command.")
    default String getOnlyPlayer() { return "Only Player can use this command."; }

    @ConfigPath("messages.reload_message")
    @Comment("Message who appear when you do /reload")
    default String getReloadMessage() { return "&aConfiguration file has been reloaded."; }

    @ConfigPath("messages.usage.fly")
    @Comment("The message sent to the Player if the Command is incorrectly call")
    default String getFlyUsage() { return "&c&lUSAGE: &c/fly or /fly <player>"; }

    @ConfigPath("messages.usage.reload")
    @Comment("The message sent to the Player if the Command is incorrectly call")
    default String getReloadUsage() { return "&c&lUSAGE: &c/reload"; }

    // Permision
    @ConfigPath("permissions.fly")
    @Comment("The Permission to do /fly")
    default String getFlyPermission() { return "ssf.use"; }

    @ConfigPath("permissions.fly_other")
    @Comment("The Permission to do /fly for other Player")
    default String getFlyOtherPermission() { return "ssf.use.other"; }

    @ConfigPath("permissions.reload")
    @Comment("The Permission to do /ssf reload")
    default String getReloadPermission() { return "ssf.reload"; }
}