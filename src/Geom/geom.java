package Geom;

import Coords.MyCoords;
/**
 * this class takes the right information from a line in order to create a new point
 * and check if its valid.
 * @author Daniel Ventura and Eilon Tsadok
 *
 */
public class geom implements Geom_element{
	private Point3D p;//the point we will be initializing.
	private static final int earthR = 6371000;//the radius of planet earth.
	/**
	 * this function takes x,y,z and creates the gps point.
	 * @param line - the line we will be analyzing.
	 * @param geomIndexes - the indexes of x, y and z in the line.
	 */
	public geom(String[] line) {
		double yp = Double.parseDouble(line[2]);
		double xp = Double.parseDouble(line[3]);
		double zp = Double.parseDouble(line[4]);
		p = new Point3D(xp,yp,zp);
	}
	
	public geom(Point3D p) {
		this.p = p;
	}
	/**
	 * another constructor, takes a geom element and convert it into a gps point.
	 * @param ge
	 */
	public geom(geom ge) {
		this.p=new Point3D(ge.p);
	}
	/**
	 * calculates the 3d distance between our point and another one.
	 * @param p - the other point.
	 */
	@Override
	public double distance3D(Point3D p) {
		MyCoords x = new MyCoords();
		return x.distance3d(this.p,p);
	}
	/**
	 * calculates the 2d distance between our point and another one.
	 * @param p - the other point.
	 */
	@Override
	public double distance2D(Point3D p) {
		double LonNorm = Math.cos(Math.toRadians(p.y()));
		double meterX = LonNorm*earthR*Math.sin(Math.toRadians(this.p.x()-p.x()));
		double meterY = earthR*Math.sin(Math.toRadians(this.p.y()-p.y()));
		double Dis2D = Math.sqrt((meterX*meterX)+(meterY*meterY));
		if (Dis2D>100000)
			return Double.NaN;
		return Dis2D;
	}
	/**
	 * @return this.p
	 */
	public Point3D getP() {
		return p;
	}
	/**
	 * sets this.p to be the new point (other).
	 * @param other - the new this.p.
	 */
	public void setP(Point3D other) {
		this.p =new Point3D(other);
	}
}