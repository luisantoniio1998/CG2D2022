package projeto2d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	static BufferedImage image;
	static BufferedImage imageRock;
	static BufferedImage imageScissors;
	static BufferedImage imagePaper; 
	
	static BufferedImage image1; 
	static BufferedImage imageRock1;
	static BufferedImage imageScissors1;
	static BufferedImage imagePaper1; 
	
	private static BufferedImage image2;
	private static BufferedImage imageRockUser;
	private static BufferedImage imageScissorsUser;
	private static BufferedImage imagePaperUser;
	
	private static BufferedImage image3;
	private static BufferedImage imageRockPC;
	private static BufferedImage imageScissorsPC;
	private static BufferedImage imagePaperPC;
	
	JButton jbutton;
	JButton jbutton1;
	
	public GamePanel() {
		setBackground(Color.black);
		setPreferredSize(new Dimension(700, 800));
		
		//========= JBUTTON BACK TO MENU =============
		jbutton = new JButton("Go back to menu");
		jbutton.addActionListener(this);
		add(jbutton);
		
		//========== JBUTTON RESET SCORE ==============
		jbutton1 = new JButton("Reset Score");
		jbutton1.addActionListener(this);
		add(jbutton1);
		
		// ============ IMAGES AND SUBIMAGES =======================
		URL url = getClass().getClassLoader().getResource("rockpaper1.png");
		try {
			image = ImageIO.read(url);
			imageRock = image.getSubimage(0, 0, 236, 292);
			imagePaper = image.getSubimage(236, 0, 236, 292);
			imageScissors = image.getSubimage(472, 0, 236, 292);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		URL url1 = getClass().getClassLoader().getResource("rockpaper1.png");
		try {
			image1 = ImageIO.read(url1);
			imageRock1 = image1.getSubimage(0, 0, 236, 292);
			imagePaper1 = image1.getSubimage(236, 0, 236, 292);
			imageScissors1 = image1.getSubimage(472, 0, 236, 292);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		URL url2 = getClass().getClassLoader().getResource("rockpaperUser.png");
		try {
			image2 = ImageIO.read(url2);
			imageRockUser = image2.getSubimage(0, 0, 236, 292);
			imagePaperUser = image2.getSubimage(236, 0, 236, 292);
			imageScissorsUser = image2.getSubimage(472, 0, 236, 292);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		URL url3 = getClass().getClassLoader().getResource("rockpaperPC.png");
		try {
			image3 = ImageIO.read(url3);
			imageRockPC = image3.getSubimage(0, 0, 236, 292);
			imagePaperPC = image3.getSubimage(236, 0, 236, 292);
			imageScissorsPC= image3.getSubimage(472, 0, 236, 292);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		//============ KEY LISTENER ==================
		addKeyListener(this);
		setFocusable(true);	
		
		//============ MOUSE LISTENER =================
		addMouseListener(this);
	}
	
	//========== FUNCTION TO GO BACK TO MENU =============
	public void menuOn() {
		Projeto2D.cardlayout.show(Projeto2D.mainPanel, "painter");
	}
	
	// ========= RESET SCORE ===============
	public void resetScore() {
		UserScore = 0;
		PCScore = 0; 
		string = " ";
		string1 = " ";
		string2 = " ";
		imageRock = image.getSubimage(0, 0, 236, 292);
		imagePaper = image.getSubimage(236, 0, 236, 292);
		imageScissors = image.getSubimage(472, 0, 236, 292);
		imageRock1 = image1.getSubimage(0, 0, 236, 292);
		imagePaper1 = image1.getSubimage(236, 0, 236, 292);
		imageScissors1 = image1.getSubimage(472, 0, 236, 292);
		repaint();
	}
	
	// =========== SCORES ===============
	static int UserScore = 0;
	static int PCScore = 0; 
	
	//============  STRINGS ==============
	static String string = " ";
	static String string1 = " ";
	static String string2 = " ";
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		//=============== FONTS =======================
		Font font = new Font("Courier", Font.BOLD, 30);
		Font font1 = new Font("Arial", Font.PLAIN, 15);
		Font font2 = new Font("Courier", Font.BOLD, 70);
		  
		//============ CROPPED IMAGES ========================
		g2.drawImage(imageRock, 50, 50, null);
		g2.drawImage(imagePaper,250, 50, null);
		g2.drawImage(imageScissors, 450, 50, null);
		
		g2.drawImage(imageRock1, 50, 500, null);
		g2.drawImage(imagePaper1, 250, 500, null );
		g2.drawImage(imageScissors1, 450, 500, null);
		
		//================ RECTANGLES ========================
		g2.setColor(Color.white);
		g2.drawRect(50, 100, 200, 210);
		g2.drawRect(250, 100, 200, 210);
		g2.drawRect(450, 100, 200, 210);
		
		g2.drawRect(50, 550, 200, 210);
		g2.drawRect(250, 550, 200, 210);
		g2.drawRect(450, 550, 200, 210);
		
		//================== TEXTS AND FONTS =================
		g2.setFont(font);
		g2.drawString("You:",50 , 350);
		g2.drawString("Computer:", 444, 520);
		g2.drawString(String.valueOf(UserScore), 130, 350);
		g2.drawString(String.valueOf(PCScore), 620, 520);
		
		g2.setFont(font1);
		g2.setColor(Color.cyan);
		g2.drawString("PC choice: ", 450, 540);
		g2.drawString(string1, 530, 540);
		
		g2.setColor(Color.yellow);
		g2.drawString("User choice: ", 50, 370);
		g2.drawString(string2, 140, 370);
		
		g2.setFont(font2);
		g2.setColor(Color.DARK_GRAY);
		g2.drawString(string, 250, 450);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbutton) {
		menuOn();
	}else if(e.getSource() == jbutton1) {
		resetScore();
	}
}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	// ================ CHOICES FROM PC AND USER =================
	int UserChoice ;
	int PcChoice ; 
	
	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//================== ROCK FROM USER ====================== 
		if(mouseOver(mx, my, 50, 100, 200, 210)	) {
			 UserChoice = 0;
			imagePaper = image.getSubimage(236, 0, 236, 292);
			imageScissors = image.getSubimage(472, 0, 236, 292);
			 imageRock = imageRockUser;
			 string2 = "Rock";
			 Random rand = new Random();
			 PcChoice = rand.nextInt(3);
			if(PcChoice == 0) {
				imageRock1 = imageRockPC;
				imagePaper1 = image1.getSubimage(236, 0, 236, 292);
				imageScissors1 = image1.getSubimage(472, 0, 236, 292);
				string = "DRAW!";
				string1 = "Rock";
			}else if(PcChoice == 1) {
				imagePaper1 = imagePaperPC;
				imageRock1 = image1.getSubimage(0, 0, 236, 292);
				imageScissors1 = image1.getSubimage(472, 0, 236, 292);
				PCScore++;
				string = " ";
				string1 = "Paper";
			}else if(PcChoice == 2) {
				imageScissors1 = imageScissorsPC;
				imageRock1 = image1.getSubimage(0, 0, 236, 292);
				imagePaper1 = image1.getSubimage(236, 0, 236, 292);
				UserScore++;
				string = " ";
				string1 = "Scissors";
			}
			repaint();
		}
		
		//=================== PAPER FROM USER ==================
		if(mouseOver(mx, my, 250, 100, 200, 210)) {
			imageRock = image.getSubimage(0, 0, 236, 292);
			imageScissors = image.getSubimage(472, 0, 236, 292);
			imagePaper = imagePaperUser;
			UserChoice = 1;
			string2 = "Paper";
			Random rand = new Random();
			PcChoice = rand.nextInt(3);
			if(PcChoice == 0) {
				imageRock1 = imageRockPC;
				imagePaper1 = image1.getSubimage(236, 0, 236, 292);
				imageScissors1 = image1.getSubimage(472, 0, 236, 292);
				UserScore++;
				string = " ";
				string1 = "Rock";
			}else if(PcChoice == 1) {
				imageRock1 = image1.getSubimage(0, 0, 236, 292);
				imagePaper1 = imagePaperPC;
				imageScissors1 = image1.getSubimage(472, 0, 236, 292);
				string = "DRAW!";
				string1 = "Paper";
			}else if(PcChoice == 2) {
				imageRock1 = image1.getSubimage(0, 0, 236, 292);
				imagePaper1 = image1.getSubimage(236, 0, 236, 292);
				imageScissors1 = imageScissorsPC;
				PCScore++;
				string = " ";
				string1 = "Scissors";
			}
			repaint();
		}
		//================ SCISSORS FROM USER ====================
		if(mouseOver(mx, my, 450, 100, 200, 210)) {
			UserChoice = 2;
			imageScissors = imageScissorsUser;
			imageRock = image.getSubimage(0, 0, 236, 292);
			imagePaper = image.getSubimage(236, 0, 236, 292);
			string2 = "Scissors";
			Random rand = new Random ();
			PcChoice = rand.nextInt(3);
			if(PcChoice == 0) {
				imageRock1 = imageRockPC;
				imagePaper1 = image1.getSubimage(236, 0, 236, 292);
				imageScissors1 = image1.getSubimage(472, 0, 236, 292);
				PCScore++;
				string = " ";
				string1 = "Rock";
			}else if(PcChoice == 1) {
				imageRock1 = image1.getSubimage(0, 0, 236, 292);
				imagePaper1 = imagePaperPC;
				imageScissors1 = image1.getSubimage(472, 0, 236, 292);
				UserScore++;
				string = " ";
				string1 = "Paper";
			}else if(PcChoice == 2) {
				imageRock1 = image1.getSubimage(0, 0, 236, 292);
				imagePaper1 = image1.getSubimage(236, 0, 236, 292);
				imageScissors1 = imageScissorsPC;
				string = "DRAW!";
				string1 = "Scissors";
			}
			repaint();
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
	
	private boolean mouseOver (int mx, int my, int x, int y , int width, int height) {
		if(mx > x && mx < x + width ) {
			if(my > y && my < y + height) {
				return true;
			} else return false;
		}else return false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		//================== ROCK FROM USER ====================== 
		if ( key == KeyEvent.VK_R) {
			 UserChoice = 0;
				 imagePaper = image.getSubimage(236, 0, 236, 292);
				 imageScissors = image.getSubimage(472, 0, 236, 292);
				 imageRock = imageRockUser;
				 string2 = "Rock";
				 Random rand = new Random();
				 PcChoice = rand.nextInt(3);
		if(PcChoice == 0) {
					imageRock1 = imageRockPC;
					imagePaper1 = image1.getSubimage(236, 0, 236, 292);
					imageScissors1 = image1.getSubimage(472, 0, 236, 292);
					string = "DRAW!";
					string1 = "Rock";
		}else if(PcChoice == 1) {
					imagePaper1 = imagePaperPC;
					imageRock1 = image1.getSubimage(0, 0, 236, 292);
					imageScissors1 = image1.getSubimage(472, 0, 236, 292);
					PCScore++;
					string = " ";
					string1 = "Paper";
		}else if(PcChoice == 2) {
					imageScissors1 = imageScissorsPC;
					imageRock1 = image1.getSubimage(0, 0, 236, 292);
					imagePaper1 = image1.getSubimage(236, 0, 236, 292);
					UserScore++;
					string = " ";
					string1 = "Scissors";
				}
				repaint();
			}
		//=================== PAPER FROM USER ==================
		else if( key == KeyEvent.VK_P) {
			imageRock = image.getSubimage(0, 0, 236, 292);
			imageScissors = image.getSubimage(472, 0, 236, 292);
			imagePaper = imagePaperUser;
			UserChoice = 1;
			string2 = "Paper";
			Random rand = new Random();
			PcChoice = rand.nextInt(3);
		if(PcChoice == 0) {
				imageRock1 = imageRockPC;
				imagePaper1 = image1.getSubimage(236, 0, 236, 292);
				imageScissors1 = image1.getSubimage(472, 0, 236, 292);
				UserScore++;
				string = " ";
				string1 = "Rock";
		}else if(PcChoice == 1) {
				imageRock1 = image1.getSubimage(0, 0, 236, 292);
				imagePaper1 = imagePaperPC;
				imageScissors1 = image1.getSubimage(472, 0, 236, 292);
				string = "DRAW!";
				string1 = "Paper";
		}else if(PcChoice == 2) {
				imageRock1 = image1.getSubimage(0, 0, 236, 292);
				imagePaper1 = image1.getSubimage(236, 0, 236, 292);
				imageScissors1 = imageScissorsPC;
				PCScore++;
				string = " ";
				string1 = "Scissors";
			}
			repaint();
		}
		//================ SCISSORS FROM USER ====================
		else if(key == KeyEvent.VK_S) {
			UserChoice = 2;
			imageScissors = imageScissorsUser;
			imageRock = image.getSubimage(0, 0, 236, 292);
			imagePaper = image.getSubimage(236, 0, 236, 292);
			string2 = "Scissors";
			Random rand = new Random ();
			PcChoice = rand.nextInt(3);
		if(PcChoice == 0) {
				imageRock1 = imageRockPC;
				imagePaper1 = image1.getSubimage(236, 0, 236, 292);
				imageScissors1 = image1.getSubimage(472, 0, 236, 292);
				PCScore++;
				string = " ";
				string1 = "Rock";
		}else if(PcChoice == 1) {
				imageRock1 = image1.getSubimage(0, 0, 236, 292);
				imagePaper1 = imagePaperPC;
				imageScissors1 = image1.getSubimage(472, 0, 236, 292);
				UserScore++;
				string = " ";
				string1 = "Paper";
		}else if(PcChoice == 2) {
				imageRock1 = image1.getSubimage(0, 0, 236, 292);
				imagePaper1 = image1.getSubimage(236, 0, 236, 292);
				imageScissors1 = imageScissorsPC;
				string = "DRAW!";
				string1 = "Scissors";
			}
			repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
