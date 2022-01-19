.PHONY: all clean

FLAGS = -std=c++11 -Wall -O3
LIBS = -lpthread

CODEDIR = ./code
BUILDDIR = ./build

MAIN = main.o
OTHER = game.o party.o server.o

OBJECTFILES = $(addprefix $(BUILDDIR)/, $(MAIN) $(OTHER))

### ALL ####

all: server
	
server: $(OBJECTFILES)
	g++ -o server $(OBJECTFILES) $(FLAGS) $(LIBS)

### MAIN ###

$(BUILDDIR)/main.o: $(CODEDIR)/main.cpp
	g++ -c $(CODEDIR)/main.cpp -o $@ $(FLAGS)

### OTHER ###

$(BUILDDIR)/game.o: $(CODEDIR)/game.cpp $(CODEDIR)/game.hpp
	g++ -c $(CODEDIR)/game.cpp -o $@ $(FLAGS)

$(BUILDDIR)/party.o: $(CODEDIR)/party.cpp $(CODEDIR)/party.hpp
	g++ -c $(CODEDIR)/party.cpp -o $@ $(FLAGS)

$(BUILDDIR)/server.o: $(CODEDIR)/server.cpp $(CODEDIR)/server.hpp
	g++ -c $(CODEDIR)/server.cpp -o $@ $(FLAGS)

### CLEAN ###

clean:
	rm -rf $(BUILDDIR)/*.o
	rm -rf server
