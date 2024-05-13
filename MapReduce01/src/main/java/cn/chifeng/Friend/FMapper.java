package cn.chifeng.Friend;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    @Override
    protected void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException {
        //jim-lucy  1
        String[] arr = value.toString().split("\t");
        //好友关系作为key 后面数字(0或1)作为value
        context.write(new Text(arr[0]),new IntWritable(Integer.parseInt(arr[1])));
    }
}
