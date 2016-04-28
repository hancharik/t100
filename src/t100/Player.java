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
public class Player {

    int identifier;
    ArrayList<Board> losingCombinations;

    public Player(int id) {

        identifier = id;

        losingCombinations = new ArrayList();

    }

    public String showPlayer() {

        return "" + identifier;

    }

    public boolean isNewLosingCombination(Board current) {

        if (losingCombinations.size() > 0) {

            for (int z = 0; z < losingCombinations.size(); z++) {

                if (compareGrids(current, losingCombinations.get(z)) == 0) {
                    return true;
                }

            }

        }

        return false;

    }  // end check for losing combination

    public int compareGrids(Board current, Board fromMemory) {

        int temporaryCounter = 0;
        // as long as we keep a numbering system of 0,0 in the upper left corner, this will work
        for (int v = 0; v < current.size; v++) {
            for (int w = 0; w < current.size; w++) {
                if (current.grid[v][w] == fromMemory.grid[v][w]) {
                    temporaryCounter++;
                }
            }
        }

        if(temporaryCounter==9){
            // addGridToMemory(current);
             return 0;
            
        }else{
         return 1;
    }
       

    }

    
   public void addGridToMemory(Board board){
       
       
       losingCombinations.add(board);
       
       
       
   } 
    
    
    
    
    
} // end class
