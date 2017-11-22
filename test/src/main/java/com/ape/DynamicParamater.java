package com.ape;

/**
 * AngryApe created at 2017-11-10
 */
public class DynamicParamater {

    public static void main(String[] args) {
//        String methodName = "apparentPower";
//        String parameterListStr = "p:1,c:100,p:2,c:200";
//        List<String> paraArr = Arrays.asList(parameterListStr.split(","));
//
//        //模拟参数
//        Map<String, Double> dataList = new HashMap<>();
//        dataList.put("1", 111.0);
//        dataList.put("2", 222.0);
//        dataList.put("3", 333.0);
//        dataList.put("4", 444.0);
//        dataList.put("5", 555.0);
//
//        Object[] para = new Object[paraArr.size()];
//        //常数
//        int index = 0;
//        for (String s : paraArr) {
//            if (s.startsWith("c:"))
//                para[index] = Double.valueOf(s.split(":")[1]);
//            index++;
//        }
//        //point
//        for (String s : dataList.keySet()) {
//            index = paraArr.indexOf("p:" + s);
//            if (index >= 0)
//                para[index] = dataList.get(s);
//        }
//        for (Object o : para) {
//            System.out.println(o);
//        }
        new DynamicParamater().test();
    }

    private Double apparentPower(Object averagePower, Object reactivePower) {
        Double p1 = (Double) averagePower;
        Double p2 = (Double) reactivePower;
        return Math.sqrt(Math.pow(p1, 2) + Math.pow(p2, 2));
    }

    private void test(){
        byte[] rushVallyFlag = new byte[24];
        for (int i = 0; i < rushVallyFlag.length; i++) {
            rushVallyFlag[i] = (byte) (1+Math.random()*4);
        }
        for (byte b : rushVallyFlag) {
            System.out.print(b+",");
        }
    }

}
