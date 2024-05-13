package cn.chifeng.LiuLiang;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class LiuLiangMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
@Override
    protected void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException {
    //转字符串
    String str = value.toString();
    //拆分
    String[] arr = str.split("");
    //姓名
    String name=arr[2];
    //流量 上 下
    int up = Integer.parseInt(arr[3]);
    int down = Integer.parseInt(arr[4]);
    //总流量
    int sum=up+down;
    //名字作为key输出 arr[2]
    context.write(new Text(name),new IntWritable(sum));


}
}
