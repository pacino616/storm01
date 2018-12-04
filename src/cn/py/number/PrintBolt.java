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
	
	//����bolt����ķ����������������η���tuple
	private OutputCollector collector;
	
	/**
	 * �˷�����bolt����ĳ�ʼ������
	 */
	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		//û�����Σ�����
	}
	
	/**
	 * �˷�����bolt������ڽ������η�����tuple
	 * ���⣬��������������Ҳ���ڴ˷����з���tuple
	 */
	@Override
	public void execute(Tuple input) {
		//ͨ��tuple��keyȡֵ
		Integer number = input.getIntegerByField("number");
		System.out.println(number);
	}
}
