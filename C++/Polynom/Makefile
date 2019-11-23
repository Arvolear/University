flags = -std=c++11 -Wall

polynom: main.o polynom.o
	g++ -o polynom main.o polynom.o $(flags)

main.o: main.cpp
	g++ -c main.cpp $(flags)

polynom.o: polynom.cpp polynom.hpp
	g++ -c polynom.cpp $(flags)

clean:
	rm -rf polynom
	rm -rf *.o
