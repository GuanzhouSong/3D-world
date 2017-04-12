
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

public class TopviewCamera  extends CameraNode implements KeyListener {

	Helicopter helicopter;
	float distance=20;         //the distance between  TrackingCamera and helicopter
	
	
	public TopviewCamera  (String name, ParentableNode parent, GLAutoDrawable canvas,Helicopter Helicopter) {
		super(name, parent);
		canvas.addKeyListener(this);
		this.helicopter= Helicopter;
		
	}

	public void move() {	
		   getPosition().x =helicopter.getPosition().x;
	       getPosition().y =helicopter.getPosition().y+30;
	       getPosition().z =helicopter.getPosition().z;     
		
	}
	
	public void rotate() {	
		getOrientation().setPitch  (-90.0f);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		
		case KeyEvent.VK_NUMPAD6: move();rotate(); break;
		case KeyEvent.VK_NUMPAD4: move();rotate(); break;
		case KeyEvent.VK_NUMPAD5: move();rotate(); break;
		case KeyEvent.VK_NUMPAD8: move();rotate(); break;
		
		case KeyEvent.VK_LEFT:	move();rotate(); break;
		case KeyEvent.VK_RIGHT: move();rotate();break;
		case KeyEvent.VK_UP:	move();rotate(); break;
		case KeyEvent.VK_DOWN:	move();rotate(); break;
		
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}
