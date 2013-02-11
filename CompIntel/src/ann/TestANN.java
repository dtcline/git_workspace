package ann;

/** Class solely for testing the functionality of the Artificial Neural Network
 * 
 * @author dustin
 *
 */
public final class TestANN {

    /** Entry point
     * 
     * @param args
     * 
     */
    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();
        
        final int[] parameters = { 4, 3, 2 };
        ANN ann = new ANN(parameters);
        
        final long stopTime = System.currentTimeMillis();
        System.out.println("Finished in " + (stopTime - startTime) + " milliseconds.");
    }

}
