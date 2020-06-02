Algorithm = Loader.class Solver.class MatrixHelper.class GraphHelper.class AlgoHelper.class
Graphics = Interface.class ProblemChooser.class InputShower.class Solutor.class SolutionShower.class Menu.class

CLASSES = Main.class $(addprefix Algorithm/, $(Algorithm)) $(addprefix Graphics/, $(Graphics))

### ALL ###

ALL: $(CLASSES) 

Main.class: Main.java
	javac Main.java

### ALGORITHM ###

Algorithm/Loader.class: Algorithm/Loader.java
	javac Algorithm/Loader.java

Algorithm/Solver.class: Algorithm/Solver.java
	javac Algorithm/Solver.java

Algorithm/MatrixHelper.class: Algorithm/MatrixHelper.java
	javac Algorithm/MatrixHelper.java

Algorithm/GraphHelper.class: Algorithm/GraphHelper.java
	javac Algorithm/GraphHelper.java

Algorithm/AlgoHelper.class: Algorithm/AlgoHelper.java
	javac Algorithm/AlgoHelper.java

### GRAPHICS ###

Graphics/Interface.class: Graphics/Interface.java
	javac Graphics/Interface.java

Graphics/ProblemChooser.class: Graphics/ProblemChooser.java
	javac Graphics/ProblemChooser.java

Graphics/InputShower.class: Graphics/InputShower.java
	javac Graphics/InputShower.java

Graphics/Solutor.class: Graphics/Solutor.java
	javac Graphics/Solutor.java

Graphics/SolutionShower.class: Graphics/SolutionShower.java
	javac Graphics/SolutionShower.java

Graphics/Menu.class: Graphics/Menu.java
	javac Graphics/Menu.java

clean:
	rm -rf ./Main.class
	rm -rf ./Algorithm/*.class
	rm -rf ./Graphics/*.class
