package GIS;

import java.util.ArrayList;
import java.util.Iterator;

import Geom.Point3D;
/**
 * the class represent a box in the game
 * @author EILON
 */
public class box  {
	private int id;
	private Point3D p1;//main point
	private Point3D p2;//main point
	private Point3D p3;
	private Point3D p4;
	
	/**
	 * the constructor of a box that get String[] with the box information 
	 * @param line String[]
	 */
	public box(String[] line) {
		this.id = Integer.parseInt(line[1]);
		this.p1 = new Point3D(Double.parseDouble(line[3]),Double.parseDouble(line[2]),Double.parseDouble(line[4]));
		this.p2 = new Point3D(Double.parseDouble(line[6]),Double.parseDouble(line[5]),Double.parseDouble(line[7]));
		if (p2.y()>p1.y()) {
			this.p3 = new Point3D(p1.x(),p2.y());
			this.p4 = new Point3D(p2.x(),p1.y());
		}else {
			this.p4 = new Point3D(p1.x(),p2.y());
			this.p3 = new Point3D(p2.x(),p1.y());
		}

	}

	/**
	 * get a global point and return true if the point is inside some box 
	 * @param player_loc Point3D
	 * @param box_set ArrayList<box>
	 */
	public static boolean isInsideBox(Point3D player_loc,ArrayList<box> box_set){
		boolean insideBox = false;
		Iterator<box> IterBox = box_set.iterator();
		while (IterBox.hasNext()&&!insideBox) {
			box curr_box = IterBox.next();
			if ((player_loc.x()>=curr_box.getP1().x())&&(player_loc.x()<=curr_box.getP2().x())) {
				if ((player_loc.y()>=curr_box.getP1().y())&&(player_loc.y()<=curr_box.getP2().y())) {
					insideBox = true;
				}
			}
		}
		return insideBox;

	}
	public int getId() {
		return id;
	}

	public Point3D getP1() {
		return p1;
	}

	public Point3D getP2() {
		return p2;
	}

	public Point3D getP3() {
		return p3;
	}

	public Point3D getP4() {
		return p4;
	}


}
