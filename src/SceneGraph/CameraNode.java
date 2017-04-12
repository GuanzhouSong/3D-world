package SceneGraph;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class CameraNode extends Node {
	protected Point3D eye = new Point3D(0,0,1);
	protected Point3D  at = new Point3D(0,0,0);
	protected Vector3D up = new Vector3D(0,1,0);
	protected double fov = 45.0f;
	protected double aspectRatio = 1.0f;
	protected double znear = 0.01f;
	protected double zfar = 100.0f;

	public CameraNode(String name, ParentableNode parent) {
		super(name, parent);
	}

	public void setAspectRatio(double aspectRatio) {
		this.aspectRatio = aspectRatio;
	}
	
	@Override
	public void action(GL gl) {
		// set up the projection matrix
    	gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(fov, aspectRatio, znear, zfar);

        // set up the model-view matrix
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluLookAt(
        		eye.x, eye.y, eye.z,
        		 at.x,  at.y,  at.z,
        		 up.x,  up.y,  up.z);
        
        // apply inverse transforms!!
        applyInverseGlobalTransforms(gl);
	}
}
