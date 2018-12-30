package GIS;

import java.util.ArrayList;
import java.util.Collection;

import java.util.Iterator;

/**
 * the class is contain all the fruit and the pachman objects in the game
 * @author Daniel Ventura and Eilon tsadok
 *
 */
public class GameBoard implements GIS_layer  {
	private ArrayList<GIS_element> element_set;
	private ArrayList<box> box_set;
	private player player;
	/**
	 * the constructor
	 */
	public GameBoard() {
		this.player = null;
		this.element_set=new ArrayList<GIS_element>();
		this.box_set = new ArrayList<box>();
	}
	/**
	 * the copy constactor
	 * @param other GameBoard parameter
	 */
//	public GameBoard(GameBoard other) {
//		this.element_set = new ArrayList<GIS_element>();
//		Iterator<GIS_element>  IterE = other.iterator();
//		while (IterE.hasNext()) {
//			GIS_element curr = IterE.next();
//			if (curr instanceof pachman)
//				this.element_set.add(new pachman((pachman)curr));
//			else this.element_set.add(new fruit((fruit)curr));
//		}
//	}

/**
 * constractor that buid GameBoard from mat(after taking the csv file and convert it to mat)
 * @param c2m csv2mat parameter
 */
	public GameBoard(ArrayList<String[]> g){
		this();
		for (int i=0;i<g.size();i++) {
			String[] line = g.get(i);
			if (line[0].contains("P")) {
				pachman pach = new pachman(line);
				element_set.add(pach);
			}else if (line[0].contains("F")){
				fruit fru = new fruit(line);
				element_set.add(fru); 
			}else if (line[0].contains("G")){
				Ghost ghost = new Ghost(line);
				element_set.add(ghost);
			}else if (line[0].contains("B")){
				box box = new box(line);
				box_set.add(box);
			}else {
				player player = new player(line);
//				element_set.add(player);
				this.player = player;
			}
		}
	}

	/**
	 * 
	 * @return element_set
	 */
	public ArrayList<GIS_element> getElement_Set(){
		return element_set;
	}
	
	public ArrayList<box> getBox_set() {
		return box_set;
	}

	public player getPlayer() {
		return player;
	}



	@Override
	public synchronized boolean add(GIS_element arg0) {
		return element_set.add((arg0));
	}
	public synchronized boolean add_Box(box arg0) {
		return box_set.add((arg0));
	}
	
	@Override
	public synchronized boolean addAll(Collection<? extends GIS_element> arg0) {
		
		return element_set.addAll(arg0);
	}

	@Override
	public void clear() {
		element_set.clear();
		
	}

	@Override
	public boolean contains(Object arg0) {
		return element_set.contains(arg0);//add element.equals()
	}
	public boolean contains_box(Object arg0) {
		return box_set.contains(arg0);//add element.equals()
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return element_set.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return element_set.isEmpty();
	}

	@Override
	public synchronized  Iterator<GIS_element> iterator() {
		return element_set.iterator();
	}
	public synchronized  Iterator<box> box_iterator() {
		return box_set.iterator();
	}
	@Override
	public synchronized boolean remove(Object arg0) {
		return element_set.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return element_set.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return element_set.retainAll(arg0);
	}

	@Override
	public int size() {
		return element_set.size();
	}

	@Override
	public Object[] toArray() {
		return element_set.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return element_set.toArray(arg0);
	}
	@Override
	public Meta_Data get_Meta_data() {
		// TODO Auto-generated method stub
		return null;
	}

}
