package SceneGraph;

import javax.media.opengl.GL;

public class DummyNode extends ParentableNode {
	static int depth = 0;
	
	public DummyNode(String name, ParentableNode parent) {
		super(name, parent);
	}

	public void action(GL gl) {
		String indent = "";
		for (int i = 0; i < depth; i++) indent += " ";
		System.out.println(indent + "Dummy " + getName() + " is actioned..");
		
		depth++;
		super.action(gl);
		depth--;
	}
}
