package experiments;

public final class Shape {

	private float centerX;
	private float centerY;
	private final float[] vertexData = new float[9];
	private final float[] colorData = new float[9];
	
	public Shape(float centerX, float centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
	}
}
