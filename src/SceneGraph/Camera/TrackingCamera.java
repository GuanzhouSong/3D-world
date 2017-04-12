

package SceneGraph.Camera;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

import SceneGraph.*;
import SceneGraph.Renderables.Fan;
import SceneGraph.Renderables.Helicopter;
import SceneGraph.Renderables.SharpTree;


public class TrackingCamera  extends CameraNode implements KeyListener {

	Helicopter helicopter;
	public float distance=15;         //the distance between  TrackingCamera and tree
	protected double moveSpeed = 0.1f;
	protected double rotSpeed  = 1.0f;
	
	public TrackingCamera  (String name, ParentableNode parent, GLAutoDrawable canvas,Helicopter Helicopter) {
		super(name, parent);
		canvas.addKeyListener(this);
		this.helicopter= Helicopter;
		
	}


	
	public void move() {
		
	
		double pitch = Orientation.deg2rad(orient.getYaw());
		double cos = Math.cos(pitch);
		double sin = Math.sin(pitch);

		//Vector3D vec = new Vector3D(sin * dz + cos * dx, dy, cos * dz - sin * dx);
		//centre = centre.add(vec);
		   getPosition().x =helicopter.getPosition().x + sin * distance;
	       getPosition().y =helicopter.getPosition().y + cos * distance;
	       getPosition().z =helicopter.getPosition().z;
	       

		
	}
	
	public void rotate() {
		
		//orient.setPitch(orient.getPitch() + dPitch);
		//orient.setYaw  (helicopter.getOrientation().getYaw());

		orient.setYaw  (helicopter.getOrientation().getYaw());
		
    	double yaw = helicopter.getOrientation().deg2rad(helicopter.getOrientation().getYaw());
		   
			
			double cos = Math.cos(yaw);
			double sin = Math.sin(yaw);

			
			getPosition().x=helicopter.getPosition().x + sin * distance;
			getPosition().z=helicopter.getPosition().z + cos * distance;
			getPosition().y =helicopter.getPosition().y + 3;
		
		
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		
		
		case KeyEvent.VK_D: move();rotate(); break;
		case KeyEvent.VK_A:	move();rotate(); break;
		case KeyEvent.VK_S: move();rotate(); break;
		case KeyEvent.VK_W:	move();rotate(); break;
		case KeyEvent.VK_UP: move();rotate(); break;
		case KeyEvent.VK_DOWN: move();rotate(); break;
		case KeyEvent.VK_LEFT: move();rotate(); break;
		case KeyEvent.VK_RIGHT:	move();rotate(); break;
		
		
		
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}





