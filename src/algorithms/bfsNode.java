package algorithms;


import Geom.Point3D;

public class bfsNode {
	private int dis2src;
	private Point3D father;
	private boolean isVisited;
	
	

public bfsNode() {
		this.isVisited = false;
		this.father = null;
		this.dis2src = Integer.MAX_VALUE;
	}


//*************** getters and setters***************************

public int getDis2src() {
	return dis2src;
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



public boolean isVisited() {
	return isVisited;
}



public void setVisited(boolean isVisited) {
	this.isVisited = isVisited;
}

	
	}

