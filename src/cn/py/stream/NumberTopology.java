package cn.py.stream;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;

public class NumberTopology {
	
	public static void main(String[] args) {
		Config conf = new Config();
		
		NumberStream stream = new NumberStream();
		NumberBolt bolt = new NumberBolt();
		LessThanBolt less = new LessThanBolt();
		MoreThanBolt more = new MoreThanBolt();
		
		TopologyBuilder builder = new TopologyBuilder();
		
		builder.setSpout("numberSpout", stream);
		builder.setBolt("numberBolt", bolt).shuffleGrouping("numberSpout");
		builder.setBolt("lessThan", less).globalGrouping("numberBolt","lessThan");
		builder.setBolt("moreThan", more).globalGrouping("numberBolt","moreThan");
		
		StormTopology topology = builder.createTopology();

		LocalCluster cluster = new LocalCluster();
		
		cluster.submitTopology("numberTopology", conf, topology);
	}
}
