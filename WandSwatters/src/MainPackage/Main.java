package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//author Dawson
//updated

public class Main extends JFrame {
    static Main theMain;
    private GameLoop playarea = null;
    public Main() {
        super(); //This calls the constructor of the parent class JFrame
        this.setResizable(false);
        //this.setUndecorated(true);
        this.setBounds(new Rectangle(100, 10, 925, 750));
        this.setMinimumSize(new Dimension(1005, 750));
        this.setMaximumSize(new Dimension(1005, 750));
        
        this.setContentPane(getJContentPane());
        this.setTitle("Wand Swatters");
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                playarea.keyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                playarea.keyReleased(evt);
            }
        });
    }
    
    public static void main(String[] args) {
        Main thisClass = new Main();
        thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisClass.setVisible(true);
        theMain = thisClass;
    }

    private JPanel getJContentPane() {
        JPanel jContentPane = new JPanel();
        jContentPane.setLayout(new BorderLayout());
        playarea = new GameLoop();
        jContentPane.add(playarea, BorderLayout.CENTER);
        return jContentPane;
    }
}