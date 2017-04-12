package SceneGraph.Renderables;

import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import SceneGraph.Material;
import SceneGraph.ParentableNode;
import SceneGraph.RenderableNode;

public abstract class Quadrics extends RenderableNode {
	protected static GLUquadric quadric = null;
	protected Material material = Material.CLAY;
	
	public Quadrics(String name, ParentableNode parent) {
		super(name, parent);
		
		quadric = glu.gluNewQuadric();
		glu.gluQuadricDrawStyle(quadric, GLU.GLU_FILL);
	}
	
	public void setMaterial(Material material) {
		this.material = material;
	}
}
