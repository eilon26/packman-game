package Coords;

import Geom.Point3D;

/**
 * 
 * @author Daniel Ventura
 *this class implements "coords_converter" and its functions.
 */
public class MyCoords implements coords_converter {
	
	private static final int earthR = 6371000;//the radius of planet earth.
	
	/**
	 * this function computes a new point which is the gps point transformed by a 3D vector (in meters)
	 * @param gps - a gps point with (x,y,z).
	 * @param local_vector_in_meter - a vector.
	 * @return the transformed point.
	 */
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		if (isValid_GPS_Point(gps)) {
			double LonNorm = Math.cos(Math.toRadians(gps.y()));
			double y1 = Math.toDegrees(Math.asin(local_vector_in_meter.y()/(earthR)))+gps.y();
			double x1 = Math.toDegrees(Math.asin(local_vector_in_meter.x()/(earthR*LonNorm)))+gps.x();
			double z1 = local_vector_in_meter.z()+gps.z();
			Point3D newPoint = new Point3D(x1,y1,z1);
			if (isValid_GPS_Point(newPoint)) return newPoint;
		}
		return null;
	}
	
	/**
	 * this function computes the 3D distance (in meters) between the two gps like points.
	 * @param gps0 - the first gps point.
	 * @param gps1 - the second gps point.
	 * @return the 3d distance of these two points.	 
	 */
	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		if ((isValid_GPS_Point(gps0))&&(isValid_GPS_Point(gps1))) {
		double LonNorm = Math.cos(Math.toRadians(gps0.y()));
		double meterX = LonNorm*earthR*Math.sin(Math.toRadians(gps1.x()-gps0.x()));
		double meterY = earthR*Math.sin(Math.toRadians(gps1.y()-gps0.y()));
		double Dis2D = Math.sqrt((meterX*meterX)+(meterY*meterY));
		double dz = gps1.z()-gps0.z();
		double dis = Math.sqrt((Dis2D*Dis2D)+(dz*dz));
		if (dis<=100000) return  dis;
		}
		return Double.NaN;
	}

	/**
	 * this function computes the 3D vector (in meters) between two gps like points.
	 * @param gps0 - the first gps point.
	 * @param gps1 - the second gps point.
	 * @return the 3D vector (in meters) between two gps like points.	 
	 */
	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		if ((isValid_GPS_Point(gps0))&&(isValid_GPS_Point(gps1))) {
		double meterY = earthR*Math.sin(Math.toRadians(gps1.y()-gps0.y()));
		double meterX = earthR*Math.sin(Math.toRadians(gps1.x()-gps0.x()));
		double LonNorm = Math.cos(Math.toRadians(gps0.y()));
		meterX *= LonNorm;
		double meterZ = gps1.z()-gps0.z();
		return new Point3D(meterX,meterY,meterZ);
		}
		return null;
	}

	/**
	 * this function computes the polar representation of the 3D vector be gps0 to gps1.
	 * @param gps0 - the first gps point.
	 * @param gps1 - the second gps point.
	 * @return the polar representation.
	 */
	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		
		double[] polarVec = new double[3];
		if (isValid_GPS_Point(gps0)&&isValid_GPS_Point(gps1)) {
			Point3D vec = vector3D(gps0,gps1);
			polarVec[2] = distance3d(gps0,gps1);
			polarVec[0] = Math.toDegrees(Math.atan(Math.abs((vec.y())/(vec.x()))));
			
			double y = (Math.sin(gps1.x()-gps0.x())*Math.cos(gps1.y()));
			double x = (Math.cos(gps0.y())*Math.sin(gps1.y()))-(Math.sin(gps0.y())*Math.cos(gps1.y())*Math.cos(gps1.x()-gps0.x()));
			polarVec[0] = Math.atan2(y, x);
			polarVec[0] = Math.toDegrees(polarVec[0]);
//			if ((x<0)&&(y>=0)) polarVec[0]+= Math.PI;
//			if ((x<0)&&(y<0)) polarVec[0]-= Math.PI;
//			if ((x==0)&&(y>0)) polarVec[0]= Math.PI/2;
//			if ((x==0)&&(y<0)) polarVec[0]= -Math.PI/2;
//			if ((x==0)&&(y==0)) polarVec[0]= Double.NaN;
//			System.out.println(polarVec[0]);
//			polarVec[0]= Math.toDegrees(polarVec[0]);
//			if ((vec.y()<0)&&(vec.x()>0)) polarVec[0]=180-polarVec[0];
//			if ((vec.x()<0)&&(vec.y()>0)) polarVec[0]=360-polarVec[0];
//			if ((vec.y()<0)&&(vec.x()<0)) polarVec[0]=180+polarVec[0];
			if (polarVec[0]<0) polarVec[0]+=360;
			if (polarVec[0]>360) polarVec[0]-=360;
			
			polarVec[1] = Math.toDegrees(Math.asin(vec.z()/polarVec[2]));
			
			return polarVec;
		}
		return null;
	}

	/**
	 * return true iff this point is a valid lat, lon , lat coordinate: [-180,+180],[-90,+90],[-450, +inf]
	 * @param p - a gps point.
	 * @return true if the point is indeed a gps point and false if not.
	 */
	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		if (p.x()>=-180 && p.x()<=180) {
			if (p.y()>=-90 && p.y()<=90) {
				if (p.z()>=-450) {
					return true;
				}
			}
		}
		return false;
	}
}
