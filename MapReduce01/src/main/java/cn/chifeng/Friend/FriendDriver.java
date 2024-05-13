package cn.chifeng.Friend;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.awt.peer.TextComponentPeer;
import java.io.IOException;

public class FriendDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(FriendDriver.class);
        job.setMapperClass(FriendMapper.class);
        job.setReducerClass(FriendReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job,
                new Path("hdfs://hadoop01:9000/txt/friend.txt"));
        FileOutputFormat.setOutputPath(job,
                new Path("hdfs://hadoop01:9000/result/friendT"));

        job.waitForCompletion(true);
    }
}
