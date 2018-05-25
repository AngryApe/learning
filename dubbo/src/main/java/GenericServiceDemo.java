/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSONObject;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Map;

/**
 * @auther qiys@hzzh.com
 * @date 2018-04-09
 */
public class GenericServiceDemo {

    public static void main(String[] args) {
        testThread();
    }

    private static void testThread() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            /*
              6	Monitor Ctrl-Break
              5	Attach Listener
              4	Signal Dispatcher   分发处理发送给JVM信号的线程
              3	Finalizer   调用对象finalize的方法
              2	Reference Handler   清除reference的线程
              1	main    主线程
             */
            System.out.println(threadInfo.getThreadId() + "\t" + threadInfo.getThreadName());
        }
    }

    /**
     * 经过验证，dubbo 支持对象传输
     * <p>
     * 在调用方未知dto类元信息的情况下，可以通过map形式传输数据
     */
    public void testGenericService() {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("dubboTest");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setProtocol("zookeeper");
        registry.setAddress("10.1.170.80:2181");

        application.setRegistry(registry);

        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setInterface("com.ioe.fee.service.PowerFactorAssessmentService");
        reference.setApplication(application);
        reference.setVersion("3.2.0");
        reference.setGeneric(true);

        GenericService genericService = reference.get();

        Map<String, String> param = (Map<String, String>) JSONObject.toJSON(getEntity());
        param.put("class", "com.ioe.fee.entity.PowerFactorAssessment");
        Object result = genericService.$invoke("createPowerFactorAssessmentEntity",
                new String[] {"com.ioe.fee.entity.PowerFactorAssessment"}, new Object[] {param});

        System.out.println(JSONObject.toJSONString(result));
    }

    private static PowerFactorAssessment getEntity() {
        String type = "1";
        Double rangeFrom = 0.92;
        Double rangeTo = 1.00;
        String formula = "-0.013";
        PowerFactorAssessment assessment = new PowerFactorAssessment();
        assessment.setFormula(formula);
        assessment.setRangeFrom(rangeFrom);
        assessment.setRangeTo(rangeTo);
        assessment.setType(type);
        return assessment;
    }

}
