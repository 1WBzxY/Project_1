package cn.chifeng.score;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 根据月份分区
 * 泛型:
 * key 人名
 * value 分数与月份
 */
public class Month extends Partitioner<Text,Score> {
    //重写
    @Override
    public int getPartition(Text text, Score score, int i) {
        //获取月份
        int m=score.getMonth();
        //根据月份来决定当前分区
//        if (m==1) return 0;
//        else if (m==2) return 1;
//        else if (m==3) return 2;
//        else if (m==4) return 3;
//        else if (m==3) return 4;
//        else if (m==3) return 5;
//        else if (m==3) return 6;
//        else if (m==3) return 7;
//        else if (m==3) return 8;
        //月份减1就是分区号
        return m-1;
    }
}
