package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import GIS.fruit;
import Geom.Point3D;
import Geom.geom;


public class fruit_group implements Set<fruit>{
	private int id;
	private ArrayList<fruit> fruits;
	
	
	public fruit_group(int id) {
		this.id = id;
		this.fruits = new ArrayList<fruit>();
	}
	
	public void addFruit(fruit fruit) {
		this.fruits.add(fruit);
	}

	public int getId() {
		return id;
	}

	public ArrayList<fruit> getFruits() {
		return fruits;
	}
	public Point3D group_center() {
		Point3D center = null;
		double sumX = 0;
		double sumY = 0;
		Iterator<fruit> IterFruit = fruits.iterator();
		while (IterFruit.hasNext()) {
			fruit curr = IterFruit.next();
			sumX+=((geom)curr.getGeom()).getP().x();
			sumY+=((geom)curr.getGeom()).getP().y();
		}
		if (sumX!=0) center = new Point3D(sumX/fruits.size(),sumY/fruits.size());
		return center;
	}
	
	@Override
	public boolean add(fruit arg0) {
		// TODO Auto-generated method stub
		return fruits.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends fruit> arg0) {
		// TODO Auto-generated method stub
		return fruits.addAll(arg0);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		fruits.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return fruits.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return fruits.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return fruits.isEmpty();
	}

	@Override
	public Iterator<fruit> iterator() {
		// TODO Auto-generated method stub
		return fruits.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return fruits.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return fruits.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return fruits.retainAll(arg0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return fruits.size();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return fruits.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return fruits.toArray(arg0);
	}

}
