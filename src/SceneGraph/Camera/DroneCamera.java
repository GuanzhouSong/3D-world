package SceneGraph.Camera;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

import SceneGraph.*;

public class DroneCamera extends CameraNode implements KeyListener {
	protected double moveSpeed = 0.1f;
	protected double rotSpeed  = 1.0f;
	
	public DroneCamera(String name, ParentableNode parent, GLAutoDrawable canvas) {
		super(name, parent);
		canvas.addKeyListener(this);
	}

	private void move(double dx, double dy, double dz) {
		double yaw = Orientation.deg2rad(orient.getYaw());
		double cos = Math.cos(yaw);
		double sin = Math.sin(yaw);

		Vector3D vec = new Vector3D(sin * dz + cos * dx, dy, cos * dz - sin * dx);
		centre = centre.add(vec);
	}
	
	private void rotate(double dPitch, double dYaw) {
		//orient.setPitch(orient.getPitch() + dPitch);
		orient.setYaw  (orient.getYaw()   + dYaw  );
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_D: move(+moveSpeed,0,0); break;
		case KeyEvent.VK_A:	move(-moveSpeed,0,0); break;
		case KeyEvent.VK_S: move(0,0,+moveSpeed); break;
		case KeyEvent.VK_W:	move(0,0,-moveSpeed); break;
		case KeyEvent.VK_E: move(0,+moveSpeed,0); break;
		case KeyEvent.VK_Q: move(0,-moveSpeed,0); break;
		case KeyEvent.VK_LEFT:	rotate(0, rotSpeed); break;
		case KeyEvent.VK_RIGHT:	rotate(0,-rotSpeed); break;
		case KeyEvent.VK_UP:	rotate( rotSpeed,0); break;
		case KeyEvent.VK_DOWN:	rotate(-rotSpeed,0); break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}
