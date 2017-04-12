package SceneGraph;

public class Vector3D {
	public double x;
	public double y;
	public double z;
	
	public Vector3D(double vx, double vy, double vz) {
		this.x = vx;
		this.y = vy;
		this.z = vz;
	}
	
	public Vector3D() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public Vector3D(Vector3D v) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}
	
	public Vector3D add(Vector3D other)
	{
		return new Vector3D(this.x+other.x, this.y+other.y,this.z+other.z);
	}
	
	public Vector3D multiply(double k)
	{
		return new Vector3D(k*this.x, k*this.y, k*this.z);
	}
	
	public double norm() {
		return Math.sqrt(normSquare());
	}
	
	public double normSquare() {
		return x * x + y * y + z * z;
	}
	
	public Vector3D getNormalised() {
		double n = norm();
		return new Vector3D(x/n, y/n, z/n);
	}
	
	public void normalise() {
		Vector3D nv = getNormalised();
		x = nv.x;
		y = nv.y;
		z = nv.z;
	}
	
	public Vector3D crossProduct(Vector3D other)
   {
		double cx = this.y*other.z - this.z*other.y;
		double cy = this.z*other.x - this.x*other.z;
		double cz = this.x*other.y - this.y*other.x;
		
		return new Vector3D(cx, cy, cz);
   }
}
