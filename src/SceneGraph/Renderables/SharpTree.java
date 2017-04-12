package SceneGraph.Renderables;

import javax.media.opengl.GL;

import SceneGraph.Material;
import SceneGraph.ParentableNode;
import SceneGraph.RenderableNode;

public class SharpTree extends Tree {
	public SharpTree(String name, ParentableNode parent, double trunkHeight, double headHeight) {
		super(name, parent, trunkHeight, headHeight);

		double upHeight = headHeight * 0.65f;
		double loHeight = headHeight * 0.82f;
		
		double loRadius = headHeight * 0.8f; // use 90% of the height as the base radius
		double upRadius = loRadius * 0.65f; // use 45% of the base radius as the upper head's radius
		
		SharpTreeHead lo = new SharpTreeHead("lowerHead",  this, loRadius, loHeight);
		SharpTreeHead up = new SharpTreeHead("upperHead",  this, upRadius, upHeight);

		lo.getPosition().y += trunkHeight;
		up.getPosition().y += trunkHeight + headHeight - upHeight;
	}
	
	@Override
	public void render(GL gl) {} // nothing to draw.. my children nodes will do the work

	protected class SharpTreeHead extends RenderableNode {
		public SharpTreeHead(String name, SharpTree tree, double radius, double height) {
			super(name, tree);

			int slices = 12;
			int stacks = 10;
			
			Cylinder head = new Cylinder("head",  this, radius, 0, height, slices, stacks);
			head.getOrientation().setPitch(-90.0f);
			head.setMaterial(Material.Jade);
			
			Disc base = new Disc("base", this, 0, radius, slices, 2);
			base.getOrientation().setPitch(-90.0f);
			base.setMaterial(Material.Jade);
		}

		@Override
		public void render(GL gl) {} // nothing to draw.. my children nodes will do the work
	}
}
