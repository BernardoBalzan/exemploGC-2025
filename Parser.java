//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "exemploGC.y"
  import java.io.*;
  import java.util.ArrayList;
  import java.util.Stack;
//#line 21 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short ID=257;
public final static short INT=258;
public final static short FLOAT=259;
public final static short BOOL=260;
public final static short NUM=261;
public final static short LIT=262;
public final static short VOID=263;
public final static short MAIN=264;
public final static short READ=265;
public final static short WRITE=266;
public final static short IF=267;
public final static short ELSE=268;
public final static short WHILE=269;
public final static short TRUE=270;
public final static short FALSE=271;
public final static short DO=272;
public final static short EQ=273;
public final static short LEQ=274;
public final static short GEQ=275;
public final static short NEQ=276;
public final static short AND=277;
public final static short OR=278;
public final static short INC=279;
public final static short DEC=280;
public final static short ADDEQ=281;
public final static short BREAK=282;
public final static short CONTINUE=283;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    3,    0,    5,    7,    4,    2,    2,    8,    1,    1,
    1,    6,    6,    9,    9,    9,   11,    9,    9,    9,
    9,   12,    9,   13,   14,    9,   15,    9,   17,   16,
   16,   10,   10,   10,   10,   10,   10,   10,   10,   10,
   10,   10,   10,   10,   10,   10,   10,   10,   10,   10,
   10,   10,   10,   10,   10,   10,   10,
};
final static short yylen[] = {                            2,
    0,    3,    0,    0,    9,    2,    0,    3,    1,    1,
    1,    2,    0,    2,    3,    5,    0,    8,    5,    2,
    2,    0,    8,    0,    0,    7,    0,    7,    0,    3,
    0,    1,    1,    1,    1,    3,    2,    2,    2,    2,
    2,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    5,    3,
};
final static short yydefred[] = {                         1,
    0,    0,    9,   10,   11,    0,    0,    0,    0,    0,
    2,    6,    8,    0,    0,    3,    0,   13,    0,    0,
   32,    0,    0,    0,   24,   33,   34,   22,    0,    0,
    0,    0,    0,    0,   13,    0,   12,    0,   40,   41,
    0,    0,    0,    0,    0,    0,    0,   38,   39,   20,
   21,   37,    0,    0,    5,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   14,
    0,    0,    0,    0,    0,    0,    0,   36,   15,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   45,   46,   47,    0,    0,    0,    0,    0,    0,    0,
   19,   16,    0,    0,   25,    0,    0,    0,    0,    0,
    0,    0,   29,   28,   26,    0,   18,    0,   23,   30,
};
final static short yydgoto[] = {                          1,
    6,    7,    2,   11,   17,   19,   36,    8,   37,   38,
   96,   47,   46,  110,   97,  114,  118,
};
final static short yysindex[] = {                         0,
    0, -225,    0,    0,    0, -241, -246, -225,  -23, -239,
    0,    0,    0,   -3,   -2,    0,  -85,    0,    7,  -19,
    0,    1,    3,    4,    0,    0,    0,    0, -212, -211,
  -11,  -10,   23,   23,    0,  -75,    0,   42,    0,    0,
   23,   23, -206, -210,   23,   13,    7,    0,    0,    0,
    0,    0,   66,  -33,    0,   23,   23,   23,   23,   23,
   23,   23,   23,   23,   23,   23,   23,   23,   23,    0,
  137,  137,   14,   16,  137,   23, -215,    0,    0,  -34,
  -34,  -34,  -34,  151,  144,   77,  -34,  -34,  -32,  -32,
    0,    0,    0,   -1,    2,   18,   19,  101,   24,   23,
    0,    0,   23,    7,    0,   23,  137,  108, -202,    7,
  115,    8,    0,    0,    0,    9,    0,    7,    0,    0,
};
final static short yyrindex[] = {                         0,
    0, -194,    0,    0,    0,    0,    0, -194,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -55,   35,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -40,  -29,    0,   27,   33,    0,    0,    0,    0,  263,
  273,  304,  339,  -37,  -35,    0,  346,  388,  160,  167,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -27,    0,  -13,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   67,    0,    0,    0,   46,    0,    0,  -45,  419,
    0,    0,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=666;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         33,
   42,   77,   69,   55,   69,   54,   34,   67,   65,   67,
   66,   57,   68,   56,   68,    9,   10,   42,   42,   31,
   55,   55,   54,   54,   14,   55,   31,   54,   57,   57,
   56,   56,    3,    4,    5,   13,   15,   18,   16,   33,
   43,   42,   44,   45,   48,   49,   34,   50,   51,   55,
   73,   74,   76,   99,   94,   33,   95,  101,  109,  104,
  102,  103,   34,  106,  115,  113,  117,  119,    7,    4,
   17,   35,  120,   27,   12,   35,   35,   35,   69,   35,
   54,   35,    0,   67,   65,    0,   66,    0,   68,   35,
    0,   79,   35,   35,   35,    0,   35,   35,    0,    0,
   70,   64,   69,   63,   62,    0,   78,   67,   65,   31,
   66,   31,   68,   69,    0,    0,    0,    0,   67,   65,
    0,   66,    0,   68,    0,   64,    0,   63,   62,   35,
    0,    0,    0,    0,  100,    0,   64,   69,   63,   62,
    0,  105,   67,   65,   69,   66,    0,   68,  112,   67,
   65,   69,   66,    0,   68,  116,   67,   65,    0,   66,
   64,   68,   63,   62,    0,    0,    0,   64,    0,   63,
   62,    0,    0,   69,   64,    0,   63,   62,   67,   65,
   69,   66,    0,   68,    0,   67,   65,   69,   66,    0,
   68,    0,   67,   65,    0,   66,   64,   68,   63,   62,
   43,    0,   43,   64,   43,   63,    0,   44,    0,   44,
   64,   44,   63,    0,    0,    0,    0,   43,   43,   43,
    0,   43,   43,   20,   44,   44,   44,   21,   44,   44,
    0,   22,   23,   24,    0,   25,   26,   27,   28,   55,
   55,    0,   54,   31,    0,   29,   30,   31,   31,   32,
    0,   31,   31,   31,    0,   31,   31,   31,   31,   39,
   40,   41,    0,   20,    0,   31,   31,   21,   31,   31,
    0,   22,   23,   24,    0,   25,   26,   27,   28,   20,
    0,    0,    0,   21,    0,   29,   30,    0,   31,   32,
    0,    0,   26,   27,    0,    0,    0,    0,    0,    0,
    0,   29,   30,   50,    0,    0,    0,   35,   35,   35,
   35,   35,   35,   51,   56,   57,   58,   59,   60,   61,
   50,   50,   50,    0,   50,   50,    0,    0,    0,    0,
   51,   51,   51,    0,   51,   51,    0,    0,   56,   57,
   58,   59,   60,   61,   52,    0,    0,    0,    0,   56,
   57,   58,   59,   60,   61,    0,    0,    0,    0,    0,
    0,   52,   52,   52,    0,   52,   52,    0,    0,    0,
    0,    0,    0,   56,   57,   58,   59,   60,   61,   53,
   56,   57,   58,   59,   60,   61,   48,   56,   57,   58,
   59,   60,   61,    0,    0,    0,   53,   53,   53,    0,
   53,   53,    0,   48,   48,   48,    0,   48,   48,   56,
   57,   58,   59,   60,   61,    0,   56,   57,   58,   59,
   60,    0,    0,   56,   57,   58,   59,    0,   49,    0,
    0,    0,   43,   43,   43,   43,   43,   43,    0,   44,
   44,   44,   44,   44,   44,   49,   49,   49,    0,   49,
   49,   52,   53,    0,    0,    0,    0,    0,    0,   71,
   72,    0,    0,   75,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   80,   81,   82,   83,   84,   85,
   86,   87,   88,   89,   90,   91,   92,   93,    0,    0,
    0,    0,    0,    0,   98,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  107,    0,
    0,  108,    0,    0,  111,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   50,   50,   50,   50,   50,
   50,    0,    0,    0,    0,   51,   51,   51,   51,   51,
   51,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   52,   52,   52,   52,
   52,   52,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   53,   53,   53,   53,   53,   53,    0,   48,   48,
   48,   48,   48,   48,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   49,   49,   49,   49,   49,   49,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   41,   47,   37,   41,   37,   41,   40,   42,   43,   42,
   45,   41,   47,   41,   47,  257,  263,   58,   59,   33,
   58,   59,   58,   59,  264,   63,   40,   63,   58,   59,
   58,   59,  258,  259,  260,   59,   40,  123,   41,   33,
   40,   61,   40,   40,  257,  257,   40,   59,   59,  125,
  257,  262,   40,  269,   41,   33,   41,   59,  104,   41,
   59,   44,   40,   40,  110,  268,   59,   59,  263,  125,
   44,   37,  118,   41,    8,   41,   42,   43,   37,   45,
   35,   47,   -1,   42,   43,   -1,   45,   -1,   47,  123,
   -1,  125,   58,   59,   60,   -1,   62,   63,   -1,   -1,
   59,   60,   37,   62,   63,   -1,   41,   42,   43,  123,
   45,  125,   47,   37,   -1,   -1,   -1,   -1,   42,   43,
   -1,   45,   -1,   47,   -1,   60,   -1,   62,   63,  123,
   -1,   -1,   -1,   -1,   58,   -1,   60,   37,   62,   63,
   -1,   41,   42,   43,   37,   45,   -1,   47,   41,   42,
   43,   37,   45,   -1,   47,   41,   42,   43,   -1,   45,
   60,   47,   62,   63,   -1,   -1,   -1,   60,   -1,   62,
   63,   -1,   -1,   37,   60,   -1,   62,   63,   42,   43,
   37,   45,   -1,   47,   -1,   42,   43,   37,   45,   -1,
   47,   -1,   42,   43,   -1,   45,   60,   47,   62,   63,
   41,   -1,   43,   60,   45,   62,   -1,   41,   -1,   43,
   60,   45,   62,   -1,   -1,   -1,   -1,   58,   59,   60,
   -1,   62,   63,  257,   58,   59,   60,  261,   62,   63,
   -1,  265,  266,  267,   -1,  269,  270,  271,  272,  277,
  278,   -1,  278,  257,   -1,  279,  280,  261,  282,  283,
   -1,  265,  266,  267,   -1,  269,  270,  271,  272,  279,
  280,  281,   -1,  257,   -1,  279,  280,  261,  282,  283,
   -1,  265,  266,  267,   -1,  269,  270,  271,  272,  257,
   -1,   -1,   -1,  261,   -1,  279,  280,   -1,  282,  283,
   -1,   -1,  270,  271,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  279,  280,   41,   -1,   -1,   -1,  273,  274,  275,
  276,  277,  278,   41,  273,  274,  275,  276,  277,  278,
   58,   59,   60,   -1,   62,   63,   -1,   -1,   -1,   -1,
   58,   59,   60,   -1,   62,   63,   -1,   -1,  273,  274,
  275,  276,  277,  278,   41,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,  277,  278,   -1,   -1,   -1,   -1,   -1,
   -1,   58,   59,   60,   -1,   62,   63,   -1,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,   41,
  273,  274,  275,  276,  277,  278,   41,  273,  274,  275,
  276,  277,  278,   -1,   -1,   -1,   58,   59,   60,   -1,
   62,   63,   -1,   58,   59,   60,   -1,   62,   63,  273,
  274,  275,  276,  277,  278,   -1,  273,  274,  275,  276,
  277,   -1,   -1,  273,  274,  275,  276,   -1,   41,   -1,
   -1,   -1,  273,  274,  275,  276,  277,  278,   -1,  273,
  274,  275,  276,  277,  278,   58,   59,   60,   -1,   62,
   63,   33,   34,   -1,   -1,   -1,   -1,   -1,   -1,   41,
   42,   -1,   -1,   45,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   56,   57,   58,   59,   60,   61,
   62,   63,   64,   65,   66,   67,   68,   69,   -1,   -1,
   -1,   -1,   -1,   -1,   76,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  100,   -1,
   -1,  103,   -1,   -1,  106,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,  277,
  278,   -1,   -1,   -1,   -1,  273,  274,  275,  276,  277,
  278,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,
  277,  278,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  273,  274,  275,  276,  277,  278,   -1,  273,  274,
  275,  276,  277,  278,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  273,  274,  275,  276,  277,  278,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=283;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'","'?'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"ID","INT","FLOAT","BOOL","NUM","LIT",
"VOID","MAIN","READ","WRITE","IF","ELSE","WHILE","TRUE","FALSE","DO","EQ","LEQ",
"GEQ","NEQ","AND","OR","INC","DEC","ADDEQ","BREAK","CONTINUE",
};
final static String yyrule[] = {
"$accept : prog",
"$$1 :",
"prog : $$1 dList mainF",
"$$2 :",
"$$3 :",
"mainF : VOID MAIN '(' ')' $$2 '{' lcmd $$3 '}'",
"dList : decl dList",
"dList :",
"decl : type ID ';'",
"type : INT",
"type : FLOAT",
"type : BOOL",
"lcmd : lcmd cmd",
"lcmd :",
"cmd : exp ';'",
"cmd : '{' lcmd '}'",
"cmd : WRITE '(' LIT ')' ';'",
"$$4 :",
"cmd : WRITE '(' LIT $$4 ',' exp ')' ';'",
"cmd : READ '(' ID ')' ';'",
"cmd : BREAK ';'",
"cmd : CONTINUE ';'",
"$$5 :",
"cmd : DO $$5 cmd WHILE '(' exp ')' ';'",
"$$6 :",
"$$7 :",
"cmd : WHILE $$6 '(' exp ')' $$7 cmd",
"$$8 :",
"cmd : IF '(' exp $$8 ')' cmd restoIf",
"$$9 :",
"restoIf : ELSE $$9 cmd",
"restoIf :",
"exp : NUM",
"exp : TRUE",
"exp : FALSE",
"exp : ID",
"exp : '(' exp ')'",
"exp : '!' exp",
"exp : INC ID",
"exp : DEC ID",
"exp : ID INC",
"exp : ID DEC",
"exp : ID ADDEQ exp",
"exp : exp '+' exp",
"exp : exp '-' exp",
"exp : exp '*' exp",
"exp : exp '/' exp",
"exp : exp '%' exp",
"exp : exp '>' exp",
"exp : exp '<' exp",
"exp : exp EQ exp",
"exp : exp LEQ exp",
"exp : exp GEQ exp",
"exp : exp NEQ exp",
"exp : exp OR exp",
"exp : exp AND exp",
"exp : exp '?' exp ':' exp",
"exp : ID '=' exp",
};

