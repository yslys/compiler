import java.io.*;
import java.util.*;

// **********************************************************************
// The ASTnode class defines the nodes of the abstract-syntax tree that
// represents a C-- program.
//
// Internal nodes of the tree contain pointers to children, organized
// either in a list (for nodes that may have a variable number of
// children) or as a fixed set of fields.
//
// The nodes for literals and ids contain line and character number
// information; for string literals and identifiers, they also contain a
// string; for integer literals, they also contain an integer value.
//
// Here are all the different kinds of AST nodes and what kinds of children
// they have.  All of these kinds of AST nodes are subclasses of "ASTnode".
// Indentation indicates further subclassing:
//
//     Subclass            Children
//     --------            ----
//     ProgramNode         DeclListNode
//     DeclListNode        linked list of DeclNode
//     DeclNode:
//       VarDeclNode       TypeNode, IdNode, int
//       FnDeclNode        TypeNode, IdNode, FormalsListNode, FnBodyNode
//       FormalDeclNode    TypeNode, IdNode
//       StructDeclNode    IdNode, DeclListNode
//
//     FormalsListNode     linked list of FormalDeclNode
//     FnBodyNode          DeclListNode, StmtListNode
//     StmtListNode        linked list of StmtNode
//     ExpListNode         linked list of ExpNode
//
//     TypeNode:
//       IntNode           -- none --
//       BoolNode          -- none --
//       VoidNode          -- none --
//       StructNode        IdNode
//
//     StmtNode:
//       AssignStmtNode      AssignNode
//       PostIncStmtNode     ExpNode
//       PostDecStmtNode     ExpNode
//       ReadStmtNode        ExpNode
//       WriteStmtNode       ExpNode
//       IfStmtNode          ExpNode, DeclListNode, StmtListNode
//       IfElseStmtNode      ExpNode, DeclListNode, StmtListNode,
//                                    DeclListNode, StmtListNode
//       WhileStmtNode       ExpNode, DeclListNode, StmtListNode
//       RepeatStmtNode      ExpNode, DeclListNode, StmtListNode
//       CallStmtNode        CallExpNode
//       ReturnStmtNode      ExpNode
//
//     ExpNode:
//       IntLitNode          -- none --
//       StrLitNode          -- none --
//       TrueNode            -- none --
//       FalseNode           -- none --
//       IdNode              -- none --
//       DotAccessNode       ExpNode, IdNode
//       AssignNode          ExpNode, ExpNode
//       CallExpNode         IdNode, ExpListNode
//       UnaryExpNode        ExpNode
//         UnaryMinusNode
//         NotNode
//       BinaryExpNode       ExpNode ExpNode
//         PlusNode
//         MinusNode
//         TimesNode
//         DivideNode
//         AndNode
//         OrNode
//         EqualsNode
//         NotEqualsNode
//         LessNode
//         GreaterNode
//         LessEqNode
//         GreaterEqNode
//
// Here are the different kinds of AST nodes again, organized according to
// whether they are leaves, internal nodes with linked lists of children, or
// internal nodes with a fixed number of children:
//
// (1) Leaf nodes:
//        IntNode,   BoolNode,  VoidNode,  IntLitNode,  StrLitNode,
//        TrueNode,  FalseNode, IdNode
//
// (2) Internal nodes with (possibly empty) linked lists of children:
//        DeclListNode, FormalsListNode, StmtListNode, ExpListNode
//
// (3) Internal nodes with fixed numbers of children:
//        ProgramNode,     VarDeclNode,     FnDeclNode,     FormalDeclNode,
//        StructDeclNode,  FnBodyNode,      StructNode,     AssignStmtNode,
//        PostIncStmtNode, PostDecStmtNode, ReadStmtNode,   WriteStmtNode
//        IfStmtNode,      IfElseStmtNode,  WhileStmtNode,  RepeatStmtNode,
//        CallStmtNode
//        ReturnStmtNode,  DotAccessNode,   AssignExpNode,  CallExpNode,
//        UnaryExpNode,    BinaryExpNode,   UnaryMinusNode, NotNode,
//        PlusNode,        MinusNode,       TimesNode,      DivideNode,
//        AndNode,         OrNode,          EqualsNode,     NotEqualsNode,
//        LessNode,        GreaterNode,     LessEqNode,     GreaterEqNode
//
// **********************************************************************

// **********************************************************************
// ASTnode class (base class for all other kinds of nodes)
// **********************************************************************

