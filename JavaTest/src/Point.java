import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public final class Point implements Serializable {

    public final int x;
    public final int y;
    public final int z;
    private List<Point> otherPoints;
    
    public Point() {
        this.x = 10;
        this.y = 10;
        this.z = 10;
    }
    
    public Point(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        otherPoints = new ArrayList<Point>();
        for (int i = 0; i < 100; i++) {
            otherPoints.add(new Point());
        }
    }
}
