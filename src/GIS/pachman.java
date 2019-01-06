package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;
import Geom.geom;
/**
 * the class that represent the pachman, ghost and player it has geom object pachman meta data object 
 * @author EILON
 */
public class pachman implements GIS_element {

	private geom ge;
	private pachman_metaData md;
	public static String pic ="pachman.png";
	/**
	 * constructor of pachman that get line
	 * @param line array of string that contain all the information about a pachman (or ghost or player )
	 */
	public pachman(String[] line) {
		this.ge = new geom(line);
		this.md= new pachman_metaData(line);
	}
	/**
	 * constructor of pachman that get p and md
	 * @param p Point3D parameter with the location of pachman
	 * @param md pachman_metaData parameter 
	 */
	public pachman(Point3D p,pachman_metaData md) {
		this.ge = new geom(p);
		this.md = md;
	}
	/**
	 * the copy constractor of pachman
	 * @param other pachman parameter
	 */
	public pachman(pachman other) {
		this.ge = new geom (other.ge);
		this.md = new pachman_metaData(md);
	}
	/**
	 * 
	 * @param ge the geom object of the pachman
	 */
	public void set_Geom(geom newGeom) {
		this.ge = newGeom;
	}

	
public void setMd(pachman_metaData md) {
		this.md = md;
	}

@Override
public Geom_element getGeom() {
	return ge;
}
@Override
public Meta_Data getData() {
	return md;
}
@Override
public void translate(Point3D vec) {
	MyCoords MC = new MyCoords();
	this.ge.setP(MC.add(this.ge.getP(), vec));
}
}
