package GIS;

import java.util.Date;

import Geom.Point3D;

/**
 * the class is responsible to represent fruit 
 * @author EILON
 *
 */
public class fruit_metaData implements Meta_Data {
	private int id;
	private double weight;
	/**
	 * the constructor get Array of strings that represent the fruit and get array of indexes. 
	 * by the array of indexes it know to put the information in the suitable field
	 * @param line the Array of strings 
	 * @param metaIndexes the array of indexes
	 */
	public fruit_metaData(String[] line) {
		id = Integer.parseInt(line[1]);
		weight = Double.parseDouble(line[5]);	
	}
	/**
	 * the constructor of fruit meta data that get id and weight
	 * @param id  int
	 * @param weight int
	 */
	public fruit_metaData(int id,int weight) {
		this.id=id;
		this.weight=weight;
	}
	/**
	 * the copy constructor of fruit meta data
	 * @param other fruit_metaData object
	 */
	public fruit_metaData(fruit_metaData other) {
		this.id = other.getId();
		this.weight = other.getweight();

	}

    /**
     * @return the id
     */
	public int getId() {
		return id;
	}
    /**
     * @return the weight
     */
	public double getweight() {
		return weight;
	}

	/**
	 * set the this.id by the id that is get
	 * @param id integer parameter
	 */
	public void setId(int id) {
		this.id = id;
	}
    /**
     * @return the representation of fruit metadata
     */
	@Override
	public String toString() {
		return "fruit [id=" + id + ", weight=" + weight + "]";
	}
    /**
     * @return the accurate time
     */
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