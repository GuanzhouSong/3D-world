package SceneGraph;

import javax.media.opengl.GL;

public class Point3D {
	public double x = 0.0f;
	public double y = 0.0f;
	public double z = 0.0f;
	
	public Point3D() {}
	
	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point3D add(Vector3D vec) {
		return new Point3D(x + vec.x, y + vec.y, z + vec.z);
	}
	
	public Vector3D subtract(Point3D other)
	{
		return new Vector3D(x - other.x, y - other.y, z - other.z);
	}
	
	public String toString() {
		return String.format("(%.02f, %.02f, %.02f)", x, y, z);
	}
	
	public void draw(GL gl) {
		gl.glVertex3d(x, y, z);
	}
	
	public static final Point3D Zero = new Point3D(0,0,0);
}
