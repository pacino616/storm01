package cn.py.wordcount;

import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class WordCountSpout extends BaseRichSpout{
	
	private SpoutOutputCollector collector;
	
	private String[] lines = new String[]{"hello world","hello hadoop","hello 1811","hello storm"};
	
	private int index = 0;
	
	@Override
	public void nextTuple() {
		String line = lines[index];
		index++;
		collector.emit(new Values(line));
		if(index==lines.length-1){
			index = 0;
		} 
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("line"));
	}

}
