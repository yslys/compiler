////////////////////////////////////////////////////////////////////////////////
// 
// Title:            P1 part1 - a symbol table
// Files:            P1.java, Sym.java, SymTable.java, 
//                   DuplicateSymException.java, EmptySymTableException.java
// Semester:         CS 536 Spring 2021
//
// Author:           Yusen Liu
// Email:            liu797@wisc.edu
// CS Login:         yusen
// Lecturer's Name:  Loris D'Antoni
//
////////////////////////////////////////////////////////////////////////////////

import java.util.*;


/**
 * TestP1
 * 
 * This is a class with a purpose to test all of the Sym and SymTable operations 
 * and all situations under which exceptions are thrown. 
 * 
 * Sym provides the following operations:
 *      Sym(String type)	
 *        -- The constructor that initializes the Sym to have the given type.
 *      String getType()	
 *        -- Return this Sym's type.
 *      String toString()	
 *        -- Return this Sym's type. 
 *          (This method will be changed later in a future project.)
 * 
 * SymTable provides the following operations:
 *      no-arg constructor  
 *        -- Initialize the SymTable's List field to contain a single, empty 
 *           HashMap.
 *      void addDecl(String name, Sym sym)
 *        -- If SymTable's list is empty, throw an EmptySymTableException. If 
 *           either name or sym (or both) is null, throw a 
 *           IllegalArgumentException. If the first HashMap in the list already 
 *           contains the given name as a key, throw a DuplicateSymException. 
 *           Otherwise, add name and sym to the first HashMap in the list.
 *      void addScope()
 *        -- Add a new, empty HashMap to the front of the list.
 *      Sym lookupLocal(String name)
 *        -- If this SymTable's list is empty, throw an EmptySymTableException. 
 *           Otherwise, if the first HashMap in the list contains name as a key,
 *           return the associated Sym; otherwise, return null.
 *      Sym lookupGlobal(String name)
 *        -- If this SymTable's list is empty, throw an EmptySymTableException. 
 *           If any HashMap in the list contains name as a key, return the first
 *           associated Sym; otherwise, return null.
 *      void removeScope()
 *        -- If this SymTable's list is empty, throw an EmptySymTableException; 
 *           otherwise, remove the HashMap from the front of the list. 
 *      void print()
 *        -- This method is for debugging. First, print “\nSym Table\n”. Then, 
 *           for each HashMap M in the list, print M.toString() followed by a 
 *           newline. Finally, print one more newline.
 * 
 * This code tests every Sym and SymTable operation, including both correct and
 * bad calls to the operations that throws exceptions.
 * 
 * It calls print() twice to test the constructor of SymTable, and the test of
 * print() could be found when two HashMaps are created with some keys and 
 * values added. Besides that, there will be no output produced except a test
 * fails.
 */

