package cn.chifeng.partFlow;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Flow implements Writable {
    //区域
    private String city="";
    private int upFlow;
    private int downFlow;
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(city);
        out.writeInt(upFlow);
        out.writeInt(downFlow);
    }
    @Override
    public void readFields(DataInput in) throws IOException {
        this.city=in.readUTF();
        this.upFlow=in.readInt();
        this.downFlow=in.readInt();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(int upFlow) {
        this.upFlow = upFlow;
    }

    public int getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(int downFlow) {
        this.downFlow = downFlow;
    }
}
