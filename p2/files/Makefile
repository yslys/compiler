###
# This Makefile can be used to make a scanner for the C-- language
# (Yylex.class) and to make a program that tests the scanner (P2.class).
#
# The default makes both the scanner and the test program.
#
# make clean removes all generated files.
#
# Note: P2.java will not compile unless Yylex.class exists.
#
###

# define the java compiler to be used and the flags
JC = javac
FLAGS = -g -cp $(CP) 
CP = ./deps:.

P2.class: P2.java Yylex.class sym.class
	$(JC) $(FLAGS) P2.java

Yylex.class: cminusminus.jlex.java ErrMsg.class sym.class
	$(JC) $(FLAGS) cminusminus.jlex.java

cminusminus.jlex.java: cminusminus.jlex sym.class
	java -cp $(CP) JLex.Main cminusminus.jlex

sym.class: sym.java
	$(JC) $(FLAGS) sym.java

ErrMsg.class: ErrMsg.java
	$(JC) $(FLAGS) ErrMsg.java

	
###
# testing - add more here to run your tester and compare its results
# to expected results
###
test:
	java -cp $(CP) P2 2> errors.out

	diff testAllTokens.out testAllTokensExpect.out
	diff testIDTokens.out testIDTokensExpect.out
	diff testIntLitTokens.out testIntLitTokensExpect.out
	diff testStrLitTokens.out testStrLitTokensExpect.out
	diff testEOF.out testEOFExpect.out
	diff testCommentEOF.out testCommentEOFExpect.out
	diff testBadStrLitEOF.out testBadStrLitEOFExpect.out
	diff errors.out errorsExpect.out

###
# clean up
###

clean:
	rm -f *~ *.class cminusminus.jlex.java
	rm -f testAllTokens.out testEOF.out testIDTokens.out testIntLitTokens.out 
	rm -f testStrLitTokens.out errors.out testCommentEOF.out testBadStrLitEOF.out

cleantest:
	rm -f allTokens.out
