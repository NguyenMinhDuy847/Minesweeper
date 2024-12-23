package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import model.LoadData;

public class GameFrame extends JFrame
{
    private static final long serialVersionUID = 1L;

    private LoadData loadData;

    private GamePanel gamePanel;

    private JMenu menu;

    private JMenuItem basic;
    private JMenuItem normal;
    private JMenuItem hard;
    private JMenuItem newGame;
    private JMenuItem exit;

    public GameFrame(int w, int h, int boom)
    {
        setLoadData(new LoadData());

        editGameMenu();

        if(h == 8)
        {
            getBasic().setIcon(new ImageIcon(getLoadData().getListImage().get("symbol")));
        }
        else if(h == 16)
        {
            getNormal().setIcon(new ImageIcon(getLoadData().getListImage().get("symbol")));
        }
        else 
        {
            getHard().setIcon(new ImageIcon(getLoadData().getListImage().get("symbol")));
        }

        getBasic().addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                setVisible(false);
                new GameFrame(8, 8, 10);
            }
        });

        getNormal().addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                setVisible(false);
                new GameFrame(16, 16, 40);
            }
        });

        getHard().addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                setVisible(false);
                new GameFrame(16, 30, 99);
            }
        });

        getNewGame().addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                setVisible(false);
                new GameFrame(w, h, boom);
            }
        });

        getExit().addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                System.exit(0);
            }
        });

        setGamePanel(new GamePanel(w, h, boom, this));
        add(getGamePanel());

        pack();
        setIconImage(getLoadData().getListImage().get("icon"));
        setTitle("Minesweeper");
        
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new GameFrame(8, 8, 10);
    }

    //private methods to reduce function complexity

    private void editGameMenu()
    {
        setJMenuBar(new JMenuBar());
        setMenu(new JMenu("Game"));

        setNewGame(new JMenuItem("New Game"));
        setExit(new JMenuItem("Exit"));
        setBasic(new JMenuItem("Basic"));
        setNormal(new JMenuItem("Normal"));
        setHard(new JMenuItem ("Hard"));

        getJMenuBar().add(getMenu()); 

        getMenu().add(getNewGame());
        getMenu().addSeparator();

        getMenu().add(getBasic());
        getMenu().add(getNormal());
        getMenu().add(getHard());

        getMenu().addSeparator();
        getMenu().add(getExit());

        getNewGame().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        getBasic().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        getNormal().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
        getHard().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
        getExit().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));

        getNewGame().setText("New Game        ");
        getBasic().setText("Basic        ");
        getNormal().setText("Normal        ");
        getHard().setText("Hard        ");
        getExit().setText("Exit        ");
    }

    //Getters and Setters

    public LoadData getLoadData() 
    {
        return this.loadData;
    }

    public void setLoadData(LoadData loadData) 
    {
        this.loadData = loadData;
    }
    
    public GamePanel getGamePanel() 
    {
        return this.gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) 
    {
        this.gamePanel = gamePanel;
    }
    
    public JMenu getMenu() 
    {
        return this.menu;
    }

    public void setMenu(JMenu menu) 
    {
        this.menu = menu;
    }

    public JMenuItem getBasic() 
    {
        return this.basic;
    }

    public void setBasic(JMenuItem basic) 
    {
        this.basic = basic;
    }

    public JMenuItem getNormal() 
    {
        return this.normal;
    }

    public void setNormal(JMenuItem normal) 
    {
        this.normal = normal;
    }

    public JMenuItem getHard() 
    {
        return this.hard;
    }

    public void setHard(JMenuItem hard) 
    {
        this.hard = hard;
    }
    
    public JMenuItem getNewGame() 
    {
        return this.newGame;
    }

    public void setNewGame(JMenuItem newGame) 
    {
        this.newGame = newGame;
    }

    public JMenuItem getExit() 
    {
        return this.exit;
    }

    public void setExit(JMenuItem exit) 
    {
        this.exit = exit;
    }
}