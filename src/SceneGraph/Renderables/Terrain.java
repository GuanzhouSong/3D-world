package SceneGraph.Renderables;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import SceneGraph.ParentableNode;
import SceneGraph.Point3D;
import SceneGraph.Texel2D;
import SceneGraph.Vector3D;

public class Terrain extends Mesh {
	protected int rows;
	protected int cols;
	protected double tileHeight;
	protected double tileWidth;
	protected double maxHeight;
	protected double[][] heightmap;
	
	// default ctor, initialise a flat heightmap
	protected Terrain(
			String name, ParentableNode parent,
			int rows, int cols, double tileHeight, double tileWidth, double maxHeight) {
		super(name, parent);
		this.rows = rows;
		this.cols = cols;
		this.tileHeight = tileHeight;
		this.tileWidth = tileWidth;
		this.maxHeight = maxHeight;
		this.heightmap = new double[rows][cols];
	}

	protected void buildMesh() {
		faces.clear();
		geometry.clear();
		normals.clear();
		texels.clear();
		
		double x0 = -0.5f * (rows - 1) * tileHeight;
		double y0 = 0;
		double z0 = -0.5f * (cols - 1) * tileWidth;
		
		Vertex[][] v = new Vertex[rows][cols];

		// Pass 1: geometry and topology construction
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				double x = x0 + tileHeight * (rows - 1 - i);
				double y = y0 + maxHeight  * heightmap[i][j];
				double z = z0 + tileWidth  * j;
				double s = x;
				double t = z;
				
				v[i][j] = new Vertex(
						new Point3D(x, y, z),
						new Vector3D(0.0f, 1.0f, 0.0f),
						new Texel2D(s, t));
				
				geometry.add(v[i][j].geometry);
				normals. add(v[i][j].normal);
				texels.  add(v[i][j].texel);

				if (i > 0 && j > 0) {		//  v0 - v3
					int i0 = i - 1, i1 = i;	//   | \ |
					int j0 = j - 1, j1 = j;	//  v1 - v2
					
					Vertex v0 = v[i0][j0];
					Vertex v1 = v[i1][j0];
					Vertex v2 = v[i1][j1];
					Vertex v3 = v[i0][j1];
					
					TriangularFace f1 = new TriangularFace(v0, v1, v2);
					TriangularFace f2 = new TriangularFace(v2, v3, v0);
					
					faces.add(f1);
					faces.add(f2);
				}
			}
		}
		
		// Pass 2: normal computation
		for (int i = 1; i < rows-1; i++) {
			for (int j = 1; j < cols-1; j++) {
				ArrayList<Vector3D> n = new ArrayList<Vector3D>();
				int i0 = i - 1, i1 = i + 1;	//	     v0
				int j0 = j - 1, j1 = j + 1;	//       |
				 							// v1 <--o--> v3
											//       |		
											//       v2
				Point3D g0 = v[i][j].geometry;
				Vector3D v0 = v[i0][j].geometry.subtract(g0).getNormalised();
				Vector3D v1 = v[i][j0].geometry.subtract(g0).getNormalised();
				Vector3D v2 = v[i1][j].geometry.subtract(g0).getNormalised();
				Vector3D v3 = v[i][j1].geometry.subtract(g0).getNormalised();
				
				Vector3D n01 = v0.crossProduct(v1).multiply(0.25f);
				Vector3D n12 = v1.crossProduct(v2).multiply(0.25f);
				Vector3D n23 = v2.crossProduct(v3).multiply(0.25f);
				Vector3D n30 = v3.crossProduct(v0).multiply(0.25f);
				
				v[i][j].normal = n01.add(n12).add(n23).add(n30);
			}
		}
	}
	
	public static Terrain fromHeightmapFile(
			String name, ParentableNode parent,
			double tileHeight, double tileWidth, double maxHeight, File file) {
		Terrain terrain = null;
		try {
            BufferedImage im = ImageIO.read(file);
            int rows = im.getHeight();
            int cols = im.getWidth();
            
            terrain = new Terrain(name, parent, rows, cols, tileHeight, tileWidth, maxHeight);
            
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int grey = im.getRGB(j,i) & 255;
                    terrain.heightmap[i][j] = (double)grey / 255.0f;
                }
            }
            
            // generate mesh elements - geometry, normals,..
            terrain.buildMesh();
            
        } catch (IOException e) {
            System.err.println(e);
        }
        return terrain;
	}
}
