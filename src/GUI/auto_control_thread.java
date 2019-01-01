//package GUI;
//
//import java.util.Stack;
//
//import Coords.MyCoords;
//import GIS.pachman_metaData;
//import GIS.player;
//import Geom.Point3D;
//import Geom.geom;
//import algorithms.advence_dijkstra;
//
//public class auto_control_thread extends Thread {
//	private JPanelWithBackground jpanel;
//	private Point3D dst_point;
//	
//	public auto_control_thread(JPanelWithBackground jpanel,Point3D dst_point) {
//		this.jpanel = jpanel;
//		this.dst_point = dst_point;
//	}
//	public void run() {
//
//		Point3D player_loc = ((geom)jpanel.getGB().getPlayer().getGeom()).getP();//global location
//		advence_dijkstra dijkstra_route =  new advence_dijkstra(jpanel, player_loc, dst_point);
//		Stack<Point3D> route = dijkstra_route.getRoute();
//		Point3D curr_dst;
//		MyCoords MC = new MyCoords();
//		double[] AED;
//		while(!route.isEmpty()) {
//			curr_dst = route.pop();//global point
//			player_loc = ((geom)jpanel.getGB().getPlayer().getGeom()).getP();//global location
//			AED = MC.azimuth_elevation_dist(player_loc, curr_dst);
//			jpanel.setAzimuth(AED[0]);
//			double sec_to_curr_dst = AED[2]/20;
//			//Thread auto = new auto_thread(jpanel,sec_to_curr_dst);
//			//auto.start();
////			try {auto.join();
////			} catch (InterruptedException e) {}
//		}
//	}
//}
