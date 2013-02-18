package pso;

import java.util.Random;

public final class Particle {

    private double currentFitness;
    private double bestFitness;
    private final int[] currentPosition;
    private final int[] bestPosition;
    private double[] velocity;
    
    
    public Particle(int positionLength, double velocityMin, double velocityMax) {
        currentPosition = new int[positionLength];
        
        Random random = new Random(); 
        for (int i = 0; i < positionLength; i++) {
            currentPosition[i] = random.nextInt(2);
        }
        
        velocity = new double[positionLength];
        for (int i = 0; i < positionLength; i++) {
        	// we're using velocity as a probability of whether to change a 
        	//     position or not rather than as an amount by which to change a position
            velocity[i] = random.nextDouble();
        }
        
        bestPosition = new int[positionLength];
        setBestPosition(currentPosition);
    } // end Individual(int[])
    
    
    public Particle(Particle p) {
        this.currentFitness = p.currentFitness;
        this.bestFitness = p.bestFitness;
        this.currentPosition = new int[p.currentPosition.length];
        System.arraycopy(p.currentPosition, 0, this.currentPosition, 0, p.currentPosition.length);
        this.bestPosition = new int[p.bestPosition.length];
        System.arraycopy(p.bestPosition, 0, this.bestPosition, 0, p.bestPosition.length);
        this.velocity = new double[p.velocity.length];
        System.arraycopy(p.velocity, 0, this.velocity, 0, p.velocity.length);
    }
    
    
    public int[] getCurrentPosition() {
        int[] position = new int[currentPosition.length];
        System.arraycopy(currentPosition, 0, position, 0, currentPosition.length);
        return position;
    } // end int[] getCurrentPosition()
    
    
    public int[] getBestPosition() {
        int[] position = new int[bestPosition.length];
        System.arraycopy(bestPosition, 0, position, 0, bestPosition.length);
        return position;
    } // end int[] getBestPosition()
    
    
    public double getCurrentFitness() {
        return currentFitness;
    } // end double getCurrentFitness()

    
    public double getBestFitness() {
        return bestFitness;
    } // double getBestFitness()
    
    
    public double[] getVelocity() {
        return velocity;
    } // end double[] getVelocity()
    
    
    public void setCurrentPosition(int[] newPosition) {
        System.arraycopy(newPosition, 0, currentPosition, 0, newPosition.length);
    } // end void setCurrentPosition(int[])
    
    
    public void setBestPosition(int[] newPosition) {
        System.arraycopy(newPosition, 0, bestPosition, 0, newPosition.length);
    } // end void setBestPosition(int[])
    
    
    public void setCurrentFitness(double fitness) {
        currentFitness = fitness;
    } // end void setFitness(double)

    
    public void setBestFitness(double fitness) {
        bestFitness = fitness;
    } // end void setBestFitness(double)
    
    
    public void setVelocity(double[] velocity) {
        System.arraycopy(velocity, 0, this.velocity, 0, velocity.length);
    } // end void setVelocity(double[])
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Current: ");
        int positionLength = currentPosition.length;
        for (int i = 0; i < positionLength; i++) {
            sb.append(currentPosition[i]);
        }
        
        sb.append(" Fitness: ").append(currentFitness).append("\n");
        
        sb.append("Best:    ");
        for (int i = 0; i < positionLength; i++) {
            sb.append(bestPosition[i]);
        }
        
        sb.append(" Fitness: ").append(bestFitness).append("\n");
        
        sb.append("Velocity: ");
        for (int i = 0; i < positionLength; i++) {
            sb.append(velocity[i]).append(" ");
        }
        sb.append("\n");
        
        return sb.toString();
    } // end String toString()
    
} // end Individual class
