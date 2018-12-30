package GIS;

import java.text.SimpleDateFormat;
import java.util.Date;

import GIS.Meta_Data;
import Geom.Point3D;
/**
 * this class takes the information from the line and posts it under
 * the right subject.
 * @author Daniel Ventura
 *
 */
public class element_metaData implements Meta_Data{
	private String time;
	private String name;
	private int rssi;
	private String mac;
	private String AuthMode;
	private int Channel;
	private int AccuracyMeters;
	private String Type;
	private String color;
	
	/**
	 * this function take out of the line the information and stores it.
	 * @param line - 1 line from the file.
	 * @param metaIndexes - the indexes of the information.
	 */
	public element_metaData(String[] line,int[] metaIndexes) {
		time = line[metaIndexes[0]];
		rssi = Integer.parseInt(line[metaIndexes[1]]);
		name = line[metaIndexes[2]];	
		mac = line[metaIndexes[3]];
		AuthMode = line[metaIndexes[4]];
		Channel = Integer.parseInt(line[metaIndexes[5]]);
		AccuracyMeters = Integer.parseInt(line[metaIndexes[6]]);
		Type = line[metaIndexes[7]];
		if (rssi<-90) color="red";
		else color = "green";
	}
	
	/**
	 * this function is a copy.copies "other" info into out info
	 * @param other - the other meta data element.
	 */
	public element_metaData(element_metaData other) {
		this.time = other.time;
		this.name=new String(other.name);
		this.rssi=other.rssi;
		this.mac = other.mac;
		this.AuthMode = other.AuthMode;
		this.Channel = other.Channel;
		this.AccuracyMeters = other.AccuracyMeters;
		this.Type = other.Type;
		this.color= other.color;
	}
	/**
	 * @return a String that describes the class.
	 */
	@Override
	public String toString() {
		return "name: "+this.name+", time: "+this.time+", rssi: "+this.rssi+", mac:"+this.mac+", authMode: "+this.AuthMode+", Channel: " + this.Channel +", AccuracyMeters:" +this.AccuracyMeters+", type: "+this.Type;
	}
	/**
	 * @return color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @return time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @return mac
	 */
	public String getMac() {
		return mac;
	}
	/**
	 * @return authmode
	 */
	public String getAuthMode() {
		return AuthMode;
	}
	/**
	 * @return channel
	 */
	public int getChannel() {
		return Channel;
	}
	/**
	 * @return accuracy
	 */
	public int getAccuracyMeters() {
		return AccuracyMeters;
	}
	/**
	 * @return type
	 */
	public String getType() {
		return Type;
	}
	/**
	 * @return converts the time into utc and returns the time.
	 */
	@Override
	public long getUTC() {
		SimpleDateFormat pattern = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		Date d = null;
			try {
				d = pattern.parse(time);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return d.getTime();
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return rssi
	 */
	public int getRssi() {
		return rssi;
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}

}