import java.util.*;


public class TSym {
    private String type;
    private LinkedList<String> formalTypeList; // FnSym
    private String structName; // struct decl
    private SymTable fields; // struct decl

    private boolean isVar; //VarSym
    private boolean isFunc; // FnSym
    private boolean isStruct; // struct decl
    
    public TSym(String type) {
        this.type = type;
        this.formalTypeList = null;
        this.structName = null;
        this.fields = null;
        this.isVar = false;
        this.isFunc = false;
        this.isStruct = false;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return type;
    }

    public LinkedList<String> getTypeList(){
        return formalTypeList;
    }

    public void setTypeList(LinkedList<String> l){
        this.formalTypeList = l;
    }

    public String getStructName(){
        return structName;
    }

    public void setStructName(String s){
        this.structName = s;
    }

    public SymTable getSymTable(){
        return this.fields;
    }

    public void setSymTable(SymTable t){
        this.fields = t;
    }

    public void setVar(){
        this.isVar = true;
    }

    public void setFunc(){
        this.isFunc = true;
    }

    public void setStruct(){
        this.isStruct = true;
    }

    public boolean isStruct(){
        if(this.isStruct){
            return true;
        }
        return false;
    }
    
}

