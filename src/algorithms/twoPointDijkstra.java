package algorithms;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import GIS.ariel_map;
import GUI.JPanelWithBackground;
import Geom.Point3D;

public class twoPointDijkstra {

	
	public static Stack<Point3D> Dijkstra(JPanelWithBackground jpanel, Point3D a, Point3D b,char[][] mat){
		//convert the points to pixel
		ariel_map m = new ariel_map(jpanel);
		Point3D pixel_player_loc = m.global2pixel(a);
		Point3D pixel_dst_loc = m.global2pixel(b);
		dijkstra_node[][] dijkstraMat = new dijkstra_node[mat.length][mat[0].length];
		//init all the nodes
		for (int r=0;r<mat.length;r++) {
			for(int c=0;c<mat[0].length;c++) {
				if (mat[r][c] !='B')
					dijkstraMat[r][c] = new dijkstra_node(c,r);
				else dijkstraMat[r][c]=null;
			}
		}
		//distance of src = 0
		dijkstraMat[(int)pixel_player_loc.y()][(int)pixel_player_loc.x()].setDis2src(0);
		
		//insert all the  into the Queue
		PriorityQueue<dijkstra_node> pq = new PriorityQueue<dijkstra_node>();
		for (int r=0;r<mat.length;r++) {
			for(int c=0;c<mat[0].length;c++) {
				if (dijkstraMat[r][c]!=null) pq.add(dijkstraMat[r][c]);
			}
		}
		
		
		dijkstra_node curr;
		int r;
		int c;
		while (!pq.isEmpty()) {
			curr = pq.remove();
//			System.out.println(curr.getDis2src());
//			try {Thread.sleep((long) (500));//refer to the defined time rati
//			} catch (InterruptedException e) {}
			r = (int) curr.getR();
			c = (int) curr.getC();
			//update all the neighbors of curr
			for (int row = r-1;row<=r+1;row++) {
				for (int col = c-1;col<=c+1;col++) {
					if ((row!=r)||(col!=c)) {
						if ((row<dijkstraMat.length)&&(col<dijkstraMat[0].length)&&(row>=0)&&(col>=0)&&(dijkstraMat[row][col]!=null)){
//							int dist_throgh_father = (dijkstraMat[r][c].getDis2src())+(Math.abs(row-r)+Math.abs(col-c));
//							double neighbor_dist = Math.sqrt(Math.abs(row-r)+Math.abs(col-c));
							int neighbor_dist = Math.abs(row-r)+Math.abs(col-c);
							if (neighbor_dist==1) neighbor_dist=10;
							else neighbor_dist=14;
							int dist_throgh_father = (dijkstraMat[r][c].getDis2src())+neighbor_dist;
							
							if (dijkstraMat[row][col].getDis2src()>dist_throgh_father) {
								//update the mat and the PQ
								pq.remove(dijkstraMat[row][col]);
								dijkstraMat[row][col].setDis2src(dist_throgh_father);
								dijkstraMat[row][col].setFather(new Point3D(c,r));
								pq.add(dijkstraMat[row][col]);
							}
						}
					}
				}
			}
		}
		
		//restore the route from a to b
		Stack<Point3D> stack = new Stack<Point3D>();
		Point3D curr_pixel = pixel_dst_loc;
//		System.out.println( dijkstraMat[(int)curr_pixel.y()][(int)curr_pixel.x()].getDis2src());
		//int i=0;
//		while(((int)curr_pixel.x()!=(int)pixel_player_loc.x())||((int)curr_pixel.y()!=(int)pixel_player_loc.y())) {
//			for (int i=0;i<56;i++) {
		while(dijkstraMat[(int)curr_pixel.y()][(int)curr_pixel.x()].getDis2src()!=0) {
			stack.push(new Point3D(curr_pixel));
			curr_pixel = dijkstraMat[(int)curr_pixel.y()][(int)curr_pixel.x()].getFather();
			
			}
//			System.out.println( dijkstraMat[(int)curr_pixel.y()][(int)curr_pixel.x()].getDis2src());
		return stack;
	}
}
