package control;

import java.util.Random;

import view.ButtonPlay;
import view.ButtonSmile;
import view.GamePanel;
import view.LabelNumber;

public class World 
{
    private ButtonPlay[][] arrayButton;

    private int[][] arrayBoom; //boom mark is -1

    private boolean[][] arrayBoolean;
    private boolean[][] arrayTick;

    private boolean isComplete;
    private boolean isEnd;
    private boolean hasBoom;

    private ButtonSmile buttonSmile; 

    private LabelNumber labelTime;
    private LabelNumber labelBoom;

    private int boom;
    private int flag;

    private GamePanel game;

    public World(int w, int h, int boom, GamePanel game)
    {
        setGame(game);
        setBoom(boom);

        setArrayButton(new ButtonPlay[w][h]);
        setArrayBoom(new int[w][h]);
        setArrayBoolean(new boolean[w][h]);
        setArrayTick(new boolean[w][h]);

        createArrayBoom(w, h, boom);
        numberedBoom();
    }

    public void createArrayBoom(int w, int h, int boom)
    {
        Random rand = new Random();
        int locationX = rand.nextInt(w);
        int locationY = rand.nextInt(h);
        getArrayBoom()[locationX][locationY] = -1; //Boom' number is -1

        int count = 1;
        while(count != boom)
        {
            locationX = rand.nextInt(w);
            locationY = rand.nextInt(h);

            if(getArrayBoom()[locationX][locationY] != -1)
            {
                getArrayBoom()[locationX][locationY] = -1;

                count = 0;
                for(int i = 0; i < getArrayBoom().length; i++)
                {
                    for(int j = 0; j < getArrayBoom()[i].length; j++)
                    {
                        if(getArrayBoom()[i][j] == -1)
                            count++;
                    }
                }
            }
        }
    }

    public boolean checkWin()
    {
        int count = 0;

        for(int i = 0; i < getArrayBoolean().length; i++)
        {
            for(int j = 0; j < getArrayBoolean()[i].length; j++)
            {
                if(!getArrayBoolean()[i][j])
                {
                    count++;
                }
            }
        }

        return count == getBoom(); 
    }

    public void numberedBoom()
    {
        for(int i = 0; i < getArrayBoom().length; i++)
        {
            for(int j = 0; j < getArrayBoom()[i].length; j++)
            {
                if(getArrayBoom()[i][j] == 0)
                {
                    markBoomCount(i, j);
                }
            }
        }
    }

    public boolean clickDouble(int i, int j)
    {
        setHasBoom(false);

        for(int k = i - 1; k <= i + 1; k++)
        {
            for(int l = j - 1; l <= j + 1; l++)
            {
                if(k >= 0 && k <= (getArrayBoom().length - 1) && l >= 0 && l <= (getArrayBoom()[i].length - 1) 
                    && !getArrayBoolean()[k][l])
                {
                    if(getArrayTick()[k][l])
                    {
                        continue;
                    }
                    
                    reduceClickDoubleComplexity(k, l);             
                }                  
            }
        }

        if(getHasBoom())
        {
            showRemainBooms(i, j);
            return false;
        } 
        return true;
    }

    public void tick(int i, int j)
    {
        if(!getArrayBoolean()[i][j])
        {
            if(getArrayTick()[i][j])
            {
                setFlag(getFlag() - 1);
                getArrayTick()[i][j] = false;
                getArrayButton()[i][j].setNumber(-1);
                getArrayButton()[i][j].repaint();
                getGame().getP1().updateLabelBoom();
            }
            else if(getFlag() < getBoom())
            {
                setFlag(getFlag() + 1);
                getArrayTick()[i][j] = true;
                getArrayButton()[i][j].setNumber(9);
                getArrayButton()[i][j].repaint();
                getGame().getP1().updateLabelBoom();
            }
        }
    }
    
    public boolean open(int i, int j)
    {
        if(!getIsComplete() && !getIsEnd())
        {
            if(!getArrayBoolean()[i][j])
            {
                checkArrayBoolean(i, j);
            }

            if(getArrayBoom()[i][j] == -1)
            {
                getArrayButton()[i][j].setNumber(10);
                getArrayButton()[i][j].repaint();
                setIsComplete(true);
                showRemainBooms(i, j);

                return false;
            }
            else
            { 
                conditionSatisfy();
                return true;
            }
        }
        else return false;
    }   

    public void fullTrue()
    {
        for(int i = 0; i < getArrayBoolean().length; i++)
        {
            for(int j = 0; j < getArrayBoolean()[i].length; j++)
            {
                if(!getArrayBoolean()[i][j])
                {
                    getArrayBoolean()[i][j] = true;
                }
            }
        }
    }

    //private methods to reduce function complexity

    private void markBoomCount(int i, int j)
    {
        int boomCount = 0;
        for(int k = i - 1; k <= i + 1; k++)
        {
            for(int l = j - 1; l <= j + 1; l++)
            {
                if(k >= 0 && k <= (getArrayBoom().length - 1) && l >= 0 && l <= (getArrayBoom()[i].length - 1) 
                    && getArrayBoom()[k][l] == -1)
                    {
                        boomCount++;
                    }    
            }
        }
        getArrayBoom()[i][j] = boomCount;
    }

