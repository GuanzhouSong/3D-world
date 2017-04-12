
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

import SceneGraph.*;
import SceneGraph.Renderables.*;
import SceneGraph.Camera.*;
import SceneGraph.Light.*;

public class Main implements GLEventListener, KeyListener {
	//private Lighting lights;
	private Scene scene = null;
	Fan helicopter;
	

    boolean bool=true;
    TrackingCamera camera1;
	TopviewCamera camera2;
	
	boolean FogTurnOn = false;
   
	public void init(GLAutoDrawable drawable) {
    	scene = new Scene();
 
    	// create axes and grid objects for debugging
        //Axes axes = new Axes("Axes", scene.getRootNode());       
        Ground ground = new Ground("Ground", scene.getRootNode(), 80, 80, 1, 1, 0f, 0.5f, 0.25f);
        ground.getOrientation().setPitch(90.0f);
        ground.getPosition().y = 0.01;

        // let's plant some trees..
        Random r = new Random();
        for (int i = 0; i < 50; i++) {
        	Tree tree;
	        if (r.nextInt() % 2 == 0) {
	        	tree = new SharpTree("Tree No." + i, scene.getRootNode(), 1.2f, 2.0f);
	        } else {
	        	tree = new BallTree ("Tree No." + i, scene.getRootNode(), 1.2f, 2.0f);
	        }
	        
	        tree.getPosition().x = r.nextDouble() * 50.0f - 25.0f;
	        tree.getPosition().z = r.nextDouble() * 50.0f - 25.0f;
        }

        // make a directional light
        DirectionalLight light = new DirectionalLight("Point Light", scene.getRootNode(), new Vector3D(0,1,0));
        light.getOrientation().setRoll(45.0f);
        light.getOrientation().setYaw(45.0f);
        
        

        Helicopter helicopter =new Helicopter("helicopter", scene.getRootNode(), 2.0f, 20, drawable);
        helicopter.getPosition().y = 1.2;
        
        camera2 = new TopviewCamera("Topviewcamera", scene.getRootNode(), drawable, helicopter);
        camera1 = new TrackingCamera("Trackingcamera", scene.getRootNode(), drawable, helicopter);
        camera1.getPosition().y = 3.0f;
        camera1.getPosition().z = 5.0f;
        camera1.getOrientation().setPitch(-10.0f);
        scene.setActiveCamera(camera1);
        Skybox skybox = null;
    	try {
    		Texture skyfont = TextureIO.newTexture(new File("data/textures/a08.png"), true);		
    		Texture skyleft = TextureIO.newTexture(new File("data/textures/a05.png"), true);
    		Texture skyright = TextureIO.newTexture(new File("data/textures/a07.png"), true);
    		Texture skytop = TextureIO.newTexture(new File("data/textures/a02.png"), true);
    		Texture skyback = TextureIO.newTexture(new File("data/textures/a06.png"), true);
    		Texture skyground = TextureIO.newTexture(new File("data/textures/a10.png"), true);
    		
    		skybox = new Skybox("lovely day", scene.getRootNode(), skyfont, skyleft, skyright, skytop, skyback, skyground);
    	} catch (IOException e) {
            throw new RuntimeException(e);
        }
    	skybox.setScale(80.0f, 80.0f, 80.0f);
    	skybox.getPosition().y = 25;
    	
    	Building building = null;
    	try {
    		Texture bfont = TextureIO.newTexture(new File("data/textures/building1.jpg"), true);		
    		Texture bleft = TextureIO.newTexture(new File("data/textures/building1.jpg"), true);
    		Texture bright = TextureIO.newTexture(new File("data/textures/building1.jpg"), true);
    		Texture btop = TextureIO.newTexture(new File("data/textures/bt.png"), true);
    		Texture bback = TextureIO.newTexture(new File("data/textures/building1.jpg"), true);
    		Texture bground = TextureIO.newTexture(new File("data/textures/a10.png"), true);
    		
    		building = new Building("building", scene.getRootNode(), bfont, bleft, bright, btop, bback, bground);
    	} catch (IOException e) {
            throw new RuntimeException(e);
        }
    	building.setScale(10.0f, 10.0f, 10.0f);
    	building.getPosition().z = -15;
    	building.getPosition().x = 15;
    	building.getPosition().y = 10;
    	
    	
    	
//    	Terrain terrain = Terrain.fromHeightmapFile(
//    			"terrain", scene.getRootNode(),
//    			0.25f, 0.25f, 15.0f, new File("data/heightmaps/heightmap1.jpg"));   
//    	terrain.getPosition().y -= 5.0f;
    	
    	// set a sand texture for the terrain
//    	try {
//    		Texture grass = TextureIO.newTexture(new File("data/textures/grass.jpg"), true); 
//    		grass.setTexParameteri(GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
//    		grass.setTexParameteri(GL.GL_TEXTURE_WRAP_T, GL.GL_REPEAT);
//    		terrain.setTexture(grass);
//    		terrain.setMaterial(Material.CLAY);
//		} catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        
    }

    @Override // called when something needs to be drawn on the canvas
    public void display(GLAutoDrawable drawable) {
    	scene.renderScene(drawable);
    	
    	GL gl = drawable.getGL();
    	if(FogTurnOn){
    		gl.glEnable(GL.GL_FOG);
    		gl.glFogf(GL.GL_FOG_MODE, GL.GL_LINEAR);
    		gl.glFogf(GL.GL_FOG_START, 20.0F);
    		gl.glFogf(GL.GL_FOG_END, 50.0F);
    		
    		float[] fogColour = {0.3f, 0.3f, 0.3f, 1.0f};
    		gl.glFogfv(GL.GL_FOG_COLOR, fogColour, 0);
    	}
    	else{
    		gl.glDisable(GL.GL_FOG);
    	}
    }

    @Override // called when component's location or size has changed
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}

    @Override // called when moved to a new display
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}

    public static void main(String[] args) {
    	Frame frame = new Frame("Leon Yu 14871132");
		GLCanvas canvas = new GLCanvas();
		Main quest = new Main();
		canvas.addGLEventListener(quest);
		canvas.addKeyListener(quest);
		frame.add(canvas);
		frame.setSize(1280, 768);
		final Animator animator = new Animator(canvas);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						animator.stop();
						System.exit(0);
					}
				}).start();
			}
		});
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		animator.start();
		
		System.out.println("[W]: move forward");
		System.out.println("[S]: move backward");
	    System.out.println("[A]: move to left");
		System.out.println("[D]: move to right");         
		System.out.println("[V]: Switch the view"); 
		System.out.println("[UP]: rise"); 
		System.out.println("[DOWN]: down"); 
		System.out.println("[LEFT]: turn left"); 
		System.out.println("[RIGHT]: turnright"); 
		System.out.println("[2]: FogTurnOn"); 
		System.out.println("[L]: wireframeMode"); 
		System.out.println("[NUM4]: look left"); 
		System.out.println("[NUM5]: look back"); 
		System.out.println("[NUM6]: look right"); 
		
		
    }
    public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			
		case KeyEvent.VK_V:	 
			
			if(bool==false){
				scene.setActiveCamera(camera1); 
				camera1.move();
				camera1.rotate();
				this.bool=true;
				
				break; 
			}
			
			if(bool==true){
				scene.setActiveCamera(camera2);
				camera2.move();
				camera2.rotate();
				this.bool=false;
			
				break;  
				
			}
			
		case KeyEvent.VK_L:scene.wireframeMode=!scene.wireframeMode;
		
		case KeyEvent.VK_2:FogTurnOn =! FogTurnOn; 
		}		
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