abstract class ASTnode {
    // every subclass must provide an unparse operation
    abstract public void unparse(PrintWriter p, int indent);

    // this method can be used by the unparse methods to do indenting
    protected void addIndentation(PrintWriter p, int indent) {
        for (int k = 0; k < indent; k++) p.print(" ");
    }
}

// **********************************************************************
// ProgramNode,  DeclListNode, FormalsListNode, FnBodyNode,
// StmtListNode, ExpListNode
// **********************************************************************

class ProgramNode extends ASTnode {
    public ProgramNode(DeclListNode L) {
        myDeclList = L;
    }

    public void unparse(PrintWriter p, int indent) {
        myDeclList.unparse(p, indent);
    }

    // name analysis
    public void analysis(){
        SymTable t = new SymTable();
        myDeclList.analysis(t);
    }

    private DeclListNode myDeclList;
}

class DeclListNode extends ASTnode {
    public DeclListNode(List<DeclNode> S) {
        myDecls = S;
    }

    public void unparse(PrintWriter p, int indent) {
        Iterator it = myDecls.iterator();
        try {
            while (it.hasNext()) {
                ((DeclNode)it.next()).unparse(p, indent);
            }
        } catch (NoSuchElementException ex) {
            System.err.println("unexpected NoSuchElementException in DeclListNode.print");
            System.exit(-1);
        }
    }

    // do name analysis for each DeclListNode in myDecls
    public void analysis(SymTable t){
        for(DeclNode d : myDecls){
            d.analysis(t);
        }
    }
    private List<DeclNode> myDecls;
}

class FormalsListNode extends ASTnode {
    public FormalsListNode(List<FormalDeclNode> S) {
        myFormals = S;
    }

    public void unparse(PrintWriter p, int indent) {
        Iterator<FormalDeclNode> it = myFormals.iterator();
        if (it.hasNext()) { // if there is at least one element
            it.next().unparse(p, indent);
            while (it.hasNext()) {  // print the rest of the list
                p.print(", ");
                it.next().unparse(p, indent);
            }
        }
    }

    // do name analysis for each FormalDeclNode in myFormals
    public void analysis(SymTable t){
        for(FormalDeclNode f : myFormals){
            f.analysis(t);
        }
    }

    // get the list of types of formal list
    public LinkedList<String> getFormalTypeList(){
        LinkedList<String> l = new LinkedList<>();
        Set<String> set = new HashSet<String>();

        for(FormalDeclNode f : myFormals){
            // if type of the formal is NOT void, and the set does not contain
            // such id, then add to the set and the list
            // let naFormalDeclNode() to report error
            if(!f.getTypeStrVal().equals("void")
                    && !set.contains(f.getMyIdStrVal())){
                set.add(f.getMyIdStrVal());
                l.addLast(f.getTypeStrVal());
            }
        }
        return l;
    }

    private List<FormalDeclNode> myFormals;
}

class FnBodyNode extends ASTnode {
    public FnBodyNode(DeclListNode declList, StmtListNode stmtList) {
        myDeclList = declList;
        myStmtList = stmtList;
    }

    public void unparse(PrintWriter p, int indent) {
        myDeclList.unparse(p, indent);
        myStmtList.unparse(p, indent);
    }

    // do name anlaysis for myDeclList and myStmtList
    public void analysis(SymTable t){
        myDeclList.analysis(t);
        myStmtList.analysis(t);
    }

    private DeclListNode myDeclList;
    private StmtListNode myStmtList;
}

class StmtListNode extends ASTnode {
    public StmtListNode(List<StmtNode> S) {
        myStmts = S;
    }

    public void unparse(PrintWriter p, int indent) {
        Iterator<StmtNode> it = myStmts.iterator();
        while (it.hasNext()) {
            it.next().unparse(p, indent);
        }
    }

    // do name analysis for each StmtNode in myStmts
    public void analysis(SymTable t){
        for(StmtNode s : myStmts){
            s.analysis(t);
        }
    }

    private List<StmtNode> myStmts;
}

class ExpListNode extends ASTnode {
    public ExpListNode(List<ExpNode> S) {
        myExps = S;
    }

    public void unparse(PrintWriter p, int indent) {
        Iterator<ExpNode> it = myExps.iterator();
        if (it.hasNext()) { // if there is at least one element
            it.next().unparse(p, indent);
            while (it.hasNext()) {  // print the rest of the list
                p.print(", ");
                it.next().unparse(p, indent);
            }
        }
    }

