package cn.Profit;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class ProfitDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        //设置入口类
        job.setJarByClass(ProfitDriver.class);
        job.setMapperClass(ProfitMapper.class);
        job.setReducerClass(ProfirReducer.class);
        //设置指定类型
        job.setOutputKeyClass(Profit.class);
        job.setOutputValueClass(NullWritable.class);
        //指定文件路径
        FileInputFormat.addInputPath(job,
                new Path("hdfs://hadoop01:9000/txt/profit"));
        FileOutputFormat.setOutputPath(job,
                new Path("hdfs://hadoop01:9000/result/sortProfit"));
        //提交任务
        job.waitForCompletion(true);
    }
}
