package sga;

import java.util.ArrayList;
import java.util.Random;

public final class SGA {
    
    private ArrayList<Chromosome> population;
    private final int populationSize;
    private final int nGenerations;
    private final int chromosomeLength;
    private final float crossoverProbability;
    private final float mutationProbability;
    private final Random randomizer = new Random();
    private final IFitnessFunction fitnessFunction;
    private double[] rouletteWheel;
    private double maxFitness;
    private final int tournamentSize = 4;
    private Chromosome fittestChromosome;
    
    
    public SGA(int populationSize, int nGenerations, int chromosomeLength, float crossoverProbability, float mutationProbability, IFitnessFunction fitnessFunction) {
        population = new ArrayList<Chromosome>();
        this.populationSize = populationSize;
        this.nGenerations = nGenerations;
        this.chromosomeLength = chromosomeLength;
        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;
        this.fitnessFunction = fitnessFunction;//new MFDFitnessFunction(symptoms);
        this.rouletteWheel = new double[populationSize];
        new ArrayList<Chromosome>();
        
        createInitialPopulation();
    }
    
    
    public final void setupRouletteWheel() {
        double currentFitness = 0.0;
        int rouletteIndex = 0;
        for(Chromosome c : population) {
            currentFitness += c.getFitness();
            rouletteWheel[rouletteIndex] = currentFitness;
            rouletteIndex++;
        }
        maxFitness = currentFitness;
    }
    
    
    // problem-dependent???
    public final void createInitialPopulation() {
        for(int i = 0; i < populationSize; i++) {
            int[] randomizedData = new int[chromosomeLength];
            for(int j = 0; j < chromosomeLength; j++) {
                float randomFloat = randomizer.nextFloat();
                if(randomFloat > 0.5f) {
                    randomizedData[j] = 1;
                }
                else {
                    randomizedData[j] = 0;
                }
            }
            Chromosome newChromosome = new Chromosome(randomizedData);
            calculateFitness(newChromosome);
            population.add(newChromosome);
        }
        //System.out.println("Generation 0:");
        //printPopulation();
        //System.out.println();
    } // end createInitialPopulation()
    
    
    public final void calculateFitness(Chromosome chromosome) {
        fitnessFunction.calculateFitness(chromosome);
    } // end calculateFitness()
    
    
    // roulette selection
    public final Chromosome selectParentRoulette() {
        Chromosome parent = new Chromosome();
        
        double randomDouble = randomizer.nextDouble() * maxFitness;
        for(int i = 0; i < rouletteWheel.length; i++) {
            if(randomDouble < rouletteWheel[i]) {
                parent = population.get(i);
                break;
            }
        }
        
        return parent;
    } // end selectParents()
    
    
    // tournament selection
    public final Chromosome selectParentTournament() {
        ArrayList<Chromosome> possibleParents = new ArrayList<Chromosome>();
        Chromosome selectedParent = new Chromosome();
        Chromosome possibleParent;
        for(int i = 0; i < tournamentSize; i++) {
            int randomInt = randomizer.nextInt(populationSize);
            possibleParent = new Chromosome(population.get(randomInt));
            possibleParents.add(possibleParent);
        }
        
        selectedParent = possibleParents.get(0);
        for(Chromosome c : possibleParents) {
            if(selectedParent.getFitness() < c.getFitness()) {
                selectedParent = c;
            }
            
        }
        
        return selectedParent;
    }
    
    
    // problem-dependent (mutation operation) 
    public final void createNewPopulation() {
        ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>();
        //setupRouletteWheel();
        
        for(int i = 0; i < populationSize / 2; i++) {
            Chromosome firstParent = selectParentTournament();
            Chromosome secondParent = selectParentTournament();
            
            int[] firstParentData = firstParent.getData();
            int[] secondParentData = secondParent.getData();
            
            int[] firstChildData = new int[chromosomeLength];
            int[] secondChildData = new int[chromosomeLength];
            
            float randomFloat = randomizer.nextFloat();
            
            // crossover, if necessary
            if(randomFloat < crossoverProbability) {
                int crossoverIndex = randomizer.nextInt(chromosomeLength);
                
                System.arraycopy(firstParentData, 0, firstChildData, 0, crossoverIndex);
                System.arraycopy(secondParentData, 0, secondChildData, 0, crossoverIndex);
                
                System.arraycopy(firstParentData, crossoverIndex, secondChildData, crossoverIndex, chromosomeLength-crossoverIndex);
                System.arraycopy(secondParentData, crossoverIndex, firstChildData, crossoverIndex, chromosomeLength-crossoverIndex);
            }
            else {
                firstChildData = firstParentData;
                secondChildData = secondParentData;
            }
            
            // mutate, if necessary
            for(int j : firstChildData) {
                randomFloat = randomizer.nextFloat();
                if(randomFloat < mutationProbability) {
                 // mutate first child
                    if(firstChildData[j] == 1) {
                        firstChildData[j] = 0;
                    }
                    else {
                        firstChildData[j] = 1;
                    }
                }
            }
            
            for(int j : secondChildData) {
                randomFloat = randomizer.nextFloat();
                if(randomFloat < mutationProbability) {
                 // mutate second child
                    if(secondChildData[j] == 1) {
                        secondChildData[j] = 0;
                    }
                    else {
                        secondChildData[j] = 1;
                    }
                }
            }
            
            Chromosome firstChild = new Chromosome(firstChildData);
            calculateFitness(firstChild);
            Chromosome secondChild = new Chromosome(secondChildData);
            calculateFitness(secondChild);
            
            newPopulation.add(firstChild);
            newPopulation.add(secondChild);
        }
        
        population = newPopulation;
    } // end crossChromosomes()
    
    
    public final void run() {
        for(int i = 1; i < nGenerations; i++) {
            createNewPopulation();
            //System.out.println("Generation " + i + ":");
            //printPopulation();
            //System.out.println();
        }
        
        // store the fittest chromosome
        Chromosome possibleFittestChromosome = new Chromosome();
        for(Chromosome c : population) {
            if(c.getFitness() > possibleFittestChromosome.getFitness()) {
                possibleFittestChromosome = new Chromosome(c);
            }
        }
        fittestChromosome = possibleFittestChromosome;
        //System.out.println("Fittest chromosome is: " + possibleFittestChromosome.toString());
    } // end run()
    
    
    public final void printPopulation() {
        for(Chromosome chromosome : population) {
            System.out.println(chromosome.toString());
        }
    } // end printPopulation
    
    
    public final void printFittestChromosome() {
        System.out.println(fittestChromosome);
    }
    
    
    public final Chromosome getFittestChromosome() {
        return fittestChromosome;
    }
}
