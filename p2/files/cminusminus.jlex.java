import java_cup.runtime.*; // defines the Symbol class
// The generated scanner will return a Symbol for each token that it finds.
// A Symbol contains an Object field named value; that field will be of type
// TokenVal, defined below.
//
// A TokenVal object contains the line number on which the token occurs as
// well as the number of the character on that line that starts the token.
// Some tokens (literals and IDs) also include the value of the token.
class TokenVal {
  // fields
    int linenum;
    int charnum;
  // constructor
    TokenVal(int line, int ch) {
        linenum = line;
        charnum = ch;
    }
}
class IntLitTokenVal extends TokenVal {
  // new field: the value of the integer literal
    int intVal;
  // constructor
    IntLitTokenVal(int line, int ch, int val) {
        super(line, ch);
        intVal = val;
    }
}
class IdTokenVal extends TokenVal {
  // new field: the value of the identifier
    String idVal;
  // constructor
    IdTokenVal(int line, int ch, String val) {
        super(line, ch);
    idVal = val;
    }
}
class StrLitTokenVal extends TokenVal {
  // new field: the value of the string literal
    String strVal;
  // constructor
    StrLitTokenVal(int line, int ch, String val) {
        super(line, ch);
        strVal = val;
    }
}
// The following class is used to keep track of the character number at which
// the current token starts on its line.
class CharNum {
    static int num=1;
}


class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NOT_ACCEPT,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NOT_ACCEPT,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NOT_ACCEPT,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NOT_ACCEPT,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NOT_ACCEPT,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NOT_ACCEPT,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NOT_ACCEPT,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NOT_ACCEPT,
		/* 78 */ YY_NOT_ACCEPT,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"45:9,44,25,45:2,24,45:18,44,39,21,43,45:2,40,23,28,29,37,35,31,36,32,38,20:" +
"10,45,30,33,42,34,23,45,18:26,45,22,45:2,19,45,13,1,15,8,11,12,18,17,4,18:2" +
",3,18,5,2,18:2,9,14,6,10,7,16,18:3,26,41,27,45:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,103,
"0,1,2,3,4,1:9,5,6,7,8,1,9,10,11,12,13,1:7,14,1:4,13:2,15,13:9,1,16,17,18,19" +
",20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,24,43" +
",19,44,13,45,46,47,48,49,50,51,52,53,54,55,56,57,13,58,59,60,61,62,63,64,65")[0];

	private int yy_nxt[][] = unpackFromString(66,46,
"1,2,94:2,50,94,96,97,94,98,94,99,100,94,101,74,102,94:3,3,4,5:2,-1,6,7,8,9," +
"10,11,12,13,14,15,16,17,18,19,20,51,55,21,59,22,5,-1:47,94,79,94:16,80:2,-1" +
":45,3,-1:26,4:20,24,49,4:2,-1,4:20,-1:33,25,-1:8,26,-1:37,27,-1:7,28,-1:38," +
"29,-1:46,30,-1:47,31,-1:49,32,-1:45,35,-1:47,22,-1:2,94:18,80:2,-1:26,31:24" +
",-1,31:20,-1,75:20,-1,53,75:2,-1,75:20,-1,38:4,4:2,38:14,4:3,38,52,38:20,-1" +
",94:4,54,94:6,23,94:6,80:2,-1:65,33,-1:6,57:20,-1,60,57:2,-1,57:20,-1,75:4," +
"56:2,75:14,56:3,75,76,75:20,-1,94:5,36,94:12,80:2,-1:66,34,-1:5,56:20,48,77" +
",56:2,-1,56:20,-1,57:20,-1,64,57:2,-1,57:20,-1,94:4,37,94:13,80:2,-1:68,31," +
"-1:3,57:4,66:2,57:14,66:3,57,76,57:20,-1,94:2,39,94:15,80:2,-1:26,75:24,76," +
"75:20,-1,94:10,40,94:7,80:2,-1:26,57:24,76,57:20,-1,94:7,41,94:10,80:2,-1:2" +
"6,66:20,48,68,66:2,-1,66:20,-1,94:10,42,94:7,80:2,-1:26,78:4,66:2,78:14,66:" +
"3,78,52,78:20,-1,94:5,43,94:12,80:2,-1:26,94:10,44,94:7,80:2,-1:26,94:10,45" +
",94:7,80:2,-1:26,94:4,46,94:13,80:2,-1:26,94:5,47,94:12,80:2,-1:26,94,87,94" +
",58,94:14,80:2,-1:26,75:20,-1,62,75:2,-1,75:20,-1,38:4,56:2,38:14,56:3,38,5" +
"2,38:20,-1,94,61,94:16,80:2,-1:26,94:9,63,94:8,80:2,-1:26,94:3,65,94:14,80:" +
"2,-1:26,94:5,89,94:12,80:2,-1:26,94:13,67,94:4,80:2,-1:26,94:2,90,94:15,80:" +
"2,-1:26,94:8,95,94:9,80:2,-1:26,94:9,69,94:8,80:2,-1:26,94:3,91,94:14,80:2," +
"-1:26,94:9,92,94:8,80:2,-1:26,94:13,70,94:4,80:2,-1:26,94:2,71,94:15,80:2,-" +
"1:26,94:8,72,94:9,80:2,-1:26,94:14,73,94:3,80:2,-1:26,94:9,93,94:8,80:2,-1:" +
"26,94:8,81,94:9,80:2,-1:26,94,82,94:16,80:2,-1:26,94:10,83,94:7,80:2,-1:26," +
"94:2,84,94:15,80:2,-1:26,94:12,85,94:5,80:2,-1:26,94:5,86,94:12,80:2,-1:26," +
"94:16,88,94,80:2,-1:25");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

