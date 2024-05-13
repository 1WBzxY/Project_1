package cn.chifeng.wordCount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

/*
演示:一个文件中单词出现的次数
输入:
k行字节偏移量
v当前行数据
输出:
k文本(在当前的案例中是一个单词)
v数字(单词出现次数为1)
 */
public class WordMapper extends Mapper<LongWritable, Text,Text, IntWritable>{
    //重写方法
    /*
    参数1:字节偏移量
    参数2:当前行数据
    参数3:环境参数
     */
    @Override
    protected void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException {
        //转换为字符串类别
        String str = value.toString();
        //拆分 每一个单词用空格隔开
        String[] arr = str.split("");
        //遍历
        for (String s:arr){
            //写出
            context.write(new Text(s),new IntWritable(1));
        }
    }
}
