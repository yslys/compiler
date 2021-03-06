###
# This Makefile can be used to make a parser for the C-- language
# (parser.class) and to make a program (P3.class) that tests the parser and
# the unparse methods in ast.java.
#
# make clean removes all generated files.
#
###

JC = javac
FLAGS = -g
CP = ./deps:.

P3.class: P3.java parser.class Yylex.class ASTnode.class
	$(JC) $(FLAGS) -cp $(CP) P3.java

parser.class: parser.java ASTnode.class Yylex.class ErrMsg.class
	$(JC) $(FLAGS) -cp $(CP) parser.java

parser.java: cminusminus.cup
	java -cp $(CP) java_cup.Main < cminusminus.cup

Yylex.class: cminusminus.jlex.java sym.class ErrMsg.class
	$(JC) $(FLAGS) -cp $(CP) cminusminus.jlex.java

ASTnode.class: ast.java
	$(JC) $(FLAGS) -cp $(CP) ast.java

cminusminus.jlex.java: cminusminus.jlex sym.class
	java -cp $(CP) JLex.Main cminusminus.jlex

sym.class: sym.java
	$(JC) $(FLAGS) -cp $(CP) sym.java

sym.java: cminusminus.cup
	java -cp $(CP) java_cup.Main < cminusminus.cup

ErrMsg.class: ErrMsg.java
	$(JC) $(FLAGS) -cp $(CP) ErrMsg.java

##test
test:
	java -cp $(CP) P3 test.cminusminus test.out

###
# clean
###
clean:
	rm -f *~ *.class parser.java cminusminus.jlex.java sym.java

## cleantest (delete test artifacts)
cleantest:
	rm -f *.out
