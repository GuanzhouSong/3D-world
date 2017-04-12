package SceneGraph.Renderables;

import java.awt.Color;
import java.io.File;

import javax.media.opengl.GL;

import SceneGraph.ParentableNode;
import SceneGraph.RenderableNode;

public class Ground extends RenderableNode {
	private int dimsX = 0;
	private int dimsY = 0;
	private float sizeX = 0;
	private float sizeY = 0;
	private int thickness = 1;
	private float colorx;
	private float colory;
	private float colorz;
	
	
	public Ground(
		String name, ParentableNode parent,
		int dimsX, int dimsY, float sizeX, float sizeY, float colorx,float colory,float colorz) {

		super(name, parent);

		this.dimsX = dimsX;
		this.dimsY = dimsY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.colorx = colorx;
		this.colory = colory;
		this.colorz = colorz;
	}

	

	@Override
	public void render(GL gl) {
		gl.glLineWidth(thickness);
		gl.glDisable(GL.GL_LIGHTING);
		gl.glBegin(GL.GL_QUADS);		
		
		float x0 = -0.5f * dimsX * sizeX;
		float y0 = -0.5f * dimsY * sizeY;
		float x1 = x0 + dimsX * sizeX;
		float y1 = y0 + dimsY * sizeY;
		
		for (int i = 0; i < dimsY + 1; i++) {
			float y = y0 + i * sizeY;
			    for (int j = 0; j < dimsX + 1; j++) {
				    float x = x0 + j * sizeX;
				
				gl.glColor3d(colorx, colory, colorz); 
				gl.glVertex3f(x, x, 0);
			    gl.glVertex3f(x, y, 0);
			    gl.glVertex3f(y, y, 0);
			    gl.glVertex3f(y, x, 0);
			    
		}
			
		}

		gl.glEnd();
		gl.glEnable(GL.GL_LIGHTING);
	}

}
