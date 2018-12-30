package algorithms;

import java.util.ArrayList;

import Geom.Point3D;

public class advence_dijkstra_node implements Comparable<advence_dijkstra_node> {
	private advence_dijkstra_node father;
	private double dis2src;
	private Point3D loc;
	private ArrayList<advence_dijkstra_node> neighbors;
	
	public advence_dijkstra_node(Point3D loc, double dis2src) {
		this.father = null;
		this.dis2src = dis2src;
		this.loc = new Point3D(loc);
		this.neighbors = new ArrayList<advence_dijkstra_node>();
	}
	
	public advence_dijkstra_node(Point3D loc) {
		this(loc,Double.MAX_VALUE);
	}
	public advence_dijkstra_node getFather() {
		return father;
	}

	public void setFather(advence_dijkstra_node father) {
		this.father = father;
	}

	public double getDis2src() {
		return dis2src;
	}

	public void setDis2src(double dis2src) {
		this.dis2src = dis2src;
	}

	public Point3D getLoc() {
		return loc;
	}

	public void setLoc(Point3D loc) {
		this.loc = loc;
	}

	public ArrayList<advence_dijkstra_node> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(ArrayList<advence_dijkstra_node> neighbors) {
		this.neighbors = neighbors;
	}

	@Override
	public int compareTo(advence_dijkstra_node arg0) {
		return (int) (this.dis2src - arg0.dis2src);
	}


	
	
}
