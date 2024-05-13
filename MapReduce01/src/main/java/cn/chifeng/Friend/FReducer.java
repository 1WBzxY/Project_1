package cn.chifeng.Friend;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import javax.xml.soap.Text;
import java.io.IOException;

public class FReducer extends Reducer<Text, IntWritable,Text, NullWritable> {
    @Override
    protected void reduce(Text key,Iterable<IntWritable> values,Context context) throws IOException, InterruptedException {
        System.out.println(key.toString());
        for (IntWritable value:values){
            //本身是好友
            if (value.get()!=0)
                return;
        }
        context.write(key,NullWritable.get());
    }
}
