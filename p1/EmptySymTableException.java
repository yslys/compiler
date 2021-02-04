////////////////////////////////////////////////////////////////////////////////
// 
// Main Class File:  P1.java
// File:             EmptySymTableException.java
// Semester:         CS 536 Spring 2021
//
// Author:           Yusen Liu
// Email:            liu797@wisc.edu
// CS Login:         yusen
// Lecturer's Name:  Loris D'Antoni
//
////////////////////////////////////////////////////////////////////////////////

/**
 * This class simply defines the checked exception that can be thrown by the 
 * SymTable class. The class has an empty body. This class deals with the
 * possible exception when the hashMapList of the SymTable is empty.
 * 
 * @author Yusen Liu
 * 
 * Bugs: none known
 */

public class EmptySymTableException extends Exception{
    EmptySymTableException(){
        
    }
}