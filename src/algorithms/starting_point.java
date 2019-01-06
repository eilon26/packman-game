package algorithms;

import java.util.ArrayList;
import java.util.Iterator;

import Coords.MyCoords;
import GIS.GameBoard;
import GUI.MainJPanel;
import Geom.*;
/**
 * this class is responsible to determine witch point will be the starting point of the player
 * @author EILON
 *
 */
public class starting_point {
	private static MyCoords MC = new MyCoords();
/**
 * calculate what will be the best point to start with for the player
 * @param GB GameBoard
 * @param jpanel MainJPanel
 * @return Point3D that represent the best point to start with for the player
 */
	public static Point3D find(GameBoard GB, MainJPanel jpanel) {
		// create groups of fruit without the groups that related to some pachman
		fruit_group_layer fruits_gruops = new fruit_group_layer(GB);
		ArrayList<fruit_group> groupsRelatedPachmans = fruits_gruops.gruops_related_to_pachmans();
		if (groupsRelatedPachmans.size() < fruits_gruops.size())
			fruits_gruops.removeAll(groupsRelatedPachmans);
		// find the the groups with the max distance between them
		double maxDist = -1;
		Point3D maxP = null;
		Point3D mainP = null;
		Iterator<fruit_group> IterGroup = fruits_gruops.iterator();
		while (IterGroup.hasNext()) {
			fruit_group mainGroup = IterGroup.next();
			mainP = fruit_group_layer.farestPoints(mainGroup);
			Iterator<fruit_group> IterSecGroup = fruits_gruops.iterator();
			while (IterSecGroup.hasNext()) {
				fruit_group secGroup = IterSecGroup.next();
				if (secGroup != mainGroup) {
					Point3D secP = fruit_group_layer.farestPoints(secGroup);
					double dist = new advence_dijkstra(jpanel, secP, mainP).getRouteDist();
					if (dist > maxDist) {
						maxDist = dist;
						if (mainGroup.size()>secGroup.size())
							maxP = mainP;
						else
							maxP = secP;
					}
				}
			}
		}
		if (maxP == null)
			maxP = mainP;
		return MC.add(maxP, new Point3D(1, 1, 0));
	}

}
