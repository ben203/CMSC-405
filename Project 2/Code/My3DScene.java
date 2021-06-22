package project2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLJPanel;

/**
 *  
 *  Name: Bedemariam Degef
 *  Class: CMSC 405
 *  Date: 02/06/2021
 *  Description: This class will create 3D openGL scene using at least 6 different shapes and transformation methods.
 *               The scene will be displayed in a 640 by 480 px window and use JOGL for the implementation of OpenGL.
 *  
 * 
 */
@SuppressWarnings("serial")
public class My3DScene extends JPanel implements GLEventListener
{

    public static void main(String[] args)
    {
        JFrame window = new JFrame("My 3D Graphics Scene");
        My3DScene panel = new My3DScene();
        window.setContentPane(panel);
        window.pack(); // Set window size based on the preferred sizes of its contents.
        window.setResizable(false);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation( // Center window on screen.
                (screen.width - window.getWidth())/2, 
                (screen.height - window.getHeight())/2 );
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
    
    private MyShapes myShapes = new MyShapes(); // An instance of the MyShpaes class containing all the shapes that I have drawn. 
    private int frameNumber;    // Current frame number, increases by 1 in each frame.
    private Timer animationTimer;

    
    /**
     * Constructor creates the GLJPanel that will be used for drawing and adds
     *  it to the main panel.  It also starts a timer to draw the animation.  And
     *  it sets the preferred size to be 640-by-480.
     */
    public My3DScene()
    {
    	GLCapabilities caps = new GLCapabilities(null);
    	
    	GLJPanel display = new GLJPanel(caps);
    	display.setPreferredSize( new Dimension(640,480) ); //sets display size of the viewing screen to 640-by-480
    	display.addGLEventListener(this);
        setLayout(new BorderLayout());
        add(display, BorderLayout.CENTER);

        //start the animation
        animationTimer = new Timer(20, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                frameNumber++;
                display.repaint();
            }
        });
        animationTimer.start();       
    }

    // ---------------  Methods of the GLEventListener interface -----------
    
    /**
     * This method is called when the GLJPanel is first created.  It initializes
     * the GL drawing context.  Here, it sets the clear color to be dark gray and
     * it sets the xy-limits for drawing so that x ranges from -10 to 10 and
     * y ranges from -10 to 10.
     */
    public void init(GLAutoDrawable drawable)
    {
        // called when the panel is created
        GL2 gl2 = drawable.getGL().getGL2();
        
        gl2.glEnable(GL2.GL_DEPTH_TEST); // Required for 3D drawing, not usually for 2D.
        gl2.glLineWidth(2);
        gl2.glShadeModel(GL2.GL_SMOOTH);
        gl2.glEnable(GL2.GL_COLOR_MATERIAL);
        gl2.glClearColor(0.5f,0.5f,0.5f,1); // Set background color

        // The next three lines set up the coordinates system.
        gl2.glMatrixMode(GL2.GL_PROJECTION);
        gl2.glLoadIdentity();
        gl2.glOrtho(-10, 10, -10, 10, -5, 5); //sets up the orthographic projection with the near and far perspective to 5. 
        gl2.glMatrixMode(GL2.GL_MODELVIEW);
        
        
        gl2.glClearDepth(1.0f);
        gl2.glDepthFunc(GL2.GL_LEQUAL);
        gl2.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST); 
        
        //turn transparency on
        gl2.glEnable(GL2.GL_BLEND);
        gl2.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
        
    }

    /**
     * This method is called when the GLJPanel needs to be redrawn.
     * It draws the current frame of the animation.
     */
    @SuppressWarnings("static-access")
	public void display(GLAutoDrawable drawable)
    {
        GL2 gl2 = drawable.getGL().getGL2();
        gl2.glClear( GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT ); // need depth buffer for 3D.     

        gl2.glLoadIdentity();
                
        /* Draw the 3D Triangle.  The drawTriangle method draws the shape with the center at (0,0).
         * The image will be rotating in an infinite loop about the z-axis
         * It will be uniformly scaled by 1.5 so that it's displayed proportionally on the window.
         * It will be rotated 90 degrees about the line through the points (0,0,0) and (5,5,5).
         * A translation is applied to move the triangle to the coordinate (-6,5,0).
         */

        gl2.glPushMatrix(); 
        gl2.glLineWidth(2);
        gl2.glTranslated(-6.0,5.0,0); 
        gl2.glRotated(90,1.5,2,-3); 
        gl2.glScaled(1.5,1.5,1.5);  
        gl2.glRotated(frameNumber*0.7,0,0,1);
        myShapes.drawTriangle(gl2);
        gl2.glPopMatrix();
        
        
        /* Draw the 3D Cube.  The drawCube method draws the shape with the center at (0,0).
         * The image will be rotating in an infinite loop about the x-axis
         * It will be uniformly scaled by 1.5 so that it's displayed proportionally on the window.
         * It will be rotated 50 degrees about the line through the points (0,0,0) and (1.5,2,-3)
         * A 3D translation is applied to move the triangle to the coordinate (0,5,0).
         */
        gl2.glPushMatrix();
        gl2.glLineWidth(1);
        gl2.glTranslated(0,5.0,0); 
        gl2.glRotated(30,1.5,-2,3);  
        gl2.glScaled(1.5,1.5,1.5);  
        gl2.glRotated(-frameNumber*0.7,1,0,0);
        myShapes.drawCube(gl2);
        gl2.glPopMatrix();
        
        
        /* Draw the 3D Cylinder.  The drawCylinder method draws the shape with the center at (0,0).
         * The image will be rotating in an infinite loop about the y-axis.
         * It will be uniformly scaled by 1.5 so that it's displayed proportionally on the window.
         * It will be rotated 45 degrees about the line through the points (0,0,0) and (1.5,2,-3).
         * A translation is applied to move the triangle to the coordinate (6,5,0).
         * The cylinder will be moving in a continuous circular path of radius 2 from its new location. 
         */
        gl2.glPushMatrix();
        gl2.glTranslated(2*Math.cos(.015*frameNumber) + 0, 2*Math.sin(.015*frameNumber) + 0, 0.0);
        gl2.glTranslated(6.0,5.0,0); 
        gl2.glRotated(45,1.5,-2,3); 
        gl2.glScaled(1.6,1.6,1.6);  
        gl2.glRotated(-frameNumber*0.7,0,1,0);
        myShapes.drawCylinder(gl2,0.5,1,32,10,5);
        gl2.glPopMatrix();
                
        
        /* Draw the 3D Plus symbol.  The drawPlusSymbol method draws the shape with the center at (0,0).
         * The image will be rotating in an infinite loop about the y-axis.
         * It will be uniformly scaled by .275 so that it's displayed proportionally on the window.
         * It will be rotated 45 degrees about the line through the points (0,0,0) and (1.5,2,-3).
         * 
         * A translation is applied to move the cylinder horizontally.  The amount of the translation
         * depends on the frame number, which makes the shape move from left to right across
         * the screen as the animation progresses.  The animation repeats every 500 
         * frames. At the beginning of the animation, the cylinder is off the left edge of the
         * screen.
         */
        gl2.glPushMatrix(); 
        gl2.glLineWidth(2);
        gl2.glTranslated(-12 + 24*(frameNumber % 500) / 500.0, 0, 0);
        gl2.glRotated(45,1.5,-2,3); 
        gl2.glScaled(.275,.275,.275);  
        gl2.glRotated(-frameNumber*0.7,0,1,0);
        myShapes.drawPlusSymbol(gl2);
        gl2.glPopMatrix();
          
        
        /* Draw the 3D Tetrahedron.  The drawTetrahedron method draws the shape with the center at (0,0).
         * The image will be rotating in an infinite loop about the x-axis.
         * It will also be uniformly scaled  in a loop every 250 frames from small(0.004) to normal scale(.99)
         * It will be rotated 20 degrees about the line through the points (0,0,0) and (1.5,2,-3)
         * A translation is applied to move the triangle to the coordinate (-6,-5,0).
         */
        gl2.glPushMatrix();  
        gl2.glLineWidth(1);
        gl2.glTranslated(-6.0,-5.0,0); 
        gl2.glRotated(20,1.5,-2,3);  
        gl2.glScaled(.99*(frameNumber % 250) /250,.99*(frameNumber % 250) / 250,.99*(frameNumber % 250) / 250);  
        gl2.glRotated(-frameNumber*0.7,1,0,0);
        myShapes.drawTetrahedron(gl2);      
        gl2.glPopMatrix();
        
        
        /* Draw the 3D Icosahedron.  The drawIcosahedron method draws the shape with the center at (0,0).
         * The image will be rotating in an infinite loop about the x-axis.
         * It will be uniformly scaled by 1.1 so that it's displayed proportionally on the window.
         * It will be rotated 30 degrees about the line through the points (0,0,0) and (1.5,2,-3).
         * A translation is applied to move the triangle to the coordinate (0,-5,0).
         */
        gl2.glPushMatrix();  
        gl2.glLineWidth(2);
        gl2.glTranslated(0.0,-5.0,0); 
        gl2.glRotated(30,1.5,-2,3); 
        gl2.glScaled(1.1,1.1,1.1);  
        gl2.glRotated(-frameNumber*0.7,1,0,0);
        myShapes.drawIcosahedron(gl2);      
        gl2.glPopMatrix();
        
        
        /* Draw the 3D Diamond.  The drawDiamond method draws the shape with the center at (0,0).
         * The image will be rotating in an infinite loop about the y-axis.
         * It will be uniformly scaled by .35 so that it's displayed proportionally on the window.
         * It will be rotated 35 degrees about the line through the points (0,0,0) and (1.5,2,-3)
         * A translation is applied to move the triangle to the coordinate (6,-5,0).
         */
        gl2.glPushMatrix();  
        gl2.glLineWidth(2);
        gl2.glTranslated(6.0,-5.0,0); 
        gl2.glRotated(35,1.5,-2,3);  
        gl2.glScaled(.35,.35,.35);  
        gl2.glRotated(-frameNumber*0.7,0,1,0);
        myShapes.drawDiamond(gl2);      
        gl2.glPopMatrix();             
    }  

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height){  	
    	
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void dispose(GLAutoDrawable arg0) {
    }
}
