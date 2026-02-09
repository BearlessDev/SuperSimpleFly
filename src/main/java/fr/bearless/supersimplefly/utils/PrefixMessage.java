package fr.bearless.supersimplefly.utils;

import fr.bearless.supersimplefly.SuperSimpleFly;
import fr.bearless.supersimplefly.configs.BaseConfig;

public class PrefixMessage {
    private final BaseConfig baseConfig;
    private String message;

    public PrefixMessage(String message) {
        baseConfig = SuperSimpleFly.getBaseConfig();
        this.message = message;
    }

    public String build() {
        if(baseConfig.getEnablePrefix()) {
            message = baseConfig.getPrefix() + " " + message;
            return message;
        }
        return message;
    }
}