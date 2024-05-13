package cn.chifeng.Tempereture;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class TempretureDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "temperature average");
        job.setJarByClass(TempretureDriver.class);
        job.setMapperClass(TemperatureMapper.class);
        job.setReducerClass(TempretureReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);
        FileInputFormat.addInputPath(job,
                new Path("hdfs://hadoop01:9000/txt/tem.txt"));
        //指定文件输出类型
        FileOutputFormat.setOutputPath(job,
                new Path("hdfs://hadoop01:9000/result/flow"));

        job.waitForCompletion(true);

    }
}
