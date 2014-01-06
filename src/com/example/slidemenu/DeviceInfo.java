package com.example.slidemenu;

public class DeviceInfo {
    private static DeviceInfo instance = null;
    private static int mDeviceWidth = 0;
    private static int mDeviceHeight = 0;

    public DeviceInfo() {
    }

    public static DeviceInfo getInstance() {
        if (instance == null) {
            instance = new DeviceInfo();
        }
        return instance;
    }

    public static void setHeightWidth(int width, int height) {
        mDeviceWidth = width;
        mDeviceHeight = height;
    }

    public static int getHeight() {
        return mDeviceHeight;
    }

    public static int getWidth() {
        return mDeviceWidth;
    }
}
