
package SceneGraph.Renderables;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

import SceneGraph.Material;
import SceneGraph.Orientation;
import SceneGraph.ParentableNode;
import SceneGraph.RenderableNode;
import SceneGraph.Vector3D;


public class Fan extends RenderableNode implements Runnable {

	public Fan fan;
	public double speed=0.0;
	private double radius;
	
	public Fan(String name, ParentableNode parent, double height) {
		super(name, parent);
		this.radius = height;
		
		int slices = 12;
		int stacks = 10;
					
			Disc tailleaf1 = new Disc("Tailleaf1", this, 0, radius * 2, stacks, stacks);
			tailleaf1.setScale(radius * 0.01, height * 0.15, stacks);			
			tailleaf1.getOrientation().setPitch(90);
			tailleaf1.getOrientation().setYaw(180);
			tailleaf1.getOrientation().setRoll(270);
			tailleaf1.setMaterial(Material.Ruby);
			
			Disc tailleaf2 = new Disc("Tailleaf1", this, 0, radius * 2, stacks, stacks);
			tailleaf2.setScale(radius * 0.01, height * 0.15, stacks);			
			tailleaf2.getOrientation().setPitch(90);
			tailleaf2.getOrientation().setYaw(270);
			tailleaf2.getOrientation().setRoll(270);
			tailleaf2.setMaterial(Material.BLACK);

	}	

	public void SetTheSpeed(double speed){
	if(speed <= 0.0002){
		this.speed = speed;}
}
	
		@Override
		public void render(GL gl) {
			// TODO Auto-generated method stub
			
		}

	
	public void run() {//主要实现直升机螺旋桨类似效果
		while (true){

			getOrientation().setPitch(getOrientation().getPitch() + speed);
		}
		
	}


}

