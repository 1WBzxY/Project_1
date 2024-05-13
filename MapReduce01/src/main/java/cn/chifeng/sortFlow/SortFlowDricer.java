package cn.chifeng.sortFlow;

import cn.chifeng.score.ScoreMapper;
import cn.chifeng.score.ScoreReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class SortFlowDricer {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        //设置入口类
        job.setJarByClass(SortFlowDricer.class);
        job.setMapperClass(ScoreMapper.class);
        job.setReducerClass(ScoreReducer.class);

        //设置指定类型
        job.setMapOutputKeyClass(Flow.class);
        job.setOutputValueClass(NullWritable.class);
        job.setOutputValueClass(Text.class);
        job.setOutputKeyClass(IntWritable.class);

        //指定文件路径
        FileInputFormat.addInputPath(job,
                new Path("hdfs://hadoop01:9000/result/partFlow"));
        FileOutputFormat.setOutputPath(job,
                new Path("hdfs://hadoop01:9000/result/sortFlow"));
        //提交任务
        job.waitForCompletion(true);
    }
}
