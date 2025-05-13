
/**
 * Write a description of class ButtonTester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ButtonTester
{
 
    private MinesweepButton [][] field;
    
    public static int ROWS = 8;
    public static int COLS = 10;
    public static int BOMB_COUNT = 10;
    
    public ButtonTester()
    {
        field = new MinesweepButton[ROWS][COLS];
        
        for (int r = 0; r < ROWS; r++)
        {
            for (int c = 0; c < COLS; c++)
            {
                field[r][c] = new MinesweepButton();
            }
        }
    }
    
    public void printField()
    {
        for (int r = 0; r < ROWS; r++)
        {
            for (int c = 0; c < COLS; c++)
            {
                System.out.print(field[r][c].getNeighBombs() + "\t");
            }
            System.out.println();
        }
    }
    
    public void setBombs(int row, int col)
    {
        for (int b = 1; b <= BOMB_COUNT; b++)
        {
            int r = (int)(Math.random() * ROWS);
            int c = (int)(Math.random() * COLS);
            
            while (field[r][c].getNeighBombs() == -1)
            {
                r = (int)(Math.random() * ROWS);
                c = (int)(Math.random() * COLS);
            }
            
            field[r][c].setNeighBombs(-1);
        }
    }
    
    public void setNeighBombs()
    {
        for (int r = 0; r < ROWS; r++)
        {
            for (int c = 0; c < COLS; c++)
            {
                if (field[r][c].getNeighBombs() > -1)
                {
                    field[r][c].setNeighBombs(countNeighBombs(r, c));
                }
            }
            System.out.println();
        }        
    }
    
    public int countNeighBombs(int row, int col)
    {
        int count = 0;
        for (int r = row - 1; r <= row + 1; r++)
        {
            for (int c = col - 1; c <= col + 1; c++)
            {
                if (r >= 0 && r < ROWS && c >= 0 && c < COLS &&
                    (r != row || c != col) && field[r][c].getNeighBombs() == -1)
                {
                    count++;
                }
                
            }
        }
        return count;
    }
    
    
    public static void main (String[] args)
    {
        ButtonTester game = new ButtonTester();
        
        
        int row = 3;
        int col = 5;
        
        game.setBombs(row, col);
        game.printField();
        System.out.println();
        
        game.setNeighBombs();
        game.printField();
        
        
    }
}
