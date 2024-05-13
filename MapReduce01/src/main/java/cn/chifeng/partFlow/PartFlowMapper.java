package cn.chifeng.partFlow;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 演示将文件中,安照区域来计算流量总和
 * 输入:
 * 偏移量
 * 当前行数据
 * 输出:
 * 人名
 * flow对象
 */
public class PartFlowMapper extends Mapper<LongWritable, Text,Text,Flow> {
@Override
    protected void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException {
    //18642971356 shanghai david 4132 4121
    //拆分
    String[] arr = value.toString().split("");
    //构建对象
    Flow f = new Flow();
    f.setCity(arr[1]);
    f.setUpFlow(Integer.parseInt(arr[3]));
    f.setDownFlow(Integer.parseInt(arr[4]));
    //写出
    context.write(new Text(arr[2]),f);
}
}
