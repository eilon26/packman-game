package GUI;

import java.util.Iterator;
import java.util.Stack;

import Coords.MyCoords;
import GIS.GIS_element;
import GIS.fruit;
import GIS.pachman_metaData;
import GIS.player;
import Geom.Point3D;
import Geom.geom;
import algorithms.advence_dijkstra;
import algorithms.closet_fruit;

public class auto_control_thread2 extends Thread {
	private JPanelWithBackground jpanel;
	private closet_fruit CF;

	public auto_control_thread2(JPanelWithBackground jpanel, closet_fruit CF) {
		this.jpanel = jpanel;
		this.CF = CF;
	}
	public void run() {

		if (CF.getMinRoute()!=null) {
			Point3D player_loc = ((geom)jpanel.getGB().getPlayer().getGeom()).getP();//global location
			Point3D curr_dst;
			MyCoords MC = new MyCoords();
			double[] AED;
			//		double time_to_next_fruit = CF.getMinDist()/20;
			//		if (!fruits_of_pachman.contains(CF.getDst_fruit())||(fruits_of_pachman.size()>=amount_of_fruits()){
			while (jpanel.getPlay1().isRuning()&&(!CF.getMinRoute().isEmpty())&&(jpanel.getGB().contains(CF.getDst_fruit()))) {
				curr_dst = CF.getMinRoute().pop();//global point
				player_loc = ((geom)jpanel.getGB().getPlayer().getGeom()).getP();//global location
				AED = MC.azimuth_elevation_dist(player_loc, curr_dst);
				jpanel.setAzimuth(AED[0]);
				double sec_to_curr_dst = AED[2]/20;
				Thread auto = new auto_thread(jpanel,sec_to_curr_dst,CF);
				auto.start();
				try {auto.join();
				} catch (InterruptedException e) {}
			}
		}
	}

}
