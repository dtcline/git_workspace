package sga;

import java.util.ArrayList;


public final class TestSGA {

    private static final int POPULATION_SIZE = 200;
    private static final int NUMBER_OF_GENERATIONS = 30;
    private static final float CROSSOVER_PROBABILITY = 0.6f;
    private static final float MUTATION_PROBABILITY = 0.001f;

    private static final int NUMBER_OF_DISEASES = 25;
    private static final int NUMBER_OF_SYMPTOMS = 10;

    
    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();

        final MFDFitnessFunction fitnessFunction = new MFDFitnessFunction();
        final int[] symptoms = new int[NUMBER_OF_SYMPTOMS];
        final StringBuilder sb = new StringBuilder();
        final ArrayList<Chromosome> fittestChromosomes = new ArrayList<Chromosome>();
        //DecimalFormat df = new DecimalFormat("##.###############");
        //double totalReliability = 0.0;
        int firstReliability = 0;
        int secondReliability = 0;
        int thirdReliability = 0;
        // check all symptom sets
        for (int i = 1; i < 1024; i++) {
            
            // setup this symptom set
            sb.setLength(0);
            sb.append(Integer.toBinaryString(i));
            while (sb.length() < 10) {
                sb.insert(0, 0);
            }
            for (int j = 0; j < NUMBER_OF_SYMPTOMS; j++) {
                symptoms[j] = Character.getNumericValue(sb.charAt(j));
            }

            // setup fitness function for this symptom set
            fitnessFunction.setSymptoms(symptoms);

            // run an 10 iterations of the GA with this symptom set
            fittestChromosomes.clear();
            int symptomSetIndex = i - 1;
            
            for (int j = 0; j < 10; j++) {
                SGA sga = new SGA(POPULATION_SIZE, NUMBER_OF_GENERATIONS,
                        NUMBER_OF_DISEASES, CROSSOVER_PROBABILITY,
                        MUTATION_PROBABILITY, fitnessFunction);
                sga.run();
                //double fitness = Double.valueOf(df.format(sga.getFittestChromosome().getFitness()));
                double fitness = sga.getFittestChromosome().getFitness();
                double tolerance = 0.000001;
                double diff1 = Math.abs(fitness - MFDFitnessFunction.firstBestFitnesses[symptomSetIndex]);
                double diff2 = Math.abs(fitness - MFDFitnessFunction.secondBestFitnesses[symptomSetIndex]);
                double diff3 = Math.abs(fitness - MFDFitnessFunction.thirdBestFitnesses[symptomSetIndex]);
                if (tolerance >= diff1) {
                    firstReliability++;
                } 
                else if (tolerance >= diff2) {
                    secondReliability++;
                }
                else if (tolerance >= diff3) {
                    thirdReliability++;
                }
            }
            //totalReliability += reliability;
            
            System.out.println(sb + " (" + i + ") : " + firstReliability + " ; " + secondReliability + " ; " 
                    + thirdReliability);
            //reliability = 0.0;
        }
        
        //System.out.println("Reliability : " + (totalReliability / 10230.0));
        System.out.println(firstReliability + " => " + (float)(firstReliability / 10230) + "\n" 
                + secondReliability + " => " + (float)(secondReliability / 10230) + "\n" 
                + thirdReliability + " => " + (float)(thirdReliability / 10230));
        
        System.out.println("Finished in: "
                + (System.currentTimeMillis() - startTime) + " milliseconds.");
    }

}
