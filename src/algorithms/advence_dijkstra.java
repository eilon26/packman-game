package algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import Coords.MyCoords;
import GIS.ariel_map;
import GIS.box;
import GUI.JPanelWithBackground;
import Geom.Point3D;

public class advence_dijkstra {

	
	public static Stack<Point3D> Dijkstra(JPanelWithBackground jpanel, Point3D a, Point3D b){

		MyCoords MC = new MyCoords();
		//init all the nodes
		ArrayList<advence_dijkstra_node> nodes = new ArrayList<advence_dijkstra_node>();
		advence_dijkstra_node src_node = new advence_dijkstra_node(a,0);
		nodes.add(src_node);
		advence_dijkstra_node dst_node = new advence_dijkstra_node(b);
		nodes.add(dst_node);
		Iterator<box> IterBox = jpanel.getGB().box_iterator();
		while (IterBox.hasNext()) {
			box curr = IterBox.next();
			nodes.add(new advence_dijkstra_node(MC.add(curr.getP1(),new Point3D(-10,-10,0))));//add vec
			nodes.add(new advence_dijkstra_node(MC.add(curr.getP2(),new Point3D(10,10,0))));
			nodes.add(new advence_dijkstra_node(MC.add(curr.getP3(),new Point3D(-10,10,0))));
			nodes.add(new advence_dijkstra_node(MC.add(curr.getP4(),new Point3D(10,-10,0))));
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
					
//					System.out.println(dist_throgh_father);
//					try {Thread.sleep((long) (500));//refer to the defined time rati
//					} catch (InterruptedException e) {}
					pq.remove(neighbor);
					neighbor.setDis2src(dist_throgh_father);
					neighbor.setFather(curr);
					pq.add(neighbor);
				}
			}
		}

//restore the route from a to b
		Stack<Point3D> stack = new Stack<Point3D>();
		advence_dijkstra_node curr_node = dst_node;
//			for (int i=0;i<2;i++) {
		while(curr_node!=src_node) {
			stack.push(curr_node.getLoc());
			curr_node = curr_node.getFather();
			
			}
		return stack;
	}
}
