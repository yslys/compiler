import java.util.*;

/**
 * TSym class - for variable definitions
 */
public class TSym {
    private String type;
    
    public TSym(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return type;
    }
}

/**
 * a subclass of TSym for function declarations
 */
class FnSym extends TSym{
    private String retType;
    private LinkedList<String> formalTypeList;

    public FnSym(String retType, LinkedList<String> ftl) {
        super("function");
        this.retType = retType;
        this.formalTypeList = ftl;
    }

}

/**
 * a subclass of TSym for struct declarations
 */
class StructDeclSym extends TSym{
    SymTable fields;

    public StructDeclSym(String type, SymTable fields){
        super(type);
        this.fields = fields;
    }
}