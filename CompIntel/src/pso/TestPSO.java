package pso;

public final class TestPSO {

    public static void main(String[] args) {
        MFDFitnessFunction fitnessFunction = new MFDFitnessFunction();
        fitnessFunction.setSymptoms(new int[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 });
        PSO pso = new PSO(20, 25, -4.0, 4.0, 2.0, 2.0, fitnessFunction);
        pso.printPopulation();
    }

}
