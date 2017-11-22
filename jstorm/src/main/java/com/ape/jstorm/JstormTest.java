package com.ape.jstorm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import com.ape.jstorm.bolt.WordCountBolt;
import com.ape.jstorm.bolt.WordReaderBolt;
import com.ape.jstorm.spout.WordReaderSpout;

/**
 * 主类
 */
public class JstormTest {
    public static void main(String[] args) {
        //定义拓扑
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("word-reader" , new WordReaderSpout());
        builder.setBolt("word-normalizer" , new WordReaderBolt()).shuffleGrouping("word-reader" );
        builder.setBolt("word-counter" , new WordCountBolt()).fieldsGrouping("word-normalizer" , new Fields("word"));
        StormTopology topology = builder .createTopology();
        //配置

        Config conf = new Config();
        String fileName ="words.txt" ;
        conf.put("fileName" , fileName );
        conf.setDebug(false);

        //运行拓扑

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("Getting-Started-Topologie" , conf , topology );
        try {
            Thread. sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cluster.shutdown();

    }
}
