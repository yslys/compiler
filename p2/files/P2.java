

import java.util.*;
import java.io.*;
import java_cup.runtime.*;  // defines Symbol

/**
 * This program is to be used to test the C-- scanner. This version is set up 
 * to test all tokens including input that causes errors, character numbers, 
 * values associated with tokens.
 */
public class P2 {
    public static void main(String[] args) throws IOException {
                                           // exception may be thrown by yylex
        // test all tokens
        testAllTokens("testAllTokens.in", "testAllTokens.out");
        CharNum.num = 1;
    
        // test indentifier tokens
        testAllTokens("testIDTokens.in", "testIDTokens.out");
        CharNum.num = 1;

        // test integer literals tokens
        testAllTokens("testIntLitTokens.in", "testIntLitTokens.out");
        CharNum.num = 1;

        // test string literals tokens
        testAllTokens("testStrLitTokens.in", "testStrLitTokens.out");
        CharNum.num = 1;

        // test EOF
        testAllTokens("testEOF.txt", "testEOF.out");
        CharNum.num = 1;

        // test comment EOF
        testAllTokens("testCommentEOF.txt", "testCommentEOF.out");
        CharNum.num = 1;

        // test bad string literal EOF
        testAllTokens("testBadStrLitEOF.txt", "testBadStrLitEOF.out");
        CharNum.num = 1;
    }

    /**
     * testAllTokens
     *
     * Open and read from input file.
     * For each token read, write the corresponding string and its line number 
     * and char number to output file.
     * Create an expected for each input file, we can verify correctness
     * of the scanner by comparing the expected and real output files
     * (e.g., using a 'diff' command).
     */
    private static void testAllTokens(String fileIn, String fileOut) throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader(fileIn);
            outFile = new PrintWriter(new FileWriter(fileOut));
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("allTokens.out cannot be opened.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
            case sym.BOOL:
                outFile.println("bool" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum); 
                break;
            case sym.INT:
                outFile.println("int" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.VOID:
                outFile.println("void" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.TRUE:
                outFile.println("true" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.FALSE:
                outFile.println("false" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.STRUCT:
                outFile.println("struct" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.CIN:
                outFile.println("cin" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.COUT:
                outFile.println("cout" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;				
            case sym.IF:
                outFile.println("if" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.ELSE:
                outFile.println("else" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.WHILE:
                outFile.println("while" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.RETURN:
                outFile.println("return" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.ID:
                outFile.println(((IdTokenVal)token.value).idVal + " lineNum: " 
                                    + ((IdTokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((IdTokenVal)token.value).charnum);
                break;
            case sym.INTLITERAL: 
                outFile.println(((IntLitTokenVal)token.value).intVal + " lineNum: " 
                                    + ((IntLitTokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((IntLitTokenVal)token.value).charnum); 
                break;
            case sym.STRINGLITERAL: 
                outFile.println(((StrLitTokenVal)token.value).strVal + " lineNum: " 
                                    + ((StrLitTokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((StrLitTokenVal)token.value).charnum);
                break;    
            case sym.LCURLY:
                outFile.println("{" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.RCURLY:
                outFile.println("}" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.LPAREN:
                outFile.println("(" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.RPAREN:
                outFile.println(")" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.SEMICOLON:
                outFile.println(";" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.COMMA:
                outFile.println("," + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.DOT:
                outFile.println("." + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.WRITE:
                outFile.println("<<" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.READ:
                outFile.println(">>" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;				
            case sym.PLUSPLUS:
                outFile.println("++" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.MINUSMINUS:
                outFile.println("--" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;	
            case sym.PLUS:
                outFile.println("+" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.MINUS:
                outFile.println("-" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.TIMES:
                outFile.println("*" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.DIVIDE:
                outFile.println("/" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.NOT:
                outFile.println("!" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.AND:
                outFile.println("&&" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.OR:
                outFile.println("||" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.EQUALS:
                outFile.println("==" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.NOTEQUALS:
                outFile.println("!=" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.LESS:
                outFile.println("<" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.GREATER:
                outFile.println(">" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.LESSEQ:
                outFile.println("<=" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
            case sym.GREATEREQ:
                outFile.println(">=" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
			case sym.ASSIGN:
                outFile.println("=" + " lineNum: " 
                                    + ((TokenVal)token.value).linenum 
                                    + " charNum: " 
                                    + ((TokenVal)token.value).charnum);
                break;
			default:
				outFile.println("UNKNOWN TOKEN");
            } // end switch

            token = scanner.next_token();
        } // end while
        outFile.close();
    }
}