public class P1{
    public static void main(String[] args) {
        int lenSym = 10;

        // test of methods in Sym

        // initialize an arraylist of Sym of length 10, with the first five of 
        // type "int", and the last five of type "char"
        ArrayList<Sym> symList = new ArrayList<Sym>();

        for(int i = 0; i < lenSym/2; i++){
            symList.add(new Sym("int"));
        }
        for(int i = 0; i < lenSym/2; i++){
            symList.add(new Sym("char"));
        }

        // test of getType()
        // the 0th element should have type "int"
        if(!symList.get(0).getType().equals("int")){
            System.out.println("getType() failed");
        }

        // test of constructor
        // the 0th and 4th element should have the same type
        if(symList.get(0).getType().equals(
                symList.get(lenSym/2-1).getType()) == false){
            System.out.println("constructor in Sym failed");
        }

        // test of toString()
        // the 9th and 4th element should have different types
        if(symList.get(lenSym - 1).toString().equals(
                symList.get(lenSym/2 - 1).toString()) == true){
            System.out.println("toString() in Sym failed");
        }

        // combination test of getType() and toString()
        // note that this test ONLY works in P1a
        if(symList.get(lenSym/2 + 1).getType().equals(
                symList.get(lenSym/2 + 1).toString()) == false){
            System.out.println(
                "getType() and toString in Sym are not the same in P1a");
        }

        // test of methods in SymTable

        // test of constructor
        // symTable should consist of one empty hashmap
        SymTable symTable = new SymTable();

        // test of print() and check if constructor has initialized an empty
        // HashMap
        symTable.print();

        
        // use lookupLocal() to test addDecl()
        try{
            // add the given name and sym to the first HashMap in the list
            symTable.addDecl("name11", symList.get(0)); // int
            symTable.addDecl("name12", symList.get(7)); // char
            symTable.addDecl("nameSame", symList.get(6)); // char

            // use lookupLocal() to test if addDecl() worked well
            // if any of the names cannot be found, then addDecl() failed
            if(symTable.lookupLocal("name11") == null 
                    && symTable.lookupLocal("name12") == null 
                    && symTable.lookupLocal("nameSame") == null){
                System.out.println("addDecl() failed to add names");
            }
        }
        catch(DuplicateSymException ex){
            System.out.println("DuplicateSymException occurs");
        }
        catch(EmptySymTableException ex){
            System.out.println("EmptySymTableException occurs");
        }

        // add a new, empty HashMap to the front of the symTable
        symTable.addScope();

        // use lookupLocal() and lookupGLobal() to test addScope()
        // lookupLocal("name11") should return null
        // lookupGlobal("name11") should NOT return null
        try{
            if(symTable.lookupLocal("name11") != null
                    || symTable.lookupGlobal("name11") == null){
                System.out.println("addScope() failed");
            }
        }
        catch(EmptySymTableException ex){
            System.out.println("EmptySymTableException occurs");
        }
        
        try{
            // test of addDecl()
            // add the given name and sym to the first HashMap in the list
            symTable.addDecl("name21", symList.get(7)); // char
            symTable.addDecl("name22", symList.get(0)); // int
            symTable.addDecl("nameSame", symList.get(0)); // int

            // use lookupLocal() to test if addDecl() worked well
            if(symTable.lookupLocal("name21") == null 
                    && symTable.lookupLocal("name22") == null 
                    && symTable.lookupLocal("nameSame") == null){
                System.out.println("addDecl() failed to add names");
            }
        }
        catch(DuplicateSymException ex){
            System.out.println("DuplicateSymException occurs");
        }
        catch(EmptySymTableException ex){
            System.out.println("EmptySymTableException occurs");
        }

        // test functionality of print()
        symTable.print();

        try{
            // test if lookupLocal() checked the first HashMap
            // lookupLocal() should find ("nameSame"="int") in this case
            // if failed, will output an error message.
            if(!symTable.lookupLocal("nameSame").getType().equals("int")){
                System.out.println(
                    "lookupLocal() failed to find the first HashMap in List");
            }

            // test if lookupLocal() can find the correct key
            // lookupLocal() should find ("name21"="char") in this case
            // if failed, will output an error message.
            if(!symTable.lookupLocal("name21").getType().equals("char")){
                System.out.println(
                    "lookupLocal() failed to find the first HashMap in List");
            }

            // test if lookupLocal() would return null when key is invalid
            // lookupLocal() cannot find it when key is "nameNotExist"
            // if found, will output an error message.
            if(symTable.lookupLocal("nameNotExist") != null){
                System.out.println(
                    "lookupLocal() should return null in this case");
            }

            // test if lookupLocal() would return null when the key is not in 
            // the first HashMap
            // if found, will output an error message.
            if(symTable.lookupLocal("name11") != null){
                System.out.println(
                    "lookupLocal() should return null in this case");
            }
        }
        catch(EmptySymTableException ex){
            System.out.println("EmptySymTableException occurs");
        }

        // add a duplicate key to test addDecl()
        // this should be an DuplicateSymException, will be caught, no output
        try{
            symTable.addDecl("name21", symList.get(7));
            System.out.println("this should not be print");
        }
        catch(DuplicateSymException ex){
            // Exception caught, no output here
        }
        catch(EmptySymTableException ex){
            System.out.println("EmptySymTableException occurs");
        }
        
        // test addDecl() with name is null
        // this should be an IllegalArgumentException, will be caught, no output
        try{
            symTable.addDecl(null, symList.get(0));
        }
        catch(IllegalArgumentException ex){
            // Exception caught, no output here
        }
        catch(DuplicateSymException ex){
            System.out.println("DuplicateSymException occurs");
        }
        catch(EmptySymTableException ex){
            System.out.println("EmptySymTableException occurs");
        }

        // similarly, test addDecl() with Sym is null
        // this should be an IllegalArgumentException, will be caught, no output
        try{
            symTable.addDecl("namex", null);
        }
        catch(IllegalArgumentException ex){
            // Exception caught, no output here
        }
        catch(DuplicateSymException ex){
            System.out.println("DuplicateSymException occurs");
        }
        catch(EmptySymTableException ex){
            System.out.println("EmptySymTableException occurs");
        }

        
        try{
            // test if lookupGlobal() could find the first key "nameSame"
            // in this case, the corresponding value should be "int"
            if(!symTable.lookupGlobal("nameSame").getType().equals("int")){
                System.out.println(
                    "lookupGlobal() failed to find the key in first HashMap");
            }

            // test if lookupGlobal() could find "name22" in the first HashMap
            // in this case, the corresponding value should be "int"
            if(!symTable.lookupGlobal("name22").getType().equals("int")){
                System.out.println(
                    "lookupGlobal() failed to find the key name22 in symTable");
            }

            // test if lookupGlobal() could find "name12" in the second HashMap
            // in this case, the corresponding value should be "char"
            if(!symTable.lookupGlobal("name12").getType().equals("char")){
                System.out.println(
                    "LookupGlobal() failed to find the key name12 in symTable");
            }

            // test if lookupGlobal() could return null when key is not in any
            // HashMap in the symTable
            if(symTable.lookupGlobal("nameNotExist") != null){
                System.out.println(
                    "LookupGlobal() failed to return null when key is invalid");
            }
        }
        catch(EmptySymTableException ex){
            System.out.println("EmptySymTableException occurs");
        }

        // test if removeScope() could successfully remove the first HashMap
        // If it does, then we cannot find the names in the first HashMap. 
        try{
            symTable.removeScope();
            if(symTable.lookupGlobal("name21") != null 
                    || symTable.lookupGlobal("name22") != null 
                    || symTable.lookupGlobal("name23") != null 
                    || symTable.lookupGlobal("nameSame")
                               .getType().equals("int")){
                System.out.println("removeScope() failed");
            }
        }
        catch(EmptySymTableException ex){
            System.out.println("EmptySymTableException occurs");
        }

        // test of EmptySymTableException of addDecl(), lookupLocal(),
        //                                    lookupGlobal, removeScope()
        // firstly, need to call removeScope() to make the symTable empty
        try{
            symTable.removeScope();
        }
        catch(EmptySymTableException ex){
            System.out.println("EmptySymTableException occurs");
        }

        // test of EmptySymTableException of addDecl()
        try{
            symTable.addDecl("namex", symList.get(0));
        }
        catch(EmptySymTableException ex){
            // Exception will be caught, no output
        }
        catch(DuplicateSymException ex){
            System.out.println("DuplicateSymException occurs");
        }
        
        // test of EmptySymTableException of lookupLocal()
        try{
            symTable.lookupLocal("name11");
        }
        catch(EmptySymTableException ex){
            // Exception will be caught, no output
        }

        // test of EmptySymTableException of lookupGlobal()
        try{
            symTable.lookupGlobal("name11");
        }
        catch(EmptySymTableException ex){
            // Exception will be caught, no output
        }
        
        // test of EmptySymTableException of removeScope()
        try{
            symTable.removeScope();
        }
        catch(EmptySymTableException ex){
            // Exception will be caught, no output
        }
    }
}