package SceneGraph.Renderables;

import javax.media.opengl.GL;

import SceneGraph.ParentableNode;

public class Disc extends Quadrics {
	double innerRadius;
	double outerRadius;
	int slices;
	int loops;
	
	public Disc(
			String name, ParentableNode parent,
			double innerRadius, double outerRadius, int slices, int loops) {
		super(name, parent);

		this.innerRadius = innerRadius;
		this.outerRadius = outerRadius;
		this.slices = slices;
		this.loops = loops;
	}

	@Override
	public void render(GL gl) {
		material.apply(gl);
		glu.gluDisk(quadric, innerRadius, outerRadius, slices, loops);
	}
}
