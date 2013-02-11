package sga;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class MFDFitnessFunction implements IFitnessFunction {
    
    private static double[] priorProbabilities;
    private static double[][] symptomValues;
    private static final int nDiseases = 25;
    private static final int nSymptoms = 10;
    private int[] symptoms;
    public static double[] firstBestFitnesses;
    public static double[] secondBestFitnesses;
    public static double[] thirdBestFitnesses;
    
    // setup
    static {
        priorProbabilities = new double[nDiseases];
        symptomValues = new double[nSymptoms][nDiseases];
        
        try {
            Scanner scanner = new Scanner(new File("TendencyMatrix_10x25.txt"));
            for(int i = 0; i < nDiseases; i++) {
                priorProbabilities[i] = scanner.nextDouble();
            }
            for(int i = 0; i < nSymptoms; i++) {
                for(int j = 0; j < nDiseases; j++) {
                    double d = scanner.nextDouble();
                    if(d == 0.0) {
                        symptomValues[i][j] = Double.MIN_VALUE;
                    }
                    else {
                        symptomValues[i][j] = d;
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: TendencyMatrix_10x25.txt not found.");
            e.printStackTrace();
        }
        
        firstBestFitnesses = new double[1023];
        secondBestFitnesses = new double[1023];
        thirdBestFitnesses = new double[1023];
        
        try {
            Scanner scanner = new Scanner(new File("ExhaustiveResults10x25.txt"));
            //DecimalFormat df = new DecimalFormat("##.###############");
            for (int i = 0; i < 1023; i++) {
                // ignore the int indicating symptom value
                scanner.nextInt();
                firstBestFitnesses[i] = scanner.nextDouble();//Double.valueOf(df.format(scanner.nextDouble()));
                secondBestFitnesses[i] = scanner.nextDouble();//Double.valueOf(df.format(scanner.nextDouble()));
                thirdBestFitnesses[i] = scanner.nextDouble();//Double.valueOf(df.format(scanner.nextDouble()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: ExhaustiveResults10x25.txt");
            e.printStackTrace();
        }
    }
    
    public MFDFitnessFunction() {
        //this.symptoms = symptoms; 
    }
    
    
    public void setSymptoms(int[] symptoms) {
        this.symptoms = symptoms;
    }
    

    @Override
    public void calculateFitness(Chromosome chromosome) {
        final int[] chromosomeData = chromosome.getData();
        double fitness = 0.0;
        
        // calculate L1
        double fitness1 = 1.0;
        for(int i = 0; i < nSymptoms; i++) {
            double d = 1.0;
            if(symptoms[i] == 1) {
                for(int j = 0; j < nDiseases; j++) {
                    if(chromosomeData[j] == 1) {
                        d *= (1 - symptomValues[i][j]);
                    }
                }
                fitness1 *= 1 - d;
            }
        }
        
        // calculate L2
        double fitness2 = 1.0;
        for(int i = 0; i < nDiseases; i++) {
            double d = 1.0;
            if(chromosomeData[i] == 1) {
                for(int j = 0; j < nSymptoms; j++) {
                    if(symptomValues[j][i] > Double.MIN_VALUE && symptoms[j] == 0) {
                        d *= (1 - symptomValues[j][i]);
                    }
                }
                fitness2 *= d;
            }
        }
        
        // calculate L3
        double fitness3 = 1.0;
        for(int i = 0; i < nDiseases; i++) {
            if(chromosomeData[i] == 1) {
                fitness3 *= priorProbabilities[i] / (1 - priorProbabilities[i]);
            }
        }
        
        fitness = fitness1 * fitness2 * fitness3;
        chromosome.setFitness(fitness);
        
    }

}












