    // do name analysis on each ExpNode of myExps
    public void analysis(SymTable t){
        for(ExpNode e : myExps){
            e.analysis(t);
        }
    }
    private List<ExpNode> myExps;
}

// **********************************************************************
// DeclNode and its subclasses
// **********************************************************************

abstract class DeclNode extends ASTnode {
    abstract public void analysis(SymTable t);

}

class VarDeclNode extends DeclNode {
    public VarDeclNode(TypeNode type, IdNode id, int size) {
        myType = type;
        myId = id;
        mySize = size;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndentation(p, indent);
        myType.unparse(p, 0);
        p.print(" ");
        myId.unparse(p, 0);
        p.println(";");
    }

    /** 
     * name analysis for variable declaration node
     * need to check if it's primitive type or struct type
     */  
    public void analysis(SymTable t){
        
        // if myType == primitive type
        if(this.mySize == NOT_STRUCT){
            
            // if type == void, then fatal
            if(myType.getStrVal().equals("void")){
                ErrMsg.fatal(myId.getLineNum(), myId.getCharNum(), 
                        "Non-Function declared void");
            }

            try{
                // if struct type being overriden by a local variable, then fatal
                if(t.lookupGlobal(myType.getStrVal()) != null 
                    && t.lookupGlobal(myId.getStrVal()).getType()
                                                       .equals("struct")){
                    ErrMsg.fatal(myId.getLineNum(), myId.getCharNum(), 
                            "Invalid name of struct type");
                }
                
                // if already declared, then fatal
                if(t.lookupLocal(myId.getStrVal()) != null){
                    ErrMsg.fatal(myId.getLineNum(), myId.getCharNum(), 
                        "Multiply declared identifier");
                }
                else { // not found in local scope
                    TSym newSym = new TSym(myType.getStrVal());
                    newSym.setVar();
                    t.addDecl(myId.getStrVal(), newSym);
                }

            }
            catch(EmptySymTableException e){
                System.out.println("Empty SymTable Exception occurs");
            }
            catch(DuplicateSymException d){
                System.out.println("Duplicate Sym Exception occurs");
            }
        }
        // if myType == struct type
        else{
            try{
                // if this struct has not been declared earlier, fatal
                // int node
                // struct node {};
                // struct node n; myType = node, myId = n

                TSym sym = t.lookupGlobal(myType.getStrVal());
                if(sym == null){
                    ErrMsg.fatal(myId.getLineNum(), myId.getCharNum(), 
                            "Invalid name of struct type");
                    // return;
                }
                // now, sym != null
                if(sym != null && sym.isStruct() == false){
                    ErrMsg.fatal(myId.getLineNum(), myId.getCharNum(), 
                            "Invalid name of struct type");
                    // return;
                }

                /**
                 * If a variable or a function with the same name has been 
                 * declared in the same scope before, then do not add a SymTable 
                 * entry for the struct.
                 */
                if(t.lookupLocal(myId.getStrVal()) != null){
                    ErrMsg.fatal(myId.getLineNum(), myId.getCharNum(), 
							"Multiply decalred identifier");
                }
                // else, add to symtable
                else{
                    TSym newSym = new TSym(myType.getStrVal());
                    newSym.setStructVar();
                    t.addDecl(myId.getStrVal(), newSym);
                }
            }
            catch(EmptySymTableException e){
                System.out.println("Empty SymTable Exception occurs");
            }
            catch(DuplicateSymException d){
                System.out.println("Duplicate Sym Exception occurs");
            }
        }
    }

    private TypeNode myType;
    private IdNode myId;
    private int mySize;  // use value NOT_STRUCT if this is not a struct type

    public static int NOT_STRUCT = -1;
}

// fnDecl          ::= type id formals fnBody
class FnDeclNode extends DeclNode {
    public FnDeclNode(TypeNode type,
                      IdNode id,
                      FormalsListNode formalList,
                      FnBodyNode body) {
        myType = type;
        myId = id;
        myFormalsList = formalList;
        myBody = body;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndentation(p, indent);
        myType.unparse(p, 0);
        p.print(" ");
        myId.unparse(p, 0);
        p.print("(");
        myFormalsList.unparse(p, 0);
        p.println(") {");
        myBody.unparse(p, indent+4);
        p.println("}\n");
    }

