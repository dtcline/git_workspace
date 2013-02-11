package pso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class PSO {

    private List<Particle> population;
    private final MFDFitnessFunction fitnessFunction;
    private final double velocityMin;
    private final double veolicityMax;
    private final double individualAcceleration;
    private final double populationAcceleration;
    private int[] bestPosition;
    private double bestFitness;
    
    public PSO(int populationSize, int particleLength, double velocityMin, double velocityMax, double individualAcceleration, double populationAcceleration, MFDFitnessFunction fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
        population = new ArrayList<Particle>();
        bestPosition = new int[particleLength];
        
        this.velocityMin = velocityMin;
        this.veolicityMax = velocityMax;
        
        this.individualAcceleration = individualAcceleration;
        this.populationAcceleration = populationAcceleration;
        
        for (int i = 0; i < populationSize; i++) {
            Particle p = new Particle(particleLength, velocityMin, velocityMax);
            this.fitnessFunction.calculateFitness(p);
            p.setBestFitness(p.getCurrentFitness());
            if (p.getBestFitness() > bestFitness) {
                bestPosition = p.getBestPosition();
                bestFitness = p.getBestFitness();
            }
            population.add(new Particle(p));
        }
    } // end PSO(int)
    
    
    public void run(int iterations) {
        Random random = new Random();
        
        for (int i = 0; i < iterations; i++) {
            int[] currentBestPosition = new int[bestPosition.length];
            double currentBestFitness = bestFitness;
            
            for (Particle p : population) {
                int[] currentIndividualPosition = p.getCurrentPosition();
                int[] bestIndividualPosition = p.getBestPosition();
                double[] individualVelocity = p.getVelocity();
                for (int j = 0; j < currentIndividualPosition.length; j++) {
                    int individualPositionDiff = bestIndividualPosition[j] - currentIndividualPosition[j];
                    int populationPositionDiff = currentBestPosition[j] - currentIndividualPosition[j];
                    double velocity = individualVelocity[j] + (individualAcceleration * random.nextDouble() * individualPositionDiff)
                            + (populationAcceleration * random.nextDouble() * populationPositionDiff);
                    
                }
            }
        }
    } // end void run(int)
    
    
    public void printPopulation() {
        for (Particle p : population) {
            System.out.println(p.toString());
        }
    }
    
} // end class PSO
