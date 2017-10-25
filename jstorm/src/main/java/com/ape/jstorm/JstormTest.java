package com.ape.jstorm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import com.ape.jstorm.bolt.WordCountBolt;
import com.ape.jstorm.bolt.WordReaderBolt;
import com.ape.jstorm.spout.WordReaderSpout;

public class JstormTest {
    public static void main(String[] args) {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("word-reader", new WordReaderSpout());
        builder.setBolt("word-normalizer", new WordReaderBolt()).shuffleGrouping("word-reader");
        builder.setBolt("word-counter", new WordCountBolt(),2).shuffleGrouping("","word-normalizer");

        Config conf = new Config();
        conf.put("wordFilePath", "wordCount.txt");
        conf.setDebug(true);

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("Getting-Started-Topologie", conf, builder.createTopology());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cluster.shutdown();
    }
}
