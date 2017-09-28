package com.ape;

/**
 * 此类用来学习Java doc 的使用方式
 * AngryApe created at 2017/9/25
 *
 * @author AngryApe
 * @version 1.0.0
 */
public class DocTest {

    /**
     * Class Name.
     */
    private String name = "java doc";

    /**
     * Class version.
     */
    private String version = "1.0.0";

    /**
     * 经纬度[longitude:经度，latitude:维度]
     */
    private Double latitude, longitude;

    /**
     * 程序入口方法main()
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {

    }

    /**
     * @param a
     * @param b
     * @return
     * @deprecated After version 1.0.0, {@link #add(Integer, Integer)}
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     * @throws NumberFormatException
     */
    public Integer add(Integer a, Integer b) throws NumberFormatException {
        if (a == null || b == null) {
            throw new NumberFormatException("Parameter can not be null.");
        }
        return a + b;
    }
}
