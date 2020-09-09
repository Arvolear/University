.PHONY: all clean cleanall

FLAGS = -std=c++14 -Wall -O3

INPUTDIR = ./src
OUTPUTDIR = ./build

HUFFMANDIR = Huffman

MAIN = main.o

OBJECTFILES = $(addprefix $(OUTPUTDIR)/, $(MAIN))	

all: huffman	

huffman: $(OBJECTFILES)
	g++ -o huffman $(OBJECTFILES) $(FLAGS)

$(OUTPUTDIR)/main.o: $(INPUTDIR)/main.cpp \
				$(INPUTDIR)/$(HUFFMANDIR)/Driver.hpp \
				$(INPUTDIR)/$(HUFFMANDIR)/HuffmanEncoder.hpp \
				$(INPUTDIR)/$(HUFFMANDIR)/HuffmanDecoder.hpp
	g++ -c $(INPUTDIR)/main.cpp -o $@ $(FLAGS)

clean:
	rm -rf $(OUTPUTDIR)/*.o

cleanall:
	rm huffman
	rm -rf $(OUTPUTDIR)/*.o
