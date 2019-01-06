package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import Coords.MyCoords;
import GIS.*;
import Geom.Point3D;
import Geom.geom;
/**
 * this class represent group of groups of fruits
 * The class responsibility is to divide the fruits into groups
 *  by their distance from each other (without consider the boxes). 
 * 
 * @author EILON
 *
 */
public class fruit_group_layer implements Set<fruit_group>{
	private ArrayList<fruit_group> groups;
	private GameBoard GB;
	private final int MAX_DIS_IN_GROUP = 140;
	private final int MAX_DIS_TO_ASSOCIATE_PACH = 100;
	private static MyCoords MC = new MyCoords();
/**
 * the constractor of fruit_group_layer
 * @param GB GameBoard
 */
	public fruit_group_layer(GameBoard GB) {
		this.GB = GB;
		//create collection of all the fruits
		ArrayList<fruit> fruit_colection = new ArrayList<fruit>();
		Iterator<fruit> IterFruit = GB.getFruits().iterator();
		while (IterFruit.hasNext()) {
			fruit curr = IterFruit.next();
			fruit_colection.add(curr);
		}
		//creating groups of fruits

		this.groups = new ArrayList<fruit_group>();
		int id = 0;
		while (!fruit_colection.isEmpty()) {
			fruit main_fruit = fruit_colection.get(0);
			//fruit_colection.remove(main_fruit);
			fruit_group curr_group = create_group(main_fruit, fruit_colection, id++);
			this.groups.add(curr_group);
		}
	}
/**
 * a private function that help to divide the fruits into goups
 * @param main_fruit fruit 
 * @param fruit_colection  ArrayList<fruit>
 * @param id int
 * @return  a fruit_group parameter that represent a group
 */
	private fruit_group create_group(fruit main_fruit, ArrayList<fruit> fruit_colection,int id) {
		
		fruit_group curr_group = new fruit_group(id);
		curr_group.addFruit(main_fruit);
		Point3D main_fruit_point = ((geom)main_fruit.getGeom()).getP();
		//		boolean cros;
		boolean close;
		Iterator<fruit> IterSecFruit = fruit_colection.iterator();
		IterSecFruit.next();//pass the "main fruit"
		while (IterSecFruit.hasNext()) {
			fruit sec_fruit = IterSecFruit.next();
			Point3D sec_fruit_point = ((geom)sec_fruit.getGeom()).getP();
			//cros = cross.isCross(main_fruit_point, sec_fruit_point, Box_set);
			close = MC.distance3d(main_fruit_point, sec_fruit_point)<MAX_DIS_IN_GROUP;

			if (close) {
				curr_group.addFruit(sec_fruit);
				//fruit_colection.remove(sec_fruit);
			}
		}
		fruit_colection.removeAll(curr_group);
		return curr_group;
	}
/**
 * find the fruits that related to a pachman
 * @param pach pachman
 * @return the fruits that related to a pach parameter inside a fruit_group parameter
 */
	private fruit_group find_pachman_fruits(pachman pach) {
		fruit_group min_group = null;
		double min_dist = Double.MAX_VALUE;
		MyCoords MC = new MyCoords();
		Iterator<fruit_group> IterGroup = this.iterator();
		while (IterGroup.hasNext()) {
			fruit_group curr_group = IterGroup.next();
			Iterator<fruit> Iterfruit = curr_group.iterator();
			while (Iterfruit.hasNext()) {
				fruit curr_fruit = Iterfruit.next();
				Point3D curr_P = ((geom)curr_fruit.getGeom()).getP();
				Point3D pach_P = ((geom)pach.getGeom()).getP();
				double curr_dist = MC.distance3d(curr_P, pach_P);
				if (curr_dist<min_dist) {
					min_dist = curr_dist;
					min_group = curr_group;
				}
			}
		}
		if ((min_group==null)|| (min_dist>MAX_DIS_TO_ASSOCIATE_PACH)) return null;//there is no fruits or the pachmnan is too far from the closet fruits group
		return min_group;
	}
	/**
	 * 
	 * @return  the fruits that the packmans are going to eat soon
	 */
	public ArrayList<fruit> fruits_related_to_pachmans(){
		ArrayList<fruit_group> fruit_groups = new ArrayList<fruit_group>();
		Iterator<pachman> IterPach = GB.getPachmans().iterator();
		while (IterPach.hasNext()) {
			pachman curr_pach = IterPach.next();
			fruit_group pachman_fruits = find_pachman_fruits(curr_pach);
			if ((pachman_fruits!=null)&& (!isContainsID(fruit_groups,pachman_fruits.getId()))) {
				fruit_groups.add(pachman_fruits);
			}
		}
		return convertFruitGroups2fruitsArrylist(fruit_groups);
	}
	/**
	 * 
	 * @return  the groups of fruits that the packmans are going to eat soon
	 */
	public ArrayList<fruit_group> gruops_related_to_pachmans(){
		ArrayList<fruit_group> fruit_groups = new ArrayList<fruit_group>();
		Iterator<pachman> IterPach = GB.getPachmans().iterator();
		while (IterPach.hasNext()) {
			pachman curr_pach = IterPach.next();
			fruit_group pachman_fruits = find_pachman_fruits(curr_pach);
			if ((pachman_fruits!=null)&& (!isContainsID(fruit_groups,pachman_fruits.getId()))) {
				fruit_groups.add(pachman_fruits);
			}
		}
		return fruit_groups;
	}
	/**
	 * check if a specific fruit_group are already inside ArrayList of fruit_group
	 * @param fruit_groups ArrayList<fruit_group>
	 * @param id  int
	 * @return true of the arraylist of the parameter fruit_group contain a fruit_group with a specific id
	 */
	private boolean isContainsID(ArrayList<fruit_group> fruit_groups,int id) {
		Iterator<fruit_group> IterGroup = fruit_groups.iterator();
		while (IterGroup.hasNext()) {
			fruit_group curr_group = IterGroup.next();
			if (curr_group.getId()==id) return true;
		}
		return false;
	}

