/**
 * @Summary:
 *      defines the type of the system.
 * @Author:
 *     Frank.Ng, HangZhou
 * @Date:
 *      4th Dec,2017
 */

package jdk.concurrency.sparkle;

enum EnumSystemPlatform
{
    CONST_SYSTEM_PLATFORM_CONFIG(0,'0'),
    CONST_SYSTEM_PLATFORM_USER(1,'1'),
    CONST_SYSTEM_PLATFORM_PRIVILEGE(2,'2'),
    CONST_SYSTEM_PLATFORM_BIZ(8,'8'),
    CONST_SYSTEM_PLATFORM_MAX(0x7E,'~'),
    ;

    private int intValue;
    private char charValue;

    private EnumSystemPlatform(int intValue,char charValue) {
        this.intValue = intValue;
        this.charValue = charValue;
    }

    public int getIntValue() {
        return this.intValue;
    }

    public char getCharValue() {
        return this.charValue;
    }
}
