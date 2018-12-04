package cn.py.wordcount;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

public class WordTopology {

	public static void main(String[] args) throws Exception {

		Config conf = new Config();
		
		//���õ�ǰ���˽��̲����ȣ�Ĭ����1
		conf.setNumWorkers(2);
		
		WordCountSpout spout = new WordCountSpout();
		WordCountSplit countSplit = new WordCountSplit();
		WordCountBolt bolt = new WordCountBolt();
		WordPrintBolt printBolt = new WordPrintBolt();

		TopologyBuilder builder = new TopologyBuilder();
		//����������̲߳����Ⱥ�Task������
		builder.setSpout("countSpout", spout);
		//�������������
		builder.setBolt("countSplit", countSplit,2).setNumTasks(4).shuffleGrouping("countSpout");
		//�ֶ�����������,ȷ��������ָ����key�ֶζ�Ӧ��ֵ����ͬ��ֵ����ͬһ��bolt��
		builder.setBolt("countBolt", bolt,2).setNumTasks(2).fieldsGrouping("countSplit",new Fields("word"));
		//ȫ�ַ��飬�Ὣ�������ݻ㼯��һ��bolt��
		builder.setBolt("printBolt", printBolt).globalGrouping("countBolt");

		StormTopology topology = builder.createTopology();
		//���ؼ�Ⱥģʽ
//		LocalCluster cluster = new LocalCluster();
//		cluster.submitTopology("countSpout", conf, topology);
		
		StormSubmitter cluster = new StormSubmitter();
		cluster.submitTopology("WordCountTopology", conf, topology);
	}
}
