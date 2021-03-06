package pso;

public final class TestPSO {

    public static void main(String[] args) {
    	long startTime = System.currentTimeMillis();
    	
        MFDFitnessFunction fitnessFunction = new MFDFitnessFunction();
        fitnessFunction.setSymptoms(new int[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 });
        PSO pso = new PSO(20, 25, 0.0, 1.0, 2.0, 2.0, fitnessFunction);
        pso.printBestParticle();
        pso.run(1000);
        
        long stopTime = System.currentTimeMillis();
        System.out.println("Finished in " + (double)(stopTime - startTime) / 1000.0 + " seconds.");
    }

}
