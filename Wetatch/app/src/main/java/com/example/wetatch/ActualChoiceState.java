package com.example.wetatch;

/**
 * Клсаа содержит информацию о выбранном городе, и состояния switch
 */
public class ActualChoiceState {
    private static String currentCityName;
    private static boolean switchPressure;
    private static boolean switchWindspeed;

    public ActualChoiceState() {
    }

    public ActualChoiceState(String currentCityName, boolean switchPressure, boolean switchWindspeed) {
        ActualChoiceState.currentCityName = currentCityName;
        ActualChoiceState.switchPressure = switchPressure;
        this.switchWindspeed = switchWindspeed;
    }

    public static String getCurrentCityName() {
        return currentCityName;
    }

    public static void setCurrentCityName(String currentCityName) {
        ActualChoiceState.currentCityName = currentCityName;
    }


    public static boolean isSwitchPressure() {
        return switchPressure;
    }

    public static void setSwitchPressure(boolean switchPressure) {
        ActualChoiceState.switchPressure = switchPressure;
    }


    public static boolean isSwitchWindspeed() {
        return switchWindspeed;
    }

    public static void setSwitchWindspeed(boolean switchWindspeed) {
        ActualChoiceState.switchWindspeed = switchWindspeed;
    }
}
