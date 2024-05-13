package cn.chifeng.HangCount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class HangMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    @Override
    protected void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException {
//        String str = value.toString();
//        //拆分 每一个单词用空格隔开
//        String[] arr = str.split("/n");
//        //遍历
//        for (String s:arr){
//            //写出
            context.write(new Text("一共有"),new IntWritable(1));

    }
}
