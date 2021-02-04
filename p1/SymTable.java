////////////////////////////////////////////////////////////////////////////////
// 
// Main Class File:  P1.java
// File:             SymTable.java
// Semester:         CS 536 Spring 2021
//
// Author:           Yusen Liu
// Email:            liu797@wisc.edu
// CS Login:         yusen
// Lecturer's Name:  Loris D'Antoni
//
////////////////////////////////////////////////////////////////////////////////

/**
 * The SymTable class will be used by the compiler to represent a symbol table: 
 * a data structure that stores the identifiers declared in the program being 
 * compiled (e.g., function and variable names) and information about each 
 * identifier (e.g., its type, where it will be stored at runtime). The symbol 
 * table will be implemented as a List of HashMaps. Eventually, each HashMap 
 * will store the identifiers declared in one scope in the program being 
 * compiled.
 * 
 * The HashMap keys will be Strings (the declared identifier names) and the 
 * associated information will be Syms.
 * 
 * @author Yusen Liu
 * 
 * Bugs: none known
 */

import java.util.*;


public class SymTable{

    /** The private field of a list of HashMaps */
    private List<HashMap<String, Sym>> hashMapList;
    
    /**
     * Constructor: no param required.
     * It initializes the SymTable's hashMapList field to contain a single, 
     * empty HashMap.
     */
    public SymTable(){
        this.hashMapList = new LinkedList<HashMap<String, Sym>>();
        this.hashMapList.add(0, new HashMap<String, Sym>());
    }
    
    /**
     * This method adds a name-Sym pair to the first HashMap in hashMapList.
     * If either name or sym(or both) is null, throw a IllegalArgumentException. 
     * 
     * @param name A string that stores the name of the Sym.
     * @param sym A Sym that stores the symbol information.
     * @throws DuplicateSymException if the first HashMap in the list already 
     * contains the given name as a key.
     * @throws EmptySymTableException if this SymTable's list is empty.
     */
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

    /**
     * This method adds a new, empty HashMap to the front of the hashMapList.
     */
    public void addScope(){
        this.hashMapList.add(0, new HashMap<String, Sym>());
    }

    /**
     * Returns the associated Sym if the first HashMap in the list contains 
     * name as a key.
     * 
     * @param name A string that stores the name of the Sym.
     * @return the associated Sym if the first HashMap in the list contains 
     * name as a key.
     * null is return, if the first HashMap in hashMapList does not contain name
     * as a key.
     * @throws EmptySymTableException if this SymTable's hashMapList is empty.
     */
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
    
    /**
     * Returns the first associated Sym if any HashMap in the list contains 
     * name as a key.
     *  
     * @param name A string that stores the name of the Sym.
     * @return the first associated Sym if any HashMap in the list contains 
     * name as a key.
     * null is return, if all HashMaps in hashMapList do not contain name as a
     * key.
     * @throws EmptySymTableException if this SymTable's hashMapList is empty.
     */
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

    /**
     * Removes the HashMap from the front of the list.
     * @throws EmptySymTableException (before attempting to remove) if this 
     * SymTable's hashMapList is empty.
     */
    public void removeScope() throws EmptySymTableException{
        if(this.hashMapList.isEmpty()){
            throw new EmptySymTableException();
        }
        else{
            this.hashMapList.remove(0);
        }
    }

    /**
     * Prints out the hashMapList of the SymTable, used for debugging.
     */
    public void print(){
        System.out.print("\nSym Table\n");

        for(HashMap<String, Sym> singleMap : this.hashMapList){
            System.out.println(singleMap.toString());
        }
        System.out.println();
    }

}

