package cn.chifeng.Friend;

import javafx.scene.text.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 输入:
 * 输储:
 * 真实好友关系
 * 数字
 */
public class FriendReducer extends Reducer<Text,Text,Text, IntWritable>{
    private final IntWritable trueFriend=new IntWritable(1);
    private final IntWritable falseFriend=new IntWritable(0);
    @Override
    protected void reduce(Text key,Iterable<Text> values,Context context) throws IOException, InterruptedException {
    //存储好友列表
    ArrayList<String> fs = new ArrayList<>();
    //key :tom
    //value :rose jim smith lucy
    String name = key.toString();
    //迭代好友列表
    for (Text value:values){
        //当前的好友
        String f = value.toString();
        //将好友添加到集合中
        fs.add(f);
        //比较
        if (name.compareTo(f)<0)
            context.write(new Text(name+"-"+f),trueFriend);
            //rose-tom
        else
            context.write(new Text(f+"-"+name),trueFriend);
    }
    //推算好友
        for (int i=0;i<fs.size();i++){
            //获取当前好友
            String f1 = fs.get(i);//rose
            //用当前好友与其他好友输出
            for (int j=i+1;j<fs.size();j++){
                //获取此时的好友
                String f2 = fs.get(j);
                //比较 --> 排序
                if (f1.compareTo(f2)<0)
                    context.write(new Text(f1+"-"+f2),falseFriend);
                else
                    context.write(new Text(f2+"-"+f1),falseFriend);
            }
        }
}
}
