.PHONY: all clean cleanall

FLAGS = -std=c++14 -Wall -O3

INPUTDIR = ./src
OUTPUTDIR = ./build

DIR = CRC

MAIN = main.o

OBJECTFILES = $(addprefix $(OUTPUTDIR)/, $(MAIN))	

PROGNAME = crc

all: $(PROGNAME)

$(PROGNAME): $(OBJECTFILES)
	g++ -o $(PROGNAME) $(OBJECTFILES) $(FLAGS)

$(OUTPUTDIR)/main.o: $(INPUTDIR)/main.cpp \
				$(INPUTDIR)/$(DIR)/Driver.hpp \
				$(INPUTDIR)/$(DIR)/CRCEncoder.hpp \
				$(INPUTDIR)/$(DIR)/CRCDecoder.hpp
	g++ -c $(INPUTDIR)/main.cpp -o $@ $(FLAGS)

clean:
	rm -rf $(OUTPUTDIR)/*.o

cleanall:
	rm $(PROGNAME)
	rm -rf $(OUTPUTDIR)/*.o
