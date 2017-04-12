package SceneGraph;

public class Orientation {
	private double pitch = 0.0f; // rotation about x-axis
	private double yaw   = 0.0f; // rotation about y-axis
	private double roll  = 0.0f; // rotation about z-axis
	
	public double getPitch() {
		return pitch;
	}
	
	public double getYaw() {
		return yaw;
	}
	
	public double getRoll() {
		return roll;
	}
	
	public void setPitch(double pitch) {
		this.pitch = pitch % 360;
	}
	
	public void setYaw(double yaw) {
		this.yaw = yaw % 360;
	}
	
	public void setRoll(double roll) {
		this.roll = roll % 360;
	}
	
	public static double deg2rad(double deg) {
		return deg / 180.0f * Math.PI;
	}
}
