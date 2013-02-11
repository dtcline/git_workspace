package ann;

import java.util.Random;

/** Object representing a neuron in artificial neural network
 * 
 * @author dustin
 *
 */
public final class Neuron {
    
    /** Constructor for Neuron object.  Initializes weights to random 
     * float values between 0.0f and 1.0f
     * 
     * @param nWeights Number of weights this neuron uses to modify the 
     * inputs it receives from the layer to which it belongs.
     * 
     */
    public Neuron(int nWeights) {
        weights_ = new float[nWeights];
        
        Random random = new Random();
        for (int i = 0; i < nWeights; i++) {
            weights_[i] = random.nextFloat();
        }
    } // end Neuron(int)
    
    /** Returns a String representation of this Neuron
     * 
     */
    @Override
    public final String toString() {
        StringBuilder result = new StringBuilder();
        
        result.append("Weights: ");
        for (int i = 0; i < weights_.length; i++) {
            result.append(weights_[i]);
            result.append(" ");
        }
        
        result.append("\n");
        
        result.append("Output: ");
        result.append(output_);
        
        result.append("\n");
        
        return result.toString();
    } // end toString()
    
    private final float[] weights_;
    private float output_;

} // end class Neuron
