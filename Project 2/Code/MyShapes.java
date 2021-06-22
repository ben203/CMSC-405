package project2;

import com.jogamp.opengl.GL2;

 
/**
 *  
 *  Name: Bedemariam Degef
 *  Class: CMSC 405
 *  Date: 02/06/2021
 *  Description: Provides static methods for drawing my 3D shapes in an OpenGL 
 *               drawing context of type GL2.
 * 
 */

public class MyShapes
{	
    /**
     * Creates a 3D Triangle with only the edges drawn. 
     * Each of the three vertex points of a triangle are assigned different colors.
     * The center of the triangle is located at (0,0,0).
     */
    public static void drawTriangle(GL2 gl2)
    { 	
        //Draws the triangle using a GL_LINE_LOOP, 
    	//so only the edges are shown and the triangle is not filled in. 
    	
    	gl2.glBegin(GL2.GL_LINE_LOOP);

        // Front side
        gl2.glColor3f( 1.0f, 0.0f, 0.0f ); // Red
        gl2.glVertex3f( 0f, 1.5f, 0.0f ); // Top Of Triangle (Front)
  		
        gl2.glColor3f( 0.0f, 1.0f, 0.0f ); // Green
        gl2.glVertex3f( -.5f, -.5f, .5f ); // Left Of Triangle (Front)
  		
        gl2.glColor3f( 0.0f, 0.0f, 1.0f ); // Blue
        gl2.glVertex3f( .5f, -.5f, .5f ); // Right Of Triangle (Front)
          
        // Right side
        gl2.glColor3f( 1.0f, 0.0f, 0.0f ); // Red
        gl2.glVertex3f( 0f, 1.5f, 0.0f ); // Top Of Triangle (Right)
  		
        gl2.glColor3f( 0.0f, 0.0f, 1.0f ); // Blue
        gl2.glVertex3f( .5f, -.5f, .5f ); // Left Of Triangle (Right)
  		
        gl2.glColor3f( 0.0f, 1.0f, 0.0f ); // Green
        gl2.glVertex3f( .5f, -.5f, -.5f ); // Right Of Triangle (Right)
          
        // Back side
        gl2.glColor3f( 1.0f, 0.0f, 0.0f ); // Red
        gl2.glVertex3f( 0f, 1.5f, 0.0f ); // Top Of Triangle (Back)
  		
        gl2.glColor3f( 0.0f, 1.0f, 0.0f ); // Green
        gl2.glVertex3f( .5f, -.5f, -.5f ); // Left Of Triangle (Back)
  		
        gl2.glColor3f( 0.0f, 0.0f, 1.0f ); // Blue
        gl2.glVertex3f( -.5f, -.5f, -.5f ); // Right Of Triangle (Back)
          
        //Left side
        gl2.glColor3f( 1.0f, 0.0f, 0.0f ); // Red
        gl2.glVertex3f( 0f, 1.5f, 0.0f ); // Top Of Triangle (Left)
  		
        gl2.glColor3f( 0.0f, 0.0f, 1.0f ); // Blue
        gl2.glVertex3f( -.5f, -.5f, -.5f ); // Left Of Triangle (Left)
  		
        gl2.glColor3f( 0.0f, 1.0f, 0.0f ); // Green
        gl2.glVertex3f( -.5f, -.5f, .5f ); // Right Of Triangle (Left)
  		
        gl2.glEnd(); // Done Drawing 3d triangle (Pyramid)
        gl2.glFlush();
    }
    
    /**
     * Creates a square, which will be used to create the 6 sides of a 3D cube.
     * The center of the square is located at (0,0,0).
     */
    public static void square(GL2 gl2)
    {   
    	//Will use a GL_TRIANGLE_FAN in order to create a solid filled in square.
    	// Each of the 4 corners of the square will be a different color
        gl2.glBegin(GL2.GL_TRIANGLE_FAN);
        gl2.glColor3f( 1.0f, 0.0f, 0.0f ); // Red
        gl2.glVertex3d(-0.5, -0.5, 0.5);   // Bottom left corner
        
        gl2.glColor3f( 0.0f, 1.0f, 0.0f ); // Green
        gl2.glVertex3d(0.5, -0.5, 0.5);    // Bottom right corner
        
        gl2.glColor3f( 0.0f, 0.0f, 1.0f ); // Blue
        gl2.glVertex3d(0.5, 0.5, 0.5);     //Top right corner
        
        gl2.glColor3f( 1.0f, 1.0f, 0.0f ); // Yellow
        gl2.glVertex3d(-0.5, 0.5, 0.5);    //Top left corner
        gl2.glEnd();
        
    	//Will use a GL_LINE_LOOP in order to outline the edges of the square in black
        gl2.glColor3d(0,0,0);  //black
        gl2.glBegin(GL2.GL_LINE_LOOP);
        gl2.glVertex3d(-0.5, -0.5, 0.5);
        gl2.glVertex3d(0.5, -0.5, 0.5);
        gl2.glVertex3d(0.5, 0.5, 0.5);
        gl2.glVertex3d(-0.5, 0.5, 0.5);
        gl2.glEnd();
    }
    
