/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t100;

/**
 *
 * @author mark
 */
public class Move implements Comparable<Move>{
    
   int x;
   int y;
    
    
    
    
    public  Move(int x, int y){
    
        this.x = x;
        this.y = y;
        
    } 

  

    @Override
    public int compareTo(Move t) {
        if (this.x==t.x && this.y==t.y ){
             return 0;
        }
       return 1;
    }
   
   
   
   
}
