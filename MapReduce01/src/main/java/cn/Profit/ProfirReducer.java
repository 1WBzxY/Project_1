package cn.Profit;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

/**
 *
 */
public class ProfirReducer extends Reducer<Profit, NullWritable,Profit,NullWritable> {
    @Override
    protected void reduce(Profit key, Iterable<NullWritable> values, Reducer<Profit, NullWritable, Profit, NullWritable>.Context context) throws IOException, InterruptedException {
        //输出
        context.write(key,NullWritable.get());
    }
}
