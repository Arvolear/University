Algorithm = Loader.class Solver.class 
# Graphics = 

CLASSES = Main.class $(addprefix Algorithm/, $(Algorithm))

### ALL ###

ALL: $(CLASSES) 

Main.class: Main.java
	javac Main.java

### ALGORITHM ###

Algorithm/Loader.class: Algorithm/Loader.java
	javac Algorithm/Loader.java

Algorithm/Solver.class: Algorithm/Solver.java
	javac Algorithm/Solver.java

clean:
	rm -rf ./Main.class
	rm -rf ./Algorithm/*.class
