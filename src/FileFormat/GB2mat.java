//package FileFormat;
//
//import java.util.Iterator;
//
//import GIS.*;
//import GUI.JPanelWithBackground;
//import Geom.Point3D;
//import Geom.geom;
//
//public class GB2mat {
//	char[][] mat;
//	
//	public GB2mat(JPanelWithBackground jpanel) {
//		this.mat = new char[jpanel.getHeight()][jpanel.getWidth()];
//		for (int r =0;r<jpanel.getHeight();r++) {
//			for (int c=0;c<jpanel.getWidth();c++) {
//				if (JPanelWithBackground.isInsideBox(jpanel.getM().pixel2global(new Point3D(c,r)), jpanel.getGB().getBox_set())) mat[r][c] = 'B'; 
//				else mat[r][c] = ' ';
//			}
//		}
//		Point3D playerPoint = jpanel.getM().global2pixel(((geom)jpanel.getGB().getPlayer().getGeom()).getP());//in pixel
//		mat[(int)playerPoint.y()][(int)playerPoint.x()] = 'M';
//		Iterator<GIS_element> IterElement = jpanel.getGB().iterator();
//		while (IterElement.hasNext()) {
//			GIS_element curr = IterElement.next();
//			Point3D curr_point = jpanel.getM().global2pixel(((geom)curr.getGeom()).getP());//in pixel
//			if (curr instanceof fruit)	mat[(int)curr_point.y()][(int)curr_point.x()] = 'F';
//			if (curr instanceof Ghost)	mat[(int)curr_point.y()][(int)curr_point.x()] = 'G';
////			if (curr instanceof player)	mat[(int)curr_point.y()][(int)curr_point.x()] = 'M';
//			if (curr instanceof pachman) mat[(int)curr_point.y()][(int)curr_point.x()] = 'P';
//		}
////		Iterator<box> IterBox = jpanel.getGB().box_iterator();
////		while (IterBox.hasNext()) {
////			box curr = IterBox.next();
////			Point3D p1 = jpanel.getM().global2pixel(curr.getP1());//in pixel
////			Point3D p2 = jpanel.getM().global2pixel(curr.getP2());//in pixel
////			Point3D p3 = jpanel.getM().global2pixel(curr.getP3());//in pixel
////			Point3D p4 = jpanel.getM().global2pixel(curr.getP4());//in pixel
////			mat[(int)p1.y()][(int)p1.x()] = 'B';
////			for (int i=(int)p1.y();i>(int)p3.y();i--) {
////				mat[i][(int)p1.x()] = 'B';
////			}
////			mat[(int)p3.y()][(int)p3.x()] = 'B';
////			for (int i=(int)p3.x();i<(int)p2.x();i++) {
////				mat[(int)p3.y()][i] = 'B';
////			}
////			mat[(int)p2.y()][(int)p2.x()] = 'B';
////			for (int i=(int)p2.y();i<(int)p4.y();i++) {
////				mat[i][(int)p2.x()] = 'B';
////			}
////			mat[(int)p4.y()][(int)p4.x()] = 'B';
////			for (int i=(int)p4.x();i>(int)p1.x();i--) {
////				mat[(int)p4.y()][i] = 'B';
////			}
////			
////		}
//		
//	}
//
//	public char[][] getMat() {
//		return mat;
//	}
//	
//}
