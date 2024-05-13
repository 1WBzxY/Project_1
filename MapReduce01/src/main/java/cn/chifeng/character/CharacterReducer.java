package cn.chifeng.character;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/*
输入:输入键和值Reducer的输入是Mapper的输出
 */
public class CharacterReducer extends Reducer<Text, IntWritable,Text,IntWritable> {
    /*
    重写reduce的方法
    参数1:键:当前案例中的字符
    参数2:值:当前案例字符对应次数
    参数3:环境
     */
@Override
    protected void reduce(Text key,Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
    //和
    int sum=0;
    //迭代
    for (IntWritable value:values){
    //获取当前value值
        sum+=value.get();
    }
    //将和输出
    context.write(key,new IntWritable(sum));
    System.out.println(key+","+sum);
}
}
