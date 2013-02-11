//============================================================================
// Name        : TestCPlusPlus.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <time.h>

#include "Point.h"

using namespace std;

int main() {
	clock_t time = clock();

	Point *p = new Point();
	for (int i = 0; i < 10000000; i++) {
		delete p;
		p = new Point(i, i, i);
	}

	time = clock() - time;

	cout << "Runtime: " << ((float)time / CLOCKS_PER_SEC);

	return 0;
}
