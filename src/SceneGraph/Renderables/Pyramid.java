package SceneGraph.Renderables;

import SceneGraph.*;

public class Pyramid extends Mesh {

	public Pyramid(String name, ParentableNode parent) {
		super(name, parent);
		
		//       north     x
		//    v1------v0   ^
		//    |  \   / |   |
		//    |   v4   |   +---> z
		//    |  /   \ |
		//    v3------v2
		//       south
		
		// calculate height
		double h = Math.sqrt(1.0f*1.0f - 0.5f*0.5f);
		
		// set up geometry
		geometry.add(new Point3D( 0.5f, 0.0f,  0.5f)); // v0
		geometry.add(new Point3D( 0.5f, 0.0f, -0.5f)); // v1
		geometry.add(new Point3D(-0.5f, 0.0f,  0.5f)); // v2
		geometry.add(new Point3D(-0.5f, 0.0f, -0.5f)); // v3
		geometry.add(new Point3D( 0.0f,    h,  0.0f)); // v4
		
		// set up normal vectors
		normals.add(new Vector3D( 1.0f, 1.0f,  1.0f)); // n0
		normals.add(new Vector3D( 1.0f, 1.0f, -1.0f)); // n1
		normals.add(new Vector3D(-1.0f, 1.0f,  1.0f)); // n2
		normals.add(new Vector3D(-1.0f, 1.0f, -1.0f)); // n3
		normals.add(new Vector3D( 0.0f, 1.0f,  0.0f)); // n4
		
		// set up texels
		texels.add(new Texel2D(10.0f,10.0f));
		texels.add(new Texel2D( 0.0f,10.0f));
		texels.add(new Texel2D(10.0f, 0.0f));
		texels.add(new Texel2D( 0.0f, 0.0f));
		texels.add(new Texel2D( 5.0f, 5.0f));
		
		// put them together to assemble vertices
		Vertex[] v = new Vertex[5];
		for (int i = 0; i < 5; i++) {
			v[i] = new Vertex(geometry.get(i), normals.get(i), texels.get(i));
		}

		// now let's assemble the faces - don't forget to use your right hand!

		// first - the base: v0 -> v2 -> v3 -> v1
		// think - why not:  v0 -> v1 -> v3 -> v2 ??
		Face base = new Face();
		base.vertices.add(v[0]);
		base.vertices.add(v[2]);
		base.vertices.add(v[3]);
		base.vertices.add(v[1]);
		
		// for the four sides
		Face north = new TriangularFace(v[0], v[1], v[4]); // the north side: v0 -> v1 -> v4
		Face south = new TriangularFace(v[2], v[4], v[3]); // the south side: v2 -> v4 -> v3
		// TODO : finish the other two sides!!
		// ...
		// ...
		
		faces.add(base);
		faces.add(north);
		faces.add(south);
	}

}
