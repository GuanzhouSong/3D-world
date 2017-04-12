package SceneGraph.Renderables;

import javax.media.opengl.GL;

import SceneGraph.ParentableNode;

public class Cylinder extends Quadrics {
	protected double baseRadius;
	protected double topRadius;
	protected double height;
	protected int slices;
	protected int stacks;
	
	public Cylinder(String name, ParentableNode parent, double baseRadius,
			double topRadius, double height, int slices, int stacks) {
		super(name, parent);
		
		this.baseRadius = baseRadius;
		this.topRadius  = topRadius;
		this.height = height;
		this.slices = slices;
		this.stacks = stacks;
	}

	@Override
	public void render(GL gl) {
		material.apply(gl);
		glu.gluCylinder(quadric, baseRadius, topRadius, height, slices, stacks);
	}

}
