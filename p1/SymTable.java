import java.util.*;

public class SymTable{
    private List<HashMap<String, Sym>> hashMapList;
    
    SymTable(){
        this.hashMapList = new LinkedList<HashMap<String, Sym>>();
    }
    
    public void addDecl(String name, Sym sym) throws DuplicateSymException, 
    EmptySymTableException{
        
        if(this.hashMapList.isEmpty()){
            throw new EmptySymTableException();
        }
        else if(name.equals(null) || sym == null){
            throw new IllegalArgumentException();
        }
        else if(this.hashMapList.get(0).containsKey(name)){
            throw new DuplicateSymException();
        }
        else{
            this.hashMapList.addF
        }

    }


}

