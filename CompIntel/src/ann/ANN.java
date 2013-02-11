package ann;

import java.util.ArrayList;

/** Artificial Neural Network architecture
 * 
 * @author dustin
 *
 */
public final class ANN {
    
    /** Constructor for a simple ANN
     * 
     * @param parameters Contains information regarding the parameters of the 
     * Artificial Neural Network.  First index (parameter[0]) contains the 
     * number of inputs to the network.  Remaining number of parameters
     * (parameters.length - 1) is the number of layers, including output layer.
     * Each of these remaining indices contains the number of neurons in the
     * corresponding layer.
     * 
     */
    public ANN(int[] parameters) {
        nInputs_ = parameters[0];
        
        layers_ = new ArrayList<Layer>();
        for (int i = 1; i < parameters.length; i++) {
            layers_.add(new Layer(parameters[i - 1], parameters[i]));
        }
    }
    
    private final int nInputs_;
    private final ArrayList<Layer> layers_;
    
} // end ANN class
