package GIS;

import Geom.Point3D;

public class box  {
	private int id;
	private Point3D p1;//main point
	private Point3D p2;//main point
	private Point3D p3;
	private Point3D p4;

	public box(String[] line) {
		this.id = Integer.parseInt(line[1]);
		this.p1 = new Point3D(Double.parseDouble(line[3]),Double.parseDouble(line[2]),Double.parseDouble(line[4]));
		this.p2 = new Point3D(Double.parseDouble(line[6]),Double.parseDouble(line[5]),Double.parseDouble(line[7]));
		if (p2.y()>p1.y()) {
			this.p3 = new Point3D(p1.x(),p2.y());
			this.p4 = new Point3D(p2.x(),p1.y());
		}else {
			this.p4 = new Point3D(p1.x(),p2.y());
			this.p3 = new Point3D(p2.x(),p1.y());
		}

	}

	public int getId() {
		return id;
	}

	public Point3D getP1() {
		return p1;
	}

	public Point3D getP2() {
		return p2;
	}

	public Point3D getP3() {
		return p3;
	}

	public Point3D getP4() {
		return p4;
	}


}
