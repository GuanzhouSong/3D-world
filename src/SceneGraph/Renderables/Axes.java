package SceneGraph.Renderables;

import java.awt.Color;

import javax.media.opengl.GL;
import SceneGraph.ParentableNode;
import SceneGraph.RenderableNode;

public class Axes extends RenderableNode {
	private float lenX = 1.0f;
	private float lenY = 1.0f;
	private float lenZ = 1.0f;
	private Color colourX = Color.RED;
	private Color colourY = Color.GREEN;
	private Color colourZ = Color.BLUE;
	private int thickness = 10;

	public Axes(String nodeName, ParentableNode parentNode) {
		super(nodeName, parentNode);
	}

	@Override
	public void render(GL gl) {
		gl.glDisable(GL.GL_LIGHTING);
		gl.glLineWidth(thickness);
		gl.glBegin(GL.GL_LINES);		
		gl.glColor3fv(colourX.getComponents(null), 0);
		gl.glVertex3f(0, 0, 0);
		gl.glVertex3f(lenX, 0, 0);
		gl.glColor3fv(colourY.getComponents(null), 0);
		gl.glVertex3f(0, 0, 0);
		gl.glVertex3f(0, lenY, 0);
		gl.glColor3fv(colourZ.getComponents(null), 0);
		gl.glVertex3f(0, 0, 0);
		gl.glVertex3f(0, 0, lenZ);
		gl.glEnd();
		gl.glEnable(GL.GL_LIGHTING);
	}

}
