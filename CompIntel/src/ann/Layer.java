package ann;

import java.util.ArrayList;

/** Container for holding a collection of neurons
 * 
 * @author dustin
 *
 */
public final class Layer {

    /** Constructor for Layer object.
     * 
     * @param nInputs Number of inputs that each neuron in this layer will receive.
     * 
     */
    public Layer(int nInputs, int nNeurons) {
        inputs_ = new float[nInputs];
        neurons_ = new ArrayList<Neuron>();
    } // end Layer()
    
    
    
    private final float[] inputs_;
    private final ArrayList<Neuron> neurons_;
    
} // end Layer class
