package com.projetofinal.config;

public class Config {
    private static boolean isDarkMode = false;

    public static void setDarkMode(boolean isDarkMode) {
        Config.isDarkMode = isDarkMode;
    }

    public static boolean getDarkMode() {
        return isDarkMode;
    }
}
