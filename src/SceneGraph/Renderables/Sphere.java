package SceneGraph.Renderables;

import javax.media.opengl.GL;

import SceneGraph.ParentableNode;

public class Sphere extends Quadrics {
	private double radius;
	private int slices;
	private int stacks;
	
	public Sphere(String name, ParentableNode parent, double radius, int slices, int stacks) {
		super(name, parent);
		this.radius = radius;
		this.slices = slices;
		this.stacks = stacks;
	}
	public void render(GL gl) {
		material.apply(gl);

		
		glu.gluSphere(quadric, radius,slices, stacks);
	}
}
