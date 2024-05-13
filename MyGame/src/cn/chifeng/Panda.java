package cn.chifeng;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 熊猫类
 * 属性(特征):
 *  x y 坐标
 *  宽高
 *  图片
 *
 * 方法(行为):
 *  绘制
 *
 */
public class Panda {
    int x,y;//坐标
    int w,h;//宽高
    //图片
    BufferedImage img_1=null;//正常图片
    BufferedImage img_2=null;//攻击图片
    boolean flag=false;//按键是否按下(默认false没有按下)

    //构造方法
    public Panda(){
        //加载图片
        try {
            img_1= ImageIO.read(getClass().getResourceAsStream("./images/hero.png"));
            img_2=ImageIO.read(getClass().getResourceAsStream("./images/hero1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //获取图片的宽高并赋值
        this.w=img_1.getWidth();
        this.h=img_1.getHeight();
        //初始位置屏幕中间
        this.x=MyFrame.W/2-this.w/2 -40;
        this.y=MyFrame.H-this.h-70;
    }

    //绘制方法
    public void drawImage(Graphics g){
        //判断按下了空格键就绘制攻击图片
        if (this.flag)
            //绘制图片
            g.drawImage(this.img_2,this.x,this.y,null);
        else
            g.drawImage(this.img_1,this.x,this.y,null);
    }
}