    // name analysis for FnDeclNode
    public void analysis(SymTable t){
        try{
            // if function name already declared, fatal
            if(t.lookupLocal(myId.getStrVal()) != null){
                ErrMsg.fatal(myId.getLineNum(), myId.getCharNum(), 
                        "Multiply declared identifier");
            }
            // function name not yet declared in current scope
            else{
                // new a FnSym struct which contains a field - formal list
                LinkedList<String> l = myFormalsList.getFormalTypeList();
                TSym newSym = new TSym(myType.getStrVal());
                newSym.setFunc(); // isFunc = true
                newSym.setTypeList(l);
                t.addDecl(myId.getStrVal(), newSym);
            }
        }
        catch(EmptySymTableException e){
            System.out.println(e);
        }
        catch(DuplicateSymException d){
            System.out.println(d);
        }

        // add scope, do name analysis on formals list and FnBody
        t.addScope();
        myFormalsList.analysis(t);
        myBody.analysis(t);
        try{
            // when fn finishes, remove scope
            t.removeScope();
        }
        catch(EmptySymTableException e){
            System.out.println(e);
        }
    }

    private TypeNode myType;
    private IdNode myId;
    private FormalsListNode myFormalsList;
    private FnBodyNode myBody;
}

class FormalDeclNode extends DeclNode {
    public FormalDeclNode(TypeNode type, IdNode id) {
        myType = type;
        myId = id;
    }

    public void unparse(PrintWriter p, int indent) {
        myType.unparse(p, 0);
        p.print(" ");
        myId.unparse(p, 0);
    }

    public String getTypeStrVal(){
        return this.myType.getStrVal();
    }

    public String getMyIdStrVal(){
        return myId.getStrVal();
    }
    public IdNode getMyId(){
        return myId;
    }

    public void analysis(SymTable t){
        // error report here
        if(myType.getStrVal().equals("void")){
            ErrMsg.fatal(myId.getLineNum(), myId.getCharNum(), 
                    "Non-Function declared void");
        }

        try{
            // if Id already exists, then fatal
            if(t.lookupLocal(myId.getStrVal()) != null){
                ErrMsg.fatal(myId.getLineNum(), myId.getCharNum(), 
                        "Multiply declared identifier");
            }
            else{ // not found in local scope, add to symtable
                TSym newSym = new TSym(myType.getStrVal());
                newSym.setVar();
                t.addDecl(myId.getStrVal(), newSym);
            }

        }
        catch(EmptySymTableException e){
            System.out.println(e);
        }

        catch(DuplicateSymException d){
            System.out.println(d);
        }
    }

    private TypeNode myType;
    private IdNode myId;
}

class StructDeclNode extends DeclNode {
    public StructDeclNode(IdNode id, DeclListNode declList) {
        myId = id;
        myDeclList = declList;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndentation(p, indent);
        p.print("struct ");
        myId.unparse(p, 0);
        p.println("{");
        myDeclList.unparse(p, indent+4);
        addIndentation(p, indent);
        p.println("};\n");

    }

    public void analysis(SymTable t){
        try{
            // check if struct Id already exists
            if(t.lookupGlobal(myId.getStrVal()) != null){
                ErrMsg.fatal(myId.getLineNum(), myId.getCharNum(), 
						"Multiply declared identifier");
            }
            else{ 
                t.addScope();
                myDeclList.analysis(t);

                SymTable newTable = new SymTable();
                newTable = t.getCurrentScope();

                // new a StructDeclSym, a symTable, add to current scope
                TSym newSym = new TSym("struct");
                
                newSym.setSymTable(newTable);
                newSym.setStruct();
                newSym.setStructName(myId.getStrVal());
                
                t.removeScope();
                t.addDecl(myId.getStrVal(), newSym);

                // System.out.println("added struct decl: struct "+myId.getStrVal());
                // System.out.println("now, symtable is:");
                // t.print();
                // System.out.println("finished");
            }
        }
        catch(EmptySymTableException e){
            System.out.println(e);
        }
        catch(DuplicateSymException d){
            System.out.println(d);
        }
    }

    private IdNode myId;
    private DeclListNode myDeclList;
}


// **********************************************************************
// TypeNode and its Subclasses
// **********************************************************************

abstract class TypeNode extends ASTnode {
    // every subclass must provide a getStrVal operation 
    abstract public String getStrVal();

}

class IntNode extends TypeNode {
    public IntNode() {
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("int");
    }

    // get the type
    public String getStrVal(){
        return "int";
    }

}

class BoolNode extends TypeNode {
    public BoolNode() {
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("bool");
    }

