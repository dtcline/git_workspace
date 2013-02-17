package experiments;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.LWJGLException;

public final class Experiment {
    
    // Display constants
    private final int DISPLAY_WIDTH = 640;
    private final int DISPLAY_HEIGHT = 480;
    
    
    public Experiment() {
        
    } // end Game()
    
    
    public void run() {
        long startTime = System.currentTimeMillis();
        
        initializeDisplay();
        initializeOpenGL();
        
        // Main render loop
        while(!Display.isCloseRequested()) {
            update();
            render();
            
            Display.update();
            Display.sync(60);
        } // end main render loop
        
        Display.destroy();
        
        
        long stopTime = System.currentTimeMillis();
        System.out.println("Finished in " + (float)(stopTime - startTime) / 1000.0f + " seconds.");
    } // end run()
    
    
    private void initializeDisplay() {
     // Display Initialization
        try {
            Display.setDisplayMode(new DisplayMode(DISPLAY_WIDTH, DISPLAY_HEIGHT));
            Display.setTitle("Singularity");
            Display.setResizable(true);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    } // end initializeDisplay()
    
    
    private void initializeOpenGL() {
     // OpenGL initialization
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, DISPLAY_WIDTH, 0, DISPLAY_HEIGHT, 1, -1);
        glMatrixMode(GL_MODELVIEW);
    } // end initializeOpenGL()
 
    
    private void render() {
        // Clear the screen
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        
        // call glBegin to render static objects
        glBegin(GL_POINTS);
        for (int i = 0; i < 50; i++) {
        	for (int j = 0; j < 50; j++) {
        		if (i % 2 == 0 && j % 2 == 0) {
        			glVertex2i(i, j);
        		}
        	}
        }
        glEnd();
        // call glEnd
        
        // Wait until the frame-rate is 60fps
        //Display.sync(60);
    } // end render()
    
    
    private void update() {
        System.out.println(Display.getWidth() + " x " + Display.getHeight());
    } // end update(int)

} // end Game class
