package minecraft2d;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class Boot {
    
    public Boot() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Minecraft2D");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        
        // Initialization code OpenGL
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 480, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        
        while(!Display.isCloseRequested()) {
            // Render
            
            glClear(GL_COLOR_BUFFER_BIT);
            
            
            
            Display.update();
            Display.sync(60);
        }
        
        Display.destroy();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Boot();
    }

}

