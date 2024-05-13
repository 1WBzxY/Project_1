package cn.chifeng.sortFlow;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 输入:
 * 封装对象
 * null
 * 输出:
 * 人名
 * 流量
 */
public class SortFlowReducer extends Reducer<Flow, NullWritable, Text,IntWritable> {
    @Override
    protected void reduce(Flow key,Iterable<NullWritable> values,Context context) throws IOException, InterruptedException {
        context.write(new Text(key.getName()),new IntWritable(key.getFlow()));
    }
}
