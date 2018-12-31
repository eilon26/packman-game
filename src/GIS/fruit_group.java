package GIS;

import java.util.ArrayList;

import Geom.Point3D;

public class fruit_group{
	//private int id;
	private ArrayList<fruit> fruits;
	//private ArrayList<pachman> relayted_pachman;
	
	public fruit_group(int id) {
//		this.id = id;
		this.fruits = new ArrayList<fruit>();
//		this.relayted_pachman = new ArrayList<pachman>();
	}
	
	public void addFruit(fruit fruit) {
		this.fruits.add(fruit);
		
	}
	
//	public void addPachman(pachman pach) {
//		this.relayted_pachman.add(pach);
//	}
	
	
	
	
	
}
