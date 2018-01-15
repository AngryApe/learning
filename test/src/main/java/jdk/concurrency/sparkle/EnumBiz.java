/**
 * @Summary:
 *      define the type value for all the code type.
 *      <p>pay more attentions:
 *          the code begins from 1 to 'z',and case sensitivity.
 *      </p>
 * @Author:
 *      Frank.Ng,HangZhou
 * @Date:
 *      18th Dec,2017
 */

package jdk.concurrency.sparkle;

enum EnumBiz
{
    USER_ORGANIZATION(1,"organization"),
    USER_DEPARTMENT(2,"department"),
    USER_MEMBER(3,"user"),

    PRIVILEGE_ROLE(1,"role"),
    PRIVILEGE_MODULE(2,"MODULE"),
    PRIVILEGE_URI(3,"URI"),
    PRIVILEGE_API(4,"API"),

    BIZ_STATION(1,"station"),
    BIZ_LOGICAL_MONITORED_OBJECT(2,"logical monitored object"),
    BIZ_DEVICE(3,"device"),
    BIZ_METRIC(4,"metric"),
    BIZ_METRIC_INSTANCE(5,"metric instance"),
    BIZ_ALARM_RULE(6,"alarm rule"),
    BIZ_NOTIFICATION_RULE(7,"notification rule"),

    BIZ_DEVICE_LOGICAL(8,"logical device"),
    BIZ_STAT_REPORT(9,"statistic report"),
    ;

    private int intValue;
    private String stringValue;

    private EnumBiz(int intValue,final String stringValue) {
        this.intValue = intValue;
        this.stringValue = stringValue;
    }

    public int getIntValue() {
        return this.intValue;
    }

    public String getStringValue() {
        return this.stringValue;
    }
}
