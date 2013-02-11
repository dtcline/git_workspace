package game;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.LWJGLException;

public final class Game {
    
    // Display constants
    private final int DISPLAY_WIDTH = 640;
    private final int DISPLAY_HEIGHT = 480;
    
    
    public Game() {
        
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
        System.out.println("Finished in " + (stopTime - startTime) + " milliseconds.");
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
        
        // call glTranslate(float,float,float) to move the screen
        
        // call glBegin to render static objects
        
        // call glEnd
        
        // Poll for input
        
        // Update the display
        
        // Wait until the frame-rate is 60fps
        //Display.sync(60);
    } // end render()
    
    
    private void update() {
        
    } // end update(int)

} // end Game class



















