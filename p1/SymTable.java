import java.util.*;


public class SymTable{
    private List<HashMap<String, Sym>> hashMapList;
    
    SymTable(){
        this.hashMapList = new LinkedList<HashMap<String, Sym>>();
        this.hashMapList.add(0, new HashMap<String, Sym>());
    }
    
    public void addDecl(String name, Sym sym) throws DuplicateSymException, 
    EmptySymTableException{
        
        if(this.hashMapList.isEmpty()){
            throw new EmptySymTableException();
        }
        else if(name == null || sym == null){
            throw new IllegalArgumentException();
        }
        else if(this.hashMapList.get(0).containsKey(name)){
            throw new DuplicateSymException();
        }
        else{
            this.hashMapList.get(0).put(name, sym);
        }

    }

    public void addScope(){
        this.hashMapList.add(0, new HashMap<String, Sym>());
    }

    public Sym lookupLocal(String name) throws EmptySymTableException{
        if(this.hashMapList.isEmpty()){
            throw new EmptySymTableException();
        }
        else if(this.hashMapList.get(0).containsKey(name)){
            return this.hashMapList.get(0).get(name);
        }
        else{
            return null;
        }
    }
    
    public Sym lookupGlobal(String name) throws EmptySymTableException{
        if(this.hashMapList.isEmpty()){
            throw new EmptySymTableException();
        }

        for(HashMap<String, Sym> singleMap : this.hashMapList){
            if(singleMap.containsKey(name)){
                return singleMap.get(name);
            }
        }

        return null;
    }

    public void removeScope() throws EmptySymTableException{
        if(this.hashMapList.isEmpty()){
            throw new EmptySymTableException();
        }
        else{
            this.hashMapList.remove(0);
        }
    }

    public void print(){
        System.out.print("\nSym Table\n");

        for(HashMap<String, Sym> singleMap : this.hashMapList){
            System.out.println(singleMap.toString());
        }
        System.out.println();
    }

}

