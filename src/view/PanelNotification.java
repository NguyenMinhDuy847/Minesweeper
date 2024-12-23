package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelNotification extends JPanel
{
    private static final long serialVersionUID = 1L;

    private JPanel p1_1; 
    private JPanel p1_2;
    private JPanel p1_3; 

    private GamePanel game;

    private Timer time;

    private int nowTime;

    public PanelNotification(GamePanel game)
    {
        setGame(game);
        setLayout(new BorderLayout());

        setP1_1(new JPanel());
        setP1_2(new JPanel());
        setP1_3(new JPanel());

        setBorder(BorderFactory.createLoweredBevelBorder());
        add(getP1_1(), BorderLayout.WEST);
        add(getP1_2(), BorderLayout.EAST);
        add(getP1_3(), BorderLayout.CENTER);

        getGame().getWorld().setButtonSmile(new ButtonSmile(this));

        getGame().getWorld().setLabelTime(new LabelNumber(this, "000"));

        getGame().getWorld().setLabelBoom(new LabelNumber(this, "000"));
        updateLabelBoom();
        
        getP1_1().add(getGame().getWorld().getLabelBoom());
        getP1_2().add(getGame().getWorld().getLabelTime());

        getGame().getWorld().getLabelTime().setNumber("000");
        
        setTime(new Timer(1000, new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    setNowTime(getNowTime() + 1);
                    updateLabelTime();
                }
            }));
        
        getP1_3().add(getGame().getWorld().getButtonSmile());

        getGame().getWorld().getButtonSmile().addMouseListener(new MouseListener() 
        {

            @Override
            public void mouseClicked(MouseEvent e) 
            {
                getGame().getWorld().getButtonSmile().setState(ButtonSmile.NOW);
                getGame().getWorld().getButtonSmile().repaint();

                int option = JOptionPane.showConfirmDialog(null, 
                    "Want to play a new game?", "notification", JOptionPane.YES_NO_OPTION);
                    if(option == JOptionPane.YES_OPTION)
                    {
                        getGame().getGameFrame().setVisible(false);
                        new GameFrame(getGame().getW(), getGame().getH(), getGame().getBoom());
                    }
            }

            @Override
            public void mousePressed(MouseEvent e) 
            {
                if(getGame().getWorld().getIsEnd() || getGame().getWorld().getIsComplete())
                {
                    getGame().getGameFrame().setVisible(false);
                    new GameFrame(getGame().getW(), getGame().getH(), getGame().getBoom());
                }
                else
                {
                    getGame().getWorld().getButtonSmile().setState(ButtonSmile.PRESS);
                    getGame().getWorld().getButtonSmile().repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) 
            {
                // TODO Auto-generated method stub
            }

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
        });
    }

    public void updateLabelTime()
    {
        if(getNowTime() > 999)
        {
            getGame().getWorld().getLabelTime().setNumber("infinity");
        }
        else
        {
            String cTime = String.valueOf(getNowTime());
            if(cTime.length() == 1)
            {
                getGame().getWorld().getLabelTime().setNumber("00" + cTime);
            }
            else if(cTime.length() == 2)
            {
                getGame().getWorld().getLabelTime().setNumber("0" + cTime);
            }
            else
            {
                getGame().getWorld().getLabelTime().setNumber(cTime);
            }
        }

        getGame().getWorld().getLabelTime().repaint();
    }

    public void updateLabelBoom()
    {
        String boom = String.valueOf(game.getBoom() - game.getWorld().getFlag());

        switch (boom.length()) {
            case 1:
                getGame().getWorld().getLabelBoom().setNumber("00" + boom);
                getGame().getWorld().getLabelBoom().repaint();
                break;
            case 2:
                getGame().getWorld().getLabelBoom().setNumber("0" + boom);
                getGame().getWorld().getLabelBoom().repaint();
                break;
            default:
                break;
        }
    }

    //Getters and Setters
        
    public JPanel getP1_1() 
    {
        return this.p1_1;
    }

    public void setP1_1(JPanel p1_1) 
    {
        this.p1_1 = p1_1;
    }

    public JPanel getP1_2() 
    {
        return this.p1_2;
    }

    public void setP1_2(JPanel p1_2) 
    {
        this.p1_2 = p1_2;
    }

    public JPanel getP1_3() 
    {
        return this.p1_3;
    }

    public void setP1_3(JPanel p1_3) 
    {
        this.p1_3 = p1_3;
    }

    public GamePanel getGame() 
    {
        return this.game;
    }

    public void setGame(GamePanel game) 
    {
        this.game = game;
    }    
    
    public Timer getTime() 
    {
        return this.time;
    }

    public void setTime(Timer time) 
    {
        this.time = time;
    }

    public int getNowTime() 
    {
        return this.nowTime;
    }

    public void setNowTime(int nowTime) 
    {
        this.nowTime = nowTime;
    }
}
