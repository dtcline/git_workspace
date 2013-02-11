package examples;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class InputDemo {
    
    private List<Box> shapes = new ArrayList<Box>();
    private boolean somethingIsSelected = false;
    
    public InputDemo() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Hello, LWJGL!");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        
        shapes.add(new Box(15, 15));
        shapes.add(new Box(100, 150));
        
        // Initialization code OpenGL
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 480, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        
        // Main rendering loop
        while(!Display.isCloseRequested()) {
            // Render
            
            glClear(GL_COLOR_BUFFER_BIT);
            
            if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                Display.destroy();
                System.exit(0);
            }
            
            while(Keyboard.next()) {
                if(Keyboard.getEventKey() == Keyboard.KEY_C && Keyboard.getEventKeyState()) {
                    shapes.add(new Box(15, 15));
                }
            }
            
            for(Box box : shapes) {
                if(Mouse.isButtonDown(0) && box.inBounds(Mouse.getX(), 480 - Mouse.getY()) && !somethingIsSelected) {
                    box.selected = true;
                    somethingIsSelected = true;
                    System.out.println("You clicked me!");
                }
                if(!Mouse.isButtonDown(0) && box.selected && somethingIsSelected) {
                    box.selected = false;
                    somethingIsSelected = false;
                }
                
                if(box.selected) {
                    box.update(Mouse.getDX(), -Mouse.getDY());
                }
                box.draw();
            }
            
            Display.update();
            Display.sync(60);
        }
        
        Display.destroy();
    } // end InputDemo()
    
    private static class Box {
        public int x, y;
        public int halfSide = 50;
        public boolean selected = false;
        private float colorRed, colorBlue, colorGreen;
        
        Box(int x, int y) {
            this.x = x;
            this.y = y;
            
            randomizeColors();
        }
        
        boolean inBounds(int mouseX, int mouseY) {
            if(mouseX > x && mouseX < x + halfSide && mouseY > y && mouseY < y + 50) {
                return true;
            }
            return false;
        }
        
        void randomizeColors() {
            Random randomGenerator = new Random(System.currentTimeMillis());
            colorRed = randomGenerator.nextFloat();
            colorBlue = randomGenerator.nextFloat();
            colorGreen = randomGenerator.nextFloat();
        }
        
        void update(int dx, int dy) {
            x += dx;
            y += dy;
        }
        
        void draw() {
            glColor3f(colorRed, colorGreen, colorBlue);
            
            glBegin(GL_QUADS);
                glVertex2f(x, y);
                glVertex2f(x + halfSide, y);
                glVertex2f(x + halfSide, y + halfSide);
                glVertex2f(x, y + halfSide);
            glEnd();
        }
        
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new InputDemo();
    }

}
