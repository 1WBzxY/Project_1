package cn.chifeng.wordCount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


import java.io.IOException;

/**
 * 输入
 * k文本(当前案例中表示一个单词)
 * v数字(当前案例中单词出现一次)
 * 输出:
 * k单词
 * v单词出现的总次数
 */
public class WordReducer extends Reducer<Text, IntWritable,Text,IntWritable> {
    /**
     * 重写
     * 参数1:单词
     * 参数2:迭代器(在当前案例中表示每个单词出现的每次次数)
     * 参数3:环境参数
     */
    @Override
    protected void reduce(Text key,Iterable<IntWritable> values,Context context) throws IOException, InterruptedException {
        //定义和变量
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