    // get the type
    public String getStrVal(){
        return "bool";
    }

}

class VoidNode extends TypeNode {
    public VoidNode() {
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("void");
    }

    // get the type
    public String getStrVal(){
        return "void";
    }

}

class StructNode extends TypeNode {
    public StructNode(IdNode id) {
        myId = id;
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("struct ");
        myId.unparse(p, 0);
    }

    public String getStrVal(){
        return myId.getStrVal();
    }

    public void analysis(SymTable t){
        myId.analysis(t);
    }

    private IdNode myId;
}

// **********************************************************************
// StmtNode and its subclasses
// **********************************************************************

abstract class StmtNode extends ASTnode {
    abstract public void analysis(SymTable t);
}

class AssignStmtNode extends StmtNode {
    public AssignStmtNode(AssignNode assign) {
        myAssign = assign;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndentation(p, indent);
        myAssign.unparse(p, -1); // no parentheses
        p.println(";");
    }

    public void analysis(SymTable t){
        myAssign.analysis(t);
    }

    private AssignNode myAssign;
}

class PostIncStmtNode extends StmtNode {
    public PostIncStmtNode(ExpNode exp) {
        myExp = exp;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndentation(p, indent);
        myExp.unparse(p, 0);
        p.println("++;");
    }

    public void analysis(SymTable t){
        myExp.analysis(t);
    }

    private ExpNode myExp;
}

class PostDecStmtNode extends StmtNode {
    public PostDecStmtNode(ExpNode exp) {
        myExp = exp;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndentation(p, indent);
        myExp.unparse(p, 0);
        p.println("--;");
    }

    public void analysis(SymTable t){
        myExp.analysis(t);
    }

    private ExpNode myExp;
}

class ReadStmtNode extends StmtNode {
    public ReadStmtNode(ExpNode e) {
        myExp = e;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndentation(p, indent);
        p.print("cin >> ");
        myExp.unparse(p, 0);
        p.println(";");
    }

    public void analysis(SymTable t){
        myExp.analysis(t);
    }
    // 1 child (actually can only be an IdNode or an ArrayExpNode)
    private ExpNode myExp;
}

class WriteStmtNode extends StmtNode {
    public WriteStmtNode(ExpNode exp) {
        myExp = exp;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndentation(p, indent);
        p.print("cout << ");
        myExp.unparse(p, 0);
        p.println(";");
    }

    public void analysis(SymTable t){
        myExp.analysis(t);
    }

    private ExpNode myExp;
}

class IfStmtNode extends StmtNode {
    public IfStmtNode(ExpNode exp, DeclListNode dlist, StmtListNode slist) {
        myDeclList = dlist;
        myExp = exp;
        myStmtList = slist;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndentation(p, indent);
        p.print("if (");
        myExp.unparse(p, 0);
        p.println(") {");
        myDeclList.unparse(p, indent+4);
        myStmtList.unparse(p, indent+4);
        addIndentation(p, indent);
        p.println("}");
    }

    public void analysis(SymTable t){
        myExp.analysis(t);
        t.addScope();
        myDeclList.analysis(t);
        myStmtList.analysis(t);
        try{
            t.removeScope();
        }
        catch(EmptySymTableException e){
            System.out.println(e);
        }
    }

    private ExpNode myExp;
    private DeclListNode myDeclList;
    private StmtListNode myStmtList;
}

class IfElseStmtNode extends StmtNode {
    public IfElseStmtNode(ExpNode exp, DeclListNode dlist1,
                          StmtListNode slist1, DeclListNode dlist2,
                          StmtListNode slist2) {
        myExp = exp;
        myThenDeclList = dlist1;
        myThenStmtList = slist1;
        myElseDeclList = dlist2;
        myElseStmtList = slist2;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndentation(p, indent);
        p.print("if (");
        myExp.unparse(p, 0);
        p.println(") {");
        myThenDeclList.unparse(p, indent+4);
        myThenStmtList.unparse(p, indent+4);
        addIndentation(p, indent);
        p.println("}");
        addIndentation(p, indent);
        p.println("else {");
        myElseDeclList.unparse(p, indent+4);
        myElseStmtList.unparse(p, indent+4);
        addIndentation(p, indent);
        p.println("}");
    }

