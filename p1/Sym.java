////////////////////////////////////////////////////////////////////////////////
// 
// Main Class File:  P1.java
// File:             Sym.java
// Semester:         CS 536 Spring 2021
//
// Author:           Yusen Liu
// Email:            liu797@wisc.edu
// CS Login:         yusen
// Lecturer's Name:  Loris D'Antoni
//
////////////////////////////////////////////////////////////////////////////////

/**
 * This class serves for a symbol in the SymTable
 * 
 * @author Yusen Liu
 * 
 * Bugs: none known
 */

public class Sym{

    /** The type of the symbol */
    String type;
  
    /**
     * Constructor: Requires a specified type
     * It initializes the Sym to have the given type.
     * @param type The type of the symbol.
     */
    Sym(String type){
      this.type = type;
    }
  
    /**
     * Returns the type of a Sym.
     * @return this Sym's type
     */
    public String getType(){
      return this.type;
    }
  
    /**
     * Returns the type of a Sym.
     * @return this Sym's type
     */
    public String toString(){
      return this.type;
    }
  }