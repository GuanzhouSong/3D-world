package SceneGraph.Renderables;

import javax.media.opengl.GL;

import com.sun.opengl.util.texture.Texture;
import SceneGraph.ParentableNode;
import SceneGraph.Point3D;
import SceneGraph.RenderableNode;

public class Building extends RenderableNode {
	Texture texture6 = null;
	Texture texture7 = null;
	Texture texture8 = null;
	Texture texture9 = null;
	Texture texture10 = null;
	Texture texture11 = null;
	
	public Building(String name, ParentableNode parent, Texture texture6,Texture texture7,Texture texture8,Texture texture9,Texture texture10,Texture texture11) {
		super(name, parent);
			this.texture6 = texture6;
			this.texture7 = texture7;
			this.texture8 = texture8;
			this.texture9 = texture9;
			this.texture10 = texture10;
			this.texture11 = texture11;
			
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
		Point3D v0 = new Point3D(-0.5f, -2.5f, -0.5f);
		Point3D v1 = new Point3D(+0.5f, -2.5f, -0.5f);
		Point3D v2 = new Point3D(+0.5f, -2.5f, +0.5f);
		Point3D v3 = new Point3D(-0.5f, -2.5f, +0.5f);
		Point3D v4 = new Point3D(-0.5f, +2.5f, -0.5f);
		Point3D v5 = new Point3D(+0.5f, +2.5f, -0.5f);
		Point3D v6 = new Point3D(+0.5f, +2.5f, +0.5f);
		Point3D v7 = new Point3D(-0.5f, +2.5f, +0.5f);
				
		texture6.enable();
		texture6.bind();
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
		texture6.disable();
		
		texture7.enable();
		texture7.bind();
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
		texture7.disable();
			
		texture8.enable();
		texture8.bind();
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
		texture8.disable();
		
		texture9.enable();
		texture9.bind();
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
		texture9.disable();
		
		texture10.enable();
		texture10.bind();
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
		texture10.disable();
		
		texture11.enable();
		texture11.bind();
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
		texture11.disable();
		

	}
}