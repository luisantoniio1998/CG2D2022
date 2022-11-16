package TestesProjeto2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class gp extends JFrame{

	public static void main(String[] args) {
		
	    JFrame frame = new gp();
	    frame.setTitle("Hello 2D Frame");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    JPanel panel = new gpPanel();
	    frame.getContentPane().add(panel);
	    frame.pack();
	    frame.setVisible(true);
		
	}
}

class gpPanel extends JPanel{
	
	public gpPanel() {
	    setPreferredSize(new Dimension(640, 480));
	}
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D)g;
	    
	    g2.setColor(Color.BLACK);
	    
    	GeneralPath path = new GeneralPath();
    	path.moveTo(-2f, 0f);
    	path.quadTo(0f, 2f, 2f, 0f);
    	path.quadTo(0f, -2f, -2f, 0f);
    	path.moveTo(-1f, 0.5f);
    	path.lineTo(-1f, -0.5f);
    	path.lineTo(1f, 0.5f);
    	path.lineTo(1f, -0.5f);
    	path.closePath();
    	path.setWindingRule(GeneralPath.WIND_NON_ZERO);
    	g2.fill(path);
	    
	  }
	
}