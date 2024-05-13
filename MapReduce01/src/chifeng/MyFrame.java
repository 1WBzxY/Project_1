package cn.chifeng;

import javax.swing.*;

public class MyFrame {
    public static void main(String[] args) {
        //创建窗口
        JFrame jf = new JFrame();
        //设置窗口大小
        jf.setSize(1000,625);
        //设置默认关闭
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口居中
        jf.setLocationRelativeTo(null);
        //画布对象
        MyPane mp = new MyPane();
        //将画布添加到窗口
        jf.add(mp);
        //添加鼠标移动监听器
        jf.addMouseMotionListener(mp);
        //添加鼠标监听器
        jf.addMouseListener(mp);
        //添加键盘监听器
        jf.addKeyListener(mp);

        //开始游戏
        mp.start();
        //设置可见
        jf.setVisible(true);
    }
}
