package cn.py.number;

import java.util.Map;
import java.util.Random;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

/**
 * 开发一个数据源组件，
 * 本例中，此数据源的作用时产生一个一个的随机数字，向下游的bolt发送
 * @author pyang
 *
 */
public class NUmberSpout extends BaseRichSpout{
	//通过这个组件将tuple发送给下游
	private SpoutOutputCollector collector;
	

	/**
	 * 此方法时初始化方法，此方法用于组件的初始化
	 */
	@Override
	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	/**
	 * 此方法用于声明向下游发送的tuple的key字段名字
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		//通过Fields来声明tuple的key字段，可以有多个
		declarer.declare(new Fields("number"));
	}
	
	/**
	 * 此方法用于产生数据，向下游发送tuple
	 * 此方法会被调用多次，注意，此方法不要设置为阻塞方法
	 */
	@Override
	public void nextTuple() {
		//产生一个100以内的随机数
		int number = new Random().nextInt(100);
		//把数字封装到tuple里，然后通过发射器发送出去
		collector.emit(new Values(number));
		
	}
}
