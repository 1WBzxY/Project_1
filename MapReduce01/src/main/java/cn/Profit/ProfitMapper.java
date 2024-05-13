package cn.Profit;

import cn.chifeng.score.Month;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 输入:
 * 行偏移量
 * 当前行数据
 * 输出
 * 利润对象
 *
 */
public class ProfitMapper extends Mapper<LongWritable, Text,Profit, NullWritable> {
    @Override
    protected void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException {
        //拆分
        String[] arr = value.toString().split("");
        //封装对象
        Profit profit = new Profit();
        //赋值
        profit.setMonth(Integer.parseInt(arr[0]));
        profit.setName(arr[1]);
        profit.setProfit(Integer.parseInt(arr[2]));
        //写出
        context.write(profit,NullWritable.get());
    }
}
