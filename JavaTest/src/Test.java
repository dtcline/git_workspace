import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Test {
    
    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();
        
        int n = 20;
        long factorial = 1;
        
        for (int i = n; i > 0; i--) {
            factorial *= i;
            System.out.println(factorial);
        }
       
        final long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Finished in " + totalTime + " milliseconds.");
    }
    
    
    public static float pow(float floatValue, int integerValue) {
        int result = 0;
        for (int i = 0; i < integerValue; i++) {
            
        }
        return result;
    }

}