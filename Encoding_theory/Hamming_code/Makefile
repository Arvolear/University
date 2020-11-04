.PHONY: all clean cleanall

FLAGS = -std=c++14 -Wall -O3

INPUTDIR = ./src
OUTPUTDIR = ./build

HAMMINGDIR = Hamming

MAIN = main.o

OBJECTFILES = $(addprefix $(OUTPUTDIR)/, $(MAIN))	

PROGNAME = hamming

all: $(PROGNAME)

$(PROGNAME): $(OBJECTFILES)
	g++ -o $(PROGNAME) $(OBJECTFILES) $(FLAGS)

$(OUTPUTDIR)/main.o: $(INPUTDIR)/main.cpp \
				$(INPUTDIR)/$(HAMMINGDIR)/Driver.hpp \
				$(INPUTDIR)/$(HAMMINGDIR)/HammingEncoder.hpp \
				$(INPUTDIR)/$(HAMMINGDIR)/HammingDecoder.hpp
	g++ -c $(INPUTDIR)/main.cpp -o $@ $(FLAGS)

clean:
	rm -rf $(OUTPUTDIR)/*.o

cleanall:
	rm $(PROGNAME)
	rm -rf $(OUTPUTDIR)/*.o
