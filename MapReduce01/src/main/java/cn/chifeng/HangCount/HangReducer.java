package cn.chifeng.HangCount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class HangReducer extends Reducer<Text, IntWritable,Text,IntWritable> {
    @Override
    protected void reduce(Text key,Iterable<IntWritable> values,Context context) throws IOException, InterruptedException {
        int sum=0;
        //遍历迭代器
        for (IntWritable value:values){
            //获取当前value值
            int i=value.get();
            //累加
            sum+=1;
        }
        //写出
        context.write(key,new IntWritable(sum));
    }
}
