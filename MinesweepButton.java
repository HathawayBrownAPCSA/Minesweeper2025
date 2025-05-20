
/**
 * The Buttons to play Minesweeper
 *
 * @author  APCS-A @ HB 2025
 * @version 2025-05-11
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Window;

public class MinesweepButton extends JButton
{
    private int status;       // options below
    private int neighBombs;   // -1 if it's a bomb
    private int row;
    private int col;

    public static final int OPEN = 0;
    public static final int CLOSED = 1;
    public static final int FLAGGED = 2;
    
    /**
     * Constructor for objects of class MinesweepButton
     */
    public MinesweepButton()
    {
        status = CLOSED;
        neighBombs = 0;
    }
    
    public MinesweepButton(int r, int c)
    {
        status = CLOSED;
        neighBombs = 0;
        row = r;
        col = c;
    }
    
    // ------------------------------------------------------
    //   Accessor methods  (getters)
    // ------------------------------------------------------
    
    public int getNeighBombs()
    {   return neighBombs;  }
    
    public int getStatus()
    {   return status;   }
    
    public int getRow()
    {   return row;      }
    
    public int getCol()
    {   return col;      }
    
    
    // ------------------------------------------------------
    //   Mutator methods   (seters)
    // ------------------------------------------------------
    
    /* Set the number of neighboring bombs  */
    public void setNeighBombs (int n)
    {   neighBombs = n;  }
    
    public void setStatus (int s)
    {  
        status = s;
        if (status == FLAGGED)
        {
            setFont(MinesweepField.flagFont);
        }
        else
        {
            setFont(MinesweepField.defaultFont);
        }
    }

}
