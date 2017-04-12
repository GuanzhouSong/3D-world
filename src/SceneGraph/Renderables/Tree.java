package SceneGraph.Renderables;

import javax.media.opengl.GL;
import SceneGraph.*;

public abstract class Tree extends RenderableNode {
	
	public Tree(String name, ParentableNode parent, double trunkHeight, double headHeight) {
		super(name, parent);
		Trunk trunk = new Trunk("trunk", this, trunkHeight);
	}

	protected class Trunk extends RenderableNode {
		public Trunk(String name, ParentableNode parent, double height) {
			super(name, parent);
			
			// build the trunk using one cylinder and two discs
			double radius = height * 0.33f; // set the radius of trunk to 33% of the height
			int slices = 30;
			int stacks = 20;

			Cylinder body    = new Cylinder("trunk", this, radius, radius, height, slices, stacks);
			Disc trunkBottom = new Disc("disc1", body, 0,  radius, slices, 2);
			Disc trunkTop    = new Disc("disc2", body, 0,  radius, slices, 2);

			body.setMaterial(Material.WOOD);
			trunkBottom.setMaterial(Material.WOOD);
			trunkTop.setMaterial(Material.WOOD);
			
			trunkTop.getPosition().z = height;
			body.getOrientation().setPitch(-90.0f);
		}

		@Override
		public void render(GL gl) {} // nothing to draw.. my children nodes will do the work
	}
}
