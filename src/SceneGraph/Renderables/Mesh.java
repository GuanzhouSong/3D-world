package SceneGraph.Renderables;

import java.util.ArrayList;
import javax.media.opengl.GL;
import com.sun.opengl.util.texture.Texture;

import SceneGraph.*;

public class Mesh extends RenderableNode {
	protected Material material = Material.CLAY;
	protected Texture texture = null;
	protected int textureMode = GL.GL_MODULATE;
	protected ArrayList<Point3D> geometry;
	protected ArrayList<Vector3D> normals;
	protected ArrayList<Texel2D> texels;
	protected ArrayList<Face> faces;
	protected boolean useDisplayList = true;
	protected int displayListId = -1;

	public Mesh(String name, ParentableNode parent) {
		super(name, parent);
		
		geometry = new ArrayList<Point3D>();
		normals = new ArrayList<Vector3D>();
		texels = new ArrayList<Texel2D>();
		faces = new ArrayList<Face>();
	}
	
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	@Override
	public void render(GL gl) {
		if (material != null) {
			material.apply(gl);
		}
		
		if (texture != null) {
			texture.enable();
			texture.bind();
			gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, this.textureMode);
		}
		
		boolean immediate = !useDisplayList;
		
		if (displayListId < 0 && useDisplayList) {
			displayListId = gl.glGenLists(1);
			immediate = true;
		}
		
		if (immediate) {
			if (useDisplayList) {
				gl.glNewList(displayListId, GL.GL_COMPILE);
			}
			
			for (Face f : faces) {
				gl.glBegin(GL.GL_POLYGON);
				for (Vertex v : f.vertices) {
					if (v.texel != null) {
						gl.glTexCoord2d(v.texel.s, v.texel.t);
					}
					if (v.normal != null) {
						gl.glNormal3d(v.normal.x, v.normal.y, v.normal.z);
					}
					if (v.geometry != null) {
						gl.glVertex3d(v.geometry.x, v.geometry.y, v.geometry.z);		
					}
				}
				gl.glEnd();
			}
			
			if (useDisplayList) {
				gl.glEndList();
			}
		}
		
		if (useDisplayList) {
			gl.glCallList(displayListId);
		}
		
		if (texture != null) {
			texture.disable();
		}
		
	}

	protected class Vertex {
		public Point3D geometry = null;
		public Vector3D normal = null;
		public Texel2D texel = null;
		
		public Vertex (Point3D geometry) {
			this.geometry = geometry;
		}
		
		public Vertex (Point3D geometry, Vector3D normal) {
			this.geometry = geometry;
			this.normal   = normal;
		}
		
		public Vertex (Point3D geometry, Vector3D normal, Texel2D texel) {
			this.geometry = geometry;
			this.normal   = normal;
			this.texel    = texel;
		}
	}
	
	protected class Face {
		public ArrayList<Vertex> vertices;
		public Face() {
			vertices = new ArrayList<Vertex>();
		}
	}
	
	protected class TriangularFace extends Face {
		public TriangularFace(Vertex v0, Vertex v1, Vertex v2) {
			super();
			vertices.add(v0);
			vertices.add(v1);
			vertices.add(v2);
		}
	}
}
