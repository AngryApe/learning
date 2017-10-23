package com.ape;

import org.joda.time.DateTime;

import java.io.File;

/**
 * AngryApe created at 2017/9/29
 */
public class CreateWorkRecordPath {

    public static void main(String[] args) {
        DateTime now = new DateTime();
        DateTime to = new DateTime();
        to = to.withYear(2020).withDayOfYear(1).withTimeAtStartOfDay();
        String rootPath = "D:/zhongheng/daywork/";
        String fileName = "";
        while (now.isBefore(to)) {
            int year = now.getYear();
            String month = String.format("%02d",now.getMonthOfYear());
            String day = String.format("%02d",now.getDayOfMonth());
            fileName = rootPath + String.valueOf(year % 2000) + month + "/" + day+"/";
            File file = new File(fileName);
            if (!file.exists()) {
                boolean result = file.mkdirs();//mkdir()创建不成功
                System.out.println(fileName+":"+result);
            }
            now = now.plusDays(1);
        }
    }

}
