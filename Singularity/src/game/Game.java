package game;

import gui.Button;
import gui.Text;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;

public final class Game {
    
    // Display constants
    private final int DISPLAY_WIDTH = 640;
    private final int DISPLAY_HEIGHT = 480;
    private final int MIN_DISPLAY_WIDTH = 640;
    private final int MIN_DISPLAY_HEIGHT = 480;
    private long lastFrameTime;
    
    private final Button b = new Button(Display.getWidth() / 2, Display.getHeight() / 2, 100, 100, new Text());
	private long lastFps;
	private int fps;
    
    /** Game object that serves as the client; used for rendering
     * 
     */
    public Game() {
        
    } // end Game()
    
    // TODO: Should I have a stop? and if so, should it just stop rendering????
    /** Starts the game
     * 
     */
    public void run() {
        long startTime = System.currentTimeMillis();
        
        initializeDisplay();
        initializeOpenGL();
        
        lastFrameTime = getTime();
        lastFps = getTime();
        
        // Main render loop
        while(!Display.isCloseRequested()) {
        	
            update();
            render();
            
            System.out.println(getDeltaTime());
            
            Display.update();
            Display.sync(60);
        } // end main render loop
        
        Display.destroy();
        
        
        long stopTime = System.currentTimeMillis();
        System.out.println("Finished in " + (stopTime - startTime) + " milliseconds.");
    } // end run()
    
    /** Called once for display initialization */
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
    
    /** Called once for OpenGL initialization */
    private void initializeOpenGL() {
     // OpenGL initialization
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, DISPLAY_WIDTH, 0, DISPLAY_HEIGHT, 1, -1);
        glMatrixMode(GL_MODELVIEW);
    } // end initializeOpenGL()
 
    
    private final long getTime() {
    	return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    } // end long getTime()
    
    
    private final int getDeltaTime() {
    	long currentTime = getTime();
    	int delta = (int)(currentTime - lastFrameTime);
    	lastFrameTime = getTime();
    	return delta;
    } // end double getDeltaTime()
    
    
    private void updateFps() {
    	if (getTime() - lastFps > 1000) {
    		Display.setTitle("FPS: " + fps);
    		fps = 0;
    		lastFps += 1000;
    	}
    	fps++;
    } // end void updateFps()
    
    
    /** Draws all renderables to the screen */
    private void render() {
        // Prevent the screen from being shrunk too small
    	if (Display.wasResized()) {
    		if (Display.getWidth() < MIN_DISPLAY_WIDTH) {
    			try {
    				Display.setDisplayMode(new DisplayMode(MIN_DISPLAY_WIDTH, Display.getHeight()));
    			} catch (LWJGLException e) {
    				e.printStackTrace();
    			}
    		}
    		if (Display.getHeight() < MIN_DISPLAY_HEIGHT) {
    			try {
    				Display.setDisplayMode(new DisplayMode(Display.getWidth(), MIN_DISPLAY_HEIGHT));
    			} catch (LWJGLException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	
    	// Clear the screen
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        
        // call glTranslate(float,float,float) to move the screen
        b.update();
        b.draw();
        // call glBegin to render static objects
        
        // call glEnd
        
        // Poll for input
        
        // Update the display
        
        // Wait until the frame-rate is 60fps
        Display.sync(Integer.MAX_VALUE);
    } // end render()
    
    /** Run update logic for all in-game entities */
    private void update() {
        updateFps();
    } // end update(int)

} // end Game class
