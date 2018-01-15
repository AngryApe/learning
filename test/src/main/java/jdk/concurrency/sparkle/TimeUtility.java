//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jdk.concurrency.sparkle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtility {
    public static final int INVALID_VALUE = -1;
    public static final char DATE_SEPARATOR_1 = '-';
    public static final char DATE_SEPARATOR_2 = '/';
    public static final String SHORT_DATE_FORMAT = "yy-M-d";
    public static final String LONG_DATE_FORMAT = "yyyy-MM-dd";
    public static final String CHINESE_DATE_FORMAT = "yyyy年MM月dd日";
    public static final String SHORT_MONTH_FORMAT = "M";
    public static final String LONG_MONTH_FORMAT = "MM";
    public static final String SHORT_DAY_FORMAT = "d";
    public static final String LONG_DAY_FORMAT = "dd";
    public static final String SHORT_MINUTE_FORMAT = "m";
    public static final String LONG_MINUTE_FORMAT = "mm";
    public static final String SHORT_SECOND_FORMAT = "s";
    public static final String LONG_SECOND_FORMAT = "ss";
    public static final String SHORT_TIME_FORMAT = "yy-M-d H:m:s";
    public static final String LONG_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final long SECONDS_DAY = 86400L;
    public static final long MILLI_SECONDS_DAY = 86400000L;
    public static final long MICRO_SECONDS_DAY = 86400000000L;

    public TimeUtility() {
    }

    public static final long getTimeSeconds() {
        long timeMillis = System.currentTimeMillis();
        long seconds = timeMillis / 1000L;
        return seconds;
    }

    public static final long getTimeMillis() {
        long timeMills = System.currentTimeMillis();
        return timeMills;
    }

    public static final int getIntYear(Date date) {
        if (date == null) {
            return -1;
        } else {
            int year = date.getYear() + 1900;
            return year;
        }
    }

    public static final int getShortIntYear(Date date) {
        if (date == null) {
            return -1;
        } else {
            int year = getIntYear(date);
            return year - 2000;
        }
    }

    public static final String getStringYear(Date date) {
        if (date == null) {
            return null;
        } else {
            int year = getIntYear(date);
            String stringYear = Integer.toString(year);
            return stringYear;
        }
    }

    public static final String getShortStringYear(Date date) {
        if (date == null) {
            return null;
        } else {
            int year = getShortIntYear(date);
            return Integer.toString(year);
        }
    }

    public static final int getIntMonth(Date date) {
        if (date == null) {
            return -1;
        } else {
            int month = date.getMonth() + 1;
            return month;
        }
    }

    public static final String getShortStringMonth(Date date) {
        if (date == null) {
            return null;
        } else {
            String shortMonth = Integer.toString(getIntMonth(date));
            return shortMonth;
        }
    }

    public static final String getLongStringMonth(Date date) {
        if (date == null) {
            return null;
        } else {
            String lngMonth = _formatDate(date, "MM");
            return lngMonth;
        }
    }

    public static final int getDay(Date date) {
        if (date == null) {
            return -1;
        } else {
            int day = date.getDay() + 1;
            return day;
        }
    }

    public static final String getShortStringDay(Date date) {
        if (date == null) {
            return null;
        } else {
            int day = getDay(date);
            return Integer.toString(day);
        }
    }

    public static final String getLongStringDay(Date date) {
        if (date == null) {
            return null;
        } else {
            String longDay = _formatDate(date, "dd");
            return longDay;
        }
    }

    public static final boolean isToday(Date date) {
        if (date == null) {
            return false;
        } else {
            Date today = new Date();
            boolean result = today.getYear() == date.getDate() && today.getMonth() == date.getMonth() && today.getDay() == date.getDay();
            return result;
        }
    }

    public static final String getShortDate(Date date) {
        if (date == null) {
            return null;
        } else {
            String shortDate = _formatDate(date, "yy-M-d");
            return shortDate;
        }
    }

    public static final String getLongDate(Date date) {
        if (date == null) {
            return null;
        } else {
            String lngDate = _formatDate(date, "yyyy-MM-dd");
            return lngDate;
        }
    }

    public static final int getIntHour(Date date) {
        if (date == null) {
            return -1;
        } else {
            int hour = date.getHours();
            return hour;
        }
    }

    public static final int getShortHour(Date date) {
        int hour = getIntHour(date);
        if (hour == -1) {
            return -1;
        } else {
            hour = hour > 12 ? hour : hour - 12;
            return hour;
        }
    }

    public static final String getShortStringHour(Date date) {
        if (date == null) {
            return null;
        } else {
            String shortHour = _formatDate(date, "d");
            return shortHour;
        }
    }

    public static final String getLongStringHour(Date date) {
        if (date == null) {
            return null;
        } else {
            String longHour = _formatDate(date, "dd");
            return longHour;
        }
    }

    public static final int getMinute(Date date) {
        if (date == null) {
            return -1;
        } else {
            int minute = date.getMinutes();
            return minute;
        }
    }

    public static final String getShortMinute(Date date) {
        return date == null ? null : Integer.toString(getMinute(date));
    }

    public static final String getLongMinute(Date date) {
        if (date == null) {
            return null;
        } else {
            String lngMinute = _formatDate(date, "mm");
            return lngMinute;
        }
    }

    public static final int getIntSecond(Date date) {
        if (date == null) {
            return -1;
        } else {
            int second = date.getSeconds();
            return second;
        }
    }

    public static final String getShortSecond(Date date) {
        return date == null ? null : Integer.toString(getIntSecond(date));
    }

    public static final String getLongSecond(Date date) {
        if (date == null) {
            return null;
        } else {
            String lngSecond = _formatDate(date, "ss");
            return lngSecond;
        }
    }

    public static final String getShortStringTime(Date date) {
        if (date == null) {
            return null;
        } else {
            String shortTime = _formatDate(date, "yy-M-d H:m:s");
            return shortTime;
        }
    }

    public static final String getLongStringTime(Date date) {
        if (date == null) {
            return null;
        } else {
            String longTime = _formatDate(date, "yyyy-MM-dd HH:mm:ss");
            return longTime;
        }
    }

    private static final String _formatDate(Date date, String stringFormat) {
        DateFormat dateFormat = new SimpleDateFormat(stringFormat);
        String result = dateFormat.format(date);
        return result;
    }
}
