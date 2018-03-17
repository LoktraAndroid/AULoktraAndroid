package com.au.auloktra.utils;

/**
 * Created by lalit on 15/3/18.
 */

import android.util.Log;

import com.au.auloktra.BuildConfig;
import com.au.auloktra.constants.AppConstants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Logger class for application. Should be used for logging purposes.
 * Add file and crashlytics logging here later if required.
 */
public class LogHelper {

    public static final String TAG = LogHelper.class.getSimpleName();

    private static SimpleDateFormat simpleDate = new SimpleDateFormat("yyMMddHHmmss", Locale.getDefault());
    private static Date date = new Date();

    /**
     * Warning level log
     *
     * @param tag Log tag
     * @param message Log message
     */
    public static void logWarning(String tag, String message) {

        String simpleDateFormat = getDateString();

        if (AppConstants.consoleLogs) {
            Log.w(tag, simpleDateFormat + "|" + tag + "|" + message);
        }
    }

    /**
     * Info level log
     *
     * @param tag Log tag
     * @param message Log message
     */
    public static void logInfo(String tag, String message) {

        String simpleDateFormat = getDateString();

        if (AppConstants.consoleLogs) {
            Log.i(tag, simpleDateFormat + "|" + tag + "|" + message);
        }
    }

    /**
     * Error level log
     *
     * @param tag Log tag
     * @param message Log message
     */
    public static void logError(String tag, String message) {

        String simpleDateFormat = getDateString();

        if (AppConstants.consoleLogs) {
            Log.e(tag, simpleDateFormat + "|" + tag + "|" + message);
        }

    }

    /**
     * Error level log
     *
     * @param tag Log tag
     * @param message Log message
     * @param e Exception
     */
    public static void logError(String tag, String message, Exception e) {

        String simpleDateFormat = getDateString();

        if (AppConstants.consoleLogs) {
            Log.e(tag, simpleDateFormat + "|" + tag + "|" + message, e);
        }

    }

    /**
     * This level should be used only for dev purposes while debugging or trying code.
     *
     * @param tag Log tag
     * @param message Log message
     */
    public static void logDebug(String tag, String message) {
        String simpleDateFormat = getDateString();

        if (BuildConfig.DEBUG) {

            if (AppConstants.consoleLogs) {
                Log.d(tag, simpleDateFormat + "|" + tag + "|" + message);
            }
        }
    }

    private static String getDateString() {
        date.setTime(System.currentTimeMillis());
        return simpleDate.format(date);
    }
}

