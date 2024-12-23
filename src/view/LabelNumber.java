package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;

public class LabelNumber extends JLabel
{
    private static final long serialVersionUID = 1L;
    
    private PanelNotification player;

    private String number;

    public LabelNumber(PanelNotification player, String number)
    {
        setPlayer(player);
        setNumber(number);
        setPreferredSize(new Dimension(78, 46));
    }

    @Override
    public void paint(Graphics g)
    {
        if(getNumber().equals("infinity"))
        {
            g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get("infinity"),
            0, 0, 26, 46, null);
            g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get("infinity"),
            26, 0, 26, 46, null);
            g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get("infinity"),
            52, 0, 26, 46, null);
        }
        else
        {
            g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get(String.valueOf(getNumber().charAt(0))),
            0, 0, 26, 46, null);
            g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get(String.valueOf(getNumber().charAt(1))),
            26, 0, 26, 46, null);
            g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get(String.valueOf(getNumber().charAt(2))),
            52, 0, 26, 46, null);
        }
    }

    //Getters and Setters

    public PanelNotification getPlayer() 
    {
        return this.player;
    }

    public void setPlayer(PanelNotification player) 
    {
        this.player = player;
    }  
    
    public String getNumber() 
    {
        return this.number;
    }

    public void setNumber(String number) 
    {
        this.number = number;
    } 
}