    public void analysis(SymTable t){
        myExp.analysis(t);
        t.addScope();
        myThenDeclList.analysis(t);
        myThenStmtList.analysis(t);
        try{
            t.removeScope();
        }
        catch(EmptySymTableException e){
            System.out.println(e);
        }
        t.addScope();
        myElseDeclList.analysis(t);
        myElseStmtList.analysis(t);
        try{
            t.removeScope();
        }
        catch(EmptySymTableException e){
            System.out.println(e);
        }
    }

    private ExpNode myExp;
    private DeclListNode myThenDeclList;
    private StmtListNode myThenStmtList;
    private StmtListNode myElseStmtList;
    private DeclListNode myElseDeclList;
}

class WhileStmtNode extends StmtNode {
    public WhileStmtNode(ExpNode exp, DeclListNode dlist, StmtListNode slist) {
        myExp = exp;
        myDeclList = dlist;
        myStmtList = slist;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndentation(p, indent);
        p.print("while (");
        myExp.unparse(p, 0);
        p.println(") {");
        myDeclList.unparse(p, indent+4);
        myStmtList.unparse(p, indent+4);
        addIndentation(p, indent);
        p.println("}");
    }

    public void analysis(SymTable t){
        myExp.analysis(t);
        t.addScope();
        myDeclList.analysis(t);
        myStmtList.analysis(t);
        try{
            t.removeScope();
        }
        catch(EmptySymTableException e){
            System.out.println(e);
        }
    }

    private ExpNode myExp;
    private DeclListNode myDeclList;
    private StmtListNode myStmtList;
}

class RepeatStmtNode extends StmtNode {
    public RepeatStmtNode(ExpNode exp, DeclListNode dlist, StmtListNode slist) {
        myExp = exp;
        myDeclList = dlist;
        myStmtList = slist;
    }

    public void unparse(PrintWriter p, int indent) {
	addIndentation(p, indent);
        p.print("repeat (");
        myExp.unparse(p, 0);
        p.println(") {");
        myDeclList.unparse(p, indent+4);
        myStmtList.unparse(p, indent+4);
        addIndentation(p, indent);
        p.println("}");
    }

    public void analysis(SymTable t){
        myExp.analysis(t);
        t.addScope();
        myDeclList.analysis(t);
        myStmtList.analysis(t);
        try{
            t.removeScope();
        }
        catch(EmptySymTableException e){
            System.out.println(e);
        }
    }

    private ExpNode myExp;
    private DeclListNode myDeclList;
    private StmtListNode myStmtList;
}

class CallStmtNode extends StmtNode {
    public CallStmtNode(CallExpNode call) {
        myCall = call;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndentation(p, indent);
        myCall.unparse(p, indent);
        p.println(";");
    }

    public void analysis(SymTable t){
        myCall.analysis(t);
    }

    private CallExpNode myCall;
}

class ReturnStmtNode extends StmtNode {
    public ReturnStmtNode(ExpNode exp) {
        myExp = exp;
    }

    public void unparse(PrintWriter p, int indent) {
        addIndentation(p, indent);
        p.print("return");
        if (myExp != null) {
            p.print(" ");
            myExp.unparse(p, 0);
        }
        p.println(";");
    }

    public void analysis(SymTable t){
        if(myExp != null){
            myExp.analysis(t);
        }
    }

    private ExpNode myExp; // possibly null
}

// **********************************************************************
// ExpNode and its subclasses
// **********************************************************************

abstract class ExpNode extends ASTnode {
    abstract public void analysis(SymTable t);
    public TSym getSymFromTable(SymTable t){
        return null;
    }
}

class IntLitNode extends ExpNode {
    public IntLitNode(int lineNum, int charNum, int intVal) {
        myLineNum = lineNum;
        myCharNum = charNum;
        myIntVal = intVal;
    }

    public void unparse(PrintWriter p, int indent) {
        p.print(myIntVal);
    }

    public void analysis(SymTable t){}

    private int myLineNum;
    private int myCharNum;
    private int myIntVal;
}

class StringLitNode extends ExpNode {
    public StringLitNode(int lineNum, int charNum, String strVal) {
        myLineNum = lineNum;
        myCharNum = charNum;
        myStrVal = strVal;
    }

    public void unparse(PrintWriter p, int indent) {
        p.print(myStrVal);
    }

    public void analysis(SymTable t){}

    private int myLineNum;
    private int myCharNum;
    private String myStrVal;
}

class TrueNode extends ExpNode {
    public TrueNode(int lineNum, int charNum) {
        myLineNum = lineNum;
        myCharNum = charNum;
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("true");
    }

    public void analysis(SymTable t){}

