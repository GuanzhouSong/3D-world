package SceneGraph.Light;

import java.awt.Color;
import javax.media.opengl.GL;
import SceneGraph.LightNode;
import SceneGraph.ParentableNode;
import SceneGraph.Vector3D;

public class DirectionalLight extends LightNode {
	private Vector3D directrion = null;
	
	public DirectionalLight(String name, ParentableNode parent) {
		this(name, parent, new Vector3D(1.0f, 0, 0), Color.GRAY, Color.WHITE, Color.WHITE);
	}

	public DirectionalLight(String name, ParentableNode parent, Vector3D directrion) {
		this(name, parent, directrion, Color.GRAY, Color.WHITE, Color.WHITE);
	}

	public DirectionalLight(String name, ParentableNode parent, Vector3D directrion, Color ambient, Color diffuse, Color specular) {
		super(name, parent, ambient, diffuse, specular);
		this.directrion = directrion;
	}

	@Override
	protected void lightUp(GL gl) {
		float[] p = {(float)directrion.x, (float)directrion.y, (float)directrion.z, 0.0f};
		gl.glLightfv(lightId, gl.GL_POSITION, p, 0);
	}

}
