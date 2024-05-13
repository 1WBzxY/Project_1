package cn.chifeng.Tempereture;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TemperatureMapper extends Mapper<Object, Text, Text, DoubleWritable> {
    private Text location = new Text();
    private DoubleWritable temperature = new DoubleWritable();

    @Override
    protected void map(Object key, Text value, Mapper<Object, Text, Text, DoubleWritable>.Context context) throws IOException, InterruptedException, IOException {
        String[] parts = value.toString().split(" ");
        if (parts.length == 2) {
            location.set(parts[0]);
            temperature.set(Double.parseDouble(parts[1]));
            context.write(location, temperature);
        }
    }
}
