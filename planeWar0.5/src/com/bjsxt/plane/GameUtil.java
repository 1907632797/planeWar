package com.bjsxt.plane;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class GameUtil {
    //private  GameUtil(){
    //构造器私有化 防止别人创建本类对象
    //}
    public static Image getImage(String path) {//传图片路径
        BufferedImage img = null;

        URL u = GameUtil.class.getClassLoader().getResource(path);
        try {
            img = ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static void main(String[] args) {
        Image img = GameUtil.getImage("images/plane.png");
        System.out.println(img);
        Image o = GameUtil.getImage("images/bg.png");
        System.out.println(o);
    }
}
