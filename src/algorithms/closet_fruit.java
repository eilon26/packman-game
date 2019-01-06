package algorithms;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;


import GIS.GameBoard;
import GIS.fruit;
import GUI.MainJPanel;
import Geom.Point3D;
import Geom.geom;
/**
 * the class use advence_dijkstra in order to find the closet fruit to the player and the route to this fruit
 * @author EILON
 *
 */
public class closet_fruit {
	private Stack<Point3D> minRoute;
	private double minDist;
	private fruit dst_fruit;
/**
 * the constructor of closet_fruit. it goes through all the fruits and find the closet one. it is
 * build the route and its distance and also save the destination fruit.
 * @param GB GameBoard
 * @param jpanel MainJPanel
 * @param fruit_To_Remove ArrayList<fruit>
 */
	public closet_fruit(GameBoard GB, MainJPanel jpanel, ArrayList<fruit> fruit_To_Remove) {
		minDist = Double.MAX_VALUE;
		minRoute=null;
		dst_fruit = null;
		Iterator<fruit> IterFruit = GB.getFruits().iterator();
		while (IterFruit.hasNext()) {	
			fruit curr = IterFruit.next();
			if ((fruit_To_Remove == null)||(!fruit_To_Remove.contains(curr))) {
				Point3D fruit_loc = ((geom)curr.getGeom()).getP();
				Point3D player_loc = ((geom)GB.getPlayer().getGeom()).getP();
				advence_dijkstra Dijkstra = new advence_dijkstra(jpanel,player_loc,fruit_loc);
				double curr_dist = Dijkstra.getRouteDist();
				if (curr_dist<minDist) {
					minDist = curr_dist;
					minRoute = Dijkstra.getRoute();
					dst_fruit = curr;
				}
			}

		}
	}
	/**
	 * 
	 * @return the stack that contain the points on the route. 
	 * the first one to pop up is the first one to go to.
	 */
	public Stack<Point3D> getMinRoute() {
		return minRoute;
	}

	public double getMinDist() {
		return minDist;
	}

	public fruit getDst_fruit() {
		return dst_fruit;
	}

}
