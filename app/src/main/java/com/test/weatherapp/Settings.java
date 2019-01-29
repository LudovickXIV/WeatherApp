package com.test.weatherapp;

public class Settings {
    private static final Settings ourInstance = new Settings();

    // Настройка отображения поиска на карте
    private static boolean showSearch;
    // Настройка отображения стиля карты зранить значение ресурса в int
    private static int mapStyle;

    public static Settings getInstance() {
        return ourInstance;
    }

    private Settings() {
    }

    public static boolean isShowSearch() {
        return showSearch;
    }

    public static int getStyle() {
        return mapStyle;
    }

    public static void setShowSearch(boolean show) {
        showSearch = show;
    }

    public static void setMapStyle(int style) {
        mapStyle = style;
    }
}
