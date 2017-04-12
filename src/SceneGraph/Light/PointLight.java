package SceneGraph.Light;

import java.awt.Color;

import javax.media.opengl.GL;
import SceneGraph.LightNode;
import SceneGraph.ParentableNode;
import SceneGraph.Point3D;

public class PointLight extends LightNode {
	private Point3D position = new Point3D(0, 0, 0);
	
	public PointLight(String nodeName, ParentableNode parentNode, Point3D position) {
		super(nodeName, parentNode);
		this.position = position;
	}

	public PointLight(String name, ParentableNode parent, Point3D position, Color ambient, Color diffuse, Color specular) {
		super(name, parent, ambient, diffuse, specular);
		this.position = position;
	}

	@Override
	protected void lightUp(GL gl) {
		float[] p = {(float)position.x, (float)position.y, (float)position.z, 1.0f};
		gl.glLightfv(lightId, gl.GL_POSITION, p, 0);
		gl.glLightf(lightId, gl.GL_SPOT_CUTOFF, 30);
	}
}