//#line 228 "exemploGC.y"

  private Yylex lexer;

  private TabSimb ts = new TabSimb();

  private int strCount = 0;
  private ArrayList<String> strTab = new ArrayList<String>();

  private Stack<Integer> pRot = new Stack<Integer>();
  private int proxRot = 1;

  private Stack<Integer> pBreak = new Stack<Integer>();
  private Stack<Integer> pContinue = new Stack<Integer>();

  public static int ARRAY = 100;


  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    System.err.println ("Error: " + error + "  linha: " + lexer.getLine());
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);
  }  

  public void setDebug(boolean debug) {
    yydebug = debug;
  }

  public void listarTS() { ts.listar();}

  public static void main(String args[]) throws IOException {

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
      yyparser.yyparse();
      // yyparser.listarTS();

    }
    else {
      // interactive mode
      System.out.println("\n\tFormato: java Parser entrada.cmm >entrada.s\n");
    }

  }

							
	void gcExpArit(int oparit) {
 		System.out.println("\tPOPL %EBX");
   		System.out.println("\tPOPL %EAX");

   		switch (oparit) {
     		case '+' : System.out.println("\tADDL %EBX, %EAX" ); break;
     		case '-' : System.out.println("\tSUBL %EBX, %EAX" ); break;
     		case '*' : System.out.println("\tIMULL %EBX, %EAX" ); break;

    		case '/': 
           		     System.out.println("\tMOVL $0, %EDX");
           		     System.out.println("\tIDIVL %EBX");
           		     break;
     		case '%': 
           		     System.out.println("\tMOVL $0, %EDX");
           		     System.out.println("\tIDIVL %EBX");
           		     System.out.println("\tMOVL %EDX, %EAX");
           		     break;
    		}
   		System.out.println("\tPUSHL %EAX");
	}

        /* >>> NOVO: gerador para atribuicao como expressao <<< */
        void gcAtrib(String id) {
            System.out.println("\tPOPL %EDX");           // valor da RHS
            System.out.println("\tMOVL %EDX, _" + id);   // grava na variavel
            System.out.println("\tPUSHL %EDX");          // reempilha valor atribuido
        }

		            /* >>> NOVOS: pre/pós incremento/decremento <<< */

    // ++a  -> incrementa a, empilha novo valor
    void gcPreInc(String id) {
        System.out.println("\tMOVL _" + id + ", %EAX");
        System.out.println("\tADDL $1, %EAX");
        System.out.println("\tMOVL %EAX, _" + id);
        System.out.println("\tPUSHL %EAX");
    }

    // --a  -> decrementa a, empilha novo valor
    void gcPreDec(String id) {
        System.out.println("\tMOVL _" + id + ", %EAX");
        System.out.println("\tSUBL $1, %EAX");
        System.out.println("\tMOVL %EAX, _" + id);
        System.out.println("\tPUSHL %EAX");
    }

    // a++  -> empilha valor antigo, depois incrementa variável
    void gcPosInc(String id) {
        System.out.println("\tPUSHL _" + id);          // valor antigo vira resultado da expressão
        System.out.println("\tMOVL _" + id + ", %EAX");
        System.out.println("\tADDL $1, %EAX");
        System.out.println("\tMOVL %EAX, _" + id);
    }

    // a--  -> empilha valor antigo, depois decrementa variável
    void gcPosDec(String id) {
        System.out.println("\tPUSHL _" + id);          // valor antigo vira resultado da expressão
        System.out.println("\tMOVL _" + id + ", %EAX");
        System.out.println("\tSUBL $1, %EAX");
        System.out.println("\tMOVL %EAX, _" + id);
    }

    void gcAtribAdd(String id) {
    System.out.println("\tPOPL %EDX");         // RHS
    System.out.println("\tMOVL _" + id + ", %EAX");  // carrega LHS
    System.out.println("\tADDL %EDX, %EAX");   // soma
    System.out.println("\tMOVL %EAX, _" + id); // grava em id
    System.out.println("\tPUSHL %EAX");        // reempilha resultado
}

    /* >>> NOVO: operador condicional ternário (cond ? e1 : e2) <<< */
    void gcTernario() {
        int rFalse = proxRot++;
        int rEnd   = proxRot++;

        // pilha (topo -> base): e2, e1, cond
        System.out.println("\tPOPL %EAX   # expr_falsa");
        System.out.println("\tPOPL %EBX   # expr_verdadeira");
        System.out.println("\tPOPL %ECX   # condicao");
        System.out.println("\tCMPL $0, %ECX");
        System.out.printf ("\tJE rot_%02d\n", rFalse);
        System.out.println("\tMOVL %EBX, %EAX");              // cond != 0 -> resultado = verdadeira
        System.out.printf ("\tJMP rot_%02d\n", rEnd);
        System.out.printf("rot_%02d:\n", rFalse);             // cond == 0 -> fica com falsa em %EAX
        System.out.printf("rot_%02d:\n", rEnd);
        System.out.println("\tPUSHL %EAX");                   // empilha resultado da expressão
    }


	public void gcExpRel(int oprel) {

	    System.out.println("\tPOPL %EAX");
	    System.out.println("\tPOPL %EDX");
	    System.out.println("\tCMPL %EAX, %EDX");
	    System.out.println("\tMOVL $0, %EAX");
    
	    switch (oprel) {
	       case '<':  		  System.out.println("\tSETL  %AL"); break;
	       case '>':  		  System.out.println("\tSETG  %AL"); break;
	       case Parser.EQ:  System.out.println("\tSETE  %AL"); break;
	       case Parser.GEQ: System.out.println("\tSETGE %AL"); break;
	       case Parser.LEQ: System.out.println("\tSETLE %AL"); break;
	       case Parser.NEQ: System.out.println("\tSETNE %AL"); break;
	    }
    
	    System.out.println("\tPUSHL %EAX");

	}


	public void gcExpLog(int oplog) {

	   	System.out.println("\tPOPL %EDX");
 		 	System.out.println("\tPOPL %EAX");

  	 	System.out.println("\tCMPL $0, %EAX");
 		  System.out.println("\tMOVL $0, %EAX");
   		System.out.println("\tSETNE %AL");
   		System.out.println("\tCMPL $0, %EDX");
   		System.out.println("\tMOVL $0, %EDX");
   		System.out.println("\tSETNE %DL");

   		switch (oplog) {
    			case Parser.OR:  System.out.println("\tORL  %EDX, %EAX");  break;
    			case Parser.AND: System.out.println("\tANDL  %EDX, %EAX"); break;
        }

    	System.out.println("\tPUSHL %EAX");
	}

	public void gcExpNot(){

  	    System.out.println("\tPOPL %EAX" );
 	    System.out.println("	\tNEGL %EAX" );
  	    System.out.println("	\tPUSHL %EAX");
	}

   private void geraInicio() {
			System.out.println(".text\n\n#\t nome COMPLETO e matricula dos componentes do grupo...\n#\n"); 
			System.out.println(".GLOBL _start\n\n");  
   }

   private void geraFinal(){
	
			System.out.println("\n\n");
			System.out.println("#");
			System.out.println("# devolve o controle para o SO (final da main)");
			System.out.println("#");
			System.out.println("\tmov $0, %ebx");
			System.out.println("\tmov $1, %eax");
			System.out.println("\tint $0x80");
	
			System.out.println("\n");
			System.out.println("#");
			System.out.println("# Funcoes da biblioteca (IO)");
			System.out.println("#");
			System.out.println("\n");
			System.out.println("_writeln:");
			System.out.println("\tMOVL $__fim_msg, %ECX");
			System.out.println("\tDECL %ECX");
			System.out.println("\tMOVB $10, (%ECX)");
			System.out.println("\tMOVL $1, %EDX");
			System.out.println("\tJMP _writeLit");
			System.out.println("_write:");
			System.out.println("\tMOVL $__fim_msg, %ECX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tCMPL $0, %EAX");
			System.out.println("\tJGE _write3");
			System.out.println("\tNEGL %EAX");
			System.out.println("\tMOVL $1, %EBX");
			System.out.println("_write3:");
			System.out.println("\tPUSHL %EBX");
			System.out.println("\tMOVL $10, %EBX");
			System.out.println("_divide:");
			System.out.println("\tMOVL $0, %EDX");
			System.out.println("\tIDIVL %EBX");
			System.out.println("\tDECL %ECX");
			System.out.println("\tADD $48, %DL");
			System.out.println("\tMOVB %DL, (%ECX)");
			System.out.println("\tCMPL $0, %EAX");
			System.out.println("\tJNE _divide");
			System.out.println("\tPOPL %EBX");
			System.out.println("\tCMPL $0, %EBX");
			System.out.println("\tJE _print");
			System.out.println("\tDECL %ECX");
			System.out.println("\tMOVB $'-', (%ECX)");
			System.out.println("_print:");
			System.out.println("\tMOVL $__fim_msg, %EDX");
			System.out.println("\tSUBL %ECX, %EDX");
			System.out.println("_writeLit:");
			System.out.println("\tMOVL $1, %EBX");
			System.out.println("\tMOVL $4, %EAX");
			System.out.println("\tint $0x80");
			System.out.println("\tRET");
			System.out.println("_read:");
			System.out.println("\tMOVL $15, %EDX");
			System.out.println("\tMOVL $__msg, %ECX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tMOVL $3, %EAX");
			System.out.println("\tint $0x80");
			System.out.println("\tMOVL $0, %EAX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tMOVL $0, %EDX");
			System.out.println("\tMOVL $__msg, %ECX");
			System.out.println("\tCMPB $'-', (%ECX)");
			System.out.println("\tJNE _reading");
			System.out.println("\tINCL %ECX");
			System.out.println("\tINC %BL");
			System.out.println("_reading:");
			System.out.println("\tMOVB (%ECX), %DL");
			System.out.println("\tCMP $10, %DL");
			System.out.println("\tJE _fimread");
			System.out.println("\tSUB $48, %DL");
			System.out.println("\tIMULL $10, %EAX");
			System.out.println("\tADDL %EDX, %EAX");
			System.out.println("\tINCL %ECX");
			System.out.println("\tJMP _reading");
			System.out.println("_fimread:");
			System.out.println("\tCMPB $1, %BL");
			System.out.println("\tJNE _fimread2");
			System.out.println("\tNEGL %EAX");
			System.out.println("_fimread2:");
			System.out.println("\tRET");
			System.out.println("\n");
     }

     private void geraAreaDados(){
			System.out.println("");		
			System.out.println("#");
			System.out.println("# area de dados");
			System.out.println("#");
			System.out.println(".data");
			System.out.println("#");
			System.out.println("# variaveis globais");
			System.out.println("#");
			ts.geraGlobais();	
			System.out.println("");
	
    }

     private void geraAreaLiterais() { 

         System.out.println("#\n# area de literais\n#");
         System.out.println("__msg:");
	 System.out.println("\t.zero 30");
	 System.out.println("__fim_msg:");
	 System.out.println("\t.byte 0");
	 System.out.println("\n");

         for (int i = 0; i<strTab.size(); i++ ) {
             System.out.println("_str_"+i+":");
             System.out.println("\t .ascii \""+strTab.get(i)+"\""); 
	     System.out.println("_str_"+i+"Len = . - _str_"+i);  
	 }		
   }
