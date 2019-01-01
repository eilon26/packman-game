//package GUI;
//
//import java.util.Stack;
//
//import Coords.MyCoords;
//import FileFormat.GB2mat;
//import GIS.ariel_map;
//import Geom.Point3D;
//import Geom.geom;
//import algorithms.TwoPointsRoute;
//import algorithms.twoPointDijkstra;
//
//public class auto_paint_thread extends Thread{
//	private JPanelWithBackground jpanel;
//	private Point3D player_loc;
//	private Stack<Point3D> route;
//	private MyCoords MC = new MyCoords();
//	private ariel_map m;
//	
//	public auto_paint_thread (JPanelWithBackground jpanel,Point3D dst_point) {
//		this.jpanel = jpanel;
//		this.player_loc = ((geom)jpanel.getGB().getPlayer().getGeom()).getP();//global location
////		TwoPointsRoute TPR = new TwoPointsRoute(jpanel);
////		this.route = TPR.bfs(player_loc, dst_point, new GB2mat(jpanel).getMat());
//		this.route = twoPointDijkstra.Dijkstra(jpanel, player_loc, dst_point, new GB2mat(jpanel).getMat());
//		this.m = new ariel_map(jpanel);
//	}
//	
//	/**
//	 * auto_paint_thread run method
//	 */
//	public void run() {
//		
//		while(!route.isEmpty()) {
//			Point3D curr_dst = m.pixel2global(route.pop());
//			double[] AED = MC.azimuth_elevation_dist(player_loc, curr_dst);
//			jpanel.setAzimuth(AED[0]);
//			try {Thread.sleep((long) (100));//refer to the defined time rati
//			} catch (InterruptedException e) {}
//			this.jpanel.repaint();
//			player_loc = ((geom)jpanel.getGB().getPlayer().getGeom()).getP();//global location
//		}
//	}
//
//}
