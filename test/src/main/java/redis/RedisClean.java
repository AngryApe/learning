/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package redis;

/**
 * @auther qiys@hzzh.com
 * @date 2018-01-07
 */
public class RedisClean {

    boolean dev = false;
    RedisOperator redis = new RedisOperator(dev);

    public static void main(String[] args) {
        RedisClean clean = new RedisClean();
        clean.cleanStatReport();
        //        DateTime now = new DateTime();
        //        System.out.println(now.getDayOfYear());
    }

    private void cleanStatReport() {
        String successPrefix = "ep:stat_report:success:";
        String lastStatTimeKey = "ep:stat_report:last_time";
        String[] areaCodes = new String[] {"320904", "320900", "320922", "320981", "320923",
                "320925", "320924", "320901", "320902", "320921", "320903", "320000", "320400",
                "320800", "320700", "320100", "320600", "320500", "321300", "321200", "320200",
                "320300", "321000", "321100", "330100", "330108", "330127", "330111", "330105",
                "330182", "330104", "330185", "330102", "330101", "330122", "330106", "330103",
                "330109", "330110", "150400", "150430", "150423", "150422", "150402", "150428",
                "150424", "150429", "150401", "150404", "150426", "150403", "331100", "331102",
                "331181", "331121", "331126", "331101", "331124", "331123", "331125", "331122",
                "152900", "150200", "150500", "150300", "152200", "152528", "320904", "320900",
                "320922", "320924", "320000", "320400", "320700", "330100", "330108", "150400",
                "150422", "331100", "331102", "331181", "331101", "331124", "331125", "152528"

        };
        String fieldDel = "1515168000";
        for (String areaCode : areaCodes) {
            redis.srem(successPrefix + areaCode, fieldDel);
        }
        redis.set(lastStatTimeKey, "1515081600");
    }

}
