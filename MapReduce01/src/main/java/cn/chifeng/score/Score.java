package cn.chifeng.score;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 成绩的类型
 * 封装月份和分数
 */
public class Score implements Writable {
   //月份
   private int month;
   //分数
    private int score;
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(month);
        out.writeInt(score);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.month=in.readInt();
        this.score=in.readInt();
    }
    //添加get和set

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
