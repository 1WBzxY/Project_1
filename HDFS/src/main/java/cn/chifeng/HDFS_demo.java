package cn.chifeng;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class HDFS_demo {
//演示上传
    @Test
    public void put() throws URISyntaxException, IOException, InterruptedException {
        //创建连接地址
        URI uri = new URI("hdfs://192.168.204.100:9000");
        //环境
        Configuration conf = new Configuration();
        //设置副本数量
        conf.set("dfs.replication","2");
        //设置block大小 200M
        conf.set("dfs.blocksize","209715200");
        //静态方法获取连接
        FileSystem fs =FileSystem.get(uri,conf,"root");
        //指定上传位置
        Path path = new Path("/a.xml");
        //获取输出流
        FSDataOutputStream out=fs.create(path);
        //上传的文件
        FileInputStream fis=new FileInputStream("D:\\pom.xml");
        //读
        IOUtils.copyBytes(fis,out,conf);
        //关闭
        out.close();
        fis.close();
    }
    @Test
    public void get() throws URISyntaxException, IOException, InterruptedException {
        //连接地址
        URI uri = new URI("hdfs://192.168.204.100:9000");
        //环境变量
        Configuration conf = new Configuration();
        //连接HDFS
        FileSystem fs = FileSystem.get(uri, conf, "root");
        //指定要下载的文件路径
        FSDataInputStream in=fs.open(new Path("/a.xml"));
        //指定本地存储路径
        FileOutputStream out = new FileOutputStream("D:\\a_1.xml");
        //读取数据
        IOUtils.copyBytes(in,out,conf);
        //关闭
        in.close();
        out.close();
    }
    @Test
    public void rename() throws IOException, InterruptedException, URISyntaxException {
        //连接地址
        URI uri = new URI("hdfs://192.168.204.100:9000");
        //环境变量
        Configuration conf = new Configuration();
        //连接hdfs
        FileSystem fs = FileSystem.get(uri, conf, "root");
        //重命名(被修改的文件命,修改后的文件名)
        fs.rename(new Path("/a.xml"),new Path("xl.xml"));
    }
    @Test
public void delete() throws URISyntaxException, IOException, InterruptedException {
        //连接地址
        URI uri = new URI("hdfs://192.168.204.100:9000");
        //环境变量
        Configuration conf = new Configuration();
        //连接HDFS
        FileSystem fs = FileSystem.get(uri, conf, "root");
        //删除
//        参数1:删除是文件
//        参数2:表示是否递归删除
//        true 递归删除
//        false 不是递归删除(如果是文件true和false都一样)
        fs.delete(new Path("/12.txt"),true);
    }
}
