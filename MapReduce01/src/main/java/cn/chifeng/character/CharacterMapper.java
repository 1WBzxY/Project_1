package cn.chifeng.character;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/*
输入:
    KEYIN:输入数据类型key,表示当前行的字节偏移量
    VALUEIN:输入数据类型的value,默认表示一行数据
输出:
    KEYOUT:输出的key数据类型,在当前案例中表示一个字符
    VALUEOUT:输出的的value数据类型,在当前案例中表示1

 */
public class CharacterMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    /*
    重写map方法
    参数1:当前行自己的偏移量
    参数2:当前行是数据(当前案例中的一行字符串)
    参数3:环境参数
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //将text转成string,将string转char数组
        char[] cs = value.toString().toCharArray();
        //遍历数组
        for (char c:cs){
            //写出 c+""
            context.write(new Text(c+""),new IntWritable(1));
            System.out.println(c+","+new IntWritable(1));
        }
    }
}
