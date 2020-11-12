package com.bjsxt.plane;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyGameFrame extends Frame{

    public static final int GAME_WIDTH = 910;
    public static final int GAME_HEIGHT = 682;

    Image bg = GameUtil.getImage("images/bg.png");
    Image plane = GameUtil.getImage("images/plane.png");
    //Image bg = GameUtil.getImage("images/bg.png");

    static int count = 0;

    int planeX = 1;
    int planeY = 1;

    @Override
    public void paint(Graphics g) {  //g当做一只画笔
        super.paint(g);

        System.out.println("绘制窗口次数："+count);
        count++;

        g.drawImage(plane,planeX,planeY,null);
        planeX += 1;
        planeY += 1;

        g.drawImage(bg,0,0,null);
        //g.drawImage(plane,120,180,null);

        // g.drawImage(plane,100,100,null);

        // Color c = g.getColor();

        //g.getColor(Color.red);
        // g.setColor(new Color(255,0,255));

        //g.drawLine(100,100,400,400);//直线
        //g.drawRect(100,100,300,200);//画矩形
        //g.drawOval(100,100,300,200);//画椭圆
        //g.drawString("熊仁杰",300,300);//画字符串

        //g.setColor(c);//将原来的颜色还回去
    }


    //初始化窗口
    public void launchFrame(){
        this.setTitle("飞机大战 熊仁杰");
        setVisible(true);//窗口是否可见

        setSize(GAME_WIDTH,GAME_HEIGHT);//窗口大小

        setLocation(100,10);//窗口打开的位置

        //增加关闭窗口的动作
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);//正常退出程序
            }
        });

        new PaintThread().start();//启动线程重画
    }

    /**
     * 定义了重画窗口的线程类
     * 定义成内部类是为了方便直接使用窗口类的相关方法
     */
    class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                repaint();  //内部类可以直接使用外部类,继承Frame
                try {
                    Thread.sleep(50);//一秒画20次
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Image offScreenImage = null;

    public void update(Graphics g) {
        if(offScreenImage == null)
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage,0,0,null);
    }

    public static void main(String[] args) {
        MyGameFrame gameFrame = new MyGameFrame();
        gameFrame.launchFrame();
    }
}