    private boolean checkArrayBoolean(int i, int j)
    {
        if(getArrayBoom()[i][j] == 0)
        {
            getArrayBoolean()[i][j] = true;
            getArrayButton()[i][j].setNumber(0);
            getArrayButton()[i][j].repaint();
            
            conditionSatisfy();
            recursionOpen(i, j);
        }
        else
        {
            int number = getArrayBoom()[i][j]; 

            if (number != -1) 
            {
                getArrayBoolean()[i][j] = true;

                getArrayButton()[i][j].setNumber(number);
                getArrayButton()[i][j].repaint();

                conditionSatisfy();

                return true;
            }
        }
        return false;
    }

    private void recursionOpen(int i, int j)
    {
        for(int k = i - 1; k <= i + 1; k++)
        {
            for(int l = j - 1; l <= j + 1; l++)
            {
                if(k >= 0 && k <= (getArrayBoom().length - 1) && l >= 0 && l <= (getArrayBoom()[i].length - 1) 
                    && !getArrayBoolean()[k][l])
                {
                    open(k, l);
                    conditionSatisfy();
                }          
            }
        }
    }

    private void showRemainBooms(int i, int j)
    {
        for(int a = 0; a < getArrayBoolean().length; a++)
        {
            for(int b = 0; b < getArrayBoolean()[i].length; b++)
            {
                if(getArrayBoom()[a][b] == -1 && !getArrayBoolean()[a][b] && (a != i || b != j))
                {
                    getArrayButton()[a][b].setNumber(12);
                    getArrayButton()[a][b].repaint();
                }
            }
        }
    }

    private boolean conditionSatisfy()
    {
        if(checkWin())
        {
            setIsEnd(true);

            return false;
        }
        else return true;
    }

    private void reduceClickDoubleComplexity(int k, int l)
    {
        if(getArrayBoom()[k][l] == -1)
        {
            setHasBoom(true);
            getArrayButton()[k][l].setNumber(11);
            getArrayButton()[k][l].repaint();
            getArrayBoolean()[k][l] = true;
        }
        
        else if(!getArrayBoolean()[k][l])
        {
            if(getArrayBoom()[k][l] == 0)
            {
                open(k, l);
            }
            else
            {
                getArrayButton()[k][l].setNumber(getArrayBoom()[k][l]);
                getArrayButton()[k][l].repaint();
                getArrayBoolean()[k][l] = true;
            }
        }
    }

    //Getters and Setters
    
    public ButtonPlay[][] getArrayButton() 
    {
        return this.arrayButton;
    }

    public void setArrayButton(ButtonPlay[][] arrayButton) 
    {
        this.arrayButton = arrayButton;
    }
    
    public int[][] getArrayBoom() 
    {
        return this.arrayBoom;
    }

    public void setArrayBoom(int[][] arrayBoom) 
    {
        this.arrayBoom = arrayBoom;
    }
    
    public boolean[][] getArrayBoolean() 
    {
        return this.arrayBoolean;
    }

    public void setArrayBoolean(boolean[][] arrayBoolean) 
    {
        this.arrayBoolean = arrayBoolean;
    }
    
    public boolean[][] getArrayTick() 
    {
        return this.arrayTick;
    }

    public void setArrayTick(boolean[][] arrayTick) 
    {
        this.arrayTick = arrayTick;
    }

    public boolean getIsComplete() 
    {
        return this.isComplete;
    }

    public void setIsComplete(boolean isComplete) 
    {
        this.isComplete = isComplete;
    }
    
    public boolean getIsEnd() 
    {
        return this.isEnd;
    }

    public void setIsEnd(boolean isEnd) 
    {
        this.isEnd = isEnd;
    }
    
    public boolean getHasBoom() 
    {
        return this.hasBoom;
    }

    public void setHasBoom(boolean hasBoom) 
    {
        this.hasBoom = hasBoom;
    }

    public ButtonSmile getButtonSmile() 
    {
        return this.buttonSmile;
    }

    public void setButtonSmile(ButtonSmile buttonSmile) 
    {
        this.buttonSmile = buttonSmile;
    }
      
    public LabelNumber getLabelTime() 
    {
        return this.labelTime;
    }

    public void setLabelTime(LabelNumber labelTime) 
    {
        this.labelTime = labelTime;
    }

    public LabelNumber getLabelBoom() 
    {
        return this.labelBoom;
    }

    public void setLabelBoom(LabelNumber labelBoom) 
    {
        this.labelBoom = labelBoom;
    }
    
    public int getBoom() 
    {
        return this.boom;
    }

    public void setBoom(int boom) 
    {
        this.boom = boom;
    }
    
    public int getFlag() 
    {
        return this.flag;
    }

    public void setFlag(int flag) 
    {
        this.flag = flag;
    }
    
    public GamePanel getGame() 
    {
        return this.game;
    }

    public void setGame(GamePanel game) 
    {
        this.game = game;
    }
}