    private int myLineNum;
    private int myCharNum;
}

class FalseNode extends ExpNode {
    public FalseNode(int lineNum, int charNum) {
        myLineNum = lineNum;
        myCharNum = charNum;
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("false");
    }
    
    public void analysis(SymTable t){}

    private int myLineNum;
    private int myCharNum;
}

class IdNode extends ExpNode {
    public IdNode(int lineNum, int charNum, String strVal) {
        myLineNum = lineNum;
        myCharNum = charNum;
        myStrVal = strVal;
    }

    public void unparse(PrintWriter p, int indent) {
        p.print(myStrVal);
        if(myTSym != null){
            p.print("(");
            p.print(myTSym.getType());
            p.print(")");
        }
    }

    // get methods for lineNum and charNum
    public int getLineNum(){
        return this.myLineNum;
    }

    public int getCharNum(){
        return this.myCharNum;
    }

    public String getStrVal(){
        return this.myStrVal;
    }

    public void setSym(TSym s){
        this.myTSym = s;
    }

    public TSym getSym(){
        return this.myTSym;
    }

    public TSym getSymFromTable(SymTable t){
        return this.myTSym;
    }

    public void analysis(SymTable t){
        // lookup local first
        try{
            TSym symLocal = t.lookupLocal(this.myStrVal);
            if(symLocal != null){
                // setSym(symLocal);
                this.myTSym = symLocal;
                return;
            }
        }
        catch(EmptySymTableException e){
            System.out.println(e);
        }

        // not found in local, then look up global
        try{
            TSym symGlobal = t.lookupGlobal(this.myStrVal);
            if(symGlobal != null){
                this.myTSym = symGlobal;
                return;
            }
        }
        catch(EmptySymTableException e){
            System.out.println(e);
        }
        
        ErrMsg.fatal(myLineNum, myCharNum, "Undeclared identifer");
    }

    private int myLineNum;
    private int myCharNum;
    private String myStrVal; // id value
    private TSym myTSym;
}

class DotAccessExpNode extends ExpNode {
    public DotAccessExpNode(ExpNode loc, IdNode id) {
        myLoc = loc;
        myId = id;
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myLoc.unparse(p, 0);
        p.print(").");
        myId.unparse(p, 0);
    }

    public TSym getSymFromTable(SymTable t){
        return myId.getSym();
    }

    public void analysis(SymTable t){
        // myLoc can be either IdNode or DotAccessExpNode
        // do name anlaysis on myLoc first
        myLoc.analysis(t);
        // if(myLoc instanceof IdNode){
        //     IdNode newNode = (IdNode)myLoc;
        //     try{
        //         TSym lhsSym = t.lookupGlobal(newNode.getStrVal());
        //         if(lhsSym == null){
        //             ErrMsg.fatal(newNode.getLineNum(), newNode.getCharNum(), 
        //                     "Undeclared identifier");
        //             return;
        //         } 
                
        //         if(lhsSym.isStructVar() == false){
        //             ErrMsg.fatal(newNode.getLineNum(), newNode.getCharNum(), 
        //                     "Dot-access of non-struct type");
        //             return;
        //         }

        //         // now, lhs is struct var, get its struct decl sym
        //         TSym lhsParentSym = t.lookupGlobal(lhsSym.getStructName());


        //     }

        // }

        TSym symGot = myLoc.getSymFromTable(t);
        if(symGot == null){
            return;
        }

        if(symGot.isStructVar() == false){
            if(myLoc instanceof IdNode){
                IdNode newNode = (IdNode)myLoc;
                ErrMsg.fatal(newNode.getLineNum(), newNode.getCharNum(), 
                        "Dot-access of non-struct type");
            }
            else if(myLoc instanceof DotAccessExpNode){
                DotAccessExpNode newNode = (DotAccessExpNode)myLoc;
                ErrMsg.fatal(newNode.getLineNum(), newNode.getCharNum(), 
                        "Dot-access of non-struct type");
            }
            return;
        }

        // get the StructVar Sym
        TSym lhsSym = myLoc.getSymFromTable(t);
        try{
            // get parent - StructDecl Sym
            TSym lhsParentSym = t.lookupGlobal(lhsSym.getType());
            // get table of parent
            SymTable parentFields = lhsParentSym.getSymTable();
            // lookup in parent table
            TSym foundSym = parentFields.lookupGlobal(myId.getStrVal());
            if(foundSym == null){
                ErrMsg.fatal(myId.getLineNum(), myId.getCharNum(),
                        "Invalid struct field name");
                return;
            }
            else{
                myId.setSym(foundSym);
            }
        }
        catch(EmptySymTableException e){
            System.out.println(e);
        }
        
    }

