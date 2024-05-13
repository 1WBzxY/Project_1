package cn.chifeng.partFlow;

import org.apache.hadoop.io.IntWritable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


import java.io.IOException;

public class PartFlowReducer extends Reducer<Text,Flow,Text, IntWritable>  {
@Override
    protected void reduce(Text key, Iterable<Flow> values, Context context) throws IOException, InterruptedException {
    //总流量
    int sum=0;
    //迭代
    for (Flow f:values){
        //计算和
        sum+=f.getUpFlow()+f.getDownFlow();
    }
    //写出
    context.write(key,new IntWritable(sum));
}
}
