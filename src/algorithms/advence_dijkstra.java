package algorithms;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

import java.util.Stack;

import Coords.MyCoords;
import GIS.box;
import GUI.MainJPanel;
import Geom.Point3D;
/**
 * the class advence_dijkstra is responsible to find the shortest route from one
 * global point to another. while take care not crash into the boxes
 * this class works basing on the dijkstra algorithm 
 * @author EILON
 *
 */
public class advence_dijkstra {
	private Stack<Point3D> route;
	private double routeDist;
	/**
	 * the constructor of advence_dijkstra
	 * it is get 2 global points and build the shortest route (and calculate its distance) between them
	 * without crossing the boxes 
	 * @param jpanel MainJPanel
	 * @param a Point3D global point
	 * @param b Point3D global point
	 */
	public advence_dijkstra(MainJPanel jpanel, Point3D a, Point3D b){

		MyCoords MC = new MyCoords();
		//init all the nodes
		ArrayList<advence_dijkstra_node> nodes = new ArrayList<advence_dijkstra_node>();
		advence_dijkstra_node src_node = new advence_dijkstra_node(a,0);
		nodes.add(src_node);
		advence_dijkstra_node dst_node = new advence_dijkstra_node(b);
		nodes.add(dst_node);
		Iterator<box> IterBox = jpanel.getGB().getBox_set().iterator();
		while (IterBox.hasNext()) {
			box curr = IterBox.next();
			nodes.add(new advence_dijkstra_node(MC.add(curr.getP1(),new Point3D(-5,-5,0))));//add vec
			nodes.add(new advence_dijkstra_node(MC.add(curr.getP2(),new Point3D(5,5,0))));
			nodes.add(new advence_dijkstra_node(MC.add(curr.getP3(),new Point3D(-5,5,0))));
			nodes.add(new advence_dijkstra_node(MC.add(curr.getP4(),new Point3D(5,-5,0))));
		}
		//init the nodes neighbors
		Iterator<advence_dijkstra_node> IterNode = nodes.iterator();
		while (IterNode.hasNext()) {
			advence_dijkstra_node curr = IterNode.next();
			Iterator<advence_dijkstra_node> sec_IterNode = nodes.iterator();
			while (sec_IterNode.hasNext()) {
				advence_dijkstra_node sec_node = sec_IterNode.next();
				if (!cross.isCross(curr.getLoc(), sec_node.getLoc(), jpanel.getGB().getBox_set())) 
					curr.getNeighbors().add(sec_node);
			}
		}
			

		//insert all the  into the Queue
		PriorityQueue<advence_dijkstra_node> pq = new PriorityQueue<advence_dijkstra_node>();
		pq.addAll(nodes);
		
		
		advence_dijkstra_node curr;
		
		while (!pq.isEmpty()) {
			curr = pq.remove();
			//update all the neighbors of curr
			Iterator<advence_dijkstra_node> IterNeighbor = curr.getNeighbors().iterator();
			while (IterNeighbor.hasNext()) {
				advence_dijkstra_node neighbor= IterNeighbor.next();
				double dist_throgh_father = curr.getDis2src()+MC.distance3d(curr.getLoc(), neighbor.getLoc());

				if (neighbor.getDis2src()>dist_throgh_father) {
					//update the mat and the PQ

					pq.remove(neighbor);
					neighbor.setDis2src(dist_throgh_father);
					neighbor.setFather(curr);
					pq.add(neighbor);
				}
			}
		}

//restore the route from a to b
		this.route = new Stack<Point3D>();
		this.routeDist = 0;
		advence_dijkstra_node curr_node = dst_node;
		while(curr_node!=src_node) {
			this.route.push(curr_node.getLoc());
			this.routeDist+=MC.distance3d(curr_node.getLoc(), curr_node.getFather().getLoc());
			curr_node = curr_node.getFather();
			
			}
	}
	/**
	 * 
	 * @return the stack that contain the points of the route 
	 * the first point to be pop up is the first point the player shoud go
	 */
	public Stack<Point3D> getRoute() {
		return route;
	}

	public double getRouteDist() {
		return routeDist;
	}
	
}
