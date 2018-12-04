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
 * ����һ������Դ�����
 * �����У�������Դ������ʱ����һ��һ����������֣������ε�bolt����
 * @author pyang
 *
 */
public class NUmberSpout extends BaseRichSpout{
	//ͨ����������tuple���͸�����
	private SpoutOutputCollector collector;
	

	/**
	 * �˷���ʱ��ʼ���������˷�����������ĳ�ʼ��
	 */
	@Override
	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	/**
	 * �˷����������������η��͵�tuple��key�ֶ�����
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		//ͨ��Fields������tuple��key�ֶΣ������ж��
		declarer.declare(new Fields("number"));
	}
	
	/**
	 * �˷������ڲ������ݣ������η���tuple
	 * �˷����ᱻ���ö�Σ�ע�⣬�˷�����Ҫ����Ϊ��������
	 */
	@Override
	public void nextTuple() {
		//����һ��100���ڵ������
		int number = new Random().nextInt(100);
		//�����ַ�װ��tuple�Ȼ��ͨ�����������ͳ�ȥ
		collector.emit(new Values(number));
		
	}
}
