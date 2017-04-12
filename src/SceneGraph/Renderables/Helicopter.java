package SceneGraph.Renderables;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

import SceneGraph.*;
import SceneGraph.Light.PointLight;


public class Helicopter extends RenderableNode implements KeyListener,Runnable {
	protected double moveSpeed = 0.1f;
	protected double rotSpeed  = 1.0f;
	
	public Fan fan1;
	public Fan fan2;
	PointLight pLight;
	
	double i = 0;
	
	public Helicopter(String name, ParentableNode parent, double trunkHeight, double headHeight, GLAutoDrawable canvas) {
		super(name, parent);
		canvas.addKeyListener(this);
		    int radius = 2;
		    
			int stacks1 = 20;
			
			Sphere body=new Sphere ("Body", this, radius * 0.5, stacks1, stacks1);//»úÌå
			body.setScale(1.5, 1.5, 3);
			body.setMaterial(Material.Silver);
			body.getPosition().y = 1.0f;
			
			Sphere head=new Sphere ("Body", body, radius * 0.6, stacks1, stacks1);//»úÌå
			head.setMaterial(Material.Pearl);
			head.getPosition().z = -1.3f;
			head.getPosition().y = 0.2f;
			
			Cylinder pole = new Cylinder("Pole", body, radius * 0.1, radius * 0.1, radius * 0.6, stacks1, stacks1);//ÂÝÐý½°µÄ¸Ë×Ó
			pole.getOrientation().setPitch(270);
			pole.setMaterial(Material.BlackPlastic);
			pole.getPosition().y = 0.5f;
			
			Cylinder pole1 = new Cylinder("Pole1", body, radius * 0.05, radius * 0.05, radius * 1, stacks1, stacks1);//µ××ùÊú¸Ë
			pole1.getOrientation().setPitch(90);
			pole1.setMaterial(Material.Brass);
			pole1.getPosition().x = 0.6f;
			pole1.getPosition().z = 0.8f;
			
			Cylinder pole2 = new Cylinder("Pole2", body, radius * 0.05, radius * 0.05, radius * 1, stacks1, stacks1);//µ××ùÊú¸Ë
			pole2.getOrientation().setPitch(90);
			pole2.setMaterial(Material.Brass);
			pole2.getPosition().x = -0.6f;
			pole2.getPosition().z = 0.8f;
			
			Cylinder pole3 = new Cylinder("Pole3", body, radius * 0.05, radius * 0.05, radius * 1, stacks1, stacks1);//µ××ùÊú¸Ë
			pole3.getOrientation().setPitch(90);
			pole3.setMaterial(Material.Brass);
			pole3.getPosition().x = 0.6f;
			pole3.getPosition().z = -0.8f;
			
			Cylinder pole4 = new Cylinder("Pole4", body, radius * 0.05, radius * 0.05, radius * 1, stacks1, stacks1);//µ××ùÊú¸Ë
			pole4.getOrientation().setPitch(90);
			pole4.setMaterial(Material.Brass);
			pole4.getPosition().x = -0.6f;
			pole4.getPosition().z = -0.8f;
			
			Cylinder pole5 = new Cylinder("Pole5", pole2, radius * 0.05, radius * 0.05, radius * 3.0, stacks1, stacks1);//µ××ùºá¸Ë
			pole5.getOrientation().setPitch(90);
			pole5.setMaterial(Material.Brass);
			pole5.getPosition().y = 2f;
			pole5.getPosition().z = 2f;
			
			Cylinder pole6 = new Cylinder("Pole6", pole1, radius * 0.05, radius * 0.05, radius * 3.0, stacks1, stacks1);//µ××ùºá¸Ë
			pole6.getOrientation().setPitch(90);
			pole6.setMaterial(Material.Brass);
			pole6.getPosition().y = 2f;
			pole6.getPosition().z = 2f;
			
			Cylinder tail = new Cylinder("Tail", body, radius * 0.12, radius * 0.25, radius * 4.0, stacks1, stacks1);//Î²°Íºá¸Ë
			tail.setMaterial(Material.Pearl);
			tail.getOrientation().setPitch(180);
			tail.getPosition().z = 5.5f;
			
			Sphere tailball = new Sphere("Tailball", tail, radius * 0.13, stacks1, stacks1);
			tailball.setMaterial(Material.Silver);
		
			fan1 = new Fan("Fan1", pole, radius * 1.5);
			fan1.setScale(10, 0.1, stacks1);
			fan1.getOrientation().setYaw(90.0f);
			fan1.getPosition().z = 1.2;	
			Thread thread1=new Thread(fan1);
	    	thread1.start();  
	    	
			fan2 = new Fan("Fan2", tailball, radius * 0.8); 
		    fan2.getOrientation().setYaw(180.0f);
		    fan2.getPosition().x = - radius * 0.13;
			Thread thread2=new Thread(fan2);
	    	thread2.start();  
	    	
	    	pLight = new PointLight("pLight", this, new Point3D(body.getPosition().x, body.getPosition().y + 5, body.getPosition().z));
		}

		@Override
		public void render(GL gl) {} // nothing to draw.. my children nodes will do the work
	
