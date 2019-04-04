package com.com.haoyx.com.haoyx.FileUtil;

import java.io.IOException;

public class Test {  
    public static void main(String[] args) {  
        try{  
            StringBuffer sb = new StringBuffer();  
            sb.append("?§Ý???????\n");  
            sb.append("?§Ý???????\n");  
              
            FileImageCreator creator = new FileImageCreator(new SimpleDrawer(), "E:/img.jpeg");  
            creator.setWidth(150); //?????  
            creator.setHeight(100); //?????  
            creator.setLineNum(20); //??????????  
            creator.setFontSize(18); //?????§³  
            creator.setFontName("????");  
              
            //???????  
            creator.setRadian(30.0); //???????  
            creator.setRotateX(creator.getWidth()/5);  
            creator.setRotateY(creator.getHeight()*5/10);  
              
            creator.generateImage(sb.toString());  
            
            System.out.println("ok");  
              
        }catch(IOException ex){  
            ex.printStackTrace();  
        }  
    }  
}  