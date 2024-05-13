package cn.chifeng.character;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//入口类(驱动类)
public class CharacterDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        //申请任务
        Job job = Job.getInstance(conf);
        //设置入口类
        //map入口类
        job.setMapperClass(CharacterMapper.class);
        //reduce入口类
        job.setReducerClass(CharacterReducer.class);
        //指定数据类型  map
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        // reduce
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //指定处理文件路径
        FileInputFormat.addInputPath(job,
                new Path("hdfs://hadoop01:9000/txt/characters.txt"));
        //制定结果文件路径
        FileOutputFormat.setOutputPath(job,
                new Path("hdfs://hadoop01:9000/result/cahr_count"));
        //提交
        job.waitForCompletion(true);
    }
}