	private ArrayList<fruit> convertFruitGroups2fruitsArrylist(ArrayList<fruit_group> fruit_groups){
		ArrayList<fruit> fruits = new ArrayList<fruit>();
		Iterator<fruit_group> IterGroup = fruit_groups.iterator();
		while (IterGroup.hasNext()) {
			fruit_group curr_group = IterGroup.next();
			Iterator<fruit> IterFruit = curr_group.iterator();
			while (IterFruit.hasNext()) {
				fruit curr_fruit = IterFruit.next();
				fruits.add(curr_fruit);
			}
		}
		return fruits;
	}
	/**
	 * find the biggest fruit_group inside fruit_group_layer
	 * @param groups fruit_group_layer parameter
	 * @return a fruit_group parameter that is the biggest fruit_group inside a specific fruit_group_layer
	 */
	public static fruit_group maxGroup(fruit_group_layer groups) {
		int max = -1;
		fruit_group max_group=null;
		Iterator<fruit_group> IterGroup = groups.iterator();
		while (IterGroup.hasNext()) {
			fruit_group curr_group = IterGroup.next();
			if (curr_group.size()>max) {
				max = curr_group.size();
				max_group = curr_group;
			}
		}
		return max_group;
	}
	/**
	 * the static function find the two farest points in the group
	 * @param group fruit_group
	 * @return one of the two farest points in the group
	 */
	public static Point3D farestPoints(fruit_group group) {
		double max_dist = Double.MIN_VALUE;
		Point3D max_point = ((geom)group.getFruits().get(0).getGeom()).getP();
		Iterator<fruit> IterMainfruit = group.iterator();
		while (IterMainfruit.hasNext()) {
			fruit main_fruit = IterMainfruit.next();
			Point3D main_fruit_point = ((geom)main_fruit.getGeom()).getP();
			Iterator<fruit> IterSecfruit = group.iterator();
			while (IterSecfruit.hasNext()) {
				fruit sec_fruit = IterSecfruit.next();
				if (sec_fruit!=main_fruit) {
					Point3D sec_fruit_point = ((geom)sec_fruit.getGeom()).getP();
					double curr_dist = MC.distance3d(main_fruit_point, sec_fruit_point);
					if (curr_dist>max_dist) {
						max_dist = curr_dist;
						max_point = main_fruit_point;
					}
				}
			}
		}
		return max_point;
	}
	@Override
	public boolean add(fruit_group arg0) {
		// TODO Auto-generated method stub
		return groups.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends fruit_group> arg0) {
		// TODO Auto-generated method stub
		return groups.addAll(arg0);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		groups.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return groups.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return groups.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return groups.isEmpty();
	}

	@Override
	public Iterator<fruit_group> iterator() {
		// TODO Auto-generated method stub
		return groups.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return groups.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return groups.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return groups.retainAll(arg0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return groups.size();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return groups.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return groups.toArray(arg0);
	}
}
