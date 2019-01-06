package GIS;

import java.util.ArrayList;


import java.util.Iterator;
import Geom.geom;

/**
 * the class contains all the characters in the game in the game
 * @author Daniel Ventura and Eilon tsadok
 *
 */
public class GameBoard {
	private ArrayList<fruit> fruits;
	private ArrayList<pachman> pachmans;
	private ArrayList<Ghost> ghosts;
	private ArrayList<box> box_set;
	private player player;
	/**
	 * the empty constructor
	 */
	public GameBoard() {
		this.player = null;
		this.fruits = new ArrayList<fruit>();
		this.pachmans = new ArrayList<pachman>();
		this.ghosts = new ArrayList<Ghost>();
		this.box_set = new ArrayList<box>();
	}

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
				pachmans.add(pach);
			}else if (line[0].contains("F")){
				fruit fru = new fruit(line);
				fruits.add(fru); 
			}else if (line[0].contains("G")){
				Ghost ghost = new Ghost(line);
				ghosts.add(ghost);
			}else if (line[0].contains("B")){
				box box = new box(line);
				box_set.add(box);
			}else {
				player player = new player(line);
				this.player = player;
			}
		}
	}
	
/**
 * the function get the parameter "board_data" and update the fields that change
 * @param board_data ArrayList<String>
 */
	public void update_GameBoard(ArrayList<String> board_data){
		ArrayList<String[]> Board_Data = new ArrayList<String[]>();
		for(int i=0;i<board_data.size();i++) {
			String[] element = board_data.get(i).split(",");
			Board_Data.add(element);
		}
		//update the player location and speed
		int player_ID = ((pachman_metaData)player.getData()).getId();
		String[] player_new_details = find_ID(Board_Data, player_ID,"M");
		geom newGeom = new geom(player_new_details);
		pachman_metaData newMetaData = new pachman_metaData(player_new_details);
		player.set_Geom(newGeom);
		player.setMd(newMetaData);
		//update the fruits 
		ArrayList<fruit> FtoBeRemove = new ArrayList<fruit>();
		Iterator<fruit> IterFruit = fruits.iterator();
		while (IterFruit.hasNext()) {
			fruit curr = IterFruit.next();
			int curr_ID = ((fruit_metaData)curr.getData()).getId();
			String[] fruit_new_details = find_ID(Board_Data, curr_ID,"F");
			if (fruit_new_details==null) 
				FtoBeRemove.add(curr);
			else {
				newGeom = new geom(fruit_new_details);
				((fruit) curr).set_Geom(newGeom);
			}
		}
		fruits.removeAll(FtoBeRemove);
		//update the pachmans
		ArrayList<pachman> PtoBeRemove = new ArrayList<pachman>();
		Iterator<pachman> IterPach = pachmans.iterator();
		while (IterPach.hasNext()) {
			pachman curr = IterPach.next();
			int curr_ID = ((pachman_metaData)curr.getData()).getId();
			String[] pachman_new_details = find_ID(Board_Data, curr_ID,"P");
			if (pachman_new_details==null) 
				PtoBeRemove.add(curr);
			else {
				newGeom = new geom(pachman_new_details);
				((pachman) curr).set_Geom(newGeom);
			}	
		}
		pachmans.removeAll(PtoBeRemove);
		
		//update the ghosts location
		Iterator<Ghost> IterGhost = ghosts.iterator();
		while (IterGhost.hasNext()) {
			Ghost curr = IterGhost.next();
			int curr_ID = ((pachman_metaData)curr.getData()).getId();
			String[] Ghost_new_details = find_ID(Board_Data, curr_ID,"G");
			newGeom = new geom(Ghost_new_details);
			((Ghost) curr).set_Geom(newGeom);	
		}
	}
/**
 * find the String[] from an ArrayList<String[]> that contains the information about
 * the character with the id "id"  and with the type "character_type"
 * @param g ArrayList<String[]>
 * @param id int
 * @param character_type String
 * @return the String[] from an ArrayList<String[]> that contains the information about
 * the character with the id "id"  and with the type "character_type"
 * if there is no one like that, it will return null
 */
	private String[] find_ID(ArrayList<String[]> g, int id,String character_type) {
		Iterator<String[]> IterLine = g.iterator();
		while(IterLine.hasNext()) {
			String[] line = IterLine.next();
			if ((Integer.parseInt(line[1])==id)&&(line[0].equals(character_type))) return line; 
		}
		return null;
	}

	public ArrayList<box> getBox_set() {
		return box_set;
	}

	public player getPlayer() {
		return player;
	}

	public ArrayList<fruit> getFruits() {
		return fruits;
	}

	public ArrayList<pachman> getPachmans() {
		return pachmans;
	}

	public ArrayList<Ghost> getGhosts() {
		return ghosts;
	}



}
