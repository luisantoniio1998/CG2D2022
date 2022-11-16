package projeto2d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpPanel extends JPanel implements MouseListener{

	HelpPanel helpPanel;
	BufferedImage bi;
	
	public HelpPanel() {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(700, 800));
		add(new JLabel("Help"));
		addMouseListener(this);
		
	   
		//=============== MANDELBROT CODE ======================
		
		int w = 700;
	    int h = 800;
	    setPreferredSize(new Dimension(w, h));
	    bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    WritableRaster raster = bi.getRaster();
	    int[] rgb = new int[6];
	    float xmin = -3;
	    float ymin = -5;
	    float xscale = 7f/w;
	    float yscale = 7f/h;
	    for (int i = 0; i < h; i++) {
	      for (int j = 0; j < w; j++) {
	        float cr = xmin + j * xscale;
	        float ci = ymin + i * yscale;
	        int count = iterCount(cr, ci);
	        rgb[0] = (count & 0x04) << 5;
	        rgb[1] = ((count >> 2) & 0x04) << 3;
	        rgb[2] = ((count >> 3) & 0x04) << 4;
	        rgb[3] = ((count >> 5) & 0x02) << 2;
	        rgb[4] = ((count >> 10) & 0x04) << 5;
	        rgb[5] = ((count >> 5) & 0x07) << 7;
	        raster.setPixel(j, i, rgb);
	      }
	    }  
	}
	
	  private int iterCount(float cr, float ci) {
		    int max = 50;
		    float zr = 0;
		    float zi = 0;
		    float lengthsq = 0;
		    int count = 0;
		    while ((lengthsq < 4.0) && (count < max)) {
		      float temp = zr * zr - zi * zi + cr;
		      zi = 2 * zr * zi + ci;
		      zr = temp;
		      lengthsq = zr * zr + zi * zi;
		      count++;
		    }
		    return max-count;
		  }
	
	  // ========== GOES BACK TO MENU =================
		public void menuOn() {
		Projeto2D.cardlayout.show(Projeto2D.mainPanel, "painter");
	}
		
	public void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 Graphics2D g2 = (Graphics2D)g;
		 
		 //======== FONTS ================
		 Font font2 = new Font("Courier", Font.PLAIN, 30);
		 Font fnt2 = new Font("arial", 1, 30);
		 Font font1 = new Font("Courier", Font.BOLD, 20);
		 
		 //g2.drawImage(bi, 0, 0 , this);
		 
		 //Criacao de imagem
		 
		 drawCircle(0, 0, 600, g);
		 
		 drawTree(g, 200, 800, -90 ,10);
		 
		 //========= DESCRIPTION USING FONT AND TEXT ===============
		 g2.setColor(Color.WHITE);
		 g2.setFont(font2);
		 g2.drawString("This is a try to clone the old", 100, 150);
		 g2.drawString("Rock Paper Scissors game.", 120, 200);
		 g2.drawString("You know What to do!", 170, 250);
		 
		 //============= BUTTON USING PRIMITIVE RECT AND TEXT AND FONTS ============
		 g2.setFont(fnt2);
	   	 g2.drawRect(250 , 568, 200, 64);
      	 g2.drawString("Back", 320, 608);    
      	 
      	 g2.setFont(font1);
		 g2.drawString("Click your choice with the mouse", 170, 320);
		 g2.drawString("In the upper side of the window", 170, 370);    
		 		    
		 g2.drawString("Or type R for Rock ", 250, 420);
		 g2.drawString(" P for Paper ", 250, 470);
		 g2.drawString(" or S for Scissors", 250, 520);
		 
		 //================ CLIPPING =================================
		 //NOTE : IT DOESNT APPEAR TO BE CLIPPED BECAUSE I DRAW THE STRINGS OUTSIDE AND INSIDE OF THE GP 
		 //USING THE SAME COORDINATES
		 GeneralPath gp1 = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
		    gp1.moveTo(200, 100);
		    gp1.quadTo(350, 50, 500, 100);
		    gp1.lineTo(500, 300);
		    gp1.quadTo(350, 250, 200, 300);
		    gp1.closePath();
		    g2.clip(gp1);
		    g2.setColor(Color.GRAY);
		    g2.fill(gp1);
		    
		    g2.setFont(font2);
		    g2.setColor(Color.WHITE);
		    g2.drawString("This is a try to clone the old", 100, 150);
		    g2.drawString("Rock Paper Scissors game", 120, 200);
		    g2.drawString("You know what to do", 170, 250);
      	 
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(mouseOver(mx, my, 250, 568, 200, 64)) {
			menuOn();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	//================ DRAW CIRCLES INSIDE CIRCLES ====================
	//Sinthesis of an image like the mandelbrot one 
	void drawCircle(float x, float y, float radius, Graphics g) {
		Graphics2D g2 = (Graphics2D) g; 
		Ellipse2D eli2d = new Ellipse2D.Float(x, y, radius* 2, radius *2);
		 g2.draw(eli2d);
		  if(radius > 2) {
		    //[full] drawCircle() calls itself twice, creating
		    // a branching effect.  For every circle,
		    // a smaller circle is drawn to the left and the right.
		    drawCircle(x + radius/2, y, radius/2, g);
		    drawCircle(x - radius/2, y, radius/2, g);
		  }	  
	}
	
	//================ DRAW A TREE ===================================
	 void drawTree(Graphics g, int xA , int yA, double angle , int level) {
		 if (level == 0)
				g.drawLine(xA, yA, xA, yA);//draws initial line
		 else {
		 int xB = xA + (int) (Math.cos(Math.toRadians(angle)) * level * 10);//casts long into int adds it to xA(uses cos to get x and sin for y)
	    int yB = yA + (int) (Math.sin(Math.toRadians(angle)) * level * 10);
	    g.drawLine(xA, yA, xB, yB);//draws new lines
	    drawTree(g, xB, yB, angle - 15, level - 1);//draws with decreased angles and new points
	    drawTree(g, xB, yB, angle + 15, level - 1);//draws with increased angles and new points
	  
	    }
	 }

	 // ========= FUNCTION TO SEE IF MOUSE IS OVER A PRIMITIVE =====================
	private boolean mouseOver (int mx, int my, int x, int y , int width, int height) {
		if(mx > x && mx < x + width ) {
			if(my > y && my < y + height) {
				return true;
			} else return false;
		}else return false;
	}


}
