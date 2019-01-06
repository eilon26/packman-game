package GIS;

import java.util.Date;

import Geom.Point3D;
/**
 * the class is responsible to represent pachman,ghost and player 
 * @author EILON
 *
 */
public class pachman_metaData implements Meta_Data {
	private int id;
	private double speed;
	private double radius;
	/**
	 * the constructor get Array of strings that represent the pachman(or ghost or player ) and get array of indexes. 
	 * by the array of indexes it know to put the information in the suitable field
	 * @param line the Array of strings 
	 */
	public pachman_metaData(String[] line) {
		this.id = Integer.parseInt(line[1]);
		this.speed = Double.parseDouble(line[5]);
		this.radius = Double.parseDouble(line[6]);
	}
	/**
	 * the constructor of pachman meta data that get id, speed and radius
	 * @param id int
	 * @param speed int
	 * @param radius int
	 */
	public pachman_metaData(int id,int speed,int radius) {
		this.id=id;
		this.speed=speed;
		this.radius=radius;
	}
	/**
	 * the copy constructor
	 * @param md pachman_metaData
	 */
	public pachman_metaData(pachman_metaData md) {
		this.id=md.id;
		this.speed=md.speed;
		this.radius=md.radius;

	}

	/**
	 *  
	 * @return pachman id
	 */
	public int getId() {
		return id;
	}
	/**
	 *  
	 * @return pachman speed
	 */
	public double getSpeed() {
		return speed;
	}
	
	/**
	 * set pachman id by id
	 * @param id int
	 */
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return " [id=" + id + ", speed=" + speed + ", radius=" + radius + "]";
	}
	/**
	 * 
	 * @return pachman radius
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	public long getUTC() {
		return new Date().getTime();
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}

}
