package com.ape.jstorm.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class WordReaderSpout extends BaseRichSpout {
    private SpoutOutputCollector spoutOutputCollector;
    private TopologyContext context;
    private FileReader fileReader;
    private boolean completed = false;
    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.spoutOutputCollector = spoutOutputCollector;
        this.context = topologyContext;
        try {
            this.fileReader = new FileReader(getClass().getClassLoader().getResource("wordCount.txt").getFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void nextTuple() {
        if(completed){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
        String str;
        //创建reader
        BufferedReader reader = new BufferedReader(fileReader);
        try{
            //读所有文本行
            while((str = reader.readLine()) != null){
                /**
                 * 按行发布一个新值
                 */
                this.spoutOutputCollector.emit(new Values(str),str);
            }
        }catch(Exception e){
            throw new RuntimeException("Error reading tuple",e);
        }finally{
            completed = true;
        }
    }


    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("line"));
    }
}
