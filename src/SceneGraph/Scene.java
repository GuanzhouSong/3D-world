package SceneGraph;

import java.awt.Color;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

/**
 * A mysterious class.. what's going to be here?
 *
 * @author X
 */
public class Scene {
	private ParentableNode rootNode;
	private CameraNode activeCamera;
	public boolean wireframeMode = false;
	private Color ambientLightColour = Color.GRAY;

	public Scene() {
		rootNode = new ParentableNode("root", null);
		activeCamera = null;
	}
	
	public ParentableNode getRootNode() {
		return rootNode;
	}
	
	public void setActiveCamera(CameraNode camera) {
		activeCamera = camera;
	}
	
	public void renderScene(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();

        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_NORMALIZE);
 		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glPolygonMode(GL.GL_FRONT_AND_BACK, wireframeMode ? GL.GL_LINE : GL.GL_FILL);
        gl.glLightModeli(GL.GL_LIGHT_MODEL_LOCAL_VIEWER, GL.GL_TRUE);
        gl.glLightModelfv(GL.GL_LIGHT_MODEL_AMBIENT, ambientLightColour.getComponents(null), 0);

		if (activeCamera != null) {
			activeCamera.setAspectRatio((double)drawable.getWidth() / (double)drawable.getHeight());
			activeCamera.action(gl);
		} else {
			// NO ACTIVE CAMERA SET!!
			// OpenGL will use the default camera
		}

		rootNode.applyLighting(gl);
        rootNode.action(gl);
	}

	public Color getAmbientLightColour() {
		return ambientLightColour;
	}

	public void setAmbientLightColour(Color ambientLightColour) {
		this.ambientLightColour = ambientLightColour;
	}
}