//#line 735 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 33 "exemploGC.y"
{ geraInicio(); }
break;
case 2:
//#line 33 "exemploGC.y"
{ geraAreaDados(); geraAreaLiterais(); }
break;
case 3:
//#line 35 "exemploGC.y"
{ System.out.println("_start:"); }
break;
case 4:
//#line 36 "exemploGC.y"
{ geraFinal(); }
break;
case 8:
//#line 43 "exemploGC.y"
{  
                      TS_entry nodo = ts.pesquisa(val_peek(1).sval);
    	              if (nodo != null) 
                          yyerror("(sem) variavel >" + val_peek(1).sval + "< jah declarada");
                      else 
                          ts.insert(new TS_entry(val_peek(1).sval, val_peek(2).ival)); 
                   }
break;
case 9:
//#line 52 "exemploGC.y"
{ yyval.ival = INT; }
break;
case 10:
//#line 53 "exemploGC.y"
{ yyval.ival = FLOAT; }
break;
case 11:
//#line 54 "exemploGC.y"
{ yyval.ival = BOOL; }
break;
case 15:
//#line 67 "exemploGC.y"
{ System.out.println("\t\t# terminou o bloco..."); }
break;
case 16:
//#line 69 "exemploGC.y"
{ 
                                strTab.add(val_peek(2).sval);
                                System.out.println("\tMOVL $_str_"+strCount+"Len, %EDX"); 
				System.out.println("\tMOVL $_str_"+strCount+", %ECX"); 
                                System.out.println("\tCALL _writeLit"); 
				System.out.println("\tCALL _writeln"); 
                                strCount++;
			     }
