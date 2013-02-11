/*
 * Point.h
 *
 *  Created on: Jan 28, 2013
 *      Author: dustin
 */

#ifndef POINT_H_
#define POINT_H_

class Point {

private:
	int x;
	int y;
	int z;



public:
	Point() {
			x = -1;
			y = -1;
			z = -1;
		}
	Point(int x, int y, int z);
};


#endif /* POINT_H_ */
