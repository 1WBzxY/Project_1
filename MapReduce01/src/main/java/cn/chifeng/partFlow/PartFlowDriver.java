package cn.chifeng.partFlow;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


public class PartFlowDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        // 设置入口类
        job.setJarByClass(PartFlowDriver.class);
        job.setMapperClass(PartFlowMapper.class);
        job.setReducerClass(PartFlowReducer.class);
        //输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Flow.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //指定分区类入口
        job.setPartitionerClass(CityPartitioner.class);
        //指定ReducerTask数量   如果不指定默认还是一个
        //指定3个ReducerTask来处理
        job.setNumReduceTasks(3);
        //指定输出文件
        FileInputFormat.addInputPath(job,
                new Path("hdfs://hadoop01:9000/txt/flow.txt"));
        FileOutputFormat.setOutputPath(job,
                new Path("hdfs://hadoop01:9000/result/partFlow"));
        //提交
        job.waitForCompletion(true);
    }
}
