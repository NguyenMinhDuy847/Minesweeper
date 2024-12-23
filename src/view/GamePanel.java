package view;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.World;

public class GamePanel extends JPanel implements MouseListener
{
    private static final long serialVersionUID = 1L;
    
    private PanelNotification p1;

    private PanelPlayer p2;
    
    private GameFrame gameFrame;

    private World world;
    
    private int w;
    private int h;
    private int boom;

    public GamePanel(int w, int h, int boom, GameFrame gameFrame)
    {
        setW(w);
        setH(h);
        setBoom(boom);
        setGameFrame(gameFrame);
        
        setWorld(new World(w, h, boom, this));
        
        setLayout(new BorderLayout(20, 20));
        
        setP1(new PanelNotification(this));
        setP2(new PanelPlayer(this));

        add(getP1(), BorderLayout.NORTH);
        add(getP2(), BorderLayout.CENTER);
    }
    
    @Override
    public void mouseReleased(MouseEvent e) 
    {
        getWorld().getButtonSmile().setState(ButtonSmile.NOW);
        getWorld().getButtonSmile().repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        getWorld().getButtonSmile().setState(ButtonSmile.WOW);
        getWorld().getButtonSmile().repaint();

        ButtonPlay[][] arrayButton = getP2().getArrayButton();

        for(int i = 0; i < arrayButton.length; i++)
        {
            for(int j = 0; j < arrayButton[i].length; j++)
            {
                if(e.getButton() == 1 && e.getSource() == arrayButton[i][j] && !getWorld().getArrayTick()[i][j])
                {
                    if(!getP1().getTime().isRunning())
                    {
                        getP1().getTime().start();
                    }

                    if(!getWorld().open(i, j))
                    {
                        if(getWorld().getIsComplete())
                        {
                            loseNotification();
                        }
                        else if(getWorld().getIsEnd())
                        {
                            winNotification();
                        } 
                    }
                }
                else if(e.getButton() == 3 && e.getSource() == arrayButton[i][j])
                {
                    getWorld().tick(i, j);
                }

                if(e.getClickCount() == 2 && e.getSource() == arrayButton[i][j] 
                    && getWorld().getArrayBoolean()[i][j] && !getWorld().clickDouble(i, j))
                    {
                        loseNotification();
                    }
            }
        }
    }

    //private methods to reduce function complexity

    private void loseNotification()
    {
        getP1().getTime().stop();
        getWorld().getButtonSmile().setState(ButtonSmile.LOSE);
        getWorld().getButtonSmile().repaint();

        int option = JOptionPane.showConfirmDialog(this, 
            "Would you like to try again?", "Notification", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION)
        {
            getGameFrame().setVisible(false);
            new GameFrame(getW(), getH(), getBoom());
        }
        else getWorld().fullTrue();                     
    }

    private void winNotification()
    {
        getP1().getTime().stop();
        getWorld().getButtonSmile().setState(ButtonSmile.WIN);
        getWorld().getButtonSmile().repaint();

        int option = JOptionPane.showConfirmDialog(this, 
            "You won, want to play a new game?", "Notification", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION)
        {
            getGameFrame().setVisible(false);
            new GameFrame(getW(), getH(), getBoom());
        }
        else getWorld().fullTrue();
    }

    //Getters and Setters

    public PanelNotification getP1() 
    {
        return this.p1;
    }

    public void setP1(PanelNotification p1) 
    {
        this.p1 = p1;
    }

    public PanelPlayer getP2() 
    {
        return this.p2;
    }

    public void setP2(PanelPlayer p2) 
    {
        this.p2 = p2;
    }
    
    public GameFrame getGameFrame() 
    {
        return this.gameFrame;
    }

    public void setGameFrame(GameFrame gameFrame) 
    {
        this.gameFrame = gameFrame;
    }
    
    public World getWorld() 
    {
        return this.world;
    }

    public void setWorld(World world) 
    {
        this.world = world;
    }

    public int getW() 
    {
        return this.w;
    }

    public void setW(int w) 
    {
        this.w = w;
    }
    
    public int getH() 
    {
        return this.h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getBoom() 
    {
        return this.boom;
    }

    public void setBoom(int boom) 
    {
        this.boom = boom;
    }

    //Unimplemented methods

    @Override
    public void mouseEntered(MouseEvent e) 
    {
        // TODO Auto-generated method stub
       
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        // TODO Auto-generated method stub
        
    }
}
