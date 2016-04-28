/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t100;

import java.util.ArrayList;

/**
 *
 * @author mark
 */
public class Board {
   
   int[][]grid;
   ArrayList<Move> availableMoves;
   int size; 
    public  Board(int size){
    
        this.size = size;
        grid = new int[this.size][this.size];
        availableMoves = new ArrayList();
    }
   
    
    public void addToAvailableMoves(Move move){
        
        availableMoves.add(move);
        
    }
    
    
        public void removeFromAvailableMoves(Move move){
        
            
          for(int u = 0;  u < availableMoves.size(); u++){
              if(move.compareTo(availableMoves.get(u))==0){
              availableMoves.remove(availableMoves.get(u)); 
              System.out.println(" Board.java 40 move removed");
              }else{
                 System.out.println("Board.java 42 move NOT removed"); 
              }
          }  
            
        
        
    }
  
   public void printMoves(){
       
       
       for(int i = 0; i < availableMoves.size(); i++){
         // System.out.println("availableMoves #" + (i+1) + ": (" + availableMoves.get(i).x + "," + availableMoves.get(i).y + ")"); 
       }
       
   }     
        
        
       
        
        
}// end class
