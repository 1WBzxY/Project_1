package cn.chifeng.wordCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        //构建环境
        Configuration conf = new Configuration();
        //申请任务.
        Job job = Job.getInstance();
        //设置入口类
        job.setJarByClass(WordDriver.class);
        job.setMapperClass(WordMapper.class);
        job.setReducerClass(WordReducer.class);
        //设置map和reduce的输出数据类型
        //如果map和reduce的输出类型一样,可以写一个
        //设置map输出数据类型
        job.setOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //设置reduce数据类型
        //指定文件的输入输出路径
        FileInputFormat.addInputPath(job,
                new Path("hdfs://hadoop01:9000/txt/words.txt"));
        //指定文件的输入路径
        FileOutputFormat.setOutputPath(job,
                new Path("hdfs://hadoop01:9000/result/word"));
        //提交任务
        job.waitForCompletion(true);

    }
}
