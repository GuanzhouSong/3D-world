package SceneGraph;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public abstract class Node {
	private String name = "";
	private ParentableNode parent = null;
	protected Point3D centre = new Point3D();
	protected Orientation orient = new Orientation();
	protected static GLU glu = new GLU();

	public Node(String name, ParentableNode parent) {
		this.name = name;
		this.parent = parent;

		if (parent != null) {
			parent.addNode(this);
		}
	}

	public String getName(){
		return name;
	}
	
	public Point3D getPosition() {
		return centre;
	}
	
	public Orientation getOrientation() {
		return orient;
	}
	
	protected void applyLocalTransforms(GL gl) {
		gl.glTranslated(centre.x, centre.y, centre.z);
		gl.glRotated(orient.getRoll(),  0, 0, 1);
		gl.glRotated(orient.getYaw(),   0, 1, 0);
		gl.glRotated(orient.getPitch(), 1, 0, 0);
	}

	protected void applyInverseLocalTransforms(GL gl) {
		gl.glRotated(-orient.getPitch(), 1, 0, 0);
		gl.glRotated(-orient.getYaw(),   0, 1, 0);
		gl.glRotated(-orient.getRoll(),  0, 0, 1);
		gl.glTranslated(-centre.x, -centre.y, -centre.z);
	}
	
	protected void applyInverseGlobalTransforms(GL gl) {
		applyInverseLocalTransforms(gl);
		if (this.parent != null) {
			parent.applyInverseGlobalTransforms(gl);
		}
	}
	
	public abstract void action(GL gl);
}
