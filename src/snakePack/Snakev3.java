package snakePack;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;



public class Snakev3{
  public Snakev3() {}
  
    private static void createAndShowGUI(){
        JFrame f = new JFrame("Moonssnake!");

        f.setDefaultCloseOperation(3);
        f.add(new MyPanel(f));
        f.setSize(670, 670);
        f.setVisible(true);
    }
  
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }
}
