package pso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public final class PSO {

    private List<Particle> population;
    private final MFDFitnessFunction fitnessFunction;
    private final double velocityMin;
    private final double velocityMax;
    private final double individualAcceleration;
    private final double populationAcceleration;
    private Particle bestParticle;
    private int[] bestPosition;
    private double bestFitness;
    private final double positionChangeChance = 0.5;
    
    public PSO(int populationSize, int particleLength, double velocityMin, double velocityMax, double individualAcceleration, double populationAcceleration, MFDFitnessFunction fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
        population = new ArrayList<Particle>();
        bestPosition = new int[particleLength];
        
        this.velocityMin = velocityMin;
        this.velocityMax = velocityMax;
        
        this.individualAcceleration = individualAcceleration;
        this.populationAcceleration = populationAcceleration;
        
        
        for (int i = 0; i < populationSize; i++) {
            Particle p = new Particle(particleLength, velocityMin, velocityMax);
            this.fitnessFunction.calculateFitness(p);
            p.setBestFitness(p.getCurrentFitness());
            if (p.getBestFitness() > bestFitness) {
                //bestPosition = p.getBestPosition();
                //bestFitness = p.getBestFitness();
                bestParticle = new Particle(p);
            }
            population.add(new Particle(p));
        }
    } // end PSO(int)
    
    
    public void run(int iterations) {
        Random random = new Random();
        boolean bestParticleUpdated = false;
        int n = 0;
        for (int i = 0; i < iterations; i++) {
        	bestParticleUpdated = false;
            int[] currentBestPosition = bestParticle.getBestPosition();
            double currentBestFitness = bestParticle.getBestFitness();
        	
            for (Particle p : population) {
                int[] currentIndividualPosition = p.getCurrentPosition();
                int[] bestIndividualPosition = p.getBestPosition();
                double[] individualVelocity = p.getVelocity();
                for (int j = 0; j < currentIndividualPosition.length; j++) {
                    int individualPositionDiff = bestIndividualPosition[j] - currentIndividualPosition[j];
                    int populationPositionDiff = currentBestPosition[j] - currentIndividualPosition[j];
                    double velocity = individualVelocity[j] + (individualAcceleration * random.nextDouble() * individualPositionDiff)
                            + (populationAcceleration * random.nextDouble() * populationPositionDiff);
                    velocity = 1 / (1 + Math.exp(-velocity));
                    if (velocity < velocityMin) {
                    	velocity = velocityMin;
                    }
                    else if (velocity > velocityMax) {
                    	velocity = velocityMax;
                    }
                    if (velocity < random.nextDouble()) {
                    	if (currentIndividualPosition[j] == 1) {
                    		currentIndividualPosition[j] = 0;
                    	}
                    	else {
                    		currentIndividualPosition[j] = 1;
                    	}
                    }
                    individualVelocity[j] = velocity;
                }
                p.setCurrentPosition(currentIndividualPosition);
                fitnessFunction.calculateFitness(p);
                if (p.getCurrentFitness() > p.getBestFitness()) {
                	p.setBestPosition(p.getCurrentPosition());
                	p.setBestFitness(p.getCurrentFitness());
                }
                
                if (p.getBestFitness() > currentBestFitness) {
                	bestParticle = new Particle(p);
                	bestParticleUpdated = true;
                	n = 0;
                }
            }
            
            if (!bestParticleUpdated) {
            	n++;
            }
            
            System.out.println("Iteration " + i + ":");
            printBestParticle();
            
            if (n == 200) {
            	return;
            }
        }
        
        
    } // end void run(int)
    
    
    public void printBestParticle() {
    	System.out.println(bestParticle.toString());
    }
    
    
    public void printPopulation() {
        for (Particle p : population) {
            System.out.println(p.toString());
        }
    }
    
} // end class PSO
