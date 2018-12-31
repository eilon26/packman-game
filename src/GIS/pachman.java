package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;
import Geom.geom;
/**
 * the class that represent the pachman it has geom object pachman meta data object 
 * @author EILON
 */
public class pachman implements GIS_element {

	private geom ge;
	private pachman_metaData md;
	/**
	 * constructor of pachman that get line, geomIndexes and metaIndexes
	 * @param line array of string that contain all the information about pachman
	 * @param geomIndexes array of int that contain the indexes of the geom fields inside line
	 * @param metaIndexes array of int that contain the indexes of the pachman metadata fields inside line
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
