package cn.chifeng.partFlow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 分区类
 * 定义分区类要继承Partition,并重写GetPartition方法
 */
public class CityPartitioner extends Partitioner<Text,Flow> {
    /**
     * 重写方法
     *
     * @param text key
     * @param flow value
     * @param numPartitions    numPartitions 分区编号 编号从0开始
     * @return 分区编号
     */
    @Override
    public int getPartition(Text text, Flow flow, int numPartitions) {
        //获取当前区域
        String city = flow.getCity();
        //判断 根据区域分配对应的分区号
        if (city.equals("beijing")) return 0;
        else if (city.equals("shanghai")) return 1;
        else return 2;

    }
}