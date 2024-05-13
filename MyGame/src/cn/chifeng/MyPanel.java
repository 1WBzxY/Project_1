package cn.chifeng;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 * 画布类
 * 图片
 * 文字
 * 图形
 */
public class MyPanel extends JPanel implements MouseMotionListener, MouseListener, KeyListener {

    //背景图片
    BufferedImage bg=null;
    BufferedImage loser=null;//失败
    BufferedImage succeed=null;//成功
    BufferedImage bullttip=null;//子弹背景
    //创建字体
    Font f=new Font("微软雅黑",20,20);
    //创建熊猫对象
    Panda panda=new Panda();
    //存储敌人集合
    Vector<Enemy> enemies=new Vector<>();
    //存储子弹的集合
    Vector<Bullet> bullets=new Vector<Bullet>();
    //子弹数量
    private int bulletNumber=5;
    boolean gameState;//true表示运行 false表示结束
    boolean loser_succeed;//true表示胜利 false表示失败

    public MyPanel(){
        try {
            //加载背景图片到程序中
            bg= ImageIO.read(getClass().getResourceAsStream("./images/bg.png"));
            loser=ImageIO.read(getClass().getResourceAsStream("./images/lose.jpg"));
            succeed=ImageIO.read(getClass().getResourceAsStream("./images/succeed.jpg"));
            bullttip=ImageIO.read(getClass().getResourceAsStream("./images/bullet_tip.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建5个敌人并添加到集合
        for (int i = 0; i < 5; i++) {
            enemies.add(new Enemy(i));
        }
        gameState=true;//默认运行游戏
    }

    //绘制的方法
    @Override
    public void paint(Graphics g) {
        //根据游戏状态绘制图片
        if (gameState){
            //绘制图片(图片对象,x坐标,y坐标,null)
            g.drawImage(bg,0,0,null);
            g.drawImage(bullttip,850,530,null);
            g.setFont(f);//字号大小
            g.setColor(Color.white);//字体颜色
            g.drawString("" + bulletNumber, MyFrame.W-135, MyFrame.H-59*2+30);
            //调用熊猫对象的绘制方法
            panda.drawImage(g);
            //遍历集合 调用敌人绘制方法
            for (int i = 0; i < enemies.size(); i++) {
                enemies.get(i).drawImage(g);
            }
            //遍历子弹集合
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).drawImage(g);
            }
        }else {//游戏结束
            //判断胜利与失败
            if (loser_succeed){
                g.drawImage(succeed,0,0,null);
            }else {
                g.drawImage(loser,0,0,null);
            }
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) { }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println("鼠标: "+e.getX()+","+e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    //鼠标按下
    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("Pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //键盘按下
    @Override
    public void keyPressed(KeyEvent e) {
        //获取按键的字符
        int key=e.getKeyCode();
        //判断 按下哪个按键并对其进行操作
        switch (key){
            case 32:
                panda.flag=true;
                //判断是否可以创建子弹
                if(bulletNumber>0) {
                    //创建子弹并添加到集合中
                    bullets.add(new Bullet(panda));
                    //减少子弹数量
                    bulletNumber--;
                }
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //获取按键的字符
        int key=e.getKeyCode();
        //System.out.println(key);
        //判断 按下哪个按键并对其进行操作
        switch (key){
            case 32:
                panda.flag=false;
                break;
        }
    }

    //开始游戏
    public void start(){
        //定时器 java.util
        Timer t=new Timer();
        //添加任务 java.util.TimerTask
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                //所有的动作都在当前位置调用
                //遍历集合 调用敌人的移动方法
                for (int i = 0; i < enemies.size(); i++) {
                    enemies.get(i).move();
                }
                //遍历子弹集合
                for (int i = 0; i < bullets.size(); i++) {
                    bullets.get(i).move();
                }
                //调用检查的方法
                checkObjectState();
                //调用检查的方法
                checkObject();
                //如果子弹集合为0 与子弹数量为0
                if (bulletNumber==0 && bullets.isEmpty()){
                    //修改游戏状态
                    gameState=false;
                    //判断胜利或失败
                    if (enemies.isEmpty()){//胜利
                        loser_succeed=true;
                    }else {//失败
                        loser_succeed=false;

                    }
                }
                //重绘
                repaint();
            }
        },75,75);
    }

    //检查角色状态是否可以删除
    private void checkObjectState(){
        //遍历
        for (int i = 0; i < bullets.size(); i++) {
            //获取元素
            Bullet b=bullets.get(i);
            //判断状态
            if(b.state==2){
                //将元素从集合中删除
                bullets.remove(b);
            }
        }
        //遍历敌人
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e=enemies.get(i);
            if(e.state==2){
                enemies.remove(e);
            }
        }
    }

    /**
     * 定义方法
     * 遍历敌人集合
     *      获取敌人
     *      遍历子弹集合
     *          用当前子弹判断该敌人
     *              修改敌人和子弹状态
     */
    private  void checkObject(){
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e=enemies.get(i);
            for (int j = 0; j < bullets.size(); j++) {
                Bullet bullet=bullets.get(j);
                if(bullet.checkHit(e)){
                    bullet.state=2;
                    e.state=2;
                    break;
                }
            }
        }
    }
    /**
     * 定义删除角色的方法
     *  遍历敌人集合
     *      如果敌人状态可以删就从集合中删除敌人
     *  遍历子弹集合
     *      如果子弹状态可以删除 就从集合中删除
     */


}
/**
 * 1.只能发射5发子弹
 * 2.子弹超出屏幕消失
 * 3.子弹与敌人碰撞双方消失
 */