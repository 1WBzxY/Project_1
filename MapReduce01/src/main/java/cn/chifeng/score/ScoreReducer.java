package cn.chifeng.score;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 输入:
 * 人名
 * 月份和分数
 * 输出:
 * 人名
 * 人数
 */
public class ScoreReducer extends Reducer<Text,Score,Text, IntWritable> {
    @Override
    protected void reduce(Text key,Iterable<Score> values,Context context) throws IOException, InterruptedException {
        //储存总分数
        int sum=0;
        //迭代
        for (Score s:values){
            //求和
            sum+=s.getScore();
        }
        //写出
        context.write(key,new IntWritable(sum));
    }
}
