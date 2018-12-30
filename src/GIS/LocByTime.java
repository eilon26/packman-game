package GIS;


import java.sql.Timestamp;
import Geom.Point3D;
/**
 * the class is responsible for represent location and its suitable time
 * @author EILON
 *
 */
public class LocByTime {
	private Point3D location;
	private String time;
	private long utc;
	/**
	 * the empty constructor
	 */
	public LocByTime() {
		this.location = null;
		this.time = null;
		this.utc = 0;
	}
	/**
	 * constructor that get location and time
	 * @param location Point3D parameter
	 * @param time long parameter
	 */
	public LocByTime(Point3D location, long time) {
		this.location = location;
		this.time = new Timestamp(time).toString();
		this.time = this.time.substring(0, 10)+"T"+this.time.substring(11, 19)+"Z";
		this.utc = time;
	}
	/**
	 * 
	 * @return location
	 */
	public Point3D getLocation() {
		return location;
	}
	/**
	 * 
	 * @return time
	 */
	public String getTime() {
		return time;
	}

	public long getUtc() {
		return utc;
	}

}