break;
case 17:
//#line 79 "exemploGC.y"
{ 
                                strTab.add(val_peek(0).sval);
                                System.out.println("\tMOVL $_str_"+strCount+"Len, %EDX"); 
				System.out.println("\tMOVL $_str_"+strCount+", %ECX"); 
                                System.out.println("\tCALL _writeLit"); 
				strCount++;
			      }
break;
case 18:
//#line 88 "exemploGC.y"
{ 
			 System.out.println("\tPOPL %EAX"); 
			 System.out.println("\tCALL _write");	
			 System.out.println("\tCALL _writeln"); 
                        }
break;
case 19:
//#line 95 "exemploGC.y"
{
			System.out.println("\tPUSHL $_"+val_peek(2).sval);
			System.out.println("\tCALL _read");
			System.out.println("\tPOPL %EDX");
			System.out.println("\tMOVL %EAX, (%EDX)");
		}
break;
case 20:
//#line 103 "exemploGC.y"
{
            if (pBreak.empty())
                yyerror("(sem) comando 'break' fora de laco");
            else
                System.out.printf("\tJMP rot_%02d   # break\n", pBreak.peek());
          }
break;
case 21:
//#line 110 "exemploGC.y"
{
            if (pContinue.empty())
                yyerror("(sem) comando 'continue' fora de laco");
            else
                System.out.printf("\tJMP rot_%02d   # continue\n", pContinue.peek());
          }
