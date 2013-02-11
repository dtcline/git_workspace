package sga;

import java.util.Arrays;

public final class Chromosome {
    
    private int[] fData;
    private double fFitness;
    
    
    public Chromosome() {
    } // end Chromosome()
    
    
    public Chromosome(int[] aData) {
        fData = Arrays.copyOf(aData, aData.length);
    } // end Chromosome(int[])
    
    
    public Chromosome(int[] aData, double aFitness) {
        fData = Arrays.copyOf(aData, aData.length);
        fFitness = aFitness;
    } // end Chromosome(int[], double)
    
    
    public Chromosome(Chromosome chromosome) {
        this(chromosome.getData(), chromosome.getFitness());
    } // end Chromosome(Chromosome)
    
    
    public final int[] getData() {
        int[] result = Arrays.copyOf(fData, fData.length);
        return result;
    } // end getData()
    
    
    public final double getFitness() {
        return fFitness;
    } // end getFitness()
    
    
    public final void setData(int[] aData) {
        fData = Arrays.copyOf(aData, aData.length);
    } // end setData(int[])
    
    
    public final void setFitness(double aFitness) {
        fFitness = aFitness;
    } // end setFitness(int)
    
    
    @Override
    public final String toString() {
        StringBuffer result = new StringBuffer();
        //for(int i : fData) {
        //    result.append(i);
        //}
        //result.append("\t");
        result.append(fFitness);
        return result.toString();
    } // end toString
    
}
