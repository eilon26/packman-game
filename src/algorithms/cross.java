package algorithms;

import java.util.ArrayList;
import java.util.Iterator;

import Geom.*;
import GIS.*;;

public class cross {

	/**
	 * the function get 2 global points and return true if there is crossing with some box, otherwise false
	 * if dst and src have the same value the function return true
	 * @param src Point3D - global point
	 * @param dst Point3D - global point
	 * @param box_set the array list of the boxes
	 * @return
	 */
	public static boolean isCross(Point3D src,Point3D dst,ArrayList<box> box_set) {
		if ((src.x()==dst.x())&&(src.y()==dst.y())) return true;
//		if (dst.x()<src.x()) {
//			swap(src,dst);
//		}
		double minX = Math.min(dst.x(), src.x());
		double maxX = Math.max(dst.x(), src.x());
		double minY = Math.min(dst.y(), src.y());
		double maxY = Math.max(dst.y(), src.y());
		boolean findCross = false;
		Iterator<box> IterBox = box_set.iterator();
		while (IterBox.hasNext()) {
			box curr_box = IterBox.next();
			if ((curr_box.getP1().x()>minX)&&(curr_box.getP1().x()<maxX)) {
				double result_p1p3 = function(curr_box.getP1().x(),src,dst,'Y');
				if ((result_p1p3>=curr_box.getP1().y())&&(result_p1p3<=curr_box.getP3().y())) findCross = true;
			}
			if ((curr_box.getP4().x()>minX)&&(curr_box.getP4().x()<maxX)) {
				double result_p4p2  = function(curr_box.getP4().x(),src,dst,'Y');
				if ((result_p4p2>=curr_box.getP4().y())&&(result_p4p2<=curr_box.getP2().y())) findCross =  true;
			}

			if ((curr_box.getP3().y()>minY)&&(curr_box.getP3().y()<maxY)) {
				double result_p3p2  = function(curr_box.getP3().y(),src,dst,'X');
				if ((result_p3p2>=curr_box.getP3().x())&&(result_p3p2<=curr_box.getP2().x())) findCross =  true;
			}
			//			System.out.println(curr_box.getP3().x()+"<"+result_p3p2 +"<"+curr_box.getP2().x());
			if ((curr_box.getP1().y()>minY)&&(curr_box.getP1().y()<maxY)) {
				double result_p1p4  = function(curr_box.getP1().y(),src,dst,'X');
				if ((result_p1p4>=curr_box.getP1().x())&&(result_p1p4<=curr_box.getP4().x())) findCross =  true;
			}
		}
		return findCross;
	}

	private static double function(double XorY,Point3D src,Point3D dst,char type) {
		double minX = Math.min(dst.x(), src.x());
		double maxX = Math.max(dst.x(), src.x());
		double mehane = (maxX-minX);
		if (mehane==0) mehane = 0.00001;
		double m;
		if (dst.x()> src.x())
			m = (dst.y()-src.y())/mehane;
		else m = (src.y()-dst.y())/mehane;
		if (type == 'Y')
			return (m*(XorY-src.x()))+src.y();
		else {
			if (m==0) m=0.00001;
			return ((XorY-src.y())/m)+src.x();
		}
	}
}
