package cn.py.wordcount;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class WordCountSplit extends BaseRichBolt{
	
	private OutputCollector collector;
	private Fields fileds = new Fields();
	
	@Override
	public void execute(Tuple input) {
		String lines = input.getStringByField("line");
		String[] split = lines.split(" ");
		for (String word : split) {
			collector.emit(new Values(word));
		}
	}

	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word"));
	}

}
