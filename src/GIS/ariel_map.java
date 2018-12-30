package GIS;

import GUI.JPanelWithBackground;

import Geom.Point3D;
/**
 *this class is extends from map and responsible to represent the map of Ariel
 * @author EILON
 *
 */
public class ariel_map extends map {

/**
 * ariel_map Constructor that get frame and initialize the empty fields in map class by "Ariel" pic
 * @param frame Myframe object
 */
	public ariel_map(JPanelWithBackground jpanel) {
		super(jpanel);
		DownLeftP = new Point3D( 35.202403,32.101923,0);
		UpRightP = new Point3D( 35.212438,32.105959,0);
		path = "Ariel1.png";
		//try {
		//this.BackgroundPic = ImageIO.read(new File("Ariel1.png"));
		//} catch (Exception e) {}
		//frame.setMyImage(this.BackgroundPic);
	
	}
	
	

}