break;
case 22:
//#line 118 "exemploGC.y"
{
            pRot.push(proxRot);                 /* rotulo de início do do-while*/
            pContinue.push(pRot.peek());        /* continue -> volta para o início (antes do corpo)*/
            pBreak.push(pRot.peek()+1);         /* break -> vai para o fim*/
            System.out.printf("rot_%02d:\n", pRot.peek());
            proxRot += 2;                       /* reserva rot_fim = inicio+1*/
          }
break;
case 23:
//#line 127 "exemploGC.y"
{
            System.out.println("\tPOPL %EAX   # do-while testa no final");
            System.out.println("\tCMPL $0, %EAX");
            System.out.printf ("\tJNE rot_%02d\n", pRot.peek());
            System.out.printf("rot_%02d:\n", (int)pRot.peek()+1);
            pRot.pop();
            pContinue.pop();
            pBreak.pop();
          }
break;
case 24:
//#line 138 "exemploGC.y"
{
                    pRot.push(proxRot);         /* rotulo de início do while*/
                    proxRot += 2;               /* reserva também o rotulo de fim (inicio+1)*/
                    pContinue.push(pRot.peek());        /* continue -> volta para o início (reavalia cond)*/
                    pBreak.push(pRot.peek()+1);         /* break -> salta para o fim*/
                    System.out.printf("rot_%02d:\n", pRot.peek());
              }
