package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import Coords.MyCoords;
import Geom.Point3D;
import Geom.geom;
import algorithms.cross;

public class fruit_group_layer implements Set<fruit_group>{
	ArrayList<fruit_group> groups;
	
	public fruit_group_layer(GameBoard GB) {
		//create collection of all the fruits
		ArrayList<fruit> fruit_colection = new ArrayList<fruit>();
		Iterator<GIS_element> IterElement = GB.iterator();
		while (IterElement.hasNext()) {
			GIS_element curr = IterElement.next();
			if (curr instanceof fruit) fruit_colection.add((fruit) curr);
		}
		//creating groups of fruits
		MyCoords MC = new MyCoords();
		this.groups = new ArrayList<fruit_group>();
		int id = 0;
		Iterator<fruit> IterMainFruit = fruit_colection.iterator();
		while (IterMainFruit.hasNext()) {
			fruit main_fruit = IterMainFruit.next();
			fruit_group curr_group = new fruit_group(id++);
			Point3D main_fruit_point = ((geom)main_fruit.getGeom()).getP();
			curr_group.addFruit(main_fruit);
			boolean cros;
			boolean close;
			Iterator<fruit> IterSecFruit = fruit_colection.iterator();
			while (IterSecFruit.hasNext()) {
				fruit sec_fruit = IterSecFruit.next();
				Point3D sec_fruit_point = ((geom)sec_fruit.getGeom()).getP();
				cros = cross.isCross(main_fruit_point, sec_fruit_point, GB.getBox_set());
				close = MC.distance3d(main_fruit_point, main_fruit_point)<80;
				if (!cros&&close) curr_group.addFruit(sec_fruit);
			}
			
			this.groups.add(curr_group);
			
		}
	}

	@Override
	public boolean add(fruit_group arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends fruit_group> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<fruit_group> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
