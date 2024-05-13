package cn.chifeng.sortFlow;

import org.apache.hadoop.io.WritableComparable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 封装了人名和流量
 */
public class Flow implements WritableComparable<Flow> {

    private String name="";//人名
    private int flow;//流量

    //制定比较规则  按照流量排序
    //this - o 升序
    //o - this 降序
    @Override
    public int compareTo(Flow o) {
        return o.flow-this.flow;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(name);
        out.writeInt(flow);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.name=in.readUTF();
        this.flow=in.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }
}
