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
    private List<TSym> formalsList;

    public FnSym(String type) {
        super(type);
    }

    public void setFormalsList(List<TSym> formalsList){
        this.formalsList = formalsList;
    }

    
}