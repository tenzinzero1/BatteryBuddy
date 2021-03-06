package com.receptix.batterybuddy.helper;

/**
 * Created by futech on 5/17/2017.
 */

public class Constants {

    public static class Preferences {
        public static final String PREFER_NAME = "AndroidExamplePref";
        public static final String IS_OPTIMIZED_NOW = "is_optimized";
        public static final String IS_FIRST_TIME = "is_first_time";
        // Shared pref mode (Context.MODE_PRIVATE)
        public static final int PRIVATE_MODE = 0;
    }

    public static class BatteryParams {
        public static final String BATTERY_LEVEL = "level";
        public static final String BATTERY_SCALE = "scale";
        public static final String IS_BATTERY_PRESENT = "present";
        public static final String BATTERY_VOLTAGE = "voltage";
        public static final String BATTERY_TECHNOLOGY = "technology";
        public static final String BATTERY_TEMPERATURE = "temperature";
        public static final double BATTERY_VOLTAGE_CONVERSION_UNIT = 0.001;
        public static final double BATTERY_TEMPERATURE_CONVERSION_UNIT = 0.1;
    }

    public static class Tags {
        public static final String TAG_HOME_FRAGMENT = "HomeFragment";
    }

    public static class PowerProfileParams {
        public static final String POWER_PROFILE_CLASS = "com.android.internal.os.PowerProfile";
        public static final String METHOD_GET_AVERAGE_POWER = "getAveragePower";
        public static final String BATTERY_CAPACITY = "battery.capacity";

        public static final String RADIO_ACTIVE = "radio.active";
        public static final String RADIO_SCANNING = "radio.scanning";
        public static final String RADIO_ON = "radio.on";

        public static final String CPU_AWAKE = "cpu.awake";
        public static final String CPU_ACTIVE = "cpu.active";
        public static final String CPU_IDLE = "cpu.idle";

        public static final String WIFI_ON = "wifi.on";
        public static final String WIFI_ACTIVE = "wifi.active";
        public static final String WIFI_SCANNING = "wifi.scan";

        public static final String GPS_ON = "gps.on";

        public static final String SCREEN_ON = "screen.on";
        public static final String SCREEN_FULL = "screen.full";

        public static final String DSP_AUDIO = "dsp.audio";
        public static final String DSP_VIDEO = "dsp.video";

    }

    public static class BrightnessLevel {
        public static final int BRIGHTNESS_LEVEL_0 = 0;
        public static final int BRIGHTNESS_LEVEL_1 = 1;
        public static final int BRIGHTNESS_LEVEL_2 = 2;
        public static final int BRIGHTNESS_LEVEL_3 = 3;
        public static final int BRIGHTNESS_LEVEL_4 = 4;
        public static final int BRIGHTNESS_LEVEL_5 = 5;
        public static final int BRIGHTNESS_LEVEL_6 = 6;
        public static final int BRIGHTNESS_LEVEL_7 = 7;
        public static final int BRIGHTNESS_LEVEL_8 = 8;
        public static final int BRIGHTNESS_LEVEL_9 = 9;
    }

    public static class BrightnessLevelValues {
        public static final int BRIGHTNESS_DEFAULT_VALUE = 20;
        public static final int BRIGHTNESS_LEVEL_0_UPPER_LIMIT = 26;
        public static final int BRIGHTNESS_LEVEL_1_UPPER_LIMIT = 51;
        public static final int BRIGHTNESS_LEVEL_2_UPPER_LIMIT = 77;
        public static final int BRIGHTNESS_LEVEL_3_UPPER_LIMIT = 102;
        public static final int BRIGHTNESS_LEVEL_4_UPPER_LIMIT = 128;
        public static final int BRIGHTNESS_LEVEL_5_UPPER_LIMIT = 153;
        public static final int BRIGHTNESS_LEVEL_6_UPPER_LIMIT = 179;
        public static final int BRIGHTNESS_LEVEL_7_UPPER_LIMIT = 204;
        public static final int BRIGHTNESS_LEVEL_8_UPPER_LIMIT = 230;
        public static final int BRIGHTNESS_LEVEL_9_UPPER_LIMIT = 255;
    }


    public static class SoundModes {
        public static final int SILENT_MODE = 0;
        public static final int RINGTONE_MODE = 1;
        public static final int VIBRATE_MODE = 2;
    }

    public static class StatisticTypes {
        public static final int OVERALL_BATTERY = 0;
        public static final int VOICE_CALL = 1;
        public static final int VIDEO = 2;
        public static final int WIFI = 3;
    }

    public static class ShortHandNotations {
        public static final String HOURS = "h";
        public static final String MINUTES = "min";
    }

    public static class LockScreenTimeout {
        public static final int TIMEOUT_AUTO_LOCK = 0;
        public static final int TIMEOUT_5_SECONDS = 1;
        public static final int TIMEOUT_15_SECONDS = 2;
        public static final int TIMEOUT_30_SECONDS = 3;

        public static final int TIMEOUT_1_MINUTE = 4;
        public static final int TIMEOUT_2_MINUTE = 5;
        public static final int TIMEOUT_5_MINUTES = 6;
        public static final int TIMEOUT_10_MINUTES = 7;
        public static final int TIMEOUT_30_MINUTES = 8;
    }

    public static class Params {
        public static final int INVALID_POSITION = -1;
        public static final int MINIMUM_INSTALLED_APPS = 10;
        public static final int NUMBER_OF_SYSTEM_APPS_TO_SHOW = 12;
        public static final int COUNTDOWN_TIMER_VALUE = 3000;
        public static final String APP_PACKAGE_NAME = "com.receptix.batterybuddy";

        public static final String FROM = "from";
        public static final String IS_SCREEN_ON = "isScreenOn";
        public static final String BROADCAST_RECEIVER = "broadcast";
    }
}