break;
case 25:
//#line 145 "exemploGC.y"
{
                    System.out.println("\tPOPL %EAX   # desvia se falso...");
                    System.out.println("\tCMPL $0, %EAX");
                    System.out.printf("\tJE rot_%02d\n", (int)pRot.peek()+1);
              }
break;
case 26:
//#line 150 "exemploGC.y"
{
                    System.out.printf("\tJMP rot_%02d   # terminou cmd na linha de cima\n", pRot.peek());
                    System.out.printf("rot_%02d:\n", (int)pRot.peek()+1);
                    pRot.pop();
                    pContinue.pop();
                    pBreak.pop();
              }
break;
case 27:
//#line 158 "exemploGC.y"
{	
						pRot.push(proxRot);  proxRot += 2;
															
						System.out.println("\tPOPL %EAX");
						System.out.println("\tCMPL $0, %EAX");
						System.out.printf("\tJE rot_%02d\n", pRot.peek());
				  }
break;
case 28:
//#line 167 "exemploGC.y"
{
						System.out.printf("rot_%02d:\n",pRot.peek()+1);
						pRot.pop();
				  }
break;
case 29:
//#line 174 "exemploGC.y"
{
			System.out.printf("\tJMP rot_%02d\n", pRot.peek()+1);
			System.out.printf("rot_%02d:\n",pRot.peek());
			}