    /**
     * Creates a 3D cube using the square method to create each of the 6 sides. 
     * The center of the cube is located at (0,0,0).
     */
    public static void drawCube(GL2 gl2)
    {
        gl2.glPushMatrix();
        square(gl2);                //Front face
        gl2.glPopMatrix();
        
        gl2.glPushMatrix(); 
        gl2.glRotated(90, 0, 1, 0); //Right face
        square(gl2); 
        gl2.glPopMatrix();
        
        gl2.glPushMatrix();
        gl2.glRotated(-90, 1, 0, 0); //Top face
        square(gl2); 
        gl2.glPopMatrix();
        
        gl2.glPushMatrix();
        gl2.glRotated(180, 0, 1, 0); //Back face
        square(gl2); 
        gl2.glPopMatrix();
        
        gl2.glPushMatrix();
        gl2.glRotated(-90, 0, 1, 0); //Left face
        square(gl2); 
        gl2.glPopMatrix();
        
        gl2.glPushMatrix();
        gl2.glRotated(90, 1, 0, 0); //Bottom face
        square(gl2); 
        gl2.glPopMatrix();
        gl2.glFlush();
    }

    /**
	 * Creates a transparent 3D cylinder with a given radius, number of slices, number of stacks, and
	 * number of rings.  The number of slices is the number of divisions parallel to the 
	 * axis (like the slices of an orange).  The number of stacks is the number
	 * of divisions perpendicular the axis.  If the number or rings is less than or
	 * equal to zero, then the top and bottom caps are not drawn.  If the number of
	 * rings is positive, then the top and bottom caps are drawn, and they are divided
	 * radially into the specified number of rings for drawing; the number of axial 
	 * divisions of the caps is the same as the number of slices. The cylinder has its base in the xy-plane, with 
	 * center at (0,0,0), and its axis lies along the positive direction of the z-axis.
	 */
    public static void drawCylinder(GL2 gl2, double radius, double height, int slices, int stacks, int rings)
	{
		for (int j = 0; j < stacks; j++)
		{
			double z1 = (height/stacks) * j;
			double z2 = (height/stacks) * (j+1);
			gl2.glBegin(GL2.GL_QUAD_STRIP);
			for (int i = 0; i <= slices; i++)
			{
				double longitude = (2*Math.PI/slices) * i;
				double sinLong = Math.sin(longitude);
				double cosLong = Math.cos(longitude);
				double x = cosLong;
				double y = sinLong;
				gl2.glNormal3d(x,y,0);
				
		        gl2.glColor4f( 1.0f, 0.0f, 0.0f, 0.5f ); // Transparent red
				gl2.glVertex3d(radius*x,radius*y,z2);
				gl2.glVertex3d(radius*x,radius*y,z1);
			}
			gl2.glEnd();
		}
		if (rings > 0){ // draw top and bottom
			gl2.glNormal3d(0,0,1);
			for (int j = 0; j < rings; j++)
			{
				double d1 = (1.0/rings) * j;
				double d2 = (1.0/rings) * (j+1);
				gl2.glBegin(GL2.GL_QUAD_STRIP);
				for (int i = 0; i <= slices; i++)
				{
					double angle = (2*Math.PI/slices) * i;
					double sin = Math.sin(angle);
					double cos = Math.cos(angle);			
			        gl2.glColor3f( 0.0f, 0.0f, 1.0f ); // Blue
					gl2.glVertex3d(radius*cos*d1,radius*sin*d1,height);	
			        gl2.glColor3f( 0.0f, 1.0f, 0.0f ); // Green
					gl2.glVertex3d(radius*cos*d2,radius*sin*d2,height);
				}
				gl2.glEnd();
			}
			gl2.glNormal3d(0,0,-1);
			for (int j = 0; j < rings; j++)
			{
				double d1 = (1.0/rings) * j;
				double d2 = (1.0/rings) * (j+1);
				gl2.glBegin(GL2.GL_QUAD_STRIP);
				for (int i = 0; i <= slices; i++)
				{
					double angle = (2*Math.PI/slices) * i;
					double sin = Math.sin(angle);
					double cos = Math.cos(angle);
					
			        gl2.glColor3f( 0.0f, 0.0f, 1.0f ); // Blue
					gl2.glVertex3d(radius*cos*d2,radius*sin*d2,0);
					
			        gl2.glColor3f( 0.0f, 1.0f, 0.0f ); // Green

					gl2.glVertex3d(radius*cos*d1,radius*sin*d1,0);
				}
				gl2.glEnd();
			}
		}
		
		gl2.glFlush();
	}
    
    
    /**
     * Creates a 3D plus symbol using an Indexed Face Set. It includes a list of vertices and faces of the shape, and another list to specify
     * the colors of each face. The center of the diamond shape is located at (0,0,0).
     */
    public static void drawPlusSymbol(GL2 gl2)
    {
    	// An array of all the vertices of the plus symbol in no particular order.
    	double[][] vertices =  new double[][]{
            { 2, -2, 2 },     
            { 2, -2, -2 },    
            { 2, 1, -2 },      
            { 2, 1, 2 },
            { 1.5, 5.0, 0 },
            { -1.5, 5.0, 0 },
            { -2, -2, 2 },
            { -2, 1, 2 },
            { -2, 1, -2 },
            { -2, -2, -2 },
            { 1.5, -5.0, 0 },
            { -1.5, -5.0, 0 },
            { 7, 1.5, 0 },
            { 7, -1.5, 0 },
            { -7, 1.5, 0 },
            { -7, -1.5, 0 },
         };
         
    	int[][] faces = new int[][] {
            { 0, 1, 2, 3 },
            { 3, 2, 4 },
            { 7, 3, 4, 5 },
            { 2, 8, 5, 4 },
            { 5, 8, 7 },
            { 0, 3, 7, 6 },
            { 2, 1, 9, 8 },
            { 6, 7, 8, 9 },
            { 0, 1, 10 },
            { 6, 9, 11 },
            { 6, 0, 10, 11 },
            { 9, 1, 10, 11 },
            
            { 3, 0, 13, 12  },
            { 1, 2, 12, 13  },
            { 2, 3, 12 },
            { 0, 1, 13 },
            

            { 6, 7, 14, 15  },
            { 9, 8, 14, 15  },
            { 7, 8, 14 },
            { 6, 9, 15 },

         };
         
        double[][] faceColors = new double[][] {
        	{1, 0, 0}, //red side
            {1, 1, 0}, //yellow top side
            {0, 0, 1}, //blue top side
            {0, 0, 1}, //blue top side
            {1, 1, 0}, //yellow top side
            {0, 1, 0}, //green side
            {0, 1, 0}, //green side
            {1, 0, 0}, // red side
            {1, 1, 0}, // yellow bottom side
            {1, 1, 0}, // yellow bottom side
            {0, 0, 1}, //blue bottom side
            {0, 0, 1}, //blue bottom side
            
            {0, 0, 1}, //blue right side
            {0, 0, 1}, //blue right side
            {1, 0, 0}, //yellow right side
            {1, 0, 0}, //yellow right side
            
            {0, 0, 1}, //blue left side
            {0, 0, 1}, //blue left side
            {1, 0, 0}, //yellow left side
            {1, 0, 0}, //yellow left side
        };
                             
        gl2.glPushMatrix();

        for (int i = 0; i < faces.length; i++)
        {
            gl2.glColor4d(0,0,0, .3); //color the faces of the star shape a transparent black

            
            gl2.glBegin(GL2.GL_TRIANGLE_FAN);
            for (int j = 0; j < faces[i].length; j++)
            {
                int vertexNum = faces[i][j];
                gl2.glVertex3dv( vertices[vertexNum], 0 );
            }
            gl2.glEnd();
        } 
        
        //draw edges colored in black
        gl2.glColor3f(0,0,0);
        for (int i = 0; i < faces.length; i++)
        {
            gl2.glBegin(GL2.GL_LINE_LOOP);
            for (int j = 0; j < faces[i].length; j++)
            {
                gl2.glColor3dv(faceColors[j], 0 );

                int vertexNum = faces[i][j];
                gl2.glVertex3dv( vertices[vertexNum], 0 );
            }
            gl2.glEnd();
        }

        gl2.glPopMatrix();
        gl2.glFlush();
    }
    
    
    /**
     * Creates 3D Tetrahedron using an Indexed Face Set. 
     * It includes a list of vertices and faces of the shape, and another list to specify the colors of each face.
     * The center of the tetrahedron is located at (0,0,0).
     */
    public static void drawTetrahedron(GL2 gl2)
    {
    	// An array of all the vertices of the tetrahedron in no particular order.
    	double[][] vertices = new double[][] {
            {1, -1, -1},
            {-1, -1, 1},
            {-1, 1, -1},
            {1, 1, 1}
         };
         
    	int[][] faces = new int[][] {
            {0, 3, 2},
            {3, 0, 1},
            {0, 2, 1},
            {2, 3, 1}
         };
         
        double[][] faceColors = new double[][] {
            {1, 0, 0}, // red
            {0, 1, 0}, // green
            {0, 0, 1}, // blue
            {1, 1, 0} // yellow
         };
                  
        gl2.glPushMatrix();
        
        //color the faces of the tetrahedron
        for (int i = 0; i < faces.length; i++)
        {
            gl2.glColor3dv(faceColors[i], 0 );
            
            gl2.glBegin(GL2.GL_TRIANGLE_FAN);
            for (int j = 0; j < faces[i].length; j++)
            {
                int vertexNum = faces[i][j];
                gl2.glVertex3dv( vertices[vertexNum], 0 );
            }
            gl2.glEnd();
        }  
        
       //draw edges colored in black
        gl2.glColor3f(0,0,0);
        for (int i = 0; i < faces.length; i++)
        {
            gl2.glBegin(GL2.GL_LINE_LOOP);
            for (int j = 0; j < faces[i].length; j++)
            {
                int vertexNum = faces[i][j];
                gl2.glVertex3dv( vertices[vertexNum], 0 );
            }
            gl2.glEnd();
        }
        gl2.glPopMatrix();
        gl2.glFlush();
    }

    
    /**
     * Creates a transparent 3D Icosahedron using an Indexed Face Set.
     * It includes a list of vertices and faces of the shape, and another list to specify the colors of each face.
     * The center of the icosahedron is located at (0,0,0).
     */
    public static void drawIcosahedron(GL2 gl2)
    {
    	double[][] vertices = new double[][]{
    		
        	// An array of all the vertices of the tetrahedron in no particular order.
            {-1, -0.618034, 0},
            {0, 1, 0.618034},
            {0, 1, -0.618034},
            {1, 0.618034, 0},
            {1, -0.618034, 0},
            {0, -1, -0.618034},
            {0, -1, 0.618034},
            {0.618034, 0, 1},
            {-0.618034, 0, 1},
            {0.618034, 0, -1},
            {-0.618034, 0, -1},
            {-1, 0.618034, 0}
         };
         
    	int[][] faces = new int[][] {
            {3, 7, 1},
            {4, 7, 3},
            {6, 7, 4},
            {8, 7, 6},
            {7, 8, 1},
            {9, 4, 3},
            {2, 9, 3},
            {2, 3, 1},
            {11, 2, 1},
            {10, 2, 11},
            {10, 9, 2},
            {9, 5, 4},
            {6, 4, 5},
            {0, 6, 5},
            {0, 11, 8},
            {11, 1, 8},
            {10, 0, 5},
            {10, 5, 9},
            {0, 8, 6},
            {0, 10, 11}
         };
        
         // each face color will have a 4th column to indicate the level of transparency. 
        double[][] faceColors = new double[][] {
            {1, 0, 0, 0.4},
            {0, 1, 0, 0.4},
            {0, 0, 1, 0.4},
            {1, 1, 0, 0.4},
            {0, 1, 1, 0.4},
            {1, 0, 0, 0.4},
            {0, 1, 0, 0.4},
            {0, 0, 1, 0.4},
            {1, 1, 0, 0.4},
            {0, 1, 1, 0.4},
            {1, 0, 0, 0.4},
            {0, 1, 0, 0.4},
            {0, 0, 1, 0.4},
            {1, 1, 0, 0.4},
            {0, 1, 1, 0.4},
            {1, 0, 0, 0.4},
            {0, 1, 0, 0.4},
            {0, 0, 1, 0.4},
            {1, 1, 0, 0.4},
            {0, 1, 1, 0.4},
            {0, 0, 1, 0.4},
         };
      
        gl2.glPushMatrix();
        //color the faces of the tetrahedron
        for (int i = 0; i < faces.length; i++)
        {
            gl2.glColor4dv(faceColors[i], 0 );
            
            gl2.glBegin(GL2.GL_TRIANGLE_FAN);
            for (int j = 0; j < faces[i].length; j++)
            {
                int vertexNum = faces[i][j];
                gl2.glVertex3dv( vertices[vertexNum], 0 );
            }
            gl2.glEnd();
        } 
        
        //draw edges colored in black
        gl2.glColor3f(0,0,0);
        for (int i = 0; i < faces.length; i++)
        {
            gl2.glBegin(GL2.GL_LINE_LOOP);
            for (int j = 0; j < faces[i].length; j++)
            {
                int vertexNum = faces[i][j];
                gl2.glVertex3dv( vertices[vertexNum], 0 );
            }
            gl2.glEnd();
        }

        gl2.glPopMatrix();
        gl2.glFlush();
    }
    
    
    /**
     * Creates a 3D diamond looking shape using an Indexed Face Set. 
     * It includes a list of vertices and faces of the shape, and another list to specify the colors of each face.
     * The center of the diamond shape is located at (0,0,0).
     */
    public static void drawDiamond(GL2 gl2)
    {
    	// An array of all the vertices of the diamond shape in no particular order.
    	double[][] vertices =  new double[][]{
            { 2, -2, 2 },  
            { 2, -2, -2 }, 
            { 2, 1, -2 },
            { 2, 1, 2 },
            { 1.5, 5.0, 0 },
            { -1.5, 5.0, 0 },
            { -2, -2, 2 },
            { -2, 1, 2 },
            { -2, 1, -2 },
            { -2, -2, -2 },
            { 1.5, -5.0, 0 },
            { -1.5, -5.0, 0 },
         };
         
    	int[][] faces = new int[][] {
            { 0, 1, 2, 3 }, // middle side
            { 3, 2, 4 },    // top side
            { 7, 3, 4, 5 }, //top side
            { 2, 8, 5, 4 }, // top side
            { 5, 8, 7 },    // top side
            { 0, 3, 7, 6 }, // middle side 
            { 2, 1, 9, 8 }, // middle side
            { 6, 7, 8, 9 }, // middle side
            { 0, 1, 10 },   //bottom side
            { 6, 9, 11 },   //bottom side
            { 6, 0, 10, 11 }, //bottom side
            { 9, 1, 10, 11 }, //bottom side
         };
         
        double[][] faceColors = new double[][] {
        	{1,0,0}, //red middle side
            {1,1,0}, //yellow top side
            {0,0,1}, //blue top side
            {0,0,1}, //blue top side
            {1,1,0}, //red top side
            {0,1,0}, //green middle side
            {0,1,0}, //green middle side
            {1,0,0}, // red middle side
            {1,1,0}, // yellow bottom side
            {1,1,0}, // yellow bottom side
            {0,0,1}, //blue bottom side
            {0,0,1}, //blue bottom side

        };
                             
        gl2.glPushMatrix();
        //color the faces of the diamond shape
        for (int i = 0; i < faces.length; i++)
        {
            gl2.glColor3dv(faceColors[i], 0 );
            
            gl2.glBegin(GL2.GL_TRIANGLE_FAN);
            for (int j = 0; j < faces[i].length; j++)
            {
                int vertexNum = faces[i][j];
                gl2.glVertex3dv( vertices[vertexNum], 0 );
            }
            gl2.glEnd();
        } 
        
        //draw edges colored in black
        gl2.glColor3f(0,0,0);
        for (int i = 0; i < faces.length; i++)
        {
            gl2.glBegin(GL2.GL_LINE_LOOP);
            for (int j = 0; j < faces[i].length; j++)
            {
                int vertexNum = faces[i][j];
                gl2.glVertex3dv( vertices[vertexNum], 0 );
            }
            gl2.glEnd();
        }

        gl2.glPopMatrix();
        gl2.glFlush();
    }
}