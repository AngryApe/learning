package com.ape.kafka; /**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @auther qiys@hzzh.com
 * @date 2018-03-29
 */
public class ApeKafkaConsumer {

    KafkaConsumer<String, String> consumer = null;

    Executor executor = Executors.newSingleThreadExecutor();

    private String env = "dev";

    public void init() {
        Properties props = new Properties();
        props.put("bootstrap.servers", getServers());
        props.put("group.id", "fee-test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("fetch.min.bytes", "1");
        props.put("heartbeat.interval.ms", "3000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("event3.2_notification"));
    }

    public ApeKafkaConsumer() {
    }

    public ApeKafkaConsumer(String env) {
        this.env = env;
    }

    public String getServers() {
        if ("dev".equals(env)) {
            return "dev-kafka1.ynycloud.com:9092,dev-kafka2.ynycloud.com:9092,dev-kafka3.ynycloud.com:9092";
        } else {
            return "10.1.170.51:9092,10.1.170.52:9092,10.1.170.53:9092";
        }
    }

    public void startConsume() {
        if (consumer == null) {
            init();
        }
        executor.execute(() -> {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    JSONObject object = JSON.parseObject(record.value());
                    if ("feeManagement/price/effective".equals(object.get("eventName"))) {
                        System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(),
                                record.key(), record.value());
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        ApeKafkaConsumer consumer = new ApeKafkaConsumer();
        consumer.startConsume();
    }

}
