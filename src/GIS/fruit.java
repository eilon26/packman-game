package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;
import Geom.geom;
/**
 * the class that represent the fruit it has geom object fruit meta data object and a boolean parameter to detect if it is alive
 * @author EILON
 */
public class fruit implements GIS_element {
	private geom ge;
	private fruit_metaData md;
	private boolean alive;
/**
 * constructor of fruit that get line, geomIndexes and metaIndexes
 * @param line array of string that contain all the information about fruit
 * @param geomIndexes array of int that contain the indexes of the geom fields inside line
 * @param metaIndexes array of int that contain the indexes of the fruit metadata fields inside line
 */
public fruit(String[] line) {
		this.ge = new geom(line);
		this.md= new fruit_metaData(line);
		this.alive =true;
}
/**
 * constructor of fruit that get p and md
 * @param p Point3D parameter with the location of fruit
 * @param md fruit_metaData parameter 
 */
public fruit(Point3D p,fruit_metaData md) {
	this.ge = new geom(p);
	this.md = md;
	this.alive =true;
}
/**
 * the copy constractor of fruit
 * @param other fruit parameter
 */
public fruit (fruit other) {
	this.ge= new geom((geom) other.getGeom());
	this.md = new fruit_metaData((fruit_metaData) other.getData());
	this.alive =true;
}
/**
 * @return the geom object of fruit
 */
@Override
public Geom_element getGeom() {
	return ge;
}
/**
 * @return the metadata of fruit
 */
@Override
public Meta_Data getData() {
	return md;
}
/**
 * updae the new location of fruit after adding vac to fruit point
 * @param vec Point3D that represent vector
 */
@Override
public void translate(Point3D vec) {
	MyCoords MC = new MyCoords();
	this.ge.setP(MC.add(this.ge.getP(), vec));
}
/**
 * 
 * @return true if the fruit is still wasn't eaten alreay
 */
public boolean isAlive() {
	return this.alive;
}
/**
 * set this.alive by alive parameter
 * @param alive boolean parameter
 */
public void setAlive(boolean alive) {
	this.alive = alive;
}
}
