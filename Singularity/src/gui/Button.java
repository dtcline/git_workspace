package gui;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.Color;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.input.Mouse;

public final class Button {

	private int centerX;
	private int centerY;
	private int widthHalf;
	private int heightHalf;
	private Text text;
	private float red = 0.9f;
	private float green = 0.9f;
	private float blue = 0.9f;
	
	// 
	public Button(int centerX, int centerY, int widthHalf, int heightHalf, Text text) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.widthHalf = widthHalf;
		this.heightHalf = heightHalf;
		
		// TODO: setup Text constructor
		this.text = new Text();
		
	} // end Button()
	
	
	public final void draw() {
		glColor3f(red, green, blue);
		glRecti(centerX - widthHalf, centerY - heightHalf, centerX + widthHalf, centerY + heightHalf);
	} // end void draw()
	
	
	public final void update() {
		centerX = Display.getWidth() / 2;
		centerY = Display.getHeight() / 2;
		if (isSelected()) {
			setSelected();
		}
		else {
			setUnselected();
		}
	} // end void update()
	
	
	public final boolean isSelected() {
		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();
		if (mouseX > centerX - widthHalf && mouseX < centerX + widthHalf && mouseY > centerY - heightHalf && mouseY < centerY + heightHalf) {
			return true;
		}
		return false;
	} // end boolean isSelected()
	
	
	private final void setSelected() {
		red = 1.0f;
		green = 1.0f;
		blue = 1.0f;
	} // end void setSelected()
	
	
	private final void setUnselected() {
		red = 0.9f;
		green = 0.9f;
		blue = 0.9f;
	} // end void setUnselected
	
	
} // end class Button
