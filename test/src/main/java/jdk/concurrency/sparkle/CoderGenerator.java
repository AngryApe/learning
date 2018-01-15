/**
 * @Summary:
 *      defines the method for generating the code for all the objects.
 *      the code is made up of timestamp of current and the sequence.
 *      and the length of the code is 16bytes.
 *
 *      The global unique of the code in the distributed platform is processed
 *      by the @nodeID which specifies the ID of the node which generates
 *      the code.
 *
 *      All the methods are thread safely.
 * @Author:
 *      Frank.Ng,HangZhou
 * @Date:
 *      4th Dec,2017
 */

package jdk.concurrency.sparkle;


public final class CoderGenerator
{
    private static final int CONST_SYSTEM_LENGTH = 1;
    private static final int CONST_BIZ_LENGTH = 2;

    private static final int CONST_CODE_LENGTH = 16;

    /**
     * @Summary:
     *      generates the code for an organization
     * @param nodeID
     *      @nodeID : the node which generator's ID
     * @return
     */
    public static String getOrganizationCode(char nodeID) {
        String tmp = CoderGenerator._getCode(nodeID,EnumSystemPlatform.CONST_SYSTEM_PLATFORM_USER.getIntValue(),
                EnumBiz.USER_ORGANIZATION.getIntValue());

        return tmp;
    }

    public static String getDepartmentCode(char nodeID) {
        String tmp = CoderGenerator._getCode(nodeID,EnumSystemPlatform.CONST_SYSTEM_PLATFORM_USER.getIntValue(),
                EnumBiz.USER_DEPARTMENT.getIntValue());

        return tmp;
    }

    public static String getUserCode(char nodeID) {
        String tmp = CoderGenerator._getCode(nodeID,EnumSystemPlatform.CONST_SYSTEM_PLATFORM_USER.getIntValue(),
                EnumBiz.USER_MEMBER.getIntValue());

        return tmp;
    }

    public static String getRoleCode(char nodeID) {
        String tmp = CoderGenerator._getCode(nodeID,EnumSystemPlatform.CONST_SYSTEM_PLATFORM_PRIVILEGE.getIntValue(),
                EnumBiz.PRIVILEGE_ROLE.getIntValue());

        return tmp;
    }

    public static String getModuleCode(char nodeID) {
        String tmp = CoderGenerator._getCode(nodeID,EnumSystemPlatform.CONST_SYSTEM_PLATFORM_PRIVILEGE.getIntValue(),
                EnumBiz.PRIVILEGE_MODULE.getIntValue());

        return tmp;
    }

    public static String getURICode(char nodeID) {
        String tmp = CoderGenerator._getCode(nodeID,EnumSystemPlatform.CONST_SYSTEM_PLATFORM_PRIVILEGE.getIntValue(),
                EnumBiz.PRIVILEGE_URI.getIntValue());

        return tmp;
    }

    public static String getAPICode(char nodeID) {
        String tmp = CoderGenerator._getCode(nodeID,EnumSystemPlatform.CONST_SYSTEM_PLATFORM_PRIVILEGE.getIntValue(),
                EnumBiz.PRIVILEGE_API.getIntValue());

        return tmp;
    }

    public static String getStationCode(char nodeID) {
        String tmp = CoderGenerator._getCode(nodeID,EnumSystemPlatform.CONST_SYSTEM_PLATFORM_BIZ.getIntValue(),
                EnumBiz.BIZ_STATION.getIntValue());

        return tmp;
    }

    public static String getMonitoredCode(char nodeID) {
        String tmp = CoderGenerator._getCode(nodeID,EnumSystemPlatform.CONST_SYSTEM_PLATFORM_BIZ.getIntValue(),
                EnumBiz.BIZ_LOGICAL_MONITORED_OBJECT.getIntValue());

        return tmp;
    }

