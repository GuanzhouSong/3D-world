package SceneGraph.Renderables;

import javax.media.opengl.GL;

import SceneGraph.Material;
import SceneGraph.ParentableNode;

public class BallTree extends Tree {
	public BallTree(String name, ParentableNode parent, double trunkHeight, double headHeight) {
		super(name, parent, trunkHeight, headHeight);

		Sphere head = new Sphere("ball head", this, headHeight * 0.5f, 10, 20);
		head.setMaterial(Material.Jade);
		head.getPosition().y += trunkHeight + headHeight * 0.5f * 0.5f;
	}

	@Override
	public void render(GL gl) {}
}
