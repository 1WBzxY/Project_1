package cn.chifeng;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 子弹类
 *  属性:
 *      坐标
 *      宽高
 *      图片
 *  方法:
 *      绘制方法
 *      移动方法
 */
public class Bullet {
    int x,y;
    int w,h;
    BufferedImage img;
    //子弹状态
    int state;//0活 1死 2删

    /**
     * 创建子弹对象 要求传递熊猫对象以便获取熊猫属性
     * @param panda 熊猫对象
     */
    public Bullet(Panda panda){
        try {
            img= ImageIO.read(getClass().getResourceAsStream("./images/bullet.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        w=img.getWidth();
        h= img.getHeight();
        x=panda.x+panda.w-w;
        y=panda.y;
        state=0;//初始活着

    }
    //绘制方法
    public void drawImage(Graphics g){
        g.drawImage(img,x,y,null);
    }

    //移动方法
    public void move(){
        //默认从下往上移动
        y-=5;
        //判断是否越界
        if (y<=0){
            //标记可以删除
            state=2;
        }
    }

    //检查碰撞方法
    public boolean checkHit(Enemy e){
        return   this.x>=e.x-this.w && this.x<=e.x+e.w && this.y<=e.y+e.h && this.y>=e.y;
    }
}
