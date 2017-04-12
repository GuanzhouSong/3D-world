package SceneGraph;

import java.util.ArrayList;

import javax.media.opengl.GL;

public class ParentableNode extends Node {
	private ArrayList<Node> childrenNodes = null;

	public ParentableNode(String name, ParentableNode parent) {
		super(name, parent);
		this.childrenNodes = new ArrayList<Node>();
	}
	
	protected void addNode(Node node) {
		childrenNodes.add(node);
	}

	public void applyLighting(GL gl) {
		gl.glPushMatrix();
		applyLocalTransforms(gl);
		for (Node node : childrenNodes) {
			if (node instanceof ParentableNode) {
				((ParentableNode)node).applyLighting(gl);
			}
			if (node instanceof LightNode) {
				node.action(gl);
			}
		}
		gl.glPopMatrix();
	}
	
	public void action(GL gl) {
		gl.glPushMatrix();
		applyLocalTransforms(gl);
		for (Node node : childrenNodes) {
			boolean doAction = !(node instanceof CameraNode);
			if (doAction) {
				node.action(gl);
			}
		}
		gl.glPopMatrix();
	}
}
