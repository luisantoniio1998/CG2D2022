package projeto2d;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class CustomShape2 implements Shape{
	GeneralPath path;
	
	public CustomShape2(float x, float y, float w, float h) {
		path = new GeneralPath();
		
		float x4 = x + 0.53f*w;
	    float y4 = y + 0.6f * h;
	    float x5 = x + 0.73f * w;
	    float y5 = y + 0.24f * h;
	    float x6 = x + 0.89f * w;
	    float y6 = y + 0.24f * h;
	    float x7 = x + 0.89f * w;
	    float y7 = y + 0.4f * h;
	    float x8 = x + 0.53f * w;
	    float y8 = y + 0.6f * h;
	    
	    path.moveTo(x4, y4);
	    path.lineTo(x5,y5);
	    path.lineTo(x6,y6);
	    path.lineTo(x7,y7);
	    path.lineTo(x8,y8);
	    		
	}
	
	
	@Override
	public Rectangle getBounds() {
		return path.getBounds();
	}

	@Override
	public Rectangle2D getBounds2D() {
		return path.getBounds2D();
	}

	@Override
	public boolean contains(double x, double y) {
		return path.contains(x, y);
	}

	@Override
	public boolean contains(Point2D p) {
		return path.contains(p);
	}

	@Override
	public boolean intersects(double x, double y, double w, double h) {
		return path.intersects(x, y, w, h);
	}

	@Override
	public boolean intersects(Rectangle2D r) {
		return path.intersects(r);
	}

	@Override
	public boolean contains(double x, double y, double w, double h) {
		return path.contains(x, y, w, h);
	}

	@Override
	public boolean contains(Rectangle2D r) {
		return path.contains(r);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		return path.getPathIterator(at);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		return path.getPathIterator(at, flatness);
	}

}

