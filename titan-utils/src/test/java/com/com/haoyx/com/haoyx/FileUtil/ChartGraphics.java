package com.com.haoyx.com.haoyx.FileUtil;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import com.sun.image.codec.jpeg.*;
public class ChartGraphics {

static BufferedImage image;

	static void createImage(String fileLocation) {
		try {
			FileOutputStream fos = new FileOutputStream(fileLocation);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
			encoder.encode(image);
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void graphicsGeneration(String name, String id, String classname, String imgurl) {

		int imageWidth = 2500;
		
		int imageHeight = 1200;
		
		image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, imageWidth, imageHeight);
		graphics.setColor(Color.BLACK);
		graphics.drawString("Ãû³Æ: " + name, 50, 75);
		graphics.drawString("±àºÅ: " + id, 50, 150);
		graphics.drawString("°à¼¶Ãû³Æ£º" + classname, 50, 225);
		//ImageIcon imageIcon = new ImageIcon(imgurl);
		//graphics.drawImage(imageIcon.getImage(), 230, 0, null);
		

		BufferedImage bimg = null;
		try {
			bimg = javax.imageio.ImageIO.read(new java.io.File(imgurl));
			} catch (Exception e) {}
			
			if(bimg!=null)
			graphics.drawImage(bimg, 230, 0, null);
			graphics.dispose();
			
			createImage("E:/hehe.jpg");

	}

	 public static void createImage(String name,int width, int height) throws IOException{
	        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
	        Graphics2D graphic = bi.createGraphics();
	        graphic.setColor(new Color(0.2f,0.3f,0.4f,0.4f));
	        graphic.setBackground(Color.BLUE);
	        graphic.fillRect(0, 0, width, height);
	       
	        
	        for (int i = 0; i < width; i++) {
	            for (int j = 0; j < height; j++) {
	                  //result[i][j] = bi.getRGB(i, j) & 0xFFFFFF;
	                 // System.out.println(bi.getRGB(i, j));
	            	 Random r=new Random();
	                 bi.setRGB(i, j, r.nextInt(0xFFFFFF)); 
	            }
	        }
	        graphic.drawString(name,50,40);
	        Iterator<ImageWriter> it = ImageIO.getImageWritersByFormatName("jpg");
	        ImageWriter writer = it.next();
	        File f = new File("E:/TestFile/"+name);
	        ImageOutputStream ios = ImageIO.createImageOutputStream(f);
	        writer.setOutput(ios);
	        
	        writer.write(bi);
	}
	
	 
	 public static void genImage(String url,String name,String target) {
		  
			BufferedImage bimg = null;
			Graphics graphics=null;
			try {
				image = javax.imageio.ImageIO.read(new java.io.File(url));
			} catch (Exception e) {}
				graphics=image.getGraphics();
				if(image!=null)
				{
					graphics.setColor(new Color(0.2f,0.3f,0.4f,0.4f));
					graphics.drawString(name,100,200);
					graphics.dispose();
				}
				createImage(target);
	 }
	 
public static void main(String[] args) throws IOException {

	for(int i=10;i<20;i++)
	{
		genImage("D:/images/jmeter.png","Test"+i,"D:/images/new/"+i+".jpg");
	}
	/*ChartGraphics cg = new ChartGraphics();
	try {
		cg.graphicsGeneration("ewew", "1", "12", "C:/7.jpg");
	} catch (Exception e) {
		e.printStackTrace();
	}*/
	
}
}