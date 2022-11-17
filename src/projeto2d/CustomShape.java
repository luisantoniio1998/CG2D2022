package projeto2d;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class CustomShape implements Shape{
	GeneralPath path;
	
	public CustomShape(float x, float y, float w, float h) {
		path = new GeneralPath();
		
		float x0 = x + 0.17f*w;
	    float y0 = y + 0.24f*h;
	    float x1 = x + 0.33f*w;
	    float y1 = y + 0.24f*h;
	    float x2 = x + 0.53f*w;
	    float y2 = y + 0.6f*h;  
	    float x3 = x + 0.17f*w;
	    float y3 = y + 0.4f*h;
	    
	    path.moveTo(x0, y0);
	    path.lineTo(x1, y1);
	    path.lineTo(x2, y2);
	    path.lineTo(x3, y3);
	    		
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
