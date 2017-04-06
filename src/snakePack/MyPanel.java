package snakePack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MyPanel extends JPanel{
    private GameController gameController;
    private Thread gameThread;
    
    public MyPanel(JFrame frame){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        add(jPanel);
        
        setBorder(BorderFactory.createLineBorder(Color.black));

        prepareAndStartGame();
        
        frame.setFocusable(true);
        frame.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent evt) {
                if(evt.getKeyCode() == KeyEvent.VK_LEFT){
                    System.out.println("left klik");
                    gameController.makeLeftTurn();
                }

                if(evt.getKeyCode() == KeyEvent.VK_RIGHT){
                    System.out.println("right klik");
                    gameController.makeRightTurn();
                }   
            }
        });
    }
    
    private void prepareAndStartGame(){
        gameController = new GameController(this);
                
        gameThread = new Thread(gameController);
        gameThread.start();
    }
    
    public void doRepaint(){
        repaint();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(350, 300);
    }
  
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameController.drawBoard(g);
    }
}
