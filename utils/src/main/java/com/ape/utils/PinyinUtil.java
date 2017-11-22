/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package com.ape.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * AngryApe created at 2017-11-21
 */
public class PinyinUtil {

    public static String getFirstLetters(String ChineseLanguage, HanyuPinyinCaseType caseType) {
        char[] cl_chars = ChineseLanguage.trim().toCharArray();
        String pinYin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(caseType);// 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        try {
            for (int i = 0; i < cl_chars.length; i++) {
                String str = String.valueOf(cl_chars[i]);
                if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                    pinYin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0]
                            .substring(0, 1);
                } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
                    pinYin += cl_chars[i];
                } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母
                    pinYin += cl_chars[i];
                } else {// 否则不转换
                    pinYin += cl_chars[i];//如果是标点符号的话，带着
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("字符不能转成汉语拼音");
        }
        return pinYin;
    }

    public static String getFirstLettersLowerCase(String chineseStr) {
        return getFirstLetters(chineseStr, HanyuPinyinCaseType.LOWERCASE);
    }

    public static String getFirstLettersUpperCase(String chineseStr) {
        return getFirstLetters(chineseStr, HanyuPinyinCaseType.UPPERCASE);
    }

    public static void main(String[] args) {
        String chinese = "字符不能转成汉语拼音";
        System.out.println(getFirstLetters(chinese, HanyuPinyinCaseType.LOWERCASE));
    }
}
