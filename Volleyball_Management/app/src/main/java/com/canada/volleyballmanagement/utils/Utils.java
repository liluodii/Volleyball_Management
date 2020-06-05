package com.canada.volleyballmanagement.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;

import java.util.List;


public class Utils {

    public static String bindView(String value) {
        if (value == null) {
            return "";
        } else if (value.equals("null")) {
            return "";
        } else {
            return value;
        }
    }

    public static String bindView(Integer value) {
        if (value == null) {
            return "";
        } else {
            return String.valueOf(value);
        }
    }

    public static String bindView(Double value) {
        if (value == null) {
            return "";
        } else {
            return String.valueOf(value);
        }
    }

    public static String bindView(Float value) {
        if (value == null) {
            return "";
        } else {
            return String.valueOf(value);
        }
    }

    public static boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        try {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
                List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
                for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                    if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                        for (String activeProcess : processInfo.pkgList) {
                            if (activeProcess.equals(context.getPackageName())) {
                                isInBackground = false;
                            }
                        }
                    }
                }
            } else {
                List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
                ComponentName componentInfo = taskInfo.get(0).topActivity;
                if (componentInfo.getPackageName().equals(context.getPackageName())) {
                    isInBackground = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isInBackground;
    }

    public static boolean isActivityRunning(Context context, String activityName) {

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> activitys = activityManager.getRunningTasks(Integer.MAX_VALUE);
        boolean isActivityFound = false;
        for (int i = 0; i < activitys.size(); i++) {
            if (activitys.get(i).topActivity.toString().equalsIgnoreCase(String.format("ComponentInfo{%s/%s}", context.getPackageName(), activityName))) {
                isActivityFound = true;
            }
        }
        return isActivityFound;
    }

//    public static LoginResponse getLoginResponse(Context context) {
//        Preferences pref = new Preferences(context);
//        return new Gson().fromJson(pref.getString(Constants.LOGIN_REPONSE), LoginResponse.class);
//    }

    public static boolean isLogin(Context context) {
        Preferences pref = new Preferences(context);
        return pref.getBoolean(Constants.ISLOGIN, false);
    }

    public static int getDeviceHeight(Activity context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenwidth = displayMetrics.widthPixels;
        screenwidth=screenwidth-30;
        int total = screenwidth  / 4;
        return total;
    }

}
