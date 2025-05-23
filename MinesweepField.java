import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;

/**
 * Write a description of class ButtonTester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MinesweepField extends JPanel
{
 
    private MinesweepButton [][] field;
    public static Font defaultFont = new Font("Arial", Font.BOLD, 12);
    public static Font flagFont = new Font ("Arial", Font.BOLD, 10);
    
    public static int ROWS = 8;
    public static int COLS = 10;
    public static int BOMB_COUNT = 10;
    
    public MinesweepField()
    {
        super(new GridLayout(ROWS, COLS));
        super.setPreferredSize(new Dimension(COLS*40,ROWS*40));
        field = new MinesweepButton[ROWS][COLS];
        
        for (int r = 0; r < ROWS; r++)
        {
            for (int c = 0; c < COLS; c++)
            {
                MinesweepButton b = new MinesweepButton(r, c);
                b.setFont(defaultFont);
                field[r][c] = b;
                add(b);
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
    
    public MinesweepButton getButton (int row, int col)
    {
        return field[row][col];
    }
    
    public int getRows()
    {    return ROWS;  }
    public int getCols()
    {    return COLS;  }
    
    public void setBombs(int row, int col)
    {
        //reset all cells to not have a bomb
        for (MinesweepButton[] r : field) {
            for (MinesweepButton b : r) {
                b.setNeighBombs(0);
            }
        }
        
        for (int b = 1; b <= BOMB_COUNT; b++)
        {
            int r = (int)(Math.random() * ROWS);
            int c = (int)(Math.random() * COLS);
            
            while ((field[r][c].getNeighBombs() == -1) ||
                   ((Math.abs(r - row) <= 1) && (Math.abs(c - col) <= 1)))
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
            // System.out.println();
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
    
    public void resetFieldWithSize(int r, int c) {
        
    }
    
    public int getNumBombs() {
        return BOMB_COUNT;
    }
    
    public void addMouseListeners(GameDisplay g) {
        for (MinesweepButton[] r : field) {
            for (MinesweepButton b : r) {
                b.addMouseListener(g);
            }
        }
    }
    
    public boolean checkForWin() {
        for (MinesweepButton[] r : field) {
            for (MinesweepButton b : r) {
                //if it isn't a bomb and it's not open, then nobody has won yet
                if (b.getNeighBombs() != -1 && b.getStatus() != MinesweepButton.OPEN) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void resetButtons() {
        for (MinesweepButton[] r : field) {
            for (MinesweepButton b : r) {
                b.setStatus(MinesweepButton.CLOSED);
                b.setBackground(null);
                b.setText("");
            }
        }
    }
    
    public static void main (String[] args)
    {
        MinesweepField game = new MinesweepField();
        
        
        int row = 3;
        int col = 5;
        
        game.setBombs(row, col);
        game.printField();
        System.out.println();
        
        game.setNeighBombs();
        game.printField();
        
        
    }
}
