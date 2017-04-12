package SceneGraph;

import javax.media.opengl.GL;

public abstract class RenderableNode extends ParentableNode {
	protected double scaleX = 1.0f;
	protected double scaleY = 1.0f;
	protected double scaleZ = 1.0f;
	
	public RenderableNode(String name, ParentableNode parent) {
		super(name, parent);
	}

	public void action(GL gl) {
		super.action(gl);
		gl.glPushMatrix();
		applyLocalTransforms(gl);
		gl.glScaled(scaleX, scaleY, scaleZ);
		render(gl);
		gl.glPopMatrix();
	}

	public void setScale(double scaleX, double scaleY, double scaleZ) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.scaleZ = scaleZ;
	}
	
	public abstract void render(GL gl);
}
