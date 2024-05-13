package cn.chifeng.Friend;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class DFriend {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);
        job.setJarByClass(DFriend.class);
        job.setMapperClass(FriendMapper.class);
        job.setReducerClass(FReducer.class);

        job.setMapOutputValueClass(IntWritable.class);
        job.setMapOutputKeyClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        FileInputFormat.addInputPath(job,
                new Path("hdfs://hadoop01:9000/result/friendT"));
        FileOutputFormat.setOutputPath(job,
                new Path("hdfs://hadoop01:9000/result/friendR"));
        job.waitForCompletion(true);
    }

}
