package algorithms;

import java.util.ArrayList;
import java.util.Iterator;

import Coords.MyCoords;
import GIS.GameBoard;
import GIS.fruit;
import Geom.*;

public class starting_point {
	
	public static Point3D find(GameBoard GB) {
		//create groups of fruit without the groups that related to some pachman 
		fruit_group_layer fruits_gruops = new fruit_group_layer(GB);
		ArrayList<fruit_group> groupsRelatedPachmans = fruits_gruops.gruops_related_to_pachmans();
		fruits_gruops.removeAll(groupsRelatedPachmans);
		fruit_group maxGroupNotRelated = fruit_group_layer.maxGroup(fruits_gruops);
		int randomIndex = (int)(Math.random()*maxGroupNotRelated.size());
		fruit starting_fruit = maxGroupNotRelated.getFruits().get(randomIndex);
		Point3D fruit_loc = ((geom)starting_fruit.getGeom()).getP();
		MyCoords MC = new MyCoords();
		
		return MC.add(fruit_loc, new Point3D(5,5,0));
	}
}
