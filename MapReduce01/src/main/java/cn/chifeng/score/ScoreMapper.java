package cn.chifeng.score;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 演示将3个文件中每个月的分区计算总和
 * 输入:
 * 偏移量
 * 当前行数据
 * 输出:
 * 人名
 * 分数与月份
 */
public class ScoreMapper extends Mapper<LongWritable, Text,Text,Score> {
@Override
    protected void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException {
    //1 zhang 89
    //拆分
    String[] arr = value.toString().split("");
    //封装
    Score s = new Score();
    s.setMonth(Integer.parseInt(arr[0]));
    s.setScore(Integer.parseInt(arr[2]));
    //写出
    context.write(new Text(arr[1]),s);
}
}