    public static String getDeviceCode(char nodeID) {
        String tmp = CoderGenerator._getCode(nodeID,EnumSystemPlatform.CONST_SYSTEM_PLATFORM_BIZ.getIntValue(),
                EnumBiz.BIZ_DEVICE.getIntValue());

        return tmp;
    }

    public static String getLogicalDeiviceCode(char nodeID) {
        String tmp = CoderGenerator._getCode(nodeID,EnumSystemPlatform.CONST_SYSTEM_PLATFORM_BIZ.getIntValue(),
                EnumBiz.BIZ_DEVICE_LOGICAL.getIntValue());

        return tmp;
    }

    public static String getMetricCode(char nodeID) {
        String tmp = CoderGenerator._getCode(nodeID,EnumSystemPlatform.CONST_SYSTEM_PLATFORM_BIZ.getIntValue(),
                EnumBiz.BIZ_METRIC.getIntValue());

        return tmp;
    }

    public static String getMetricInstanceCode(char nodeID) {
        String tmp = CoderGenerator._getCode(nodeID,EnumSystemPlatform.CONST_SYSTEM_PLATFORM_BIZ.getIntValue(),
                EnumBiz.BIZ_METRIC_INSTANCE.getIntValue());

        return tmp;
    }

    public static String getAlarmRule(char nodeID) {
        String tmp = CoderGenerator._getCode(nodeID,EnumSystemPlatform.CONST_SYSTEM_PLATFORM_BIZ.getIntValue(),
                EnumBiz.BIZ_ALARM_RULE.getIntValue());

        return tmp;
    }

    public static String getNotificationRuleCode(char nodeID) {
        String tmp = CoderGenerator._getCode(nodeID,EnumSystemPlatform.CONST_SYSTEM_PLATFORM_BIZ.getIntValue(),
                EnumBiz.BIZ_NOTIFICATION_RULE.getIntValue());

        return tmp;
    }
    public static String getStatReportCode(char nodeID) {
        String tmp = CoderGenerator._getCode(nodeID,EnumSystemPlatform.CONST_SYSTEM_PLATFORM_BIZ.getIntValue(),
                EnumBiz.BIZ_STAT_REPORT.getIntValue());

        return tmp;
    }


    /**
     * @Summarry:
     *  return the code for the instance of the user object.
     * @param type
     *      type of the instance
     * @param bizType
     *      type of the business
     * @return
     *      not null on success,otherwise on failure
     */
    private static String _getCode(char nodeID,int type,int bizType) {
        if(type < 0 || bizType < 0)
            throw new InvalidParamException("@param type is illegal!");

        // fix bug: duplicate code
        // when lngTime=1 and sequence=2000, lngTmp=2001
        // next second when lngTime=1001 and sequence=1000,lngTmp=2001
        // then Long.toHexString(lngTmp) generate same string.
        long lngTime = TimeUtility.getTimeSeconds(); //current time
        long sequence = 1;//SparkleWrapper.getSequence(); //sequence in current time
        long lngTmp = Long.valueOf(String.valueOf(lngTime) + String.format("%05d",sequence)) ;//get the unique code in the format of @Long

        String stringTmp = Integer.toString(type);
        int i = 0;
        for(i = 0; i < CoderGenerator.CONST_SYSTEM_LENGTH - 1;i++){
            stringTmp = '0' + stringTmp;
        }

        String tmp = Integer.toString(bizType);
        for(i = 0; i < CoderGenerator.CONST_BIZ_LENGTH - 1;i++) {
            tmp = '0' + tmp;
        }

        stringTmp = stringTmp + tmp;
        tmp = Long.toHexString(lngTmp);
        for(i = 0; i < CoderGenerator.CONST_CODE_LENGTH - tmp.length() - CoderGenerator.CONST_SYSTEM_LENGTH - CoderGenerator.CONST_BIZ_LENGTH - 1;i++){
            tmp = '0' + tmp;
        }

        stringTmp = stringTmp + Character.toString(nodeID) + tmp;

        return stringTmp;
    }
}
