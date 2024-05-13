package cn.Profit;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Profit implements WritableComparable<Profit> {
    //月份
    private int month;
    private String name="";//人名
    private int profit;//利润
    //先按月份排序 再按照利润排序
    @Override
    public int compareTo(Profit o) {
        int r=this.month-o.month;//月份排序
        //月份是否相同
        if (r==o.month)
            return o.profit-this.profit;
        return r;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(month);
        out.writeUTF(name);
        out.writeInt(profit);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.month=in.readInt();
        this.name=in.readUTF();
        this.profit=in.readInt();
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }
}
