package cn.chifeng.Friend;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * tom rose jim smith lucy
 *
 * 将每个人的好友输出
 * 好友没有主次之分,使用字母比较方法来实现名字在前在后
 *
 *
 * compare To 比较
 * equals 比较规则 Boolean
 * == 比较值
 */
public class FriendMapper extends Mapper<LongWritable, Text,Text, Text> {
    @Override
    protected void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException {
        //拆分
        String[] arr = value.toString().split("");
        //写出
        context.write(new Text(arr[0]),new Text(arr[1]));
    }
}
