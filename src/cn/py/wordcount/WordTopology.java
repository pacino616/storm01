package cn.py.wordcount;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

public class WordTopology {

	public static void main(String[] args) throws Exception {

		Config conf = new Config();
		
		//设置当前拓扑进程并发度，默认是1
		conf.setNumWorkers(2);
		
		WordCountSpout spout = new WordCountSpout();
		WordCountSplit countSplit = new WordCountSplit();
		WordCountBolt bolt = new WordCountBolt();
		WordPrintBolt printBolt = new WordPrintBolt();

		TopologyBuilder builder = new TopologyBuilder();
		//设置组件的线程并发度和Task并发度
		builder.setSpout("countSpout", spout);
		//随机数据流分组
		builder.setBolt("countSplit", countSplit,2).setNumTasks(4).shuffleGrouping("countSpout");
		//字段数据流分组,确保代码中指定的key字段对应的值，相同的值落入同一个bolt里
		builder.setBolt("countBolt", bolt,2).setNumTasks(2).fieldsGrouping("countSplit",new Fields("word"));
		//全局分组，会将所有数据汇集到一个bolt里
		builder.setBolt("printBolt", printBolt).globalGrouping("countBolt");

		StormTopology topology = builder.createTopology();
		//本地集群模式
//		LocalCluster cluster = new LocalCluster();
//		cluster.submitTopology("countSpout", conf, topology);
		
		StormSubmitter cluster = new StormSubmitter();
		cluster.submitTopology("WordCountTopology", conf, topology);
	}
}
