package cn.py.number;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;

public class NumberTopology {
	
	public static void main(String[] args) {
		//����storm�Ļ�����������
		Config conf = new Config();
		
		NUmberSpout spout = new NUmberSpout();
		PrintBolt printBolt = new PrintBolt();
		
		TopologyBuilder builder = new TopologyBuilder();
		
		//������Դ�����1�Σ����id,Ҫ��Ψһ��2�������ʵ������
		builder.setSpout("numberSpout", spout);
		//�󶨴��������ͨ���������������id,������ϵ
		builder.setBolt("printBolt", printBolt).globalGrouping("numberSpout");
		
		//�������˶���
		StormTopology topology = builder.createTopology();
		
		//storm�ı������ж��󣬱���ģʽһ�����ڲ���
		LocalCluster cluster = new LocalCluster();
		//�������ˣ�1�Σ�����id,2�Σ�storm�Ļ�������3�Σ�storm�����˶���
		cluster.submitTopology("numberSpout", conf, topology);
	}
}
