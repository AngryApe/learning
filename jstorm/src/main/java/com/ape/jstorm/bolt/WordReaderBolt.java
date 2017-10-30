package com.ape.jstorm.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.Map;

public class WordReaderBolt extends BaseRichBolt {

    private OutputCollector outputCollector;
    private TopologyContext context;
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.context = context;
        this.outputCollector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        String sentence=tuple.getString(0);
        String[] words=sentence.split(" ");
        for(String word : words){
            word=word.trim();
            if(!word.isEmpty()){
                word=word.toLowerCase();
                //发布这个单词
                outputCollector.emit(new Values(word));
            }
        }
        //对元组做出应答
        outputCollector.ack(tuple);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word"));
    }
}
