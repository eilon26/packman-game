package algorithms;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;


import GIS.ariel_map;
import GUI.JPanelWithBackground;
import Geom.Point3D;

public class TwoPointsRoute {	
	JPanelWithBackground panel;
	
public TwoPointsRoute( JPanelWithBackground panel) {
		this.panel = panel;
	}
/**
 * get to global point and return the shortest route between them
 * @param a global point
 * @param b global point
 * @param mat the mat that represent the screen 
 * @return
 */
	public Stack<Point3D> bfs(Point3D a, Point3D b,char[][] mat){
		//convert the points to pixel
		ariel_map m = new ariel_map(panel);
		Point3D pixel_player_loc = m.global2pixel(a);
		Point3D pixel_dst_loc = m.global2pixel(b);
		bfsNode[][] bfsMat = new bfsNode[mat.length][mat[0].length];
		//init all the nodes
		for (int r=0;r<mat.length;r++) {
			for(int c=0;c<mat[0].length;c++) {
				if (mat[r][c] !='B')
					bfsMat[r][c] = new bfsNode();
				else bfsMat[r][c]=null;
			}
		}
		//distance of src = 0 and visited = true

		bfsMat[(int)pixel_player_loc.y()][(int)pixel_player_loc.x()].setDis2src(0);
		bfsMat[(int) pixel_player_loc.y()][(int) pixel_player_loc.x()].setVisited(true);
		
		//insert the source into the Queue
		Queue<Point3D> q = new ArrayDeque<Point3D>();
		q.add(new Point3D(pixel_player_loc.x(),pixel_player_loc.y()));
		
		
		Point3D curr;
		int r;
		int c;
		while (!q.isEmpty()) {
			curr = q.remove();
			r = (int) curr.y();
			c = (int) curr.x();
			//update all the neighbors of curr
			for (int row = r-1;row<=r+1;row++) {
				for (int col = c-1;col<=c+1;col++) {
					if ((row!=r)||(col!=c)) {
						if ((row<bfsMat.length)&&(col<bfsMat[0].length)&&(row>=0)&&(col>=0)) {
							if ((bfsMat[row][col]!=null)&&(bfsMat[row][col].isVisited()==false)){
								bfsMat[row][col].setDis2src(bfsMat[r][c].getDis2src()+1);
								bfsMat[row][col].setVisited(true);
								bfsMat[row][col].setFather(new Point3D(c,r));
								q. add(new Point3D(col,row));
							}
						}
					}
				}
			}
		}
		//restore the route rom a to b
		Stack<Point3D> stack = new Stack<Point3D>();
		curr = pixel_dst_loc;
		//int i=0;
		//while((curr.x()!=pixel_player_loc.x())||(curr.y()!=pixel_player_loc.y())) {
			for (int i=0;i<56;i++) {
			System.out.println(i);
			stack.push(new Point3D(curr));
			curr = bfsMat[(int)curr.y()][(int)curr.x()].getFather();
			
			}
		return stack;
	}
}