    public int getLineNum() {
        return myId.getLineNum();
    }

    public int getCharNum() {
        return myId.getCharNum();
    }

    private ExpNode myLoc;
    private IdNode myId;
}

class AssignNode extends ExpNode {
    public AssignNode(ExpNode lhs, ExpNode exp) {
        myLhs = lhs;
        myExp = exp;
    }

    public void unparse(PrintWriter p, int indent) {
        if (indent != -1)  p.print("(");
        myLhs.unparse(p, 0);
        p.print(" = ");
        myExp.unparse(p, 0);
        if (indent != -1)  p.print(")");
    }

    public void analysis(SymTable t){
        myLhs.analysis(t);
        myExp.analysis(t);
    }

    private ExpNode myLhs;
    private ExpNode myExp;
}

class CallExpNode extends ExpNode {
    public CallExpNode(IdNode name, ExpListNode elist) {
        myId = name;
        myExpList = elist;
    }

    public CallExpNode(IdNode name) {
        myId = name;
        myExpList = new ExpListNode(new LinkedList<ExpNode>());
    }

    public void unparse(PrintWriter p, int indent) {
        LinkedList<String> l = myId.getSym().getTypeList();
        // myId.unparse(p, 0);
        p.print(myId.getStrVal());
        p.print("(");
        for(int i = 0; i < l.size(); i++){
            if(i == 0){
                p.print(l.get(i));
            }
            else{
                p.print("," + l.get(i));
            }
        }
        p.print("->");
        p.print(myId.getSym().getType());
        p.print(")(");
        
        if (myExpList != null) {
            myExpList.unparse(p, 0);
        }
        p.print(")");
    }

    public void analysis(SymTable t){
        myId.analysis(t);
        myExpList.analysis(t);
    }

    private IdNode myId;
    private ExpListNode myExpList;  // possibly null
}

abstract class UnaryExpNode extends ExpNode {
    public UnaryExpNode(ExpNode exp) {
        myExp = exp;
    }

    public void analysis(SymTable t){
        myExp.analysis(t);
    }

    protected ExpNode myExp;
}

abstract class BinaryExpNode extends ExpNode {
    public BinaryExpNode(ExpNode exp1, ExpNode exp2) {
        myExp1 = exp1;
        myExp2 = exp2;
    }

    public void analysis(SymTable t){
        myExp1.analysis(t);
        myExp2.analysis(t);
    }

    protected ExpNode myExp1;
    protected ExpNode myExp2;
}

// **********************************************************************
// Subclasses of UnaryExpNode
// **********************************************************************

class UnaryMinusNode extends UnaryExpNode {
    public UnaryMinusNode(ExpNode exp) {
        super(exp);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(-");
        myExp.unparse(p, 0);
        p.print(")");
    }
}

class NotNode extends UnaryExpNode {
    public NotNode(ExpNode exp) {
        super(exp);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(!");
        myExp.unparse(p, 0);
        p.print(")");
    }
}

// **********************************************************************
// Subclasses of BinaryExpNode
// **********************************************************************

class PlusNode extends BinaryExpNode {
    public PlusNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" + ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}

class MinusNode extends BinaryExpNode {
    public MinusNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" - ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}

class TimesNode extends BinaryExpNode {
    public TimesNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" * ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}

class DivideNode extends BinaryExpNode {
    public DivideNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" / ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}

class AndNode extends BinaryExpNode {
    public AndNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" && ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}

class OrNode extends BinaryExpNode {
    public OrNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" || ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}

class EqualsNode extends BinaryExpNode {
    public EqualsNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" == ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}

class NotEqualsNode extends BinaryExpNode {
    public NotEqualsNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" != ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}

class LessNode extends BinaryExpNode {
    public LessNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" < ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}

class GreaterNode extends BinaryExpNode {
    public GreaterNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" > ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}

class LessEqNode extends BinaryExpNode {
    public LessEqNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" <= ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}

class GreaterEqNode extends BinaryExpNode {
    public GreaterEqNode(ExpNode exp1, ExpNode exp2) {
        super(exp1, exp2);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("(");
        myExp1.unparse(p, 0);
        p.print(" >= ");
        myExp2.unparse(p, 0);
        p.print(")");
    }
}
