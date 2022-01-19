.PHONY: all clean

FLAGS = -std=c++11 -Wall -O3
LIBS = -lpthread

CODEDIR = ./code
BUILDDIR = ./build

MAIN = main.o
OTHER = game.o client.o

OBJECTFILES = $(addprefix $(BUILDDIR)/, $(MAIN) $(OTHER))

### ALL ####

all: TicTacToe
	
TicTacToe: $(OBJECTFILES)
	g++ -o TicTacToe $(OBJECTFILES) $(FLAGS) $(LIBS)

### MAIN ###

$(BUILDDIR)/main.o: $(CODEDIR)/main.cpp
	g++ -c $(CODEDIR)/main.cpp -o $@ $(FLAGS)

### OTHER ###

$(BUILDDIR)/game.o: $(CODEDIR)/game.cpp $(CODEDIR)/game.hpp
	g++ -c $(CODEDIR)/game.cpp -o $@ $(FLAGS)

$(BUILDDIR)/client.o: $(CODEDIR)/client.cpp $(CODEDIR)/client.hpp
	g++ -c $(CODEDIR)/client.cpp -o $@ $(FLAGS)

### CLEAN ###

clean:
	rm -rf $(BUILDDIR)/*.o
	rm -rf TicTacToe