return new Symbol(sym.EOF);
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -3:
						break;
					case 3:
						{
            // integer literal
            // (a seq of >=1 digits)
            double d = Double.parseDouble(yytext());
            int val = 0;
            // check for overflow
            if(d < Integer.MIN_VALUE || d > Integer.MAX_VALUE){
                ErrMsg.warn(yyline+1, CharNum.num, 
                        "integer literal too large; using max value");
                val = Integer.MAX_VALUE;
            }
            else{
                val = Integer.parseInt(yytext());
            }
            Symbol s = new Symbol(sym.INTLITERAL,
                            new IntLitTokenVal(yyline+1, CharNum.num, val));
            CharNum.num += yytext().length();
            return s;
          }
					case -4:
						break;
					case 4:
						{
            // unterminated string literal
            // (there is a newline or end-of-file before the closing quote)
            // ignore the unterminated Str Lit (start looking for the next token 
            // after the newline)
            // ***so this regexp must keep reading all SINGLE_CHARs***
            ErrMsg.fatal(yyline+1, CharNum.num, 
                    "unterminated string literal ignored");
            CharNum.num = 1;
}
					case -5:
						break;
					case 5:
						{ 
            ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            CharNum.num++;
          }
					case -6:
						break;
					case 6:
						{ CharNum.num = 1; }
					case -7:
						break;
					case 7:
						{   
            Symbol s = new Symbol(sym.LCURLY, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 1;
            return s;
          }
					case -8:
						break;
					case 8:
						{ 
            Symbol s = new Symbol(sym.RCURLY, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 1;
            return s;
          }
					case -9:
						break;
					case 9:
						{ 
            Symbol s = new Symbol(sym.LPAREN, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 1;
            return s;
          }
					case -10:
						break;
					case 10:
						{ 
            Symbol s = new Symbol(sym.RPAREN, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 1;
            return s;
          }
					case -11:
						break;
					case 11:
						{ 
            Symbol s = new Symbol(sym.SEMICOLON, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 1;
            return s;
          }
					case -12:
						break;
					case 12:
						{ 
            Symbol s = new Symbol(sym.COMMA, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 1;
            return s;
        }
					case -13:
						break;
					case 13:
						{ 
            Symbol s = new Symbol(sym.DOT, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 1;
            return s;
        }
					case -14:
						break;
					case 14:
						{ 
            Symbol s = new Symbol(sym.LESS, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 1;
            return s;
        }
					case -15:
						break;
					case 15:
						{ 
            Symbol s = new Symbol(sym.GREATER, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 1;
            return s;
        }
					case -16:
						break;
					case 16:
						{ 
            Symbol s = new Symbol(sym.PLUS, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return s;
        }
					case -17:
						break;
					case 17:
						{ 
            Symbol s = new Symbol(sym.MINUS, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 1;
            return s;
        }
					case -18:
						break;
					case 18:
						{ 
            Symbol s = new Symbol(sym.TIMES, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 1;
            return s;
        }
					case -19:
						break;
					case 19:
						{ 
            Symbol s = new Symbol(sym.DIVIDE, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 1;
            return s;
        }
					case -20:
						break;
					case 20:
						{ 
            Symbol s = new Symbol(sym.NOT, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 1;
            return s;
        }
					case -21:
						break;
					case 21:
						{ 
            Symbol s = new Symbol(sym.ASSIGN, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 1;
            return s;
        }
					case -22:
						break;
					case 22:
						{ CharNum.num += yytext().length(); }
					case -23:
						break;
					case 23:
						{
            Symbol s = new Symbol(sym.IF, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return s;
          }
					case -24:
						break;
					case 24:
						{
            // string literal 
            // (a seq of >=0 string characters surrounded by double quotes)
            Symbol s = new Symbol(sym.STRINGLITERAL, 
                            new StrLitTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -25:
						break;
					case 25:
						{ 
            Symbol s = new Symbol(sym.WRITE, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 2;
            return s;
        }
					case -26:
						break;
					case 26:
						{ 
            Symbol s = new Symbol(sym.LESSEQ, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 2;
            return s;
        }
					case -27:
						break;
					case 27:
						{ 
            Symbol s = new Symbol(sym.READ, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 2;
            return s;
        }
					case -28:
						break;
					case 28:
						{ 
            Symbol s = new Symbol(sym.GREATEREQ, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 2;
            return s;
        }
					case -29:
						break;
					case 29:
						{ 
            Symbol s = new Symbol(sym.PLUSPLUS, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 2;
            return s;
        }
					case -30:
						break;
					case 30:
						{ 
            Symbol s = new Symbol(sym.MINUSMINUS, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 2;
            return s;
        }
					case -31:
						break;
					case 31:
						{ CharNum.num = 1; }
					case -32:
						break;
					case 32:
						{ 
            Symbol s = new Symbol(sym.NOTEQUALS, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 2;
            return s;
        }
					case -33:
						break;
					case 33:
						{ 
            Symbol s = new Symbol(sym.AND, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 2;
            return s;
        }
					case -34:
						break;
					case 34:
						{ 
            Symbol s = new Symbol(sym.OR, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 2;
            return s;
        }
					case -35:
						break;
					case 35:
						{ 
            Symbol s = new Symbol(sym.EQUALS, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 2;
            return s;
        }
					case -36:
						break;
					case 36:
						{
            Symbol s = new Symbol(sym.INT, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return s;
          }
					case -37:
						break;
					case 37:
						{
            Symbol s = new Symbol(sym.CIN, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return s;
          }
					case -38:
						break;
					case 38:
						{
            // bad string literal
            // (includes a bad escaped char AND not terminated)
            // ignore the Bad Str Lit (start looking for the next token after 
            // the closing quote)
            // ***so this regexp must keep reading all SINGLE_CHARs*** 
            ErrMsg.fatal(yyline+1, CharNum.num,
                    "unterminated string literal with bad escaped character ignored");
            CharNum.num = 1;
}
					case -39:
						break;
					case 39:
						{
            Symbol s = new Symbol(sym.BOOL, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return s;
          }
					case -40:
						break;
					case 40:
						{
            Symbol s = new Symbol(sym.TRUE, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return s;
          }
					case -41:
						break;
					case 41:
						{
            Symbol s = new Symbol(sym.VOID, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return s;
          }
					case -42:
						break;
					case 42:
						{
            Symbol s = new Symbol(sym.ELSE, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return s;
          }
					case -43:
						break;
					case 43:
						{
            Symbol s = new Symbol(sym.COUT, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return s;
          }
					case -44:
						break;
					case 44:
						{
            Symbol s = new Symbol(sym.FALSE, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return s;
          }
					case -45:
						break;
					case 45:
						{
            Symbol s = new Symbol(sym.WHILE, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return s;
          }
					case -46:
						break;
					case 46:
						{
            Symbol s = new Symbol(sym.RETURN, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return s;
          }
					case -47:
						break;
					case 47:
						{
            Symbol s = new Symbol(sym.STRUCT, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return s;
          }
					case -48:
						break;
					case 48:
						{
            // bad string literal
            // terminated, but includes a bad escaped char
            // after reading the single escaped char, keep reading until a double 
            // quote
            ErrMsg.fatal(yyline + 1, CharNum.num, 
                    "string literal with bad escaped character ignored");
            CharNum.num = 1;
}
					case -49:
						break;
					case 50:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -50:
						break;
					case 51:
						{ 
            ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            CharNum.num++;
          }
					case -51:
						break;
					case 52:
						{
            // bad string literal
            // (includes a bad escaped char AND not terminated)
            // ignore the Bad Str Lit (start looking for the next token after 
            // the closing quote)
            // ***so this regexp must keep reading all SINGLE_CHARs*** 
            ErrMsg.fatal(yyline+1, CharNum.num,
                    "unterminated string literal with bad escaped character ignored");
            CharNum.num = 1;
}
					case -52:
						break;
					case 54:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -53:
						break;
					case 55:
						{ 
            ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            CharNum.num++;
          }
					case -54:
						break;
					case 56:
						{
            // bad string literal
            // (includes a bad escaped char AND not terminated)
            // ignore the Bad Str Lit (start looking for the next token after 
            // the closing quote)
            // ***so this regexp must keep reading all SINGLE_CHARs*** 
            ErrMsg.fatal(yyline+1, CharNum.num,
                    "unterminated string literal with bad escaped character ignored");
            CharNum.num = 1;
}
					case -55:
						break;
					case 58:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -56:
						break;
					case 59:
						{ 
            ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            CharNum.num++;
          }
					case -57:
						break;
					case 61:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -58:
						break;
					case 63:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -59:
						break;
					case 65:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -60:
						break;
					case 67:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -61:
						break;
					case 69:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -62:
						break;
					case 70:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -63:
						break;
					case 71:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -64:
						break;
					case 72:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -65:
						break;
					case 73:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -66:
						break;
					case 74:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -67:
						break;
					case 75:
						{
            // bad string literal
            // (includes a bad escaped char AND not terminated)
            // ignore the Bad Str Lit (start looking for the next token after 
            // the closing quote)
            // ***so this regexp must keep reading all SINGLE_CHARs*** 
            ErrMsg.fatal(yyline+1, CharNum.num,
                    "unterminated string literal with bad escaped character ignored");
            CharNum.num = 1;
}
					case -68:
						break;
					case 76:
						{
            // bad string literal
            // (includes a bad escaped char AND not terminated)
            // ignore the Bad Str Lit (start looking for the next token after 
            // the closing quote)
            // ***so this regexp must keep reading all SINGLE_CHARs*** 
            ErrMsg.fatal(yyline+1, CharNum.num,
                    "unterminated string literal with bad escaped character ignored");
            CharNum.num = 1;
}
					case -69:
						break;
					case 79:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -70:
						break;
					case 80:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -71:
						break;
					case 81:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -72:
						break;
					case 82:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -73:
						break;
					case 83:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -74:
						break;
					case 84:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -75:
						break;
					case 85:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -76:
						break;
					case 86:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -77:
						break;
					case 87:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -78:
						break;
					case 88:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -79:
						break;
					case 89:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -80:
						break;
					case 90:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -81:
						break;
					case 91:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -82:
						break;
					case 92:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -83:
						break;
					case 93:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -84:
						break;
					case 94:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -85:
						break;
					case 95:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -86:
						break;
					case 96:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -87:
						break;
					case 97:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -88:
						break;
					case 98:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -89:
						break;
					case 99:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -90:
						break;
					case 100:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -91:
						break;
					case 101:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -92:
						break;
					case 102:
						{
            // identifier 
            // (a seq of >=1 letters and/or digits, and/or underscores, starting 
            // with a letter or underscore, excluding reserved words)
            // Note that: we have defined the reserved words already, so no need 
            //            to exclude here.
            Symbol s = new Symbol(sym.ID, 
                               new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return s;
}
					case -93:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
