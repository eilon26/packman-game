package algorithms;


import Geom.Point3D;

public class dijkstra_node implements Comparable<dijkstra_node> {
	private int dis2src;
	private Point3D father;
	private int c;
	private int r;

	public dijkstra_node(int c,int r) {
		this.father = null;
		this.dis2src = Integer.MAX_VALUE;
		this.c = c;
		this.r = r;
	}


	//*************** getters and setters***************************

	public int getDis2src() {
		return dis2src;
	}



	public int getC() {
		return c;
	}


	public int getR() {
		return r;
	}


	public void setDis2src(int dis2src) {
		this.dis2src = dis2src;
	}



	public Point3D getFather() {
		return father;
	}



	public void setFather(Point3D father) {
		this.father = father;
	}


	@Override
	public int compareTo(dijkstra_node arg0) {
		return this.dis2src -arg0.dis2src;
	}

}

