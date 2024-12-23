package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ButtonSmile extends JPanel
{
    private static final long serialVersionUID = 1L;
    
    public static final int WIN = 0;
    public static final int LOSE = 1;
    public static final int PRESS = 2;
    public static final int WOW = 3;
    public static final int NOW = 4;

    private PanelNotification player;

    private int state;

    public ButtonSmile(PanelNotification player)
    {
        setPlayer(player);
        setPreferredSize(new Dimension(50, 50));

        setState(NOW);
    }

    @Override
    public void paint(Graphics g)
    {
        switch (getState()) 
        {
            case WIN:
                g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get("smileWin"), 0, 0, 
                    getPreferredSize().width, getPreferredSize().height, null);
                break;
        
            case LOSE:
                g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get("smileLose"), 0, 0, 
                    getPreferredSize().width, getPreferredSize().height, null);
                break;

            case PRESS:
                g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get("smilePress"), 0, 0, 
                    getPreferredSize().width, getPreferredSize().height, null);
                break;

            case WOW:
                g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get("smilePressPlay"), 0, 0, 
                    getPreferredSize().width, getPreferredSize().height, null);
                break;

            case NOW:
                g.drawImage(getPlayer().getGame().getGameFrame().getLoadData().getListImage().get("smile"), 0, 0, 
                    getPreferredSize().width, getPreferredSize().height, null);       
                break;
                
            default:
                break;
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
    
    public int getState() 
    {
        return this.state;
    }

    public void setState(int state) 
    {
        this.state = state;
    }
    
}
