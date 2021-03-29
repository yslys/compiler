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
    public LinkedList<String> getTypeList(){
        return null;
    }

    public SymTable getSymTable(){
        return null;
    }
    public String getStructName(){
        return null;
    }
}

/**
 * a subclass of TSym for function declarations
 */
class FnSym extends TSym{
    private LinkedList<String> formalTypeList;

    public FnSym(String type, LinkedList<String> ftl) {
        super(type);
        this.formalTypeList = ftl;

    }

    @Override
    public LinkedList<String> getTypeList(){
        return formalTypeList;
    }

}

/**
 * a subclass of TSym for struct declarations
 */
class StructDeclSym extends TSym{
    String name;
    SymTable fields;

    public StructDeclSym(String type, String name, SymTable fields){
        super(type);
        this.name = name;
        this.fields = fields;
    }

    @Override
    public String getStructName(){
        return name;
    }
    @Override
    public SymTable getSymTable(){
        return fields;
    }
}