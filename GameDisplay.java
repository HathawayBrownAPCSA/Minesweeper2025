import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.Container;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Write a description of class GameDisplay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameDisplay extends JFrame implements MouseListener
{    
    private int numFlags = 0;
    private JLabel pnum;
    private boolean setYet;   // is the field set up?
    private MinesweepField field;

    public GameDisplay(){
        setYet = false;   // haven't yet set the bombs

        //set up default empty border (this is an attempt to remove the space between the panels. it is failing atm)
        EmptyBorder e = new EmptyBorder(0,0,0,0);

        //set up main panel
        JPanel pane = new JPanel();
        pane.setPreferredSize(new Dimension(700, 500));

        //set up the top part of the main panel
        JPanel p1 = new JPanel();
        p1.setPreferredSize(new Dimension(700, 100));
        p1.setLocation(0,0);
        p1.setBackground(Color.BLUE);

        //set up subpanel in p1 to house flags
        JPanel pflag = new JPanel();
        pflag.setPreferredSize(new Dimension(160, 70));
        pflag.setBackground(Color.RED);
        pflag.setLocation(0,0);

        //add label for num flags
        pnum = new JLabel("Flags left: " + numFlags);
        pnum.setForeground(Color.LIGHT_GRAY);
        pnum.setFont(new Font ("Arial", Font.BOLD, 20));
        pflag.add(pnum);
        p1.add(pflag);
        pflag.setLocation(0, 0);

        //set up subpanel in p2 to house time
        JPanel ptime = new JPanel();
        ptime.setPreferredSize(new Dimension(160, 70));
        ptime.setBackground(Color.ORANGE);
        p1.add(ptime);

        pane.add(p1);

        //set up the bottom part of the main panel
        JPanel p2 = new JPanel();
        p2.setBackground(Color.GREEN);
        p2.setPreferredSize(new Dimension(700, 400));
        p2.setLocation(0, 50);
        p2.setBorder(e);

        //sets up the actual field (yippee!)
        field = new MinesweepField();
        field.addMouseListeners(this);
        numFlags = field.getNumBombs();
        updateFlags();
        p2.add(field);

        pane.add(p2);
        Container c = getContentPane();
        c.add(pane, BorderLayout.CENTER);
    }

    /**MouseListeners have a lot of info that an ActionListener doesn't; the info we are interested 
     * in specifically is which button (left, middle, right) was pressed. MouseListener requires 
     * implementing all five of these methods, because of its ability to handle very complex mouse 
     * events, but for our purposes we only care about what happens when a mouse button is "clicked"
     * (meaning pressed AND released)
     **/
    @Override
    public void mouseClicked(MouseEvent e) {
        //this check is necessary because we will eventually add event listeners for other things as well
        if (e.getSource() instanceof MinesweepButton) {
            MinesweepButton b = (MinesweepButton)e.getSource();

            //get button gets the MOUSE button that was pressed, not the JButton
            if (e.getButton() == 1) {
                if (!setYet)
                {
                    // System.out.println("setting");
                    field.setBombs(b.getRow(), b.getCol());        
                    field.setNeighBombs();
                    setYet = true;
                }
                
                int nBombs = b.getNeighBombs();
                if (nBombs == -1) {
                    this.pnum.setText("BOMB EXPLODED :(");
                    executeGameOver(false);
                } 
                else if (b.getStatus() == MinesweepButton.CLOSED) {
                    openButton(b);
                }

            } else if (e.getButton() == 3) { //right click
                if (b.getStatus() == 1) {
                    b.setStatus(2);
                    decreaseNumFlags();
                    b.setBackground(Color.RED);
                    b.setText("F");
                } else if (b.getStatus() == 2) {
                    b.setStatus(1);
                    increaseNumFlags();
                    b.setBackground(null);
                    b.setText("");
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args){
        GameDisplay game = new GameDisplay();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(new Dimension(700,500));
        game.setVisible(true);
    }

    public void openZero (int row, int col)
    {
        for (int r = row - 1; r <= row + 1; r++)  {
            for (int c = col - 1; c <= col + 1; c++)  {
                
                if ((r >= 0 && r < field.getRows()) &&
                    (c >= 0 && c < field.getCols()))  {
                        MinesweepButton b = field.getButton(r, c);
                        if (b.getStatus() != MinesweepButton.OPEN)  {
                            openButton(b);
                        }
                    }
                    
            }
        }
    }
    
    public void openButton (MinesweepButton b)
    {
        b.setBackground(Color.LIGHT_GRAY);
        b.setStatus(MinesweepButton.OPEN);
        if (b.getNeighBombs() > 0)   {
            b.setText(Integer.toString(b.getNeighBombs()));
        }
        else {
            openZero(b.getRow(), b.getCol());
        }
        
        if (field.checkForWin()) {
            executeGameOver(true);
        }
    }
    
    public void executeGameOver(boolean win) {
        //System.out.println(win);
        String msg;
        if (win) {
            msg = "Congratulations! You won!";
        } else {
            msg = "You lost.";
        }
        msg += "\nTime: 35\nPlay again?";
        int chosen = JOptionPane.showConfirmDialog(null, msg, "End Screen", JOptionPane.YES_NO_OPTION);
        if (chosen == 0) {
            resetField();
        } else {
            Window parent = SwingUtilities.getWindowAncestor(field);
            parent.dispose();
        }
    }
    
    public void resetField() {
        setYet = false;
        field.resetButtons();
        numFlags = field.getNumBombs();
        updateFlags();
    }
    
    //these 2 will also contain handling for updating the flags-remaining display
    public void decreaseNumFlags() {
        numFlags--;
        updateFlags();
    }

    public void increaseNumFlags() {
        numFlags++;
        updateFlags();
    }

    public void updateFlags() {
        this.pnum.setText("Flags left: " + numFlags);
    }
}
