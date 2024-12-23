package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;

public class ButtonPlay extends JButton
{
    private static final long serialVersionUID = 1L;
    
    private PanelPlayer player;

    private int number;

    public ButtonPlay(PanelPlayer player)
    {
        setNumber(-1);
        setPlayer(player);
        setPreferredSize(new Dimension(30, 30));
    }

    @Override
    public void paint(Graphics g)
    {
        switch (getNumber()) 
        {
            case -1:
                g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get("empty"), 0, 0,
                    getPreferredSize().width, getPreferredSize().height, null);
                break;

            case 0:
                g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get("b0"), 0, 0,
                    getPreferredSize().width, getPreferredSize().height, null);
                break;
        
            case 1:
                g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get("b1"), 0, 0,
                    getPreferredSize().width, getPreferredSize().height, null);
                break;

            case 2:
                g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get("b2"), 0, 0,
                    getPreferredSize().width, getPreferredSize().height, null);
                break;

            case 3:
                g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get("b3"), 0, 0,
                    getPreferredSize().width, getPreferredSize().height, null);
                break;

            case 4:
                g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get("b4"), 0, 0,
                    getPreferredSize().width, getPreferredSize().height, null);
                break;

            case 5:
                g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get("b5"), 0, 0,
                    getPreferredSize().width, getPreferredSize().height, null);
                break;

            case 6:
                g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get("b6"), 0, 0,
                    getPreferredSize().width, getPreferredSize().height, null);
                break;

            case 7:
                g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get("b7"), 0, 0,
                    getPreferredSize().width, getPreferredSize().height, null);
                break;

            case 8:
                g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get("b8"), 0, 0,
                    getPreferredSize().width, getPreferredSize().height, null);
                break;
            
                
            case 9:
                g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get("flag"), 0, 0,
                    getPreferredSize().width, getPreferredSize().height, null);
                break;

            case 10:
                g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get("Redboom"), 0, 0,
                    getPreferredSize().width, getPreferredSize().height, null);
                break;

            case 11:
                g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get("boomX"), 0, 0,
                    getPreferredSize().width, getPreferredSize().height, null);
                break;
            
            case 12:
                g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get("boom"), 0, 0,
                    getPreferredSize().width, getPreferredSize().height, null);
                break;

            default:
                break;
        }
    }

    //Getters and Setters

    public PanelPlayer getPlayer() 
    {
        return this.player;
    }

    public void setPlayer(PanelPlayer player) 
    {
        this.player = player;
    }

    public int getNumber() 
    {
        return this.number;
    }

    public void setNumber(int number) 
    {
        this.number = number;
    }
    
}
