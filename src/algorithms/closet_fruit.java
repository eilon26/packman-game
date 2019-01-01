package algorithms;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;


import GIS.GameBoard;
import GIS.fruit;
import GUI.JPanelWithBackground;
import Geom.Point3D;
import Geom.geom;

public class closet_fruit {
	private Stack<Point3D> minRoute;
	private double minDist;
	private fruit dst_fruit;

	public closet_fruit(GameBoard GB, JPanelWithBackground jpanel, ArrayList<fruit> fruit_To_Remove) {
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
