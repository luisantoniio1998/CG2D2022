package projeto2d;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.TexturePaint;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RescaleOp;
import java.awt.image.WritableRaster;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Projeto2D extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static void main(String[] args) {
		JFrame frame = new Projeto2D();
		frame.setTitle("2D Project");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		
		//===== FRAME STARTS IN THE MIDDLE OF THE SCREEN =============
		frame.setLocationRelativeTo(null);
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
		static JPanel mainPanel;
		MyPanel menuPanel;
		PrinterJob pj;
		static CardLayout cardlayout;
		GamePanel gamePanel;
		HelpPanel helpPanel;
		
	public Projeto2D() {
		
		//======= SET A MAIN PANEL WITH CARDLAYOUT ============
		cardlayout = new CardLayout();
		mainPanel = new JPanel(cardlayout);
		
		menuPanel = new MyPanel();
		gamePanel = new GamePanel();
		helpPanel = new HelpPanel();
		
		//======= ADDED "SUBPANELS" TO MAIN PANEL ==========
		mainPanel.add(menuPanel, "painter");
		mainPanel.add(gamePanel, "game");
		mainPanel.add(helpPanel, "help");
		
		//========== KEY LISTENER FROM GAMEPANEL ===============
		mainPanel.addKeyListener(gamePanel);
		mainPanel.setFocusable(true);
		
		add(mainPanel);
		
		//=========== PRINTER JOB ==================
		pj = PrinterJob.getPrinterJob();
		pj.setPrintable(menuPanel);
		
		//========== JMENU BAR =================== 
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);

		JMenu menu = new JMenu("File");
		JMenuItem mi = new JMenuItem("Print");
		mi.addActionListener(this);
		menu.add(mi);
		menu.addSeparator();
		mi = new JMenuItem("Exit");
		mi.addActionListener(this);
		menu.add(mi);
		mb.add(menu);

		JMenu menuProcessing = new JMenu("Process IPG Logo ");
		mb.add(menuProcessing);
		mi = new JMenuItem("Grayscale");
		mi.addActionListener(this);
		menuProcessing.add(mi);
		
		JMenuItem mi1 = new JMenuItem("Sharpen");
		mi1.addActionListener(this);
		menuProcessing.add(mi1);
		
		JMenuItem mi2 = new JMenuItem("Edge");
		mi2.addActionListener(this);
		menuProcessing.add(mi2);
		
		JMenuItem mi3 = new JMenuItem("Rescale");
		mi3.addActionListener(this);
		menuProcessing.add(mi3);
		
		JMenuItem mi4 = new JMenuItem("Rotate");
		mi4.addActionListener(this);
		menuProcessing.add(mi4);
		
		JMenuItem mi5 = new JMenuItem("Binarization(Raster)");
		mi5.addActionListener(this);
		menuProcessing.add(mi5);
		
		JMenuItem mi6 = new JMenuItem("GrayScale2");
		mi6.addActionListener(this);
		menuProcessing.add(mi6);
		
		JMenu menuWind = new JMenu("Winding Rule");
		mb.add(menuWind);
		
		JMenuItem mi7 = new JMenuItem("Even_odd_rule");
		mi7.addActionListener(this);
		menuWind.add(mi7);
		
		JMenuItem mi8 = new JMenuItem("Non_zero_rule");
		mi8.addActionListener(this);
		menuWind.add(mi8);
		
		JMenu menuHelp = new JMenu("Help");
		mb.add(menuHelp);
		mi = new JMenuItem("About");
		mi.addActionListener(this);
		menuHelp.add(mi);
	}

	
	//=============== CHECK MENU CHOICE ===================
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if("Print".equals(cmd) && pj.printDialog()) {
			try {
				pj.print();
			} catch(PrinterException ex){
				ex.printStackTrace();
			}
		}else if("Exit".equals(cmd)) {
			System.exit(0);
		}else if("About".equals(cmd)) {
			MyPanel.infoBox("Project for Computer Graphics using Java2D \n By : Luis Barros \n V1.0", "About");
		}else if("Grayscale".equals(cmd)) {
			MyPanel.processGrayScale();
			System.out.println("Grayscale applied");
			repaint();
		}else if("Sharpen".equals(cmd)) {
			MyPanel.processSharpen();
			System.out.println("Logo sharpened");
			repaint();
		}else if("Edge".equals(cmd)) {
			MyPanel.processEdge();
			System.out.println("Logo edged");
			repaint();
		}else if("Rescale".equals(cmd)) {
			MyPanel.processRescale();
			System.out.println("Logo rescaled");
			repaint();
		}else if("Rotate".equals(cmd)) {
			MyPanel.processRotate();
			System.out.println("Logo rotated");
			repaint();
		}else if("Binarization(Raster)".equals(cmd)) {
			MyPanel.processBinarization();
			System.out.println("Logo binarized");
			pack();
		}else if("GrayScale2".equals(cmd)) {
			MyPanel.processRGB2Gray();
			System.out.println("Logo processed");
			pack();
		}else if("Non_zero_rule".equals(cmd)) {
			MyPanel.windZero();
			repaint();
		}else if("Even_odd_rule".equals(cmd)){
			MyPanel.windOdd();
			repaint();
		}
	}
	
	public static void gameOn() {
		cardlayout.show(mainPanel, "game");
		
		//=========== RESET SCORE() =================== 
		//But I chose to return to save the variables of the game and the last play 
		//Because the player has the choice to reset the score 
		//Whenever he wants 
		
		/*
		GamePanel.UserScore = 0;
		GamePanel.PCScore = 0;
		GamePanel.string = " ";
		
		GamePanel.imageRock = GamePanel.image.getSubimage(0, 0, 236, 292);
		GamePanel.imagePaper = GamePanel.image.getSubimage(236, 0, 236, 292);
		GamePanel.imageScissors = GamePanel.image.getSubimage(472, 0, 236, 292);
		
		GamePanel.imageRock1 = GamePanel.image1.getSubimage(0, 0, 236, 292);
		GamePanel.imagePaper1 = GamePanel.image1.getSubimage(236, 0, 236, 292);
		GamePanel.imageScissors1 = GamePanel.image1.getSubimage(472, 0, 236, 292);*/
	}
	
	// ========= FUNCTION TO GO TO HELP PANEL ======================
	public static void helpOn() {
		cardlayout.show(mainPanel, "help");
	}
}

	
	class MyPanel extends JPanel implements Printable, MouseListener, Runnable, MouseMotionListener{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		//============ CUSTOM SHAPES ===================
		Shape cs = new CustomShape(420, -110, 500, 500);
    	Shape s2 = new Rectangle2D.Double(250 , 468, 200, 64);
		
    	//============== IMAGES =======================
		private static BufferedImage image;
		private BufferedImage image2;
		
		//=============== GENERAL PATH ARC2.PIE ================
		private GeneralPath gp1 = new GeneralPath();
		public double spinValue = 0;
		
		//======== GENERALPATH WIND RULES ==============
		static GeneralPath path = new GeneralPath();
		
		// =========== COLISION AND SELECT ============= 
		boolean colision = false;
		boolean select = false;
		
		//===== COORDINATES TO MOVE CUSTOM SHAPE ===================
		int firstX = 0;
		int firstY = 0; 
		int deltaX = 0;
		int deltaY = 0;
		
		// ==== RULES FOR COMPOSITING =================
		int[] rules = {AlphaComposite.CLEAR, AlphaComposite.SRC_OVER,
			    AlphaComposite.DST_OVER, AlphaComposite.SRC_IN,
			    AlphaComposite.DST_IN, AlphaComposite.SRC_OUT,
			    AlphaComposite.DST_OUT, AlphaComposite.SRC,
			    AlphaComposite.DST, AlphaComposite.SRC_ATOP,
			    AlphaComposite.DST_ATOP, AlphaComposite.XOR};
			  int ruleIndex = 0;
		
		public MyPanel() {
			
			// ===== BACKGROUND AND DIMENSION ==================
			setPreferredSize(new Dimension(700, 800));
			setBackground(Color.BLACK);
			
			//======== GET LOGO IMAGE ================================
			URL url = getClass().getClassLoader().getResource("logo.jpg");
			try {
				image = ImageIO.read(url);
			} catch(IOException ex) {
				ex.printStackTrace();
			}
			
			//======== GET TEXTURE IMAGE =============================
			URL url2 = getClass().getClassLoader().getResource("texture.jpg");
			try {
				image2 = ImageIO.read(url2);
			}catch(IOException ex) {
				ex.printStackTrace();
			}
			
			//====== GENERALPATH WIHT ELIPSE2D AND ARC2D.PIE APPENDED =================
			gp1.append(new Ellipse2D.Double(200, -250, 100, 100), true);
			for (double angle = 0; angle < 360; angle += 30) {
				Arc2D arc = new Arc2D.Double(200, -250, 100, 100, 
						angle, 
						30, 
						Arc2D.PIE);
				gp1.append(arc, false);
			}
			
			//====== INITIALIZED THREAD AND START IT =================
			Thread thread = new Thread(this);
			thread.start();
			
			//===== MOUSE LISTENER ====================
			addMouseListener(this);
			addMouseMotionListener(this);
			}
		
		//======== INFOBOX ======================================
		public static void infoBox(String infoMessage, String titleBar) {
			JOptionPane.showMessageDialog(null, infoMessage, "Info " + titleBar, JOptionPane.INFORMATION_MESSAGE);
		}

		@Override
		public int print(Graphics g, PageFormat pF, int pageIndex) throws PrinterException {
			switch (pageIndex) {
			case 0:
				draw(g);
				break;
			case 1:
				g.translate(-(int) pF.getImageableWidth(), 0);
				draw(g);
				break;
			default:
				return NO_SUCH_PAGE;
			}
			return PAGE_EXISTS;
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			draw(g);
		}
		
		static BufferedImage a;
		
		//========== GRAYSCALE PROCESSING IMAGE =================
		public static void processGrayScale() {
			BufferedImageOp op = null;
			op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
			BufferedImage bi = op.filter(image, null);
			a = bi;
		}
		
		//======== SHARPEN PROCESSING IMAGE ==============
		public static void processSharpen() {
			BufferedImageOp op = null;
			float[] data = { 0f, -1f, 0f, -1f, 5f, -1f, 0f, -1f, 0f };
			Kernel k = new Kernel(3, 3, data);
			op = new ConvolveOp(k);
			BufferedImage bi = op.filter(image, null);
			a = bi;
		}
		
		//======= EDGE PROCESSING IMAGE ===============
		public static void processEdge() {
			BufferedImageOp op = null;
			float[] data = { 0f, -1f, 0f, -1f, 4f, -1f, 0f, -1f, 0f };
			Kernel k = new Kernel(3, 3, data);
			op = new ConvolveOp(k);
			BufferedImage bi = op.filter(image, null);
			a = bi;
		}
		
		//======== RESCALE PROCESSING IMAGE ==============
		public static void processRescale() {
			BufferedImageOp op = null;
			op = new RescaleOp(1.5f, 0f, null);
			BufferedImage bi = op.filter(image, null);
			a = bi;
		}
		
		//===== ROTATION PROCESSING IMAGE ===============
		public static void processRotate() {
			BufferedImageOp op = null;
			AffineTransform at = new AffineTransform();
			at.setToRotation(Math.PI /4);
			op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			BufferedImage bi = op.filter(image, null);
			a = bi;
		}
		
		
		
		//============ BINARIZATION PROCESSING IMAGE ========================
		public static BufferedImage binarize(BufferedImage image) {
			WritableRaster rasterImageIn = image.getRaster();
			WritableRaster rasterImageOut = image.getRaster();
			int[] rgba = new int[4];
			for (int y = 0; y < image.getHeight(); y++) {
				for (int x = 0; x < image.getWidth(); x++) {
					rasterImageIn.getPixel(x, y, rgba);
					int gray = (int) ((rgba[0] + rgba[1] + rgba[2]) / 3f);
					if (gray < 150)
						rgba[0] = rgba[1] = rgba[2] = 255;
					else
						rgba[0] = rgba[1] = rgba[2] = 0;
					rasterImageOut.setPixel(x, y, rgba);
				}
			}
			return image;
		}	
		
	    public static void processBinarization() { 
	    	binarize(image);
	    }
	 
	    // ============ GRAY SCALE IMAGE PROCESSING ======================
	    public static BufferedImage RGB2Gray(BufferedImage image) {
			WritableRaster rasterImgIn = image.getRaster();
			WritableRaster rasterImgOut = image.getRaster();
			int[] rgba = new int[4];
			for (int y = 0; y < image.getHeight(); y++) {
				for (int x = 0; x < image.getWidth(); x++) {
					rasterImgIn.getPixel(x, y, rgba);
					int gray = (int) ((rgba[0] + rgba[1] + rgba[2]) / 3f);
					rgba[0] = rgba[1] = rgba[2] = gray;
					rasterImgOut.setPixel(x, y, rgba);
				}
			}
			return image;
		}
	    
	    public static void processRGB2Gray() {
	    	RGB2Gray(image);
	    }
	    
	    //========== WINDING RULES ===============================
	    public static void windOdd() {
			path.setWindingRule(GeneralPath.WIND_EVEN_ODD);
		}
		
		public static void windZero() {
			path.setWindingRule(GeneralPath.WIND_NON_ZERO);
		}
		
		//=============== DRAW ===================================
		private void draw(Graphics g) {
			
		    Graphics2D g2 = (Graphics2D)g;
		    
		    //================= COLORS =====================================
		    Color newColor = new Color(100, 200, 100);
		    Color newColor1 = new Color(150, 200, 150);
		    Color newColor2 = new Color(250, 250, 250);
		    Color newColor3 = new Color(198, 141, 26);
		    
		    //============== FONTS ========================================
		    Font font = new Font("Calibri", Font.BOLD, 50);
		    Font font2 = new Font("Courier", Font.PLAIN, 50);
		    Font font3 = new Font("Serif", Font.PLAIN, 50);
			Font fnt2 = new Font("arial", 1, 30);
		    
		    //=========== STROKES =============================================
		    Stroke stroke = new BasicStroke(3f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);
		    
		    //============= PAINTS ===========================================
		    TexturePaint tp = new TexturePaint(image2, 
		    new Rectangle2D.Double(100, 100, image2.getWidth(), image2.getHeight()));
		    GradientPaint gp = new GradientPaint(500, 500, newColor2, 650, 650, newColor, true);
		    
		    //======= ALPHA COMPOSITES =====================================
        	AlphaComposite ac = AlphaComposite.getInstance(rules[ruleIndex], 0.4f);
            AlphaComposite ac1 = AlphaComposite.getInstance(rules[ruleIndex], 1f);
            
            //Still drawn in interaction between shapes
            //CustomShape1 with GradientPaint 
		    //g2.setPaint(gp);
		    //Shape cs = new CustomShape(380, -110, 500, 500);
		    //g2.fill(cs);
            
            //interaction between customShape and Quit Button
            if (colision)
    			g2.setColor(Color.RED);
    		else
    			g2.setColor(Color.WHITE);
    		g2.draw(s2);

    		if (colision)
    			g2.setColor(Color.RED);
    		else
    			// ======= GRADIENT PAINT IN CUSTOM SHAPE ====================
    			g2.setPaint(gp);
    		g2.fill(cs);
		    
		    //======= CUSTOM SHAPE WITH TEXTURE PAINT ==============
		    g2.setPaint(tp);
		    Shape cs1 = new CustomShape2(250, -110, 500, 500);
		    g2.fill(cs1);
		    
		    //======= PRIMITIVE OVAL WITH COLOR PAINT =============
		    g2.setPaint(newColor3);
		    g2.fillOval(500, 50, 40, 50);
		    
		    //======= PRIMITIVE ARC ========================
		    g2.drawArc(500, 50, 50, 50, 12, 145);
		    
		    //====== PRIMITIVE RECT ==========================
		    g2.fillRect(570, 20, 100, 50);
		    
		    // ======= PRIMITIVE LINE WITH STROKE =================
		    g2.setStroke(stroke);
		    g2.drawLine(10, 10, 10, 800);
		    g2.drawLine(0, 790, 700, 790);
		    
		     //====== INSTITUTE LOGO ===================
			if(a == null) {
				g.drawImage(image, 10, 10, null);
			}else {
				g.drawImage(MyPanel.a,10, 10 , null);
			}
			
		    // ==== PRIMITIVE POLYGON ========================
			int xs[] = {image.getWidth()+10-50, image.getWidth()+10, image.getWidth()+10};
			int ys[] = {10, 10, 60};
			g.fillPolygon(xs, ys, 3);
		    
			//====== RECTANGLE2D (SHAPE FROM 2D API) =====================
			Rectangle2D rct2d = new Rectangle2D.Double(500, 15 , 180, 180);
            g2.draw(rct2d);
            
            //==== ROUND RECTANGLE 2D (SHAPE FROM 2D API) =============
            RoundRectangle2D rct2d1 = new RoundRectangle2D.Double(500, 20, 180, 180, 10, 10);
            g2.draw(rct2d1);
            
            //======== ELIPSE2D (SHAPE FROM 2D API) ===========
            Random rand = new Random();
            double upperbound = 700;
            
            Ellipse2D eli2d = new Ellipse2D.Double(rand.nextDouble(700), rand.nextDouble(700), 100, 50);
            g2.fill(eli2d);
            
            //====== QUADCURVE (SHAPE FROM 2D API) ==================
            QuadCurve2D qc2d1 = new QuadCurve2D.Double(rand.nextDouble(upperbound), rand.nextDouble(upperbound), rand.nextDouble(upperbound), rand.nextDouble(upperbound), rand.nextDouble(upperbound), rand.nextDouble(upperbound));
            g2.draw(qc2d1);
            
            //======== MENU INSIDE JPANEL ====================
            g2.setColor(Color.white);
            
            //===== HELP RECTANGLE FUNCTIONING AS BUTTON =======
            g2.setFont(fnt2);
        	g2.drawRect(350-100 , 400-32, 200, 64);
        	g2.drawString("Help", 320, 408);
        	
        	//==== PLAY RECTANGLE FUNCTIONING AS BUTTON ==========
        	g2.drawRect(250 , 268 , 200, 64);
        	g2.drawString("Play", 320, 308);
        	
        	
        	//====== CUSTOM SHAPE WITH WIND RULES CHANGING WITH MENU  =====================
        	g2.setColor(newColor);
        	path.moveTo(500, 700);
        	path.quadTo(600, 600, 700, 700);
        	path.quadTo(600, 850, 500, 700);
        	path.moveTo(550, 680);
        	path.lineTo(650, 720);
        	path.lineTo(650, 680);
        	path.lineTo(550, 720);
        	path.lineTo(550, 680);
        	g2.fill(path);
        	
        	//Still drawn in interaction between shapes
        	//Quit rectangle
        	//Shape s2 = new Rectangle2D.Double(250 , 468, 200, 64);
        	//g2.drawRect(250 , 468, 200, 64);
        	//g2.draw(s2);
        	
        	g2.setColor(Color.WHITE);
        	g2.drawString("Quit", 320, 508);  
        	
        	//====== GRAPHICS TEXT ==============================
		    g2.setColor(newColor1);
		    g2.setFont(font2);
		    g2.drawString("Graphics", 160, 100);	  
		    
		    //==== 2D PROJECT TEXT WITH ALPHA COMPOSITE ==============
        	g2.setComposite(ac);
		    g2.setColor(Color.WHITE);
		    g2.setFont(font);
		    g2.drawString("2D Project", 160, 50);  
		    
		    //=== COMPUTATION TEXT WITH ALPHACOMPOSITE ====================
		    g2.setComposite(ac1);
		    g2.setColor(newColor);
		    g2.setFont(font3);
		    g2.drawString("Computation", 160, 150);
		    
		    //==================== ROTATING PIE ===============================
            //Rotating pie (Animation) using Arc2D and Elipse2D from 2D API  and AlphaComposite
		    //Geometric transformation applied to the coordinate system 
		    g2.setColor(Color.white);
		    RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHints(hints);

            Rectangle2D bounds = gp1.getBounds2D();
            double x = (getWidth() - bounds.getWidth()) / 2d;
            double y = (getHeight() - bounds.getHeight()) / 2d;

            AffineTransform at = AffineTransform.getTranslateInstance(x, y);
            at.rotate(spinValue, bounds.getCenterX(), bounds.getCenterY());

            g2.transform(at);
            g2.draw(gp1);
	}
		
		private void updateLocationAndColisionDetection() {
			AffineTransform at = new AffineTransform();
			at.setToTranslation(deltaX, deltaY);
			cs = at.createTransformedShape(cs);
			
			firstX = firstX + deltaX;
			firstY = firstY + deltaY;
			
			deltaX = 0; 
			deltaY = 0;
			
			// ======= GET CUSTOM SHAPE BOUNDS ============================
			double x = cs.getBounds().getX() + cs.getBounds().getWidth();
			double y = cs.getBounds().getY() + cs.getBounds().getHeight() / 2; 
			Point2D p = new Point2D.Double(x, y);
		
			//========== CHECK COLLISION BETWEEN CUSTOM SHAPE AND QUIT BUTTON ======
			if(s2.contains(p))
				colision = true;
			else
				colision = false;
			repaint();
		}

		//==== COMPOSITING WHEN MOUSE CLICKED OUTSIDE BUTTONS AND INSIDE JPANEL ======= 
		@Override
		public void mouseClicked(MouseEvent e) {
			ruleIndex++;
		    ruleIndex %= 12;
		    repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {	
			int mx = e.getX();
			int my = e.getY();
			
			//========== QUIT BUTTON ========================
			if(mouseOver(mx, my, 250, 468, 200, 64)) {
				System.exit(0);
			}
			
			//=========== PLAY BUTTON ====================
			if(mouseOver(mx, my, 250, 268, 200, 64)) {
			 Projeto2D.gameOn();
			}
			
			//========== HELP BUTTON ====================
			if(mouseOver(mx, my, 250, 368, 200, 64)) {
				Projeto2D.helpOn();
			}
			
			//=========== INTERACTION BETWEEN SHAPES ============ 
			if(cs.contains(e.getX(), e.getY())) {
				select = true;
				firstX = e.getX();
				firstY = e.getY();
			}else
				select = false;
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
		
		// ===== FUNCTION TO CHECK IF MOUSE IS OVER A PRIMITIVE ================
		private boolean mouseOver (int mx, int my, int x, int y , int width, int height) {
			if(mx > x && mx < x + width ) {
				if(my > y && my < y + height) {
					return true;
				} else return false;
			}else return false;
		}
		
		// =========== RUNNABLE IMPLEMENATION ADDING SPINVALUE TO THE ARC2D ========
		@Override
		public void run() { 
			while(true) {
				spinValue += 0.01;
				repaint();
				try {
			        Thread.sleep(50);
			      } catch (InterruptedException ex) {}
			}
		}

		//========== MOVE CUSTOM SHAPE ====================
		@Override
		public void mouseDragged(MouseEvent e) {
			if(select) {
				deltaX = e.getX() - firstX;
				deltaY = e.getY() - firstY;
				
				updateLocationAndColisionDetection();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {

		}
	}