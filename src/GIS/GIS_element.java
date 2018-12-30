package GIS;

import Geom.Geom_element;
import Geom.Point3D;

/**
 * This interface represents a GIS element with geometric representation and meta data such as:
 * Orientation, color, string, timing...
 * @author Boaz Ben-Moshe
 *
 */
public interface GIS_element {//line in csv 
	public Geom_element getGeom();//valu of the point
	public Meta_Data getData();//name, time
	public void translate(Point3D vec);
}
