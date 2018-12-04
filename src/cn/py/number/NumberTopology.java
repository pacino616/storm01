package cn.py.number;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;

public class NumberTopology {
	
	public static void main(String[] args) {
		//创建storm的环境参数对象
		Config conf = new Config();
		
		NUmberSpout spout = new NUmberSpout();
		PrintBolt printBolt = new PrintBolt();
		
		TopologyBuilder builder = new TopologyBuilder();
		
		//绑定数据源组件，1参：组件id,要求唯一；2参组件的实例对象
		builder.setSpout("numberSpout", spout);
		//绑定处理组件，通过传入上有组件的id,产生关系
		builder.setBolt("printBolt", printBolt).globalGrouping("numberSpout");
		
		//创建拓扑对象
		StormTopology topology = builder.createTopology();
		
		//storm的本地运行对象，本地模式一般用于测试
		LocalCluster cluster = new LocalCluster();
		//运行拓扑，1参：拓扑id,2参；storm的环境对象，3参：storm的拓扑对象
		cluster.submitTopology("numberSpout", conf, topology);
	}
}
