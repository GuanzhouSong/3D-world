package SceneGraph;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;
import javax.media.opengl.GL;

public abstract class LightNode extends Node {
	private static Queue<Integer> availableIds = initLightIdsList();
	protected int lightId;
	protected Color ambient;
	protected Color diffuse;
	protected Color specular;
	protected float constantAttenuation = 1.0f;
	protected float linearAttenuation = 0.0f;
	protected float quadraticAttenuation = 0.0f;

	public LightNode(String nodeName, ParentableNode parentNode) {
		this(nodeName, parentNode, Color.WHITE, Color.WHITE, Color.WHITE);
	}
	
	public LightNode(String name, ParentableNode parent,
			Color ambient, Color diffuse, Color specular) {
		super(name, parent);
		this.ambient = ambient;
		this.diffuse = diffuse;
		this.specular = specular;
		
		if (availableIds.isEmpty()) {
			System.err.println("run out of available lights!");
		} else {
			this.lightId = availableIds.poll();
		}
	}
	
	public void setAttenuation(float constant, float linear, float quadratic) {
		constantAttenuation = constant;
		linearAttenuation = linear;
		quadraticAttenuation = quadratic;
	}
	
	protected abstract void lightUp(GL gl);
	
	@Override
	public void action(GL gl) {
		gl.glDisable(lightId);
		gl.glLightfv(lightId, GL.GL_AMBIENT,   ambient.getComponents(null), 0);
        gl.glLightfv(lightId, GL.GL_DIFFUSE,   diffuse.getComponents(null), 0);
        gl.glLightfv(lightId, GL.GL_SPECULAR, specular.getComponents(null), 0);
        gl.glLightf(lightId,  GL.GL_CONSTANT_ATTENUATION, constantAttenuation);
        gl.glLightf(lightId,  GL.GL_LINEAR_ATTENUATION, linearAttenuation);
        gl.glLightf(lightId,  GL.GL_QUADRATIC_ATTENUATION, quadraticAttenuation);
        
        gl.glPushMatrix();
		applyLocalTransforms(gl);
		lightUp(gl);
		gl.glPopMatrix();
		
		gl.glEnable(lightId);

	}

	private static Queue<Integer> initLightIdsList() {
		Queue<Integer> ids = new LinkedList<Integer>();
		ids.add(GL.GL_LIGHT0);
		ids.add(GL.GL_LIGHT1);
		ids.add(GL.GL_LIGHT2);
		ids.add(GL.GL_LIGHT3);
		ids.add(GL.GL_LIGHT4);
		ids.add(GL.GL_LIGHT5);
		ids.add(GL.GL_LIGHT6);
		ids.add(GL.GL_LIGHT7);

		return ids;
	}
}
