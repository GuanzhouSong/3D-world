package SceneGraph.Renderables;

import javax.media.opengl.GL;

import com.sun.opengl.util.texture.Texture;
import SceneGraph.ParentableNode;
import SceneGraph.Point3D;
import SceneGraph.RenderableNode;

public class Skybox extends RenderableNode {
	Texture texture = null;
	Texture texture1 = null;
	Texture texture2 = null;
	Texture texture3 = null;
	Texture texture4 = null;
	Texture texture5 = null;
	
	public Skybox(String name, ParentableNode parent, Texture texture,Texture texture1,Texture texture2,Texture texture3,Texture texture4,Texture texture5) {
		super(name, parent);
			this.texture = texture;
			this.texture1 = texture1;
			this.texture2= texture2;
			this.texture3 = texture3;
			this.texture4 = texture4;
			this.texture5 = texture5;
			
	}

	@Override
	public void render(GL gl) {
		// The faces of a skybox are not affected by lighting!!
		// We use GL_DECAL to override the colour of vertex shading using the texture colour
		gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_DECAL);

		// OpenGL uses the lower-left corner of the image as (0,0), while the
		// image file storage goes from the upper-left corner. Here's the workaround..
		gl.glMatrixMode(GL.GL_TEXTURE); // switch to (s,t) space
        gl.glLoadIdentity();
        gl.glTranslated(0, 1, 0);		// flip the t coordinate vertically
		gl.glScaled(1.0f, -1.0f, 1.0f); //
		gl.glMatrixMode(GL.GL_MODELVIEW); // switch back to model-view matrix
		
		//..Begin to draw the box...........................................
		Point3D v0 = new Point3D(-0.5f, -0.5f, -0.5f);
		Point3D v1 = new Point3D(+0.5f, -0.5f, -0.5f);
		Point3D v2 = new Point3D(+0.5f, -0.5f, +0.5f);
		Point3D v3 = new Point3D(-0.5f, -0.5f, +0.5f);
		Point3D v4 = new Point3D(-0.5f, +0.5f, -0.5f);
		Point3D v5 = new Point3D(+0.5f, +0.5f, -0.5f);
		Point3D v6 = new Point3D(+0.5f, +0.5f, +0.5f);
		Point3D v7 = new Point3D(-0.5f, +0.5f, +0.5f);
				
		texture.enable();
		texture.bind();
		gl.glBegin(GL.GL_QUADS);	
		
		// the font face : v2 -> v3 -> v7 -> v6
		gl.glTexCoord2d(0, 0);
		v2.draw(gl);
		gl.glTexCoord2d(1, 0);
		v3.draw(gl);
		gl.glTexCoord2d(1, 1);
		v7.draw(gl);
		gl.glTexCoord2d(0, 1);
		v6.draw(gl);
		gl.glEnd();		
		//..End of drawing ................................................
		texture.disable();
		
		texture1.enable();
		texture1.bind();
		gl.glBegin(GL.GL_QUADS);
		
		// the left face : v0 -> v3 -> v7 -> v4
		gl.glTexCoord2d(1, 0);
		v0.draw(gl);
		gl.glTexCoord2d(0, 0);
		v3.draw(gl);
		gl.glTexCoord2d(0, 1);
		v7.draw(gl);
		gl.glTexCoord2d(1, 1);
		v4.draw(gl);
		gl.glEnd();		
		//..End of drawing ................................................
		texture1.disable();
			
		texture2.enable();
		texture2.bind();
		gl.glBegin(GL.GL_QUADS);
		
		// the right face : v1 -> v2 -> v6 -> v5
		gl.glTexCoord2d(0, 0);
		v1.draw(gl);
		gl.glTexCoord2d(1, 0);
		v2.draw(gl);
		gl.glTexCoord2d(1, 1);
		v6.draw(gl);
		gl.glTexCoord2d(0, 1);
		v5.draw(gl);  
		gl.glEnd();		
		//..End of drawing ................................................
		texture2.disable();
		
		texture3.enable();
		texture3.bind();
		gl.glBegin(GL.GL_QUADS);
		
		// the top face : v4 -> v5 -> v6 -> v7
		gl.glTexCoord2d(0, 0);
		v4.draw(gl);
		gl.glTexCoord2d(1, 0);
		v5.draw(gl);
		gl.glTexCoord2d(1, 1);
		v6.draw(gl);
		gl.glTexCoord2d(0, 1);
		v7.draw(gl);  
		gl.glEnd();		
		//..End of drawing ................................................
		texture3.disable();
		
		texture4.enable();
		texture4.bind();
		gl.glBegin(GL.GL_QUADS);
		
		// the back face : v0 -> v4 -> v5 -> v1
		gl.glTexCoord2d(0, 0);
		v0.draw(gl);
		gl.glTexCoord2d(0, 1);
		v4.draw(gl);
		gl.glTexCoord2d(1, 1);
		v5.draw(gl);
		gl.glTexCoord2d(1, 0);
		v1.draw(gl); 
		gl.glEnd();		
		//..End of drawing ................................................
		texture4.disable();
		
		texture5.enable();
		texture5.bind();
		gl.glBegin(GL.GL_QUADS);
		
		// the top face : v3 -> v0 -> v1 -> v2
		gl.glTexCoord2d(0, 0);
		v3.draw(gl);
		gl.glTexCoord2d(0, 1);
		v0.draw(gl);
		gl.glTexCoord2d(1, 1);
		v1.draw(gl);
		gl.glTexCoord2d(1, 0);
		v2.draw(gl);  
		gl.glEnd();		
		//..End of drawing ................................................
		texture5.disable();
		

	}
}
