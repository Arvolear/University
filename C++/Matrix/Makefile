flags = -std=c++11 -Wall

matrix: main.o matrix.o
	g++ -o matrix main.o matrix.o $(flags)

main.o: main.cpp
	g++ -c main.cpp $(flags)

matrix.o: matrix.cpp matrix.hpp
	g++ -c matrix.cpp $(flags)

clean:
	rm -rf matrix
	rm -rf *.o
