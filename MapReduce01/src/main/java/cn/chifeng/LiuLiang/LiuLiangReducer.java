package cn.chifeng.LiuLiang;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class LiuLiangReducer extends Reducer<Text, IntWritable, Text,IntWritable> {
    @Override
    protected void reduce(Text key,Iterable<IntWritable> values,Context context) throws IOException, InterruptedException{
        //存储总流量
        int sum=0;
        //遍历
        for (IntWritable value:values){
            //累加
            sum+=value.get();
        }
        context.write(key,new IntWritable(sum));
    }
}
