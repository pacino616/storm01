package cn.py.number;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

/**
 * 
 * @author pyang
 *
 */
public class PrintBolt extends BaseRichBolt{
	
	//创建bolt组件的发射器，用于向下游发射tuple
	private OutputCollector collector;
	
	/**
	 * 此方法是bolt组件的初始化方法
	 */
	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		//没有下游，空着
	}
	
	/**
	 * 此方法是bolt组件用于接受上游发来的tuple
	 * 此外，如果有下游组件，也会在此方法中发射tuple
	 */
	@Override
	public void execute(Tuple input) {
		//通过tuple的key取值
		Integer number = input.getIntegerByField("number");
		System.out.println(number);
	}
}