        private void move(double dx, double dy, double dz) {
	        double yaw = Orientation.deg2rad(orient.getYaw());
	        double cos = Math.cos(yaw);
	        double sin = Math.sin(yaw);

	        if(this.getPosition().y >= 1){
	        	
	        	Vector3D vec = new Vector3D(sin * dz + cos * dx, dy, cos * dz - sin * dx);
		        centre = centre.add(vec);
	        	
	        };
	        if(this.getPosition().y <= 1){
	        	
	        	this.getPosition().y = this.getPosition().y + 0.1;
	        	
	        }
	        
        }

        private void rotate(double dPitch, double dYaw) {
	       //orient.setPitch(orient.getPitch() + dPitch);
	        orient.setYaw  (orient.getYaw()   + dYaw  );
        }

        public void orientationJudge(){
        	float roll=18;
        	if(orient.getYaw()>90.0f&&orient.getYaw()<180.0f){
        		orient.setPitch(-roll);
        		//orient.setPitch(roll);
        	}else{	
        		 orient.setRoll(roll);
        	}
        }
        public void OrientationJudge(){
        	float roll2=-18;
        	if(orient.getYaw()>90.0f&&orient.getYaw()<180){
        		orient.setPitch(roll2);
        	}
        	else{
        		 orient.setRoll(roll2);
        	}
        }
        public void SpeedUp(){
        fan1.SetTheSpeed(i);
        i+=0.000001;
        fan2.SetTheSpeed(i);
        i+=0.000001;	
        }
        public void Start(){
        if(fan1.speed >= 0.000199){
        move(0,+moveSpeed,0); 
        }
        if(fan2.speed >= 0.000199){
        	move(0,+moveSpeed,0); 
        	}
        SpeedUp();
        }
        public void Landing(){
        if(this.getPosition().y <= 1.1){
        	fan1.SetTheSpeed(0);
            fan2.SetTheSpeed(0);
        	i = 0.000001;				
        }
        move(0,-moveSpeed,0);
        }


            public void keyPressed(KeyEvent e) {
	                switch (e.getKeyCode()) {
	                case KeyEvent.VK_D: 
	                	if(fan1.speed >= 0.000199){move(+moveSpeed,0,0);
	                	if(orient.getYaw()<=90.0f&&orient.getYaw()>=90){orient.setRoll(20);orient.setPitch(0);}else{orient.setRoll(-20);orient.setPitch(0);}}break;
	                case KeyEvent.VK_A:	
	                	if(fan1.speed >= 0.000199){move(-moveSpeed,0,0);
	                	if(orient.getYaw()<=90.0f&&orient.getYaw()>=90){orient.setRoll(-20);orient.setPitch(0);}else{orient.setRoll(20);orient.setPitch(0);}}break;
	                case KeyEvent.VK_S: 
	                	if(fan1.speed >= 0.000199){move(0,0,+moveSpeed);
	                	orient.setPitch(18);}break;
	                case KeyEvent.VK_W:	
	                	if(fan1.speed >= 0.000199){move(0,0,-moveSpeed);
	                	orient.setPitch(-18);}break;
	                case KeyEvent.VK_UP: 
	                	Start(); break;
	                case KeyEvent.VK_DOWN: 
	                	Landing(); break;
	                case KeyEvent.VK_LEFT:	
	                	rotate(0, rotSpeed); break;
	                case KeyEvent.VK_RIGHT:	
	                	rotate(0,-rotSpeed); break;
	                case KeyEvent.VK_NUMPAD6: 
	                	move(0,0,0); orient.setYaw(-90);break;
	                case KeyEvent.VK_NUMPAD4: 
	                	move(0,0,0); orient.setYaw(90);break;
	                case KeyEvent.VK_NUMPAD5: 
	                	move(0,0,0); orient.setYaw(180);break;

	}
	
}


           public void keyReleased(KeyEvent e) {
               switch (e.getKeyCode()) {
               case KeyEvent.VK_D: 
            	   if(fan1.speed >= 0.000199){move(+moveSpeed,0,0); orient.setRoll(0);orient.setPitch(0);}break;
               case KeyEvent.VK_A:	
            	   if(fan1.speed >= 0.000199){move(-moveSpeed,0,0); orient.setRoll(0);orient.setPitch(0);}break;
               case KeyEvent.VK_S: 
            	   if(fan1.speed >= 0.000199){move(0,0,+moveSpeed); orient.setPitch(0);}break;
               case KeyEvent.VK_W:	
            	   if(fan1.speed >= 0.000199){move(0,0,-moveSpeed); orient.setPitch(0);}break;
               case KeyEvent.VK_NUMPAD6: 
               	move(0,0,0); orient.setYaw(0);break;
               case KeyEvent.VK_NUMPAD4: 
               	move(0,0,0); orient.setYaw(0);break;
               case KeyEvent.VK_NUMPAD5: 
                move(0,0,0); orient.setYaw(0);break;
               }
           }
	// TODO Auto-generated method stub
	


           public void keyTyped(KeyEvent arg0) {}
	// TODO Auto-generated method stub
	

           public void run() {}
}
