package cn.chifeng.sortFlow;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 输入:
 * 偏移量
 * 当前行数据
 * 输出:
 * 对象
 * null
 */
public class SortFlowMapper extends Mapper<LongWritable, Text,Flow, NullWritable> {
    @Override
    protected void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException {
        //拆分
        String[] arr = value.toString().split("");
        //封装对象
        Flow flow = new Flow();
        //赋值
        flow.setName(arr[0]);
        flow.setFlow(Integer.parseInt(arr[1]));
        //写出
        context.write(flow,NullWritable.get());
    }
}
