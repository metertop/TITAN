package com.com.haoyx.com.haoyx.FileUtil;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

public abstract class AbstractImageCreator {
    private static Random rnd = new Random(new Date().getTime());
    private Drawer drawer;
    public Drawer getDrawer()
    {
        return this.drawer;
    }
    public void setDrawer(Drawer drawer)
    {
        this.drawer=drawer;
    }
    //图片宽度
    private int width = 200;
    public int getWidth()
    {
        return this.width;
    }
    public void setWidth(int width)
    {
        this.width=width;
    }
    //图片高度
    private int height = 80;
    public void setHeight(int height)
    {
        this.height=height;
    }
    public int getHeight()
    {
        return this.height;
    }
    //外框颜色
    private Color rectColor;
    public Color getRectColor()
    {
        return this.rectColor;
    }
    public void setRectColor(Color rectColor)
    {
        this.rectColor=rectColor;
    }
    //背景色
    private Color bgColor;

    //干扰线数目
    private int lineNum = 0;
    public void setLineNum(int lineNum)
    {
        this.lineNum=lineNum;
    }

    //图片格式
    private String formatName = "JPEG";
    public String getFormatName()
    {
        return this.fontName;
    }

    //字体颜色
    private Color fontColor = new Color(0, 0, 0);
    public Color getFontColor()
    {
        return this.fontColor;
    }
    //字体名称
    private String fontName = "宋体";
    public String getFontName()
    {
        return this.fontName;
    }
    public void setFontName(String fontName)
    {
        this.fontName=fontName;
    }
    //字体大小
    private int fontSize = 15;
    public int getFontSize()
    {
        return this.fontSize;
    }
    public void setFontSize(int fontSize)
    {
        this.fontSize=fontSize;
    }
    //文字旋转的弧度数
    private double radian = 0;
    public double getRadian()
    {
        return this.radian;
    }
    public void setRadian(double radian)
    {
        this.radian=radian;
    }

    private double rotateX = 0;
    public double getRotateX()
    {
        return this.rotateX;
    }
    public void setRotateX(int rotateX)
    {
        this.rotateX=rotateX;
    }

    private double rotateY = 0;
    public double getRotateY()
    {
        return this.rotateY;
    }
    public void setRotateY(double rotateY)
    {
        this.rotateY=rotateY;
    }
    //缩放
    private double scale = 1;
    public double getScale()
    {
        return this.scale;
    }

    //##### 此处省略getter、setter方法 #####


    public AbstractImageCreator(Drawer drawer){
        this.drawer = drawer;
    }

    /**
     * 画干扰线
     */
    private void drawRandomLine(Graphics graph){
        for(int i=0;i<lineNum;i++){
            //线条的颜色
            graph.setColor(getRandomColor(100, 155));

            //线条两端坐标值
            int x1 = rnd.nextInt(width);
            int y1 = rnd.nextInt(height);

            int x2 = rnd.nextInt(width);
            int y2 = rnd.nextInt(height);

            //画线条
            graph.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * 随机获取颜色对象
     */
    private Color getRandomColor(int base, int range){
        if((base + range) > 255) range = 255 - base;

        int red = base + rnd.nextInt(range);
        int green = base + rnd.nextInt(range);
        int blue = base + rnd.nextInt(range);

        return new Color(red, green, blue);
    }

    public void generateImage(String text)throws IOException{
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        if(rectColor == null) rectColor = new Color(0, 0, 0);
        if(bgColor == null) bgColor = new Color(240, 251, 200);

        //获取画布
        Graphics2D g = (Graphics2D)image.getGraphics();

        //画长方形
        g.setColor(bgColor);
        g.fillRect(0, 0, width, height);

        //外框
        g.setColor(rectColor);
        g.drawRect(0, 0, width-1, height-1);

        //画干扰线
        drawRandomLine(g);

        //画字符串
        drawer.draw(this, g, text);

        //执行
        g.dispose();

        //输出图片结果
        saveImage(image);
    }

    protected abstract void saveImage(BufferedImage image)throws IOException;

}