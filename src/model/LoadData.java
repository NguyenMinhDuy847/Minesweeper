package model;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.HashMap;

import javax.imageio.ImageIO;

public class LoadData 
{
    private HashMap<String, BufferedImage> listImage;

    public LoadData()
    {
        setListImage(new HashMap<String, BufferedImage>());
        try {
            BufferedImage image = ImageIO.read(new File("minesweeper.png"));
            
            getListImage().put("icon", image.getSubimage(0, 84, 25, 25));

            getListImage().put("empty", image.getSubimage(0, 39, 16, 16));

            getListImage().put("flag", image.getSubimage(16, 39, 16, 16));
            getListImage().put("Redboom", image.getSubimage(32, 39, 16, 16));
            getListImage().put("boomX", image.getSubimage(48, 39, 16, 16));
            getListImage().put("boom", image.getSubimage(64, 39, 16, 16));

            getListImage().put("b0", image.getSubimage(0, 23, 16, 16));
            getListImage().put("b1", image.getSubimage(16, 23, 16, 16));
            getListImage().put("b2", image.getSubimage(32, 23, 16, 16));
            getListImage().put("b3", image.getSubimage(48, 23, 16, 16));
            getListImage().put("b4", image.getSubimage(64, 23, 16, 16));
            getListImage().put("b5", image.getSubimage(80, 23, 16, 16));
            getListImage().put("b6", image.getSubimage(96, 23, 16, 16));
            getListImage().put("b7", image.getSubimage(112, 23, 16, 16));
            getListImage().put("b8", image.getSubimage(128, 23, 16, 16));

            getListImage().put("smile", image.getSubimage(0, 55, 26, 26));
            getListImage().put("smilePress", image.getSubimage(26, 55, 26, 26));
            getListImage().put("smilePressPlay", image.getSubimage(52, 55, 26, 26));
            getListImage().put("smileLose", image.getSubimage(78, 55, 26, 26));
            getListImage().put("smileWin", image.getSubimage(104, 55, 26, 26));
        
            getListImage().put("0", image.getSubimage(0, 0, 13, 23));
            getListImage().put("1", image.getSubimage(13, 0, 13, 23));
            getListImage().put("2", image.getSubimage(26, 0, 13, 23));
            getListImage().put("3", image.getSubimage(29, 0, 13, 23));
            getListImage().put("4", image.getSubimage(52, 0, 13, 23));
            getListImage().put("5", image.getSubimage(65, 0, 13, 23));
            getListImage().put("6", image.getSubimage(78, 0, 13, 23));
            getListImage().put("7", image.getSubimage(91, 0, 13, 23));
            getListImage().put("8", image.getSubimage(104, 0, 13, 23));
            getListImage().put("9", image.getSubimage(117, 0, 13, 23));
            getListImage().put("infinity", image.getSubimage(120, 0, 13, 23));

            getListImage().put("symbol", image.getSubimage(140, 49, 7, 7));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Getters and Setters

    public HashMap<String,BufferedImage> getListImage() 
    {
        return this.listImage;
    }

    public void setListImage(HashMap<String,BufferedImage> listImage) 
    {
        this.listImage = listImage;
    }
}
