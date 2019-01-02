package algorithms;

import java.util.ArrayList;
import java.util.Iterator;

import Coords.MyCoords;
import GIS.GameBoard;
import GIS.fruit;
import GUI.JPanelWithBackground;
import Geom.*;

public class starting_point {
	private static MyCoords MC = new MyCoords();
	public static Point3D find1(GameBoard GB) {
		//create groups of fruit without the groups that related to some pachman 
		fruit_group_layer fruits_gruops = new fruit_group_layer(GB);
		ArrayList<fruit_group> groupsRelatedPachmans = fruits_gruops.gruops_related_to_pachmans();
		if (groupsRelatedPachmans.size()<fruits_gruops.size()) 
			fruits_gruops.removeAll(groupsRelatedPachmans);
		//find the biggest group
		fruit_group maxGroupNotRelated = fruit_group_layer.maxGroup(fruits_gruops);
		int randomIndex = (int)(Math.random()*maxGroupNotRelated.size());
		fruit starting_fruit =  maxGroupNotRelated.getFruits().get(randomIndex);
		Point3D fruit_loc = ((geom)starting_fruit.getGeom()).getP();
		

		return MC.add(fruit_loc, new Point3D(5,5,0));
	}

	public static Point3D find(GameBoard GB) {
		fruit starting_fruit = GB.getFruits().get((int)(Math.random()*GB.getFruits().size()));
		Point3D fruit_loc = ((geom)starting_fruit.getGeom()).getP();
		return MC.add(fruit_loc, new Point3D(5,5,0));
	}

	public static Point3D find2(GameBoard GB) {
		//create groups of fruit without the groups that related to some pachman 
		fruit_group_layer fruits_gruops = new fruit_group_layer(GB);
		fruit_group maxGroupNotRelated = fruit_group_layer.maxGroup(fruits_gruops);
		int randomIndex = (int)(Math.random()*maxGroupNotRelated.size());
		fruit starting_fruit =  maxGroupNotRelated.getFruits().get(randomIndex);
		Point3D fruit_loc = ((geom)starting_fruit.getGeom()).getP();

		return MC.add(fruit_loc, new Point3D(5,5,0));
	}

	public static Point3D find3(GameBoard GB,JPanelWithBackground jpanel) {
		//create groups of fruit without the groups that related to some pachman 
		fruit_group_layer fruits_gruops = new fruit_group_layer(GB);
		ArrayList<fruit_group> groupsRelatedPachmans = fruits_gruops.gruops_related_to_pachmans();
		if (groupsRelatedPachmans.size()<fruits_gruops.size()) 
			fruits_gruops.removeAll(groupsRelatedPachmans);
		//find the the groups with the max distance between them 
		double maxDist = -1; 
		Point3D maxP = null;
		Iterator<fruit_group> IterGroup = fruits_gruops.iterator();
		while (IterGroup.hasNext()) {
			fruit_group mainGroup = IterGroup.next();
			int randomNum= (int)(Math.random()*mainGroup.size());
			Point3D mainP = ((geom)mainGroup.getFruits().get(randomNum).getGeom()).getP();
			Iterator<fruit_group> IterSecGroup = fruits_gruops.iterator();
			while (IterSecGroup.hasNext()) {
				fruit_group secGroup = IterSecGroup.next();
				if (secGroup!=mainGroup) {
					randomNum= (int)(Math.random()*secGroup.size());
					Point3D secP = ((geom)secGroup.getFruits().get(randomNum).getGeom()).getP();
					double dist = new advence_dijkstra(jpanel,secP,mainP).getRouteDist();
					if (dist>maxDist) {
						maxDist = dist;
						if (mainGroup.size()>secGroup.size())
							maxP = mainP;
						else maxP = secP;
					}
				}
			}
		}
		return MC.add(maxP, new Point3D(5,5,0));
	}
}
