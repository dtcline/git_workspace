#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define NUM_DISEASES 25
#define NUM_SYMPTOMS 10
#define POP_SIZE 20
#define DBL_MIN 0.0000000000000001


double priorProbabilities[NUM_DISEASES];
double tendencyMatrix[NUM_SYMPTOMS][NUM_DISEASES];
int population[POP_SIZE][NUM_DISEASES];
double fitnesses[POP_SIZE];
int symptoms[NUM_SYMPTOMS];


void setupMfdData() {
	FILE *fp;

	fp = fopen("TendencyMatrix_10x25.txt", "r");

	int i;
	for (i = 0; i < NUM_DISEASES; i++) {
		fscanf(fp, "%lf", &priorProbabilities[i]);
	}

	int j;
	double d;
	for (i = 0; i < NUM_SYMPTOMS; i++) {
		for (j = 0; j < NUM_DISEASES; j++) {
			fscanf(fp, "%lf", &d);
			if (d == 0.0) {
				tendencyMatrix[i][j] = DBL_MIN;
			}
			else {
				tendencyMatrix[i][j] = d;
			}
			printf("%.16f\n", tendencyMatrix[i][j]);
		}
		printf("%.16f\n", tendencyMatrix[i][j]);
	}

	fclose(fp);
} // end setupMfdData()


void calculateFitness(int individualIndex) {
	int i, j;
	int individualData[NUM_DISEASES];
	for (i = 0; i < NUM_DISEASES; i++) {
		individualData[i] = population[individualIndex][i];
	}

	// calculate L1
	double fitness1 = 1.0;
	for (i = 0; i < NUM_SYMPTOMS; i++) {
		double d = 1.0;
		if (symptoms[i] == 1) {
			for (j = 0; j < NUM_DISEASES; j++) {
				if (individualData[j] == 1) {
					d *= (1 - tendencyMatrix[i][j]);
				}
			}
			fitness1 *= 1 - d;
		}
	}

	// calculate L2
	double fitness2 = 1.0;
	for (i = 0; i < NUM_DISEASES; i++) {
		double d = 1.0;
		if (individualData[i] == 1) {
			for (j = 0; j < NUM_SYMPTOMS; j++) {
				if (tendencyMatrix[j][i] > 0.0  && symptoms[j] == 0) {
					d *= (1 - tendencyMatrix[j][i]);
				}
			}
			fitness2 *= d;
		}
	}

	// calculate L3
	double fitness3 = 1.0;
	for (i = 0; i < NUM_DISEASES; i++) {
		if (individualData[i] == 1) {
			fitness3 *= priorProbabilities[i] / (1 - priorProbabilities[i]);
		}
	}

	double fitness = fitness1 * fitness2 * fitness3;
	fitnesses[individualIndex] = fitness;

} // end calculateFitness(int)


void calculatePopulationFitness() {
	int i;
	for (i = 0; i < POP_SIZE; i++) {
		calculateFitness(i);
	}
} // end calculatePopulationFitness()


void initializePopulation() {
	int i, j;
	for (i = 0; i < POP_SIZE; i++) {
		for (j = 0; j < NUM_DISEASES; j++) {
			population[i][j] = rand() % 2;
		}
	}
} // end initializePopulation()


void printPopulation() {
	int i, j;
	for (i = 0; i < POP_SIZE; i++) {
		for (j = 0; j < NUM_DISEASES; j++) {
			printf("%i", population[i][j]);
		}
		printf("\t%.16f\n", fitnesses[i]);
	}
}


int main() {
	clock_t timer = clock();

	srand(time(NULL));

	double currentSymptoms[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
	int i;
	for (i = 0; i < NUM_SYMPTOMS; i++) {
		symptoms[i] = currentSymptoms[i];
	}
	setupMfdData();
	initializePopulation();
	calculatePopulationFitness();
	printPopulation();

	timer = clock() - timer;
	printf("Finished in %f seconds.", ((float)timer)/CLOCKS_PER_SEC);
	return EXIT_SUCCESS;
}



