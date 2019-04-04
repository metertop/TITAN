package com.com.haoyx.com.haoyx.FileUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileImageCreator extends AbstractImageCreator {  
    private String fileName;  
      
    public String getFileName() {  
        return fileName;  
    }  
  
    public void setFileName(String fileName) {  
        this.fileName = fileName;  
    }  
  
    public FileImageCreator(Drawer drawer){  
        super(drawer);  
    }  
      
    public FileImageCreator(Drawer drawer, String fileName){  
        super(drawer);  
        this.fileName = fileName;  
    }  
      
    @Override  
    protected void saveImage(BufferedImage image)throws IOException{  
        ImageIO.write(image, getFormatName(), new File(fileName));  
    }  
}  