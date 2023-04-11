/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysnake;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class Board {
    
    public Map<Integer, Integer> SNAKES;
    public Map<Integer, Integer> LADDERS;
    
    public Board()
    {
        InitSnakesAndladders();
    }
    
    public void InitSnakesAndladders()
    {
        SNAKES = new HashMap<>();
	LADDERS = new HashMap<>();
		
	SNAKES.put(40, 17);
        SNAKES.put(44, 5);
	SNAKES.put(96, 47);
	SNAKES.put(61, 38);
	SNAKES.put(73, 51);
	LADDERS.put(89, 93);
	LADDERS.put(41, 84);
	LADDERS.put(79, 98);
	LADDERS.put(13, 56);
	LADDERS.put(8, 12);
    }
}
