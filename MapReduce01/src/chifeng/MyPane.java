package cn.chifeng;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 画布类
 * 文字
 * 图片
 * 图形
 */
public class MyPane extends JPanel implements MouseMotionListener,MouseListener, KeyListener {
    //背景图片
    BufferedImage bg =null;
    Font f = new Font("微软雅黑",20,20);
    //小球坐标
    int x=100,y=100;
    public MyPane(){
        try {
            //加载图片到程序中
            bg= ImageIO.read(getClass().getResourceAsStream("./images/bg.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //绘制的方法
    @Override
    public void paint(Graphics g) {
        //绘制图片(图片对象,x坐标,y坐标,null)
        g.drawImage(bg,0,0,null);
//        //设置字体
//        g.setFont(f);
//        //绘制文本
//        g.drawString("游戏",100,10);
//        //设置画笔颜色
//        g.setColor(Color.black);
//        //实心图形
//        g.fillOval(x,y,50,50);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        System.out.println("鼠标"+e.getX()+","+e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
//        System.out.println("Pressed");
    }
    //鼠标按下
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    //键盘按下
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("按下"+e.getKeyChar());
        char key=e.getKeyChar();
        //判断
        switch (key){
            case 'a':
                x-=10;
                break;
            case 'd':
                x+=10;
                break;
            case 'w':
                y-=10;
                break;
            case 's':
                y+=10;
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void start(){
        //定时器 java.util
        Timer t = new Timer();
        //添加任务 java.util.TimerTask
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                //一直运行

                   //所有的动作都在当前位置调用
                    //小球移动
                    movefillOval();
                    //重绘
                    repaint();

            }
        },75,75);
    }
    int m=10,n=10;
    //球移动
    public void movefillOval(){
        y+=m;
        if (y>500){//下边框
            m=-m;
        }
        if (y<0){//上边框
            m=10;
        }
        x+=n;
        if (x>900){//右边框
            n=-n;
        }
        if (x<0)//左边框
        System.out.println(y);
    }
}
