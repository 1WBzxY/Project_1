package cn.chifeng;

import sun.java2d.windows.GDIRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 敌人
 *  属性
 *      坐标
 *      宽高
 *      图片
 *  方法
 *      绘制方法
 *      移动方法
 */
public class Enemy {
    int x,y;//坐标
    int w,h;//宽高
    BufferedImage img_1=null;
    BufferedImage img_2=null;

    Random ran=new Random();
    int state;//状态 0活 1死 2删

    int m=5;//移动速度
    boolean dir=false;//方向 false表示向右  true表示向左

    //构造方法
    public Enemy(int number){
        try {
            //加载图片
            img_1= ImageIO.read(getClass().getResourceAsStream("./images/enemy"+(number+1)+"_1.png"));
            img_2= ImageIO.read(getClass().getResourceAsStream("./images/enemy"+(number+1)+"_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取图片宽高
        this.w=img_1.getWidth();
        this.h=img_2.getHeight();
        //随机x坐标
        this.x=ran.nextInt(MyFrame.W-this.w);
        this.y=0;
        this.state=0;
    }
    //绘制方法
    public void drawImage(Graphics g){
        if (dir)//true表示绘制向左 否则就绘制向右
            g.drawImage(img_2,x,y,null);//向右图片
        else
            g.drawImage(img_1,x,y,null);//向右图片
    }

    //移动方法
    public void move(){
        x+=m;//默认向右移动
        //判断有边框
        if(x>=MyFrame.W-this.w){
            m=-5;
            dir=true;
        }
        //判断左边框
        if(x<=0){
            m=5;
            dir=false;
        }
    }

}