break;
case 31:
//#line 180 "exemploGC.y"
{
	    System.out.printf("\tJMP rot_%02d\n", pRot.peek()+1);
		System.out.printf("rot_%02d:\n",pRot.peek());
	  }
break;
case 32:
//#line 187 "exemploGC.y"
{ System.out.println("\tPUSHL $"+val_peek(0).sval); }
break;
case 33:
//#line 188 "exemploGC.y"
{ System.out.println("\tPUSHL $1"); }
break;
case 34:
//#line 189 "exemploGC.y"
{ System.out.println("\tPUSHL $0"); }
break;
case 35:
//#line 190 "exemploGC.y"
{ System.out.println("\tPUSHL _"+val_peek(0).sval); }
break;
case 37:
//#line 192 "exemploGC.y"
{ gcExpNot(); }
break;
case 38:
//#line 195 "exemploGC.y"
{ gcPreInc(val_peek(0).sval); }
break;
case 39:
//#line 196 "exemploGC.y"
{ gcPreDec(val_peek(0).sval); }
break;
case 40:
//#line 199 "exemploGC.y"
{ gcPosInc(val_peek(1).sval); }
break;
case 41:
//#line 200 "exemploGC.y"
{ gcPosDec(val_peek(1).sval); }
break;
case 42:
//#line 201 "exemploGC.y"
{ gcAtribAdd(val_peek(2).sval); }
break;
case 43:
//#line 202 "exemploGC.y"
{ gcExpArit('+'); }
break;
case 44:
//#line 203 "exemploGC.y"
{ gcExpArit('-'); }
break;
case 45:
//#line 204 "exemploGC.y"
{ gcExpArit('*'); }
break;
case 46:
//#line 205 "exemploGC.y"
{ gcExpArit('/'); }
break;
case 47:
//#line 206 "exemploGC.y"
{ gcExpArit('%'); }
break;
case 48:
//#line 208 "exemploGC.y"
{ gcExpRel('>'); }
break;
case 49:
//#line 209 "exemploGC.y"
{ gcExpRel('<'); }
break;
case 50:
//#line 210 "exemploGC.y"
{ gcExpRel(EQ); }
break;
case 51:
//#line 211 "exemploGC.y"
{ gcExpRel(LEQ); }
break;
case 52:
//#line 212 "exemploGC.y"
{ gcExpRel(GEQ); }
break;
case 53:
//#line 213 "exemploGC.y"
{ gcExpRel(NEQ); }
break;
case 54:
//#line 215 "exemploGC.y"
{ gcExpLog(OR); }
break;
case 55:
//#line 216 "exemploGC.y"
{ gcExpLog(AND); }
break;
case 56:
//#line 219 "exemploGC.y"
{ gcTernario(); }
break;
case 57:
//#line 222 "exemploGC.y"
{ gcAtrib(val_peek(2).sval); }
break;
//#line 1163 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
