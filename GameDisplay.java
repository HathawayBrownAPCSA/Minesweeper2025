import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.Container;
import javax.swing.border.EmptyBorder;

/**
 * Write a description of class GameDisplay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameDisplay
{    
    public GameDisplay(){
        JFrame win = new JFrame();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setSize(new Dimension(700,500));
        
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
        JLabel pnum = new JLabel("Flags left: 5!");
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
        pane.add(p2);
        
        //add the main panel to the window and make it visible
        win.add(pane);
        win.setVisible(true);
    }
    
    public static void main(String[] args){
        GameDisplay game = new GameDisplay();
    }
    
    public void decreaseNumFlags() {
    
    }
    
    public void increaseNumFlags() {
    
    }
}